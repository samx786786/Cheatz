package com.cheatz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Questionbank extends AppCompatActivity {


    TextView title;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT1 = "text";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";
    public static final String TEXT4 = "text4";
    Button studymaterial,textbook,modelpaper,questionpaper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionbank);
        title=findViewById(R.id.textView14);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String branchname = sharedPreferences.getString(TEXT1, "");
        String subbranchname = sharedPreferences.getString(TEXT2, "");
        String year = sharedPreferences.getString(TEXT3, "");
        String sem = sharedPreferences.getString(TEXT4, "");
        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null)
        {

            String subjectname = bundle1.get("subjectname").toString();
            title.setText(branchname+"\n"+subbranchname+"\n"+sem+"\n"+year+"\n"+subjectname);
            textbook=findViewById(R.id.questionbank);
            textbook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });
            modelpaper=findViewById(R.id.modelpaper);
            modelpaper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
            questionpaper=findViewById(R.id.questionpaper);
            questionpaper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
            studymaterial=findViewById(R.id.textbook);
            studymaterial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });






        }


    }


}