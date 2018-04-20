package com.jy.jgcjj.bean.base;

import java.io.Serializable;

public class ProdctSeletBean implements Serializable {

    private String name;
    private int url;

    public ProdctSeletBean(String name, int url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }
}
