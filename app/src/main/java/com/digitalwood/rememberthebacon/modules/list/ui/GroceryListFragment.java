package com.digitalwood.rememberthebacon.modules.list.ui;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.digitalwood.rememberthebacon.R;
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
public class GroceryListFragment extends ListFragment implements IGroceryListView {

    private IGroceryListPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mPresenter = new GroceryListPresenter(
                this,
                new GroceryListWireframe(getActivity()),
                new GroceryListInteractor(getActivity().getApplicationContext()));

        this.setTitle(getResources().getString(R.string.list_title));
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
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
        mPresenter.onItemClicked(position);
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
            /*ImageView moviePosterImage = (ImageView) convertView.findViewById(R.id.movie_imageView);
            Picasso.with(getContext())
                    //.load("http://i.imgur.com/DvpvklR.png")
                    .load("http://www.637363.com/scans/public/842/0444" + (56 + position) + ".jpg")
                    .placeholder(android.R.drawable.gallery_thumb)
                    .error(android.R.drawable.stat_notify_error)
                    .resize(200,200)
                    .into(moviePosterImage); */
            return convertView;
        }
    }
}
