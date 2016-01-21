package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.fragments.JugglerNewInstance;
import me.ilich.juggler.fragments.content.JugglerContentFragment_;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.screens.WizardTwoScreen;

public class WizardTwoFragment extends JugglerContentFragment_ {

    @JugglerNewInstance
    public static WizardTwoFragment newInstance() {
        return new WizardTwoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wizzard_two, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJuggler().navigate(WizardTwoScreen.class).wizardThree();
            }
        });
    }

}
