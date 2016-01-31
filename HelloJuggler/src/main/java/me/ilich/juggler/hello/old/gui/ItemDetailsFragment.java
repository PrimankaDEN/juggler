package me.ilich.juggler.hello.old.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.ilich.juggler.old.fragments.JugglerNewInstance;
import me.ilich.juggler.old.fragments.content.JugglerContentFragment_;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.old.screens.ItemDetailScreen;

public class ItemDetailsFragment extends JugglerContentFragment_ {

    private static final String ARG_ID = "id";

    @JugglerNewInstance
    public static ItemDetailsFragment create(ItemDetailScreen.Params params) {
        ItemDetailsFragment f = new ItemDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_ID, params.getItemId());
        f.setArguments(bundle);
        return f;
    }

    private TextView numberTextView;
    private int id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt(ARG_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        numberTextView = (TextView) view.findViewById(R.id.number);
        numberTextView.setText(Integer.toString(id));
        getActivity().setTitle("item " + id);
    }

}