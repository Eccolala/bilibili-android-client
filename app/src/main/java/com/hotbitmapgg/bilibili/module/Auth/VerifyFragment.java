package com.hotbitmapgg.bilibili.module.Auth;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.hotbitmapgg.bilibili.network.auxiliary.ApiConstants;
import com.hotbitmapgg.ohmybilibili.R;

/**
 * 获取验证码 dialog Fragment
 */
public class VerifyFragment extends DialogFragment {

    @BindView(R.id.pg_loading)
    ProgressBar loading;

    @BindView(R.id.iv_verify)
    ImageView codeView;

    private Context context;

    private int index = 1;


    public VerifyFragment() {
        // Required empty public constructor
    }


    @Override public void onStart() {
        super.onStart();
        final AlertDialog dialog = (AlertDialog) getDialog();
        showProgress(true);
        loadVerifyPic(index);
        final Button confirm = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        confirm.setOnClickListener(v -> confirmCode());
    }


    @OnClick(R.id.btn_next)
    void nextPic() {
        index++;
        if (index > 15) {
            index = 1;
        }
        showProgress(true);
        loadVerifyPic(index);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_verify, container, false);
    }


    @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        context = getContext();
        return new AlertDialog.Builder(context)
            .setView(onCreateDialogContentView(savedInstanceState))
            .setTitle("输入验证码")
            .setPositiveButton("验证", null)
            .setNegativeButton(android.R.string.cancel, null)
            .create();

    }


    @SuppressLint("InflateParams")
    private View onCreateDialogContentView(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.fragment_verify, null);
        ButterKnife.bind(this, root);
        return root;
    }


    /**
     * 确认验证码
     */
    private void confirmCode() {
    }


    /**
     * 拼接图片地址
     */
    public void loadVerifyPic(int index) {
        String pic = ApiConstants.VERIFY_BASE_URL + index + ApiConstants.JPG;
        loadImg(pic);
    }


    /**
     * 具体加载图片逻辑
     */
    private void loadImg(String pic) {
        Glide.with(context)
            .load(pic)
            .centerCrop()
            .override(80,40)
            .into(codeView);
        showProgress(false);
    }


    /**
     * 网络不好时显示 text
     */
    private void poorNetWork() {

    }


    public static VerifyFragment newInstance() {
        return new VerifyFragment();
    }


    private void showProgress(boolean show) {
        if (show) {
            loading.setVisibility(View.VISIBLE);
        } else {
            loading.setVisibility(View.GONE);
        }
    }
}
