
package com.lalitp.myapplication.Pojo.Login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lalitp.myapplication.Pojo.ServerResponse_Item.Status;


public class LoginData {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("result")
    @Expose
    private LoginResult result;

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

    /**
     * 
     * @return
     *     The result
     */
    public LoginResult getResult() {
        return result;
    }

    /**
     * 
     * @param result
     *     The result
     */
    public void setResult(LoginResult result) {
        this.result = result;
    }

}
