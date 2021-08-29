package com.cheatz;

public class Homemodel {

    String subjectname;
    String backgroundurl;
    String intent;

    public Homemodel(){}

    public Homemodel(String subjectname, String backgroundurl, String intent) {
        this.subjectname = subjectname;
        this.backgroundurl = backgroundurl;
        this.intent = intent;
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

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }
}
