
package com.lalitp.myapplication.Pojo.ServerResponse_Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CommonResponseData {

    @SerializedName("status")
    @Expose
    private Status status;

    /**
     * 
     * @return
     *     The status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

}
