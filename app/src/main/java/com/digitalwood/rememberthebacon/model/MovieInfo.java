package com.digitalwood.rememberthebacon.model;

/**
 * Created by awood on 7/6/14.
 */
public class MovieInfo {

    private String mTitle;
    private String mRating;
    private String mPosterURL;

    public MovieInfo(String title, String rating, String posterURL)
    {
        mTitle = title;
        mRating = rating;
        mPosterURL = posterURL;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

    public String getPosterURL() {
        return mPosterURL;
    }

    public void setPosterURL(String posterURL) {
        mPosterURL = posterURL;
    }
}
