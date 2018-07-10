package com.lwp.cp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lwp.cp.R;
import com.lwp.cp.base.BaseActivity;
import com.lwp.cp.presenter.RegisterPresenter;
import com.lwp.cp.util.ToastUtils;
import com.lwp.cp.view.RegisterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * liwenpeng
 * 2018/7/7 17:49
 * CompanyProject
 * Descrobe:
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_ysername)
    EditText etYsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }


    @OnClick({R.id.iv_back, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_sure:
                if (!TextUtils.isEmpty(etPassword.getText().toString()) && !TextUtils.isEmpty(etYsername.getText().toString()) &&
                        etPassword.getText().toString().equals(etConfirmPassword.getText().toString())){
                    getPresenter().execRegister(etYsername.getText().toString(),etPassword.getText().toString(),etConfirmPassword.getText().toString());
                }
                    break;
        }
    }


}
