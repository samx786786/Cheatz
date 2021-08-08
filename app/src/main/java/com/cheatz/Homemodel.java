package com.cheatz;

public class Homemodel {

    String subjectname;
    String Youtubeurl;
    String notesurl;
    String Intent;


    public Homemodel(){}


    public Homemodel(String subjectname, String youtubeurl, String notesurl, String intent) {
        this.subjectname = subjectname;
        Youtubeurl = youtubeurl;
        this.notesurl = notesurl;
        Intent = intent;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getYoutubeurl() {
        return Youtubeurl;
    }

    public void setYoutubeurl(String youtubeurl) {
        Youtubeurl = youtubeurl;
    }

    public String getNotesurl() {
        return notesurl;
    }

    public void setNotesurl(String notesurl) {
        this.notesurl = notesurl;
    }

    public String getIntent() {
        return Intent;
    }

    public void setIntent(String intent) {
        Intent = intent;
    }
}
