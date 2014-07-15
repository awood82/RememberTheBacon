package com.digitalwood.rememberthebacon.modules.details.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.digitalwood.rememberthebacon.R;
import com.digitalwood.rememberthebacon.modules.details.applogic.DetailsInteractor;
import com.digitalwood.rememberthebacon.modules.details.applogic.DetailsWireframe;
import com.digitalwood.rememberthebacon.modules.details.presenter.DetailsPresenter;
import com.digitalwood.rememberthebacon.modules.details.presenter.IDetailsPresenter;

/**
 * Created by Andrew on 7/9/2014.
 * Copyright 2014
 */
public class DetailsFragment extends Fragment implements IDetailsView {
    public static final String EXTRA_CONSUMABLE_ID =
            "com.digitalwood.rememberthebacon.consumable_id";
    private IDetailsPresenter mPresenter;
    private EditText mEditName;
    private Button mOkButton;
    private Button mCancelButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new DetailsPresenter(
                this,
                new DetailsWireframe(getActivity()),
                new DetailsInteractor(getActivity().getApplicationContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_detailsfragment, container, false);

        mEditName = (EditText) v.findViewById(R.id.details_name_editText);
        mOkButton = (Button) v.findViewById(R.id.details_ok_button);
        mCancelButton = (Button) v.findViewById(R.id.details_cancel_button);

        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.okButtonPressed();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.cancelButtonPressed();
            }
        });

        return v;
    }

    // IDetailsView
    @Override
    public void setTitle(String title) {
        getActivity().setTitle(title); //R.string.details_title);
    }

    @Override
    public void setItemName(String name) {
        mEditName.setText(name);
    }
}
