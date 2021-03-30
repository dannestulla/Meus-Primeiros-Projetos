
package com.example.myfavouritestracks.retrofit;

import com.spotify.protocol.types.Album;

import java.util.Arrays;

public class Track
{
    private String disc_number;

    private Album album;

    private String type;



    private String uri;

    private String duration_ms;

    private String explicit;

    private String is_playable;

    private Artists[] artists;

    private String preview_url;

    private String popularity;

    private String name;

    private String track_number;

    private String href;

    private String id;

    private String is_local;


    public String getDisc_number ()
    {
        return disc_number;
    }


    public Album getAlbum ()
    {
        return album;
    }


    public String getType ()
    {
        return type;
    }


    public String getUri ()
    {
        return uri;
    }


    public String getDuration_ms ()
    {
        return duration_ms;
    }


    public String getExplicit ()
    {
        return explicit;
    }


    public String getIs_playable ()
    {
        return is_playable;
    }


    public Artists[] getArtists ()
    {
        return artists;
    }


    public String getPreview_url ()
    {
        return preview_url;
    }


    public String getPopularity ()
    {
        return popularity;
    }


    public String getName ()
    {
        return name;
    }


    public String getTrack_number ()
    {
        return track_number;
    }


    public String getHref ()
    {
        return href;
    }


    public String getId ()
    {
        return id;
    }


    public String getIs_local ()
    {
        return is_local;
    }



    @Override
    public String toString()
    {
        return "\nArtists: "+ Arrays.toString(artists) +"\n"+ preview_url;
    }
}
