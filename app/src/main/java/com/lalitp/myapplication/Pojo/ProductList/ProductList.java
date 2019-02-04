
package com.lalitp.myapplication.Pojo.ProductList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductList {

    @SerializedName("pid")
    @Expose
    private String pid;
    @SerializedName("p_title")
    @Expose
    private String pTitle;
    @SerializedName("p_desc")
    @Expose
    private String pDesc;
    @SerializedName("p_cat")
    @Expose
    private Integer pCat;
    @SerializedName("p_imageurl")
    @Expose
    private String pImageurl;
    @SerializedName("p_price")
    @Expose
    private Double pPrice;
    @SerializedName("p_isnew")
    @Expose
    private Boolean pIsnew;
    @SerializedName("p_likes")
    @Expose
    private Integer pLikes;
    @SerializedName("p_islike")
    @Expose
    private Boolean pIslike;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPTitle() {
        return pTitle;
    }

    public void setPTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getPDesc() {
        return pDesc;
    }

    public void setPDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public Integer getPCat() {
        return pCat;
    }

    public void setPCat(Integer pCat) {
        this.pCat = pCat;
    }

    public String getPImageurl() {
        return pImageurl;
    }

    public void setPImageurl(String pImageurl) {
        this.pImageurl = pImageurl;
    }

    public Double getPPrice() {
        return pPrice;
    }

    public void setPPrice(Double pPrice) {
        this.pPrice = pPrice;
    }

    public Boolean getPIsnew() {
        return pIsnew;
    }

    public void setPIsnew(Boolean pIsnew) {
        this.pIsnew = pIsnew;
    }

    public Integer getPLikes() {
        return pLikes;
    }

    public void setPLikes(Integer pLikes) {
        this.pLikes = pLikes;
    }

    public Boolean getPIslike() {
        return pIslike;
    }

    public void setPIslike(Boolean pIslike) {
        this.pIslike = pIslike;
    }

}
