package com.cheatz;

public class Homemodel {

    String subjectname;
    String backgroundurl;

    public Homemodel(){}

    public Homemodel(String subjectname, String backgroundurl) {
        this.subjectname = subjectname;
        this.backgroundurl = backgroundurl;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getBackgroundurl() {
        return backgroundurl;
    }

    public void setBackgroundurl(String backgroundurl) {
        this.backgroundurl = backgroundurl;
    }

}
