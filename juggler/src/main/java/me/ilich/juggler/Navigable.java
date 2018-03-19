package me.ilich.juggler;

import android.support.annotation.Nullable;

import me.ilich.juggler.change.Change;

public interface Navigable {

    boolean backState();

    boolean upState();

    void restore();

    void state(@Nullable Change... changes);
}
