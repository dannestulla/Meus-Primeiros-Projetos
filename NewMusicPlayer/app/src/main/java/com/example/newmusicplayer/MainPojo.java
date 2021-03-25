package com.example.newmusicplayer;

public class MainPojo {

        private String next;

        private String total;

        private String offset;

        private String limit;

        private String href;

        private Items[] items;

        public String getNext ()
        {
            return next;
        }

        public String getTotal ()
        {
            return total;
        }

        public String getOffset ()
        {
            return offset;
        }

        public String getLimit ()
        {
            return limit;
        }

        public String getHref ()
        {
            return href;
        }

        public Items[] getItems ()
        {
            return items;
        }

        private Track track;

        public String nome_da_track() {
            return track.getName();
        }



        @Override
        public String toString()
        {
            return "ClassPojo [next = "+next+", total = "+total+", offset = "+offset+", limit = "+limit+", href = "+href+", items = "+items+"]";
        }
    }

