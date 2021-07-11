package com.cheatz;

public class Importantquestionmodel {

    String quetion;
    String answer;
    String imageurl;


    public Importantquestionmodel(){}


    public Importantquestionmodel(String quetion, String answer, String imageurl) {
        this.quetion = quetion;
        this.answer = answer;
        this.imageurl = imageurl;
    }


    public String getQuetion() {
        return quetion;
    }

    public void setQuetion(String quetion) {
        this.quetion = quetion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }


}

