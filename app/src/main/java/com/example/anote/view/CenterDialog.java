package com.example.anote.view;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.anote.R;

/**
 *
 */
public class CenterDialog extends Dialog {
    private EditText content_tv;
    private TextView button_sure,button_cancel;

    public CenterDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public CenterDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    public CenterDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init(){
        setContentView(R.layout.dialog_center_layout);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置dialog 的长和高
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
        //dialogWindow.setWindowAnimations(R.style.mydialog_anim);
        initView();
    }

    private void initView(){
        content_tv=findViewById(R.id.dialog_center_content);
        button_sure=findViewById(R.id.dialog_center_sure);
        button_cancel=findViewById(R.id.dialog_center_cancel);



        button_sure.setOnClickListener(v -> {
            if (TextUtils.isEmpty(content_tv.getText().toString())){
                Toast.makeText(getContext(),"请填写名字",Toast.LENGTH_LONG).show();
                return;
            }
            this.dismiss();
            if (null!=addListeren){
                addListeren.add(content_tv.getText().toString());
            }
        });
        button_cancel.setOnClickListener(v -> {
            this.dismiss();
        });
    }

    private AddListeren addListeren;

    public void setAddListeren(AddListeren addListeren) {
        this.addListeren = addListeren;
    }

    public interface AddListeren{
        public void add(String name);
    }
}
