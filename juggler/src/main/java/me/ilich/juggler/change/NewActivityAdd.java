package me.ilich.juggler.change;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.Nullable;

import java.util.Stack;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;

import static me.ilich.juggler.Juggler.DATA_INTENT_FLAG;

public class NewActivityAdd implements Change {

    public static NewActivityAdd forResult(State state, Class<? extends JugglerActivity> activityClass, int requestCode) {
        return new NewActivityAdd(state, activityClass, 0, 0, true, requestCode);
    }

    private final State state;
    private final Class<? extends JugglerActivity> activityClass;
    @AnimRes
    private final int enterAnimationId;
    @AnimRes
    private final int exitAnimationId;

    private final boolean isForResult;
    private final int requestCode;

    @Nullable
    private Bundle activityOptions;

    public NewActivityAdd(State state) {
        this.state = state;
        this.activityOptions = state.getActivityOptions();
        this.activityClass = null;
        this.enterAnimationId = 0;
        this.exitAnimationId = 0;
        this.isForResult = false;
        this.requestCode = 0;
    }

    public NewActivityAdd(State state, Class<? extends JugglerActivity> activityClass) {
        this.state = state;
        this.activityOptions = state.getActivityOptions();
        this.activityClass = activityClass;
        this.enterAnimationId = 0;
        this.exitAnimationId = 0;
        this.isForResult = false;
        this.requestCode = 0;
    }

    public NewActivityAdd(State state, Class<? extends JugglerActivity> activityClass, @AnimRes int enterAnimationId, @AnimRes int exitAnimationId) {
        this.state = state;
        this.activityOptions = state.getActivityOptions();
        this.activityClass = activityClass;
        this.enterAnimationId = enterAnimationId;
        this.exitAnimationId = exitAnimationId;
        this.isForResult = false;
        this.requestCode = 0;
    }

    private NewActivityAdd(State state, Class<? extends JugglerActivity> activityClass, @AnimRes int enterAnimationId, @AnimRes int exitAnimationId, boolean forResult, int requestCode) {
        this.state = state;
        this.activityOptions = state.getActivityOptions();
        this.activityClass = activityClass;
        this.enterAnimationId = enterAnimationId;
        this.exitAnimationId = exitAnimationId;
        this.isForResult = true;
        this.requestCode = requestCode;
    }

    @Override
    public Item change(JugglerActivity activity, Stack<Item> items, Juggler.StateHolder currentStateHolder, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle.containsKey(DATA_INTENT_FLAG)) {
            intent.setFlags(bundle.getInt(DATA_INTENT_FLAG));
        }
        if (activityClass == null) {
            intent.setComponent(new ComponentName(activity, JugglerActivity.class));
        } else {
            intent.setComponent(new ComponentName(activity, activityClass));
        }
        JugglerActivity.state(activity, state, intent);

        if (isForResult) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && activityOptions != null) {
                //noinspection RestrictedApi
                activity.startActivityForResult(intent, requestCode, activityOptions);
            } else {
                activity.startActivityForResult(intent, requestCode);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && activityOptions != null) {
                activity.startActivity(intent, activityOptions);
            } else {
                activity.startActivity(intent);
            }
        }
        activity.overridePendingTransition(enterAnimationId, exitAnimationId);
        return null;
    }

}
