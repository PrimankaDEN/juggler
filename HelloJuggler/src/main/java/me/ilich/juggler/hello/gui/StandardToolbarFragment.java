package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.JugglerFragment;
import me.ilich.juggler.JugglerToolbarFragment;
import me.ilich.juggler.hello.R;

public class StandardToolbarFragment extends JugglerToolbarFragment {

    public static StandardToolbarFragment create() {
        return new StandardToolbarFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_toolbar_standart, container, false);
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

}
