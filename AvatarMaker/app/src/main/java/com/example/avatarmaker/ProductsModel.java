package com.example.avatarmaker;

public class ProductsModel {
    private String format;
    private String qnt_people;
    private String user_name;
    private String style;


    private ProductsModel() {

    }

    private ProductsModel(String format, String qnt_people, String style, String user_name) {
        this.format = format;
        this.qnt_people = qnt_people;
        this.style = style;
        this.user_name = user_name;


    }


    public String getFormat() {
        return format;
    }


    public String getUser_name() {
        return user_name;
    }


    public String getStyle() {
        return style;
    }


    public String getQnt_people() {
        return qnt_people;
    }


}
