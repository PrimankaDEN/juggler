package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.gui.JugglerNavigationFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.states.AboutState;
import me.ilich.juggler.hello.states.MainState;

public class StandardNavigationFragment extends JugglerNavigationFragment {

    public static StandardNavigationFragment create(int itemId) {
        StandardNavigationFragment f = new StandardNavigationFragment();
        f.setArguments(addSelectedItemToBundle(null, itemId));
        return f;
    }

    private NavigationView navigationView;
    private boolean initMenuItem = false;
    private int initMenuId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_standard, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigationView = (NavigationView) view.findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                final boolean b;
                switch (item.getItemId()) {
                    case R.id.menu_main:
                        navigateTo().clearState(new MainState());
                        b = true;
                        break;
                    case R.id.menu_about:
                        navigateTo().linearState(new AboutState());
                        b = true;
                        break;
                    default:
                        b = false;
                        break;
                }
                close();
                return b;
            }
        });
        if (initMenuItem) {
            onNavigationItemSelect(initMenuId);
        }
    }

    @Override
    protected void onNavigationItemSelect(int id) {
        if (navigationView == null) {
            initMenuId = id;
            initMenuItem = true;
        } else {
            navigationView.getMenu().getItem(id).setChecked(true);
        }
    }

}