package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.util.Log;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.hello.states.MainState;
import me.ilich.juggler.hello.states.WizardOneState;

public class HelloActivity extends JugglerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {

            //navigateTo().state(Remove.clear(), Add.deeper(new TabsState()));
            //navigateTo().clearState(new WizardOneState());
            navigateTo().state(Add.deeper(new MainState()));
            //navigateTo().state(Remove.clear(), Add.deeper(new MainState()));

            //navigateTo().clearState(new SplashState());
            //navigateTo().clearState(new LoginState());
        } else {
            navigateTo().restore();
        }
    }

    @Override
    public boolean onNavigateUp() {
        Log.v("Sokolov", "onNavigateUp");
        return super.onNavigateUp();
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.v("Sokolov", "onSupportNavigateUp");
        return super.onSupportNavigateUp();
    }

}
