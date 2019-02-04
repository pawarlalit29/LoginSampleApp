package com.lalitp.myapplication.Utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TextInputLayout;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.Toast;

import com.lalitp.myapplication.LoginSampleApp;
import com.lalitp.myapplication.UserInterface.Widget.MaterialProgress.TransparentProgressDialog;
import com.mobsandgeeks.saripaar.ValidationError;

import java.util.List;

public class Common_Utils {

    public static Toast toast;
    public static TransparentProgressDialog progressDialog;
    public static boolean isNotNullOrEmpty(String str) {

        if (str != null
                && !str.equalsIgnoreCase("null")
                && !str.isEmpty()
                //&& !str.contains("null")
                && !str.equalsIgnoreCase("")
                && !str.equalsIgnoreCase(" ")) {

            return true;
        } else {
            return false;
        }
    }

    public static void showToast(String data) {

        try {
            if (!Common_Utils.isNotNullOrEmpty(data))
                return;

            if (toast == null) {
                toast = Toast.makeText(LoginSampleApp.getAppContext(), data, Toast.LENGTH_SHORT);
            }

            if (!toast.getView().isShown()) {
                toast.setText(data);
                toast.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showError(Activity activity, List<ValidationError> errors) {
        boolean isFirst = false;
        EditText editText;
        for (ValidationError error : errors) {
            View view = error.getView();
            ViewParent viewParent = error.getView().getParent().getParent();
            String message = error.getCollatedErrorMessage(LoginSampleApp.getAppContext());
            if (!isFirst) {
                if (view instanceof EditText) {
                    editText = ((EditText) view);
                    setSelection(editText);
                    editText.requestFocus();
                    isFirst = true;
                }
            }

            if (viewParent instanceof TextInputLayout) {
                ((TextInputLayout) viewParent).setError(message);
                ((TextInputLayout) viewParent).setErrorEnabled(true);

            } else {
                Common_Utils.showToast(message);
            }
        }
    }

    public static void setSelection(EditText editText) {
        int length = editText.getText().length();
        editText.setSelection(length);
    }

    public static void showProgress(Context context) {
        //System.out.println(("show");
        if (progressDialog != null && progressDialog.isShowing())
            hideProgress();

        progressDialog = new TransparentProgressDialog(context);
        progressDialog.show();
    }

    public static void hideProgress() {
        //System.out.println(("hide");
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isNetworkAvailable() {

        ConnectivityManager cm = (ConnectivityManager) LoginSampleApp.getAppContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public static String getTimeStamp(long timestamp) {
        try {

            CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                    timestamp,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);

            return timeAgo.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
