package com.cheatz;

public class Selectormodel {

    String sem;
    String imageurl;

    public Selectormodel(){}


    public Selectormodel(String sem, String imageurl) {
        this.sem = sem;
        this.imageurl = imageurl;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
