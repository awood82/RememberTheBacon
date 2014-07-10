package com.digitalwood.rememberthebacon;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalwood.rememberthebacon.model.Consumable;

import java.util.ArrayList;

/**
 * Created by awood on 7/6/14.
 */
public class GroceryListFragment extends ListFragment {

    private ArrayList<Consumable> mConsumables;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getResources().getString(R.string.list_title));

        initConsumables();
        //mMovies = (ArrayList<MovieInfo>) getArguments().getSerializable(GroceryListActivity.EXTRA_MOVIES_KEY);

        /*ArrayAdapter<MovieInfo> adapter = new ArrayAdapter<MovieInfo>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mMovies);*/
        GroceryListAdapter adapter = new GroceryListAdapter(mConsumables);
        setListAdapter(adapter);
    }

    private void initConsumables() {
        mConsumables = new ArrayList<Consumable>();
        for (int i = 0; i < 3; i++)
        {
            mConsumables.add(new Consumable("Food #" + i));
        }
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
            //TextView ratingTextView = (TextView) convertView.findViewById(R.id.rating_textView);
            //ratingTextView.setText(mi.getRating());
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
