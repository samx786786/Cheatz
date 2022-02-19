package com.cheatz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.model.ReviewErrorCode;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SettingActivity extends AppCompatActivity {

    EditText suggestion;
    Button submit,Reset;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT1 = "text";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";
    TextView title,info;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        title=findViewById(R.id.textView);
        Reset=findViewById(R.id.button2);
        info=findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, infoActivity.class);
                startActivity(intent);
            }
        });
        progressBar=findViewById(R.id.progressBar);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String branchname = sharedPreferences.getString(TEXT1, "");
        String subbranchname = sharedPreferences.getString(TEXT2, "");
        String year = sharedPreferences.getString(TEXT3, "");
        title.setText(branchname+" - "+year);
        if (branchname.equals(""))
        {
            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if (subbranchname.equals(""))
        {
            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if (year.equals(""))
        {
            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
            startActivity(intent);
        }

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        suggestion=findViewById(R.id.editTextTextPersonName);
        submit=findViewById(R.id.button3);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = suggestion.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SettingActivity.this,"Please Add suggestions",Toast.LENGTH_LONG).show();
                    return;
                }
                else
                    {
                        progressBar.setVisibility(View.VISIBLE);
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Suggestions");
                        myRef.setValue(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                suggestion.setEnabled(false);
                                progressBar.setVisibility(View.INVISIBLE);
                                suggestion.setText("Thanks for your response, We will gladly look into it!");
                            }
                        });
                    }
            }
        });
    }
}