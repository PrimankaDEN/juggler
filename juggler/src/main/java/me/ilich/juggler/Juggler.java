package me.ilich.juggler;

import android.support.v7.app.ActionBar;
import android.util.Log;

import me.ilich.juggler.activity.JugglerActivity;
import me.ilich.juggler.activity.LayoutController;
import me.ilich.juggler.fragments.JugglerFragment;
import me.ilich.juggler.fragments.content.JugglerContentFragment;
import me.ilich.juggler.fragments.navigation.JugglerNavigationFragment;
import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;

public class Juggler<SM extends ScreensManager> {

    private SM screensManager;
    private LayoutController layoutController = null;
    private JugglerActivity<SM> activity;

    public Juggler(SM screensManager, JugglerActivity<SM> activity) {
        this.screensManager = screensManager;
        this.activity = activity;
        layoutController = new LayoutController(activity);
    }

    public SM getScreenManager() {
        return screensManager;
    }

    public void setToolbarOptions(@ActionBar.DisplayOptions int options) {
        screensManager.setToolbarOptions(options);
    }

    public int getToolbarOptions() {
        return screensManager.getToolbarOptions();
    }

    public void onAttach(JugglerFragment<SM> fragment) {
        if (fragment instanceof JugglerToolbarFragment) {
            screensManager.onToolbarAttached((JugglerToolbarFragment) fragment);
        } else if (fragment instanceof JugglerNavigationFragment) {
            screensManager.onNavigationAttached((JugglerNavigationFragment) fragment);
        } else if (fragment instanceof JugglerContentFragment) {
            screensManager.onContentAttached((JugglerContentFragment) fragment);
        }
    }

    public void onDetach(JugglerFragment<SM> fragment) {
        if (fragment instanceof JugglerToolbarFragment) {
            screensManager.onToolbarDetached((JugglerToolbarFragment) fragment);
        } else if (fragment instanceof JugglerNavigationFragment) {
            screensManager.onNavigationDetached((JugglerNavigationFragment) fragment);
        } else if (fragment instanceof JugglerContentFragment) {
            screensManager.onContentDetached((JugglerContentFragment) fragment);
        }
    }

    public void onBeginNewScreen(Screen.Instance screenInstance) {
        Log.v("Sokolov", "begin " + screenInstance);
    }

    public void onEndNewScreen(Screen.Instance screenInstance) {
        Log.v("Sokolov", "end " + screenInstance);
    }

    public boolean onBackPressed() {
        return layoutController.onBackPressed();
    }

    public LayoutController getLayoutController() {
        return layoutController;
    }

    public boolean hasToolbarContainer() {
        return layoutController.getToolbarViewGroup() != null;
    }

}
