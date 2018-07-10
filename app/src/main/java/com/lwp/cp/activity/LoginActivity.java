package com.lwp.cp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lwp.cp.R;
import com.lwp.cp.base.BaseActivity;
import com.lwp.cp.model.response.LoginResponse;
import com.lwp.cp.presenter.LoginPresenter;
import com.lwp.cp.util.Constant;
import com.lwp.cp.util.SpUtil;
import com.lwp.cp.util.ToastUtils;
import com.lwp.cp.view.LoginView;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.login_logp)
    CircleImageView loginLogp;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_commit)
    Button btCommit;
    @BindView(R.id.tv_enter_register)
    TextView tvEnterRegister;
    private ProgressDialog mypDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void LoginIng() {
        mypDialog = new ProgressDialog(this);
        //实例化
        mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //设置进度条风格，风格为圆形，旋转的
        mypDialog.setMessage("正在登陆，请稍后...");
        //设置ProgressDialog 提示信息
        mypDialog.setIndeterminate(false);
        //设置ProgressDialog 的进度条是否不明确
        mypDialog.setCancelable(true);
        //设置ProgressDialog 是否可以按退回按键取消
        mypDialog.show();
        //让ProgressDialog显示
        ToastUtils.showShort("正在登陆...");

    }

    @Override
    public void loginFail(String s) {
        ToastUtils.showShort("登陆失败："+s);
    }

    @Override
    public void loginSuccess(LoginResponse loginResponse) {
        saveData(loginResponse);
        enterMainActivity();
        finish();
    }

    private void saveData(LoginResponse loginResponse) {
        String username = loginResponse.getUsername();
        SpUtil.setString(this, Constant.USERNAME,username);
        String password = loginResponse.getPassword();
        SpUtil.setString(this,Constant.PASSWORD,password);
    }

    private void enterMainActivity() {
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public void startLogin() {

    }

    @Override
    public void startRegister() {

    }

    @Override
    public void RegisterIng() {

    }

    @Override
    public void RegisterFail() {

    }

    @Override
    public void RegisterSuccess() {

    }




    @OnClick({R.id.tv_enter_register, R.id.bt_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_enter_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.bt_commit:
                        if (!TextUtils.isEmpty(etUsername.getText().toString()) && !TextUtils.isEmpty(etPassword.getText().toString())) {
            getPresenter().execLogin(etUsername.getText().toString(),etPassword.getText().toString());
        } else {
                            ToastUtils.showShort("请检查是否输入完整");
        }
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mypDialog.dismiss();
    }
}
