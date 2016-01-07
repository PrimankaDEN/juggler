package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.fragments.JugglerNewInstance;
import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;
import me.ilich.juggler.hello.R;

public class CollapsingToolbarFragment extends JugglerToolbarFragment {

    @JugglerNewInstance
    public static CollapsingToolbarFragment create() {
        return new CollapsingToolbarFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_toolbar_collapsing, container, false);
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

}
