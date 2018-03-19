package me.ilich.juggler.change;

import android.support.annotation.AnimRes;

import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.TargetBound;

public final class Add {

    public static Change none() {
        return new NoneAdd();
    }

    public static Change deeper(State state) {
        return new DeeperAdd(state, null);
    }

    public static Change deeper(State state, String tag, TargetBound... targetBounds) {
        return new DeeperAdd(state, tag, targetBounds);
    }

    public static Change deeper(State state, TargetBound... targetBounds) {
        return new DeeperAdd(state, null, targetBounds);
    }

    public static Change linear(State state) {
        return new LinearAdd(state, null);
    }

    public static Change linear(State state, String tag, TargetBound... targetBounds) {
        return new LinearAdd(state, tag, targetBounds);
    }

    public static Change linear(State state, TargetBound... targetBounds) {
        return new LinearAdd(state, null, targetBounds);
    }

    public static Change newActivity(State state) {
        return new NewActivityAdd(state);
    }

    public static Change newActivity(State state, Class<? extends JugglerActivity> activityClass) {
        return new NewActivityAdd(state, activityClass);
    }

    public static Change newActivity(State state, Class<? extends JugglerActivity> activityClass, @AnimRes int enterAnimationId, @AnimRes int exitAnimationId) {
        return new NewActivityAdd(state, activityClass, enterAnimationId, exitAnimationId);
    }

    public static Change newActivityForResult(State state, Class<? extends JugglerActivity> activityClass, int requestCode) {
        return NewActivityAdd.forResult(state, activityClass, requestCode);
    }

    private Add() {

    }
}
