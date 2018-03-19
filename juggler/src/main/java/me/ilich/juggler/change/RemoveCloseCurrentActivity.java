package me.ilich.juggler.change;

import android.os.Bundle;

import java.util.Stack;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.gui.JugglerActivity;

import static me.ilich.juggler.Juggler.DATA_ANIMATION_FINISH_ENTER;
import static me.ilich.juggler.Juggler.DATA_ANIMATION_FINISH_EXIT;

public class RemoveCloseCurrentActivity implements Change {

    @Override
    public Item change(JugglerActivity activity, Stack<Item> items, Juggler.StateHolder currentStateHolder, Bundle bundle) {
        int finishEnterAnimation = bundle.getInt(DATA_ANIMATION_FINISH_ENTER, 0);
        int finishExitAnimation = bundle.getInt(DATA_ANIMATION_FINISH_EXIT, 0);
        activity.finish();
        activity.overridePendingTransition(finishEnterAnimation, finishExitAnimation);
        return null;
    }

}
