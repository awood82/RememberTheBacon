package com.digitalwood.rememberthebacon.modules.details.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.digitalwood.rememberthebacon.R;
import com.digitalwood.rememberthebacon.modules.details.applogic.DetailsInteractor;
import com.digitalwood.rememberthebacon.modules.details.applogic.DetailsWireframe;
import com.digitalwood.rememberthebacon.modules.details.presenter.DetailsPresenter;
import com.digitalwood.rememberthebacon.modules.details.presenter.IDetailsPresenter;

import java.util.UUID;

/**
 * Created by Andrew on 7/9/2014.
 * Copyright 2014
 */
public class DetailsFragment extends Fragment implements IDetailsView, View.OnClickListener {
    public static final String EXTRA_CONSUMABLE_INDEX =
            "com.digitalwood.rememberthebacon.consumable_index";
    public static final int EXTRA_CONSUMABLE_INDEX_NOT_SET = -1;
    private IDetailsPresenter mPresenter;
    private EditText mEditName;
    private Button mOkButton;
    private Button mCancelButton;
    private int mConsumableIndex;

    public static DetailsFragment newInstance(int consumableIndex) {
        Bundle args = new Bundle();
        args.putInt(EXTRA_CONSUMABLE_INDEX, consumableIndex);

        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new DetailsPresenter(
                this,
                new DetailsWireframe(getActivity()),
                new DetailsInteractor(getActivity().getApplicationContext()));

        mConsumableIndex = getArguments().getInt(
                EXTRA_CONSUMABLE_INDEX,
                EXTRA_CONSUMABLE_INDEX_NOT_SET);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_detailsfragment, container, false);

        mEditName = (EditText) v.findViewById(R.id.details_name_editText);
        mOkButton = (Button) v.findViewById(R.id.details_ok_button);
        mCancelButton = (Button) v.findViewById(R.id.details_cancel_button);

        mOkButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume(mConsumableIndex);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.details_ok_button:
                mPresenter.okButtonPressed();
                break;
            case R.id.details_cancel_button:
                mPresenter.cancelButtonPressed();
                break;
        }
    }

    // IDetailsView
    @Override
    public void setTitle(String title) {
        getActivity().setTitle(title); //R.string.details_title);
    }

    @Override
    public String getItemName() {
        return mEditName.getText().toString();
    }

    @Override
    public void setItemName(String name) {
        mEditName.setText(name);
    }
}
