package com.hotbitmapgg.bilibili.module.common.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.hotbitmapgg.bilibili.base.RxBaseActivity;
import com.hotbitmapgg.bilibili.module.common.MainActivity;
import com.hotbitmapgg.bilibili.utils.CommonUtil;
import com.hotbitmapgg.bilibili.utils.ConstantUtil;
import com.hotbitmapgg.bilibili.utils.PreferenceUtil;
import com.hotbitmapgg.bilibili.utils.ToastUtil;
import com.hotbitmapgg.ohmybilibili.R;

/**
 * Created by hcc on 16/8/7 14:12
 * 100332338@qq.com
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

  @BindView(R.id.et_username)
  EditText et_username;


  @Override
  public int getLayoutId() {

    return R.layout.activity_sign;
  }


  @Override
  public void initViews(Bundle savedInstanceState) {

    et_username.setOnFocusChangeListener((v, hasFocus) -> {

      if (hasFocus && et_username.getText().length() > 0) {
        mDeleteUserName.setVisibility(View.VISIBLE);
      } else {
        mDeleteUserName.setVisibility(View.GONE);
      }

      mLeftLogo.setImageResource(R.drawable.ic_22);
      mRightLogo.setImageResource(R.drawable.ic_33);
    });


    et_username.addTextChangedListener(new TextWatcher() {

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
      ToastUtil.ShortToast("当前网络不可用,请检查网络设置");
      return;
    }
    sign();
  }


  @OnClick(R.id.delete_username)
  void delete() {
    // 清空用户名以及密码
    et_username.setText("");
    mDeleteUserName.setVisibility(View.GONE);
    et_username.setFocusable(true);
    et_username.setFocusableInTouchMode(true);
    et_username.requestFocus();
  }


  private void sign() {

    String name = et_username.getText().toString();

    if (TextUtils.isEmpty(name)) {
      ToastUtil.ShortToast("用户名不能为空");
      return;
    }

    PreferenceUtil.putBoolean(ConstantUtil.KEY, true);
    startActivity(new Intent(SignActivity.this, MainActivity.class));
    finish();
  }
}
