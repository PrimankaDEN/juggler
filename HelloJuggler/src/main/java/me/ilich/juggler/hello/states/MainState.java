package me.ilich.juggler.hello.states;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.MainFragment;
import me.ilich.juggler.hello.gui.StandardNavigationFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.states.ContentToolbarNavigationState;
import me.ilich.juggler.states.VoidParams;

public class MainState extends ContentToolbarNavigationState<VoidParams> {

    public static final String TAG = "main";

    public MainState() {
        super(VoidParams.instance());
    }

    @Override
    protected JugglerFragment onCreateContent(VoidParams params) {
        return MainFragment.newInstance();
    }

    @Override
    protected JugglerFragment onCreateToolbar(VoidParams params) {
        return StandardToolbarFragment.createTitleBack();
    }

    @Override
    protected JugglerFragment onCreateNavigation(VoidParams params) {
        return StandardNavigationFragment.create(0);
    }

    @Override
    public Drawable getUpNavigationIcon(Context context, VoidParams params) {
        return context.getResources().getDrawable(android.R.drawable.ic_menu_add);
    }

    @Nullable
    @Override
    public String getTitle(Context context, VoidParams params) {
        return context.getString(R.string.title_main);
    }

    @Override
    public String getTag() {
        return TAG;
    }

}
