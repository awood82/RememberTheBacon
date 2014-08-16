package com.digitalwood.rememberthebacon.modules.list.ui;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalwood.rememberthebacon.R;
import com.digitalwood.rememberthebacon.common.datastore.IListStore;
import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.list.applogic.GroceryListInteractor;
import com.digitalwood.rememberthebacon.modules.list.presenter.GroceryListPresenter;
import com.digitalwood.rememberthebacon.modules.list.presenter.IGroceryListPresenter;
import com.digitalwood.rememberthebacon.modules.list.applogic.GroceryListWireframe;

import java.util.ArrayList;

/**
 * Created by awood on 7/6/14.
 */
public class GroceryListFragment extends ListFragment implements IGroceryListView, AdapterView.OnItemLongClickListener {
    public static final String EXTRA_LIST_STORE_FILENAME =
            "com.digitalwood.rememberthebacon.list_store_filename";
    private IGroceryListPresenter mPresenter;

    public static GroceryListFragment newInstance(String listStoreFilename) {
        Bundle args = new Bundle();
        args.putString(EXTRA_LIST_STORE_FILENAME, listStoreFilename);

        GroceryListFragment fragment = new GroceryListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        IListStore listStore = ListStore.getInstance(this.getActivity().getApplicationContext());

        String listStoreFilename = getArguments().getString(
                EXTRA_LIST_STORE_FILENAME);
        if (listStoreFilename == null || listStoreFilename.isEmpty()) {
            listStoreFilename = GroceryListInteractor.DEFAULT_LIST_STORE_FILENAME;
        }

        mPresenter = new GroceryListPresenter(
                this,
                new GroceryListWireframe(getActivity()),
                new GroceryListInteractor(getActivity().getApplicationContext(), listStore, listStoreFilename));
    }

    @Override
    public void onResume() {
        super.onResume();
        getListView().setOnItemLongClickListener(this);
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_grocery_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_consumable:
                mPresenter.onAddPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mPresenter.onItemClicked((Consumable)getListAdapter().getItem(position));
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
        mPresenter.onItemLongClicked((Consumable)getListAdapter().getItem(position));
        return true;
    }

    // IGroceryListView
    @Override
    public void setTitle(String title) {
        getActivity().setTitle(title);
    }

    @Override
    public void setItems(ArrayList<Consumable> items) {
        GroceryListAdapter adapter = new GroceryListAdapter(items);
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
/*
    @Override
    public void toggleItemBought(int index) {
        getListView()
                .getChildAt(index)
                .findViewById(R.id.bought_checkBox)
                .performClick();
    }
*/

    @Override
    public void toggleItemBought(Consumable consumable) {
        ListAdapter la = getListAdapter();
        for (int i = 0; i < la.getCount(); i++) {
            Consumable inList = (Consumable)la.getItem(i);
            if (consumable.getId().equals(inList.getId())) {
                getListView()
                        .getChildAt(i)
                        .findViewById(R.id.bought_checkBox)
                        .performClick();
                break;
            }
        }
    }

    @Override
    public void toast(int id) {
        Toast.makeText(
                getActivity().getApplicationContext(),
                getResources().getText(id),
                Toast.LENGTH_SHORT)
        .show();
    }

    private class GroceryListAdapter extends ArrayAdapter<Consumable> {
        public GroceryListAdapter(ArrayList<Consumable> consumables) {
            super(getActivity(), 0, consumables);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(
                        R.layout.list_item_consumable, null);
            }

            // Configure the view for this movie
            Consumable item = getItem(position);

            TextView nameTextView = (TextView) convertView.findViewById(R.id.name_textView);
            nameTextView.setText(item.getName());
            CheckBox boughtCheckBox = (CheckBox) convertView.findViewById(R.id.bought_checkBox);
            boughtCheckBox.setChecked(item.isBought());

            return convertView;
        }
    }
}
