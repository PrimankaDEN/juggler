package me.ilich.juggler;

import android.support.annotation.Nullable;

import java.io.Serializable;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.Remove;
import me.ilich.juggler.change.StateChanger;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;

public abstract class Transition implements Serializable {

    public static Transition transaction(String transition, @Nullable String tag) {
        return new Transaction(transition, tag);
    }

    public static Transition custom(String tag, Remove.Interface pop, Add.Interface add) {
        return new CustomTransition(tag, pop, add);
    }

    @Nullable
    private final String tag;

    protected Transition(String tag) {
        this.tag = tag;
    }

    public final State execute(JugglerActivity activity, StateChanger stateChanger) {
        return onExecute(activity, stateChanger, tag);
    }

    protected abstract State onExecute(JugglerActivity activity, StateChanger stateChanger, String tag);

    private static class Transaction extends Transition {

        private final String transactionName;

        private Transaction(String transactionName, @Nullable String tag) {
            super(tag);
            this.transactionName = transactionName;
        }

        @Override
        protected State onExecute(JugglerActivity activity, StateChanger stateChanger, String tag) {
            return stateChanger.transaction(transactionName, activity, tag);
        }

    }

    private static class CustomTransition extends Transition {

        private final Remove.Interface pop;
        private final Add.Interface addCondition;

        protected CustomTransition(String tag, Remove.Interface pop, Add.Interface addCondition) {
            super(tag);
            this.pop = pop;
            this.addCondition = addCondition;
        }

        @Override
        protected State onExecute(JugglerActivity activity, StateChanger stateChanger, String tag) {
            return stateChanger.change(activity, pop, addCondition);
        }

    }


}
