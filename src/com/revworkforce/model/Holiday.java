package com.revworkforce.model;
import java.util.Date;
public class Holiday {
    private Date date;
    private String name;

    public Holiday(Date date, String name) {
        this.date = date;
        this.name = name;
    }

    public Date getDate() { return date; }
    public String getName() { return name; }
}
