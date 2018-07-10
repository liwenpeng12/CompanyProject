package com.lwp.cp.presenter;

import com.lwp.cp.base.BasePresenter;
import com.lwp.cp.http.Api;
import com.lwp.cp.http.RxException;
import com.lwp.cp.model.response.LoginResponse;
import com.lwp.cp.util.Constant;
import com.lwp.cp.util.ToastUtils;
import com.lwp.cp.view.LoginView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * liwenpeng
 * 2018/7/7 16:44
 * CompanyProject
 * Descrobe:
 */
public class LoginPresenter extends BasePresenter<LoginView> {



    @Override
    protected void initData() {

    }

    public void execLogin(String username,String passworc){
        HashMap<String, String> map = new HashMap<>();
        map.put(Constant.USERNAME,username);
        map.put(Constant.PASSWORD,passworc);
        Disposable subscribe = Api.getApi().execLogin(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<LoginResponse>() {
            @Override
            public void accept(LoginResponse loginResponse) throws Exception {
                getView().LoginIng();
            }
        }).subscribe(new Consumer<LoginResponse>() {
            @Override
            public void accept(LoginResponse loginResponse) throws Exception {
                getView().loginSuccess(loginResponse);
            }
        },new RxException(getView()));
    }

}
