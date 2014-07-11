package com.digitalwood.rememberthebacon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Andrew on 7/9/2014.
 * Copyright 2014
 */
public class DetailsFragment extends Fragment {
    EditText editName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.details_title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_detailsfragment, container, false);

        editName = (EditText) getActivity().findViewById(R.id.details_name_editText);

        return v;
    }
}
