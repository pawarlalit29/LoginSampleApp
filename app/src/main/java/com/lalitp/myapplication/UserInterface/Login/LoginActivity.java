package com.lalitp.myapplication.UserInterface.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.lalitp.myapplication.Pojo.Login.LoginParam;
import com.lalitp.myapplication.Pojo.ProductList.ProductList;
import com.lalitp.myapplication.R;
import com.lalitp.myapplication.UserInterface.FailureLogs.FailureLogsActivity;
import com.lalitp.myapplication.UserInterface.ProductList.ProductListActivity;
import com.lalitp.myapplication.Utils.AppConstant;
import com.lalitp.myapplication.Utils.Common_Utils;
import com.lalitp.myapplication.Utils.SecurePreferences;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity implements LoginView, Validator.ValidationListener, CompoundButton.OnCheckedChangeListener {

    @NotEmpty(trim = true)
    @BindView(R.id.et_login_username)
    EditText etLoginUsername;
    @NotEmpty(trim = true)
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.til_uname)
    TextInputLayout tilUname;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;

    private Context context;
    private LoginPresenter loginPresenter;
    private Validator validator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        context = this;
        init();
    }

    private void init() {

        etLoginPassword.setTransformationMethod(new PasswordTransformationMethod());

        loginPresenter = new LoginPresenterImpl(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @OnClick(R.id.btn_login)
    public void onLoginClick() {
        validator.validate();
    }

    @OnClick(R.id.btn_logs)
    public void onLogsClick(){
        Intent intent = new Intent(LoginActivity.this, FailureLogsActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        Common_Utils.showProgress(context);
    }

    @Override
    public void navigateToHome(String msg) {
        Common_Utils.hideProgress();
        Common_Utils.showToast(msg);
        Intent intent = new Intent(LoginActivity.this, ProductListActivity.class);
        startActivity(intent);
    }

    @Override
    public void showError(String errMsg) {
        Common_Utils.hideProgress();
        Common_Utils.showToast(errMsg);
    }

    @Override
    public void showInternetError() {
        Common_Utils.showToast(AppConstant.no_internet_connection);
    }


    @Override
    public void onValidationSucceeded() {
        setLoginParams();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        Common_Utils.showError(this, errors);
    }

    private void setLoginParams() {
        String username, password;
        username = etLoginUsername.getText().toString();
        password = etLoginPassword.getText().toString();

        LoginParam loginParam = new LoginParam();
        loginParam.setuName(username);
        loginParam.setPassword(password);
        loginPresenter.validateCredentials(loginParam);

    }

    /**
     * Method to show and hide password
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // checkbox status is changed from uncheck to checked.
        if (isChecked) {
            // hide password
            // checkShowPass.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_hide_password_24dp));
            etLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            Common_Utils.setSelection(etLoginPassword);
        } else {
            // show password
            // checkShowPass.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_show_password_24dp));
            etLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            Common_Utils.setSelection(etLoginPassword);
        }
    }


}
