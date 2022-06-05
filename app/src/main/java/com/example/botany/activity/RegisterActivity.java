package com.example.botany.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;

import com.example.botany.R;
import com.example.botany.utils.ToastUtils;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends MyBaseActivity implements View.OnClickListener {
    private TextInputLayout user, pwd,name,grade,phone;
    private RadioButton nanRb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user = findViewById(R.id.user);
        pwd = findViewById(R.id.psd);
        name = findViewById(R.id.name);
        grade = findViewById(R.id.grade);
        phone = findViewById(R.id.phone);

        nanRb = findViewById(R.id.btnMan);
        findViewById(R.id.res).setOnClickListener(this);
    }

    private String getStr(TextInputLayout textInputLayout){
        if(null == textInputLayout || null == textInputLayout.getEditText()) {
            return "";
        }
        return textInputLayout.getEditText().getText().toString().trim();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.res:
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        ToastUtils.showShortToast(getApplicationContext(), "가입 성공");
                        finish();
                break;
        }
    }

}
