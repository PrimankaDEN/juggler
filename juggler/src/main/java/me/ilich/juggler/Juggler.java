package me.ilich.juggler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.io.Serializable;

import me.ilich.juggler.change.Change;
import me.ilich.juggler.change.Item;
import me.ilich.juggler.change.StateChanger;
import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.gui.JugglerNavigationFragment;
import me.ilich.juggler.states.State;

public class Juggler implements Navigable, Serializable {

    private static final int NOT_SET = -1;

    public static final String TAG = "Juggler";

    public static final String DATA_INTENT_FLAG = "intent flag extra";
    public static final String DATA_ANIMATION_FINISH_ENTER = "animation finish enter";
    public static final String DATA_ANIMATION_FINISH_EXIT = "animation finish exit";

    private StateChanger stateChanger = new StateChanger();
    private StateHolder currentStateHolder = new StateHolder();
    @LayoutRes
    private int layoutId = NOT_SET;
    private transient JugglerActivity activity = null;
    private transient FragmentManager.OnBackStackChangedListener onBackStackChangedListener = null;

    @Override
    public boolean backState() {
        if (activity == null) {
            throw new NullPointerException("activity == null");
        }
        final boolean b;
        State state = currentStateHolder.get();
        if (state == null) {
            b = false;
        } else {
            Transition transition = state.getBackTransition();
            if (transition == null) {
                b = false;
            } else {
                State st = transition.execute(activity, stateChanger);
                currentStateHolder.set(st);
                b = currentStateHolder.get() != null;
            }
        }
        return b;
    }

    @Override
    public boolean upState() {
        if (activity == null) {
            throw new NullPointerException("activity == null");
        }
        final boolean b;
        State state = currentStateHolder.get();
        if (state == null) {
            b = false;
        } else {
            Transition transition = state.getUpTransition();
            if (transition == null) {
                b = false;
            } else {
                state = transition.execute(activity, stateChanger);
                currentStateHolder.set(state);
                b = state != null;
            }
        }
        return b;
    }

    @Override
    public void restore() {
        currentStateHolder.set(stateChanger.restore(activity));
    }

    @Override
    public void state(@Nullable Change... changes) {
        if (changes == null || changes.length == 0) {
            return;
        }
        doState(changes);
    }

    public void activateCurrentState() {
        State state = currentStateHolder.get();
        if (state != null) {
            state.onActivate(activity);
        }
    }

    private void doState(@NonNull Change... changes) {
        Bundle bundle = new Bundle();
        State state = currentStateHolder.get();
        if (state != null) {
            state.onDeactivate(activity);
        }
        for (Change change : changes) {
            change.change(activity, stateChanger.getItems(), currentStateHolder, bundle);
        }
    }

    public void setActivity(JugglerActivity activity) {
        if (activity != null) {
            activity.getSupportFragmentManager().removeOnBackStackChangedListener(onBackStackChangedListener);
        }
        this.activity = activity;
        onBackStackChangedListener = () -> {
            State state = currentStateHolder.get();
            if (state != null) {
                state.onActivate(Juggler.this.activity);
            }
        };
        this.activity.getSupportFragmentManager().addOnBackStackChangedListener(onBackStackChangedListener);
    }

    public void onPostCreate(Bundle savedInstanceState) {
        State state = currentStateHolder.get();
        if (state != null) {
            state.onPostCreate(activity, savedInstanceState);
        }
    }

    /**
     * @return true if current state process back press
     * false if not
     */
    public boolean onBackPressed() {
        final boolean b;
        State state = currentStateHolder.get();
        if (state == null) {
            b = false;
        } else {
            b = state.onBackPressed(activity);
        }
        return b;
    }

    public boolean onUpPressed() {
        final boolean b;
        State state = currentStateHolder.get();
        if (state == null) {
            b = false;
        } else {
            b = state.onUpPressed(activity);
        }
        return b;
    }

    @VisibleForTesting
    public int getStackLength() {
        return stateChanger.getStackLength();
    }

    public boolean hasLayoutId() {
        return layoutId != NOT_SET;
    }

    @LayoutRes
    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    public void dump() {
        Log.v(this, "*** begin Juggler dump ***");
        int backstackSize = activity.getSupportFragmentManager().getBackStackEntryCount();
        Log.v(this, "activity = " + activity);
        Log.v(this, "backstack size = " + backstackSize);
        for (int i = 0; i < backstackSize; i++) {
            FragmentManager.BackStackEntry backStackEntry = activity.getSupportFragmentManager().getBackStackEntryAt(i);
            Log.v(this, i + ") " + backStackEntry.getId() + " " + backStackEntry.getName() + " " + backStackEntry);
        }
        Log.v(this, "stack size = " + stateChanger.getItems().size());
        for (int i = 0; i < stateChanger.getItems().size(); i++) {
            Item item = stateChanger.getItems().get(i);
            Log.v(this, i + ") " + item);
        }
        State state = currentStateHolder.get();
        Log.v(this, "currentState = " + state);
        if (state != null) {
            Log.v(this, "grid size = " + state.getGrid().getCells().size());
            for (int i = 0; i < state.getGrid().getCells().size(); i++) {
                Cell cell = state.getGrid().getCells().get(i);
                Fragment fragment = activity.getSupportFragmentManager().findFragmentById(cell.getContainerId());
                Log.v(this, i + ") " + cell.getContainerId() + " " + cell.getType() + " " + fragment);
            }
        }
        Log.v(this, "*** end Juggler dump ***");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        State state = currentStateHolder.state;
        if (state != null) {
            for (Cell cell : state.getGrid().getCells()) {
                Fragment f = activity.getSupportFragmentManager().findFragmentById(cell.getContainerId());
                f.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    public void openDrawer() {
        State state = currentStateHolder.state;
        if (state != null) {
            Cell cell = null;
            for (Cell c : state.getGrid().getCells()) {
                if (c.getType() == Cell.CELL_TYPE_NAVIGATION) {
                    cell = c;
                }
            }
            if (cell == null) {
                throw new IllegalStateException("State has no drawer");
            } else {
                JugglerNavigationFragment f = (JugglerNavigationFragment) activity.getSupportFragmentManager().findFragmentById(cell.getContainerId());
                f.openDrawer();
            }
        } else {
            throw new NullPointerException("State not initialized");
        }
    }

    public void closeDrawer() {
        State state = currentStateHolder.state;
        if (state != null) {
            Cell cell = null;
            for (Cell c : state.getGrid().getCells()) {
                if (c.getType() == Cell.CELL_TYPE_NAVIGATION) {
                    cell = c;
                }
            }
            if (cell == null) {
                throw new IllegalStateException("State has no drawer");
            } else {
                JugglerNavigationFragment f = (JugglerNavigationFragment) activity.getSupportFragmentManager().findFragmentById(cell.getContainerId());
                f.closeDrawer();
            }
        } else {
            throw new NullPointerException("State not initialized");
        }
    }

    public static class StateHolder implements Serializable {

        @Nullable
        private State state = null;

        @Nullable
        public State get() {
            return state;
        }

        public void set(@Nullable State state) {
            this.state = state;
        }

    }

}
