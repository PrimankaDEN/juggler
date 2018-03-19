package me.ilich.juggler.change;

import android.os.Bundle;

import java.util.Stack;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.gui.JugglerActivity;

/**
 * Denis Knyazev
 * denis.knyazev@altarix.com
 * 19.03.18
 */
public interface Change {
    Item change(JugglerActivity activity, Stack<Item> items, Juggler.StateHolder currentStateHolder, Bundle data);
}
