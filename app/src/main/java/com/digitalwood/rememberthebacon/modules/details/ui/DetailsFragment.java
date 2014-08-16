package com.digitalwood.rememberthebacon.modules.details.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.digitalwood.rememberthebacon.R;
import com.digitalwood.rememberthebacon.common.datastore.IListStore;
import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.applogic.DetailsInteractor;
import com.digitalwood.rememberthebacon.modules.details.applogic.DetailsWireframe;
import com.digitalwood.rememberthebacon.modules.details.presenter.DetailsPresenter;
import com.digitalwood.rememberthebacon.modules.details.presenter.IDetailsPresenter;

/**
 * Created by Andrew on 7/9/2014.
 * Copyright 2014
 */
public class DetailsFragment extends Fragment implements IDetailsView, View.OnClickListener {
    public static final String EXTRA_ID_OF_CONSUMABLE_TO_EDIT =
            "com.digitalwood.rememberthebacon.consumable_id";
    public static final String EXTRA_CONSUMABLE_TO_EDIT_NOT_SET = "";
    private IDetailsPresenter mPresenter;
    private EditText mEditName;
    private Button mOkButton;
    private Button mCancelButton;
    private String mIdOfConsumableToEdit;

    public static DetailsFragment newInstance(String id) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ID_OF_CONSUMABLE_TO_EDIT, id);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IListStore listStore = ListStore.getInstance(this.getActivity().getApplicationContext());
        mPresenter = new DetailsPresenter(
                this,
                new DetailsWireframe(getActivity()),
                new DetailsInteractor(listStore));

        mIdOfConsumableToEdit = getArguments().getString(EXTRA_ID_OF_CONSUMABLE_TO_EDIT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);

        // Setup input views
        mEditName = (EditText) v.findViewById(R.id.details_name_editText);
        mOkButton = (Button) v.findViewById(R.id.details_ok_button);
        mCancelButton = (Button) v.findViewById(R.id.details_cancel_button);

        // Setup view listeners
        mOkButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume(mIdOfConsumableToEdit);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
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
        getActivity().setTitle(title);
    }

    @Override
    public String getItemName() {
        return mEditName.getText().toString();
    }

    @Override
    public void setItemName(String name) {
        mEditName.setText(name);
    }

    @Override
    public void showKeyboard() {
        // Automatically display the keyboard so the user can begin to enter text
        InputMethodManager mgr = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.showSoftInput(mEditName, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public void hideKeyboard() {
        // Automatically hide the keyboard
        InputMethodManager mgr = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(mEditName.getWindowToken(), 0);
    }
}
