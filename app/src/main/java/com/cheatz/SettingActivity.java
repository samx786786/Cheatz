package com.cheatz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    Button reset;
    TextView title;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT1 = "text";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";
    public static final String TEXT4 = "text4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        reset=findViewById(R.id.button4);
        title=findViewById(R.id.textView13);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String branchname = sharedPreferences.getString(TEXT1, "");
        String subbranchname = sharedPreferences.getString(TEXT2, "");
        String year = sharedPreferences.getString(TEXT3, "");
        String sem = sharedPreferences.getString(TEXT4, "");
        title.setText(branchname+"\n"+subbranchname+"\n"+sem+"\n"+year);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SharedPreferences preferences =getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}