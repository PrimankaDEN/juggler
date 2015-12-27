package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.JugglerContentFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.HelloScreensManager;

public class AboutFragment extends JugglerContentFragment<HelloScreensManager> {

    public static JugglerContentFragment create() {
        return new AboutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

}
