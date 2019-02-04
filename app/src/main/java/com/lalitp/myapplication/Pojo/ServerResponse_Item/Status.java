
package com.lalitp.myapplication.Pojo.ServerResponse_Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Status {

    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * 
     * @return
     *     The response
     */
    public String getResponse() {
        return response;
    }

    /**
     * 
     * @param response
     *     The response
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
