package com.example.octave;

public class FireModel {
    public String title;
    public String desc;
    public String link;
    FireModel()
    {

    }
    FireModel(String a, String b, String c)
    {
        title=a;
        desc=b;
        link=c;

    }

    public String getDesc() {
        return desc;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
