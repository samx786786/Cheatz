package com.cheatz;

public class Shopmodel {

    String bookname;
    String author;
    String publishingyear;
    String price;
    String searchtag;
    String uploaddate;
    String offerprice;


    public Shopmodel(){}

    public Shopmodel(String bookname, String author, String publishingyear, String price, String searchtag, String uploaddate, String offerprice) {
        this.bookname = bookname;
        this.author = author;
        this.publishingyear = publishingyear;
        this.price = price;
        this.searchtag = searchtag;
        this.uploaddate = uploaddate;
        this.offerprice = offerprice;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getOfferprice() {
        return offerprice;
    }

    public void setOfferprice(String offerprice) {
        this.offerprice = offerprice;
    }


}
