package com.example.newmusicplayer;

public class Artists {
    private String name;

    private String href;

    private String id;

    private String type;


    private String uri;

    public String getName()
    {
        return name;
    }


    public String getHref ()
    {
        return href;
    }



    public String getId ()
    {
        return id;
    }


    public String getType ()
    {
        return type;
    }



    public String getUri ()
    {
        return uri;
    }


    @Override
    public String toString()
    {
        return ""+name;
    }
}
