package com.thesis.instance.metadata;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Date")
public class Date {

    private int year;
    private int month;

    public int getYear() {
        return year;
    }

    @XmlAttribute(name = "year")
    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    @XmlAttribute(name = "month")
    public void setMonth(int month) {
        this.month = month;
    }

    public String toString() {
        return getMonth() + "/" + getYear();
    }
}
