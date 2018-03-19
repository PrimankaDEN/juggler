package me.ilich.juggler;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import me.ilich.juggler.change.Change;

public class CrossActivity {

    private static final CrossActivity instance = new CrossActivity();

    public static CrossActivity getInstance() {
        return instance;
    }

    private final List<Change[]> changes = new ArrayList<>();

    private CrossActivity() {

    }

    public void add(Change... change) {
        changes.add(change);
    }

    public boolean has() {
        return changes.size() > 0;
    }

    @Nullable
    public Change[] getLast() {
        if (has()) {
            return changes.remove(changes.size() - 1);
        }
        return null;
    }
}
