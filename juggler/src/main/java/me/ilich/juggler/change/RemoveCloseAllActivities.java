package me.ilich.juggler.change;

import android.content.Intent;
import android.os.Bundle;

import java.util.Stack;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.gui.JugglerActivity;

import static me.ilich.juggler.Juggler.DATA_INTENT_FLAG;

public class RemoveCloseAllActivities implements Change {

    @Override
    public Item change(JugglerActivity activity, Stack<Item> items, Juggler.StateHolder currentStateHolder, Bundle data) {
        data.putInt(DATA_INTENT_FLAG, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return null;
    }
}
