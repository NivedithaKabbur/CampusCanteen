package com.trianz.campuscanteen;

/**
 * Created by Niveditha.Kabbur on 4/27/2017.
 */

public class CampusCafe {

    private String cafe_tag;
    private String cafe_name;
    private String cafe_url;

    public void setCafeTag(String cafe_tag)
    {
        this.cafe_tag = cafe_tag;
    }

    public String getCafeTag()
    {
        return cafe_tag;
    }

    public void setCafeName(String cafe_name)
    {
        this.cafe_name = cafe_name;
    }

    public String getCafename()
    {
        return cafe_name;
    }

    public void setCafeUrl(String cafe_url)
    {
        this.cafe_url =cafe_url;
    }

    public String getCafeUrl()
    {
        return cafe_url;
    }
}
