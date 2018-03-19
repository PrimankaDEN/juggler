package me.ilich.juggler.change;

import android.os.Bundle;

import java.util.Stack;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.gui.JugglerActivity;

public final class Remove {

    public static Change none() {
        return new RemoveNone();
    }

    public static Change all() {
        return new RemoveAll();
    }

    public static Change dig(String tag) {
        return new RemoveDig(tag);
    }

    public static Change last() {
        return new RemoveLast();
    }

    public static Change closeCurrentActivity() {
        return new RemoveCloseCurrentActivity();
    }

    public static Change closeAllActivities() {
        return new RemoveCloseAllActivities();
    }

    private Remove() {

    }
}
