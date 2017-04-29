package com.hotbitmapgg.bilibili.module.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.hotbitmapgg.bilibili.base.RxBaseActivity;
import com.hotbitmapgg.bilibili.utils.CommonUtil;
import com.hotbitmapgg.bilibili.utils.LogUtil;
import com.hotbitmapgg.bilibili.utils.StringUtil;
import com.hotbitmapgg.ohmybilibili.R;

/**
 * Designed by hcc on 16/8/7 14:12

 * <p/>
 * 登录界面
 */
public class SignActivity extends RxBaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.iv_icon_left)
    ImageView mLeftLogo;

    @BindView(R.id.iv_icon_right)
    ImageView mRightLogo;

    @BindView(R.id.delete_username)
    ImageView mDeleteUserName;

    @BindView(R.id.et_phone)
    EditText et_phone;

    @BindView(R.id.txt_exception)
    TextView tv_exception;


    @Override
    public int getLayoutId() {

        return R.layout.activity_sign;
    }


    @Override
    public void initViews(Bundle savedInstanceState) {

        et_phone.setOnFocusChangeListener((v, hasFocus) -> {

            if (hasFocus && et_phone.getText().length() > 0) {
                mDeleteUserName.setVisibility(View.VISIBLE);
            } else {
                mDeleteUserName.setVisibility(View.GONE);
            }

            mLeftLogo.setImageResource(R.drawable.ic_22);
            mRightLogo.setImageResource(R.drawable.ic_33);
        });

        et_phone.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 如果用户名清空了 清空密码 清空记住密码选项
                if (s.length() > 0) {
                    // 如果用户名有内容时候 显示删除按钮
                    mDeleteUserName.setVisibility(View.VISIBLE);
                } else {
                    // 如果用户名有内容时候 显示删除按钮
                    mDeleteUserName.setVisibility(View.GONE);
                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}


            @Override
            public void afterTextChanged(Editable s) {}
        });
    }


    @Override
    public void initToolBar() {

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        mToolbar.setTitle("注册账号");
        mToolbar.setNavigationOnClickListener(v -> finish());
    }


    @OnClick(R.id.btn_sign)
    void startSign() {

        boolean isNetConnected = CommonUtil.isNetworkAvailable(this);
        if (!isNetConnected) {
            tv_exception.setVisibility(View.VISIBLE);
            tv_exception.setText("当前网络不可用");
            return;
        }

        intentSign();
    }


    /**
     * 检查是否有多次获取验证码的行为
     */
    private void intentSign() {
        if (robot()){
            VerifyFragment.newInstance().show(getSupportFragmentManager(),"VerifyCode");
            return;
        }
        attemptSign();
    }





    @OnClick(R.id.delete_username)
    void delete() {
        // 清空用户名以及密码
        et_phone.setText("");
        mDeleteUserName.setVisibility(View.GONE);
        et_phone.setFocusable(true);
        et_phone.setFocusableInTouchMode(true);
        et_phone.requestFocus();
        et_phone.setError(null);
    }


    private void attemptSign() {

        String phone = et_phone.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            et_phone.setError("手机号不能为空");
            YoYo.with(Techniques.Shake).duration(700).repeat(1).playOn(et_phone);
            return;
        }
        if (!StringUtil.isLengthVaild(phone)) {
            YoYo.with(Techniques.Shake).duration(700).repeat(1).playOn(et_phone);
            et_phone.setError("手机号应为11位");
            return;
        }
        if (!StringUtil.isMobileNumberValid(phone)) {
            et_phone.setError("手机号不符合大陆规范");
            YoYo.with(Techniques.Shake).duration(700).repeat(1).playOn(et_phone);
            return;
        }

        //请求短信验证码
        requestSMSCode(phone);

    }


    private void requestSMSCode(String phone) {
        AVOSCloud.requestSMSCodeInBackground(phone, new RequestMobileCodeCallback() {
            @Override
            public void done(AVException e) {
                if (e != null){
                    // 发送失败可以查看 e 里面提供的信息
                    LogUtil.e("获取验证码失败", String.valueOf(e));
                    return;
                }

                startActivity(new Intent(SignActivity.this,VerifyActivity.class));
            }
        });
    }


    /**
     * 机器人操作: 如果 5 分钟内请求接口超过三次，显示
     * @return
     */
    private long lastTime;

    private boolean robot() {

        return true;
    }
}
