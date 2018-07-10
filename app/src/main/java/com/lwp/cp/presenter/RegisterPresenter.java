package com.lwp.cp.presenter;

import android.content.Context;

import com.lwp.cp.base.BaseBean;
import com.lwp.cp.base.BasePresenter;
import com.lwp.cp.http.Api;
import com.lwp.cp.http.ApiTransformer;
import com.lwp.cp.http.RxException;
import com.lwp.cp.model.response.RegisterResponse;
import com.lwp.cp.util.Constant;
import com.lwp.cp.util.ToastUtils;
import com.lwp.cp.view.RegisterView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * liwenpeng
 * 2018/7/7 17:50
 * CompanyProject
 * Descrobe:
 */
public class RegisterPresenter extends BasePresenter<RegisterView> {
    @Override
    protected void initData() {

    }

   public void execRegister(String username,String password,String confirmPassword){
       ToastUtils.showShort("execRegister");
       HashMap<String, String> map = new HashMap<>();
       map.put(Constant.USERNAME,username);
       map.put(Constant.PASSWORD,password);
       map.put("repassword",confirmPassword);
       Disposable subscribe = Api.getApi().exetRegister(map).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<RegisterResponse>() {
           @Override
           public void accept(RegisterResponse registerResponse) throws Exception {
               ToastUtils.showShort("注册成功");
           }

       },new Consumer<Throwable>() {
           @Override
           public void accept(Throwable throwable) throws Exception {
               ToastUtils.showShort("throwable"+throwable.toString());
           }
       });

   }
}
