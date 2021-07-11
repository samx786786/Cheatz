package com.cheatz;

public class Mainpagemodel {

    String topicname;
    String imageurl;
    String searchkey;
    String youtubeurl;


   public Mainpagemodel(){}


    public Mainpagemodel(String topicname, String imageurl, String searchkey, String youtubeurl) {
        this.topicname = topicname;
        this.imageurl = imageurl;
        this.searchkey = searchkey;
        this.youtubeurl = youtubeurl;
    }


    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
    }

    public String getYoutubeurl() {
        return youtubeurl;
    }

    public void setYoutubeurl(String youtubeurl) {
        this.youtubeurl = youtubeurl;
    }



}
