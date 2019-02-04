
package com.lalitp.myapplication.Pojo.ProductList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lalitp.myapplication.Pojo.ServerResponse_Item.Status;

public class ProductListData {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("data")
    @Expose
    private Data data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
