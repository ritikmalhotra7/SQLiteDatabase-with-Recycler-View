package com.example.sqlitewithrecyclerview;

public class Model {
    int id;
    String par1;
    String par2;

    public Model(int id, String par1, String par2) {
        this.id = id;
        this.par1 = par1;
        this.par2 = par2;
    }

    public Model() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPar1() {
        return par1;
    }

    public void setPar1(String par1) {
        this.par1 = par1;
    }

    public String getPar2() {
        return par2;
    }

    public void setPar2(String par2) {
        this.par2 = par2;
    }
}
