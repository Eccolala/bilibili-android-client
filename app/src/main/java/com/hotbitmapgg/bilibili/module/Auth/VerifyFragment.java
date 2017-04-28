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
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.hotbitmapgg.ohmybilibili.R;

/**
 * 获取验证码 dialog Fragment
 */
public class VerifyFragment extends DialogFragment {

    @BindView(R.id.pg_loading)
    ProgressBar loading;

    private Context context;


    public VerifyFragment() {
        // Required empty public constructor
    }


    @Override public void onStart() {
        super.onStart();
        final AlertDialog dialog = (AlertDialog) getDialog();
        final Button confirm = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        confirm.setOnClickListener(v -> confirmCode());
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
            .setPositiveButton(android.R.string.yes, null)
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
     *
     */
    private void confirmCode(){

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
