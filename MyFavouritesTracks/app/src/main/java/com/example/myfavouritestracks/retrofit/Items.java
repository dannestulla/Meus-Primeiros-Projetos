package com.example.myfavouritestracks.retrofit;

public class Items {

    private String added_at;

    private Track track;

    private Artists artists;

    public String getAdded_at ()
    {
        return added_at;
    }


    public Track getTrack ()
    {
        return track;
    }



    @Override
    public String toString()
    {
        return "Song: "+track.getName() + track;
    }
}
