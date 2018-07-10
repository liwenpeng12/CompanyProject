package com.lwp.cp.view;

import com.lwp.cp.base.BaseView;
import com.lwp.cp.model.response.LoginResponse;

/**
 * liwenpeng
 * 2018/7/7 16:33
 * CompanyProject
 * Descrobe:
 */
public interface LoginView extends BaseView{
    void LoginIng();
    void loginFail(String s);
    void loginSuccess(LoginResponse loginResponse);
    void startLogin();
    void startRegister();
    void RegisterIng();
    void RegisterFail();
    void RegisterSuccess();
}
