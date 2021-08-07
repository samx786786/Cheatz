package com.cheatz;

public class Shopmodel {

    String bookname;
    String author;
    String publishingyear;
    int price;
    String searchtag;
    String uploaddate;
    String aboutbook;
    String backgroundurl;


    public Shopmodel(){}

    public Shopmodel(String bookname, String author, String publishingyear, int price, String searchtag, String uploaddate, String aboutbook, String backgroundurl) {
        this.bookname = bookname;
        this.author = author;
        this.publishingyear = publishingyear;
        this.price = price;
        this.searchtag = searchtag;
        this.uploaddate = uploaddate;
        this.aboutbook = aboutbook;
        this.backgroundurl = backgroundurl;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingyear() {
        return publishingyear;
    }

    public void setPublishingyear(String publishingyear) {
        this.publishingyear = publishingyear;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSearchtag() {
        return searchtag;
    }

    public void setSearchtag(String searchtag) {
        this.searchtag = searchtag;
    }

    public String getUploaddate() {
        return uploaddate;
    }

    public void setUploaddate(String uploaddate) {
        this.uploaddate = uploaddate;
    }

    public String getAboutbook() {
        return aboutbook;
    }

    public void setAboutbook(String aboutbook) {
        this.aboutbook = aboutbook;
    }

    public String getBackgroundurl() {
        return backgroundurl;
    }

    public void setBackgroundurl(String backgroundurl) {
        this.backgroundurl = backgroundurl;
    }
}
