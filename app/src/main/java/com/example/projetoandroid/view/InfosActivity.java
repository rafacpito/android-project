package com.example.projetoandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projetoandroid.R;
import com.example.projetoandroid.constant.UserInfosContants;
import com.example.projetoandroid.data.SecurityPreferences;

public class InfosActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.textWelcome = findViewById(R.id.text_welcome);
        this.mViewHolder.textEmail = findViewById(R.id.text_email);
        this.mViewHolder.textPhone = findViewById(R.id.text_phone);
        this.mViewHolder.textNotificateEmail = findViewById(R.id.text_notificate_email);
        this.mViewHolder.textNotificatePhone = findViewById(R.id.text_notificate_phone);
        this.mViewHolder.buttonList = findViewById(R.id.button_list);

        this.mViewHolder.buttonList.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.setInfos();
    }

    public void setInfos() {
        String welcomeText = String.format("%s %s", "Bem vindo, ", this.mSecurityPreferences.getStoredString(UserInfosContants.nameKey));
        this.mViewHolder.textWelcome.setText(welcomeText);
        String emailText = String.format("%s %s", "Email: ", this.mSecurityPreferences.getStoredString(UserInfosContants.emailKey));
        this.mViewHolder.textEmail.setText(emailText);
        String phoneText = String.format("%s %s", "Telefone: ", this.mSecurityPreferences.getStoredString(UserInfosContants.phoneKey));
        this.mViewHolder.textPhone.setText(phoneText);
        String notificatePhone = this.mSecurityPreferences.getStoredString(UserInfosContants.notificatePhoneKey);
        String notificateEmail = this.mSecurityPreferences.getStoredString(UserInfosContants.notificateEmailKey);

        String notificatePhoneText;
        String notificateEmailText;
        if (notificatePhone.equals("true")) {
            notificatePhoneText = String.format("%s %s", "Receber notificações via telefone: ", "sim");
        } else if (notificatePhone.equals("false")) {
            notificatePhoneText = String.format("%s %s", "Receber notificações via telefone: ", "não");
        } else {
            notificatePhoneText = String.format("%s %s", "Receber notificações via telefone: ", "não informado");
        }
        if (notificateEmail.equals("true")) {
            notificateEmailText = String.format("%s %s", "Receber notificações via e-mail: ", "sim");
        } else if (notificateEmail.equals("false")) {
            notificateEmailText = String.format("%s %s", "Receber notificações via e-mail: ", "não");
        } else {
            notificateEmailText = String.format("%s %s", "Receber notificações via e-mail: ", "não informado");
        }

        this.mViewHolder.textNotificatePhone.setText(notificatePhoneText);
        this.mViewHolder.textNotificateEmail.setText(notificateEmailText);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_list) {
            Intent intent = new Intent(this, NotifyActivity.class);
            startActivity(intent);
        }
    }

    public static class ViewHolder {
        TextView textWelcome;
        TextView textEmail;
        TextView textPhone;
        TextView textNotificateEmail;
        TextView textNotificatePhone;
        Button buttonList;
    }
}
