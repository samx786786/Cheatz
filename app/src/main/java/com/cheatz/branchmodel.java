package com.cheatz;

public class branchmodel {

    String backgroundpic;
    String branchname;
    String moduels;
    String year;
    String subbranch;

    public branchmodel(){}


    public branchmodel(String backgroundpic, String branchname, String moduels, String year, String subbranch) {
        this.backgroundpic = backgroundpic;
        this.branchname = branchname;
        this.moduels = moduels;
        this.year = year;
        this.subbranch = subbranch;
    }

    public String getBackgroundpic() {
        return backgroundpic;
    }

    public void setBackgroundpic(String backgroundpic) {
        this.backgroundpic = backgroundpic;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getModuels() {
        return moduels;
    }

    public void setModuels(String moduels) {
        this.moduels = moduels;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }
}
