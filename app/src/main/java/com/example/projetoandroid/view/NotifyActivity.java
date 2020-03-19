package com.example.projetoandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.projetoandroid.R;
import com.example.projetoandroid.constant.UserInfosContants;
import com.example.projetoandroid.data.SecurityPreferences;

public class NotifyActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkBoxNotificationViaPhone = findViewById(R.id.checkbox_notification_via_phone);
        this.mViewHolder.checkBoxNotificationViaEmail = findViewById(R.id.checkbox_notification_via_email);
        this.mViewHolder.buttonYourInfos = findViewById(R.id.button_your_infos);

        this.mViewHolder.checkBoxNotificationViaPhone.setOnClickListener(this);
        this.mViewHolder.checkBoxNotificationViaEmail.setOnClickListener(this);
        this.mViewHolder.buttonYourInfos.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.setIfChecked();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_your_infos) {
            Intent intent = new Intent(this, InfosActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.checkbox_notification_via_email) {
            if (this.mViewHolder.checkBoxNotificationViaEmail.isChecked()) {
                this.mSecurityPreferences.storeString(UserInfosContants.notificateEmailKey, "true");
            } else {
                this.mSecurityPreferences.storeString(UserInfosContants.notificateEmailKey, "false");
            }
        } else if (v.getId() == R.id.checkbox_notification_via_phone) {
            if (this.mViewHolder.checkBoxNotificationViaPhone.isChecked()) {
                this.mSecurityPreferences.storeString(UserInfosContants.notificatePhoneKey, "true");
            } else {
                this.mSecurityPreferences.storeString(UserInfosContants.notificatePhoneKey, "false");
            }
        }
    }

    private void setIfChecked() {
        if (this.mSecurityPreferences.getStoredString(UserInfosContants.notificatePhoneKey).equals("true")) {
            this.mViewHolder.checkBoxNotificationViaPhone.setChecked(true);
        } else {
            this.mViewHolder.checkBoxNotificationViaPhone.setChecked(false);
        }

        if (this.mSecurityPreferences.getStoredString(UserInfosContants.notificateEmailKey).equals("true")) {
            this.mViewHolder.checkBoxNotificationViaEmail.setChecked(true);
        } else {
            this.mViewHolder.checkBoxNotificationViaEmail.setChecked(false);
        }
    }

    public static class ViewHolder {
        CheckBox checkBoxNotificationViaPhone;
        CheckBox checkBoxNotificationViaEmail;
        Button buttonYourInfos;
    }
}
