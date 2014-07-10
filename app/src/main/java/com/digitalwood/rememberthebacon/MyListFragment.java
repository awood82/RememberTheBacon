package com.digitalwood.rememberthebacon;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalwood.rememberthebacon.model.MovieInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by awood on 7/6/14.
 */
public class MyListFragment extends ListFragment {

    private ArrayList<MovieInfo> mMovies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Movies");

        initMovies();
        //mMovies = (ArrayList<MovieInfo>) getArguments().getSerializable(MyListActivity.EXTRA_MOVIES_KEY);

        /*ArrayAdapter<MovieInfo> adapter = new ArrayAdapter<MovieInfo>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mMovies);*/
        MyListAdapter adapter = new MyListAdapter(mMovies);
        setListAdapter(adapter);
    }

    private void initMovies()
    {
        mMovies = new ArrayList<MovieInfo>();
        for (int i = 0; i < 40; i++)
        {
            mMovies.add(new MovieInfo("Stamp " + i, "$" + ((i % 30 + 1) * 10), ""));
        }
    }

    private class MyListAdapter extends ArrayAdapter<MovieInfo> {
        public MyListAdapter(ArrayList<MovieInfo> movies) {
            super(getActivity(), 0, movies);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren't given a view, inflate one
            if (convertView == null)
            {
                convertView = getActivity().getLayoutInflater().inflate(
                        R.layout.list_item_movie, null);
            }

            // Configure the view for this movie
            MovieInfo mi = getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.title_textView);
            titleTextView.setText(mi.getTitle());
            TextView ratingTextView = (TextView) convertView.findViewById(R.id.rating_textView);
            ratingTextView.setText(mi.getRating());
            ImageView moviePosterImage = (ImageView) convertView.findViewById(R.id.movie_imageView);
            Picasso.with(getContext())
                    //.load("http://i.imgur.com/DvpvklR.png")
                    .load("http://www.637363.com/scans/public/842/0444" + (56 + position) + ".jpg")
                    .placeholder(android.R.drawable.gallery_thumb)
                    .error(android.R.drawable.stat_notify_error)
                    .resize(200,200)
                    .into(moviePosterImage);

            return convertView;
        }
    }
}
