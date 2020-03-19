package com.example.projetoandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetoandroid.R;
import com.example.projetoandroid.constant.UserInfosContants;
import com.example.projetoandroid.data.SecurityPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.editName = findViewById(R.id.edit_name);
        this.mViewHolder.editPhone = findViewById(R.id.edit_phone);
        this.mViewHolder.editEmail = findViewById(R.id.edit_email);
        this.mViewHolder.buttonSend = findViewById(R.id.button_send);

        this.mViewHolder.buttonSend.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.clearDatas();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_send) {
            this.mSecurityPreferences.storeString(UserInfosContants.nameKey, this.mViewHolder.editName.getText().toString());
            this.mSecurityPreferences.storeString(UserInfosContants.phoneKey, this.mViewHolder.editPhone.getText().toString());
            this.mSecurityPreferences.storeString(UserInfosContants.emailKey, this.mViewHolder.editEmail.getText().toString());

            Intent intent = new Intent(this, InfosActivity.class);
            startActivity(intent);
        }
    }

    private void clearDatas() {
        this.mSecurityPreferences.storeString(UserInfosContants.notificatePhoneKey, "");
        this.mSecurityPreferences.storeString(UserInfosContants.notificateEmailKey, "");
    }

    private static class ViewHolder {
        EditText editName;
        EditText editPhone;
        EditText editEmail;
        Button buttonSend;
    }
}
