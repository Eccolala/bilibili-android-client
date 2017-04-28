package com.hotbitmapgg.bilibili.module.Auth;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.hotbitmapgg.bilibili.base.RxBaseActivity;
import com.hotbitmapgg.ohmybilibili.R;

public class VerifyActivity extends RxBaseActivity {

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


    @Override public int getLayoutId() {
        return R.layout.activity_verify;
    }


    @Override public void initViews(Bundle savedInstanceState) {
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


    @Override public void initToolBar() {
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        mToolbar.setTitle("验证手机号");
        mToolbar.setNavigationOnClickListener(v -> finish());
    }
}
