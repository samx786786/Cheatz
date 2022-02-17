package com.cheatz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;

public class infoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setOrientation(infoActivity.this);

    }

    private void setOrientation(infoActivity infoActivity) {
        if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.O)
        {
            infoActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }


}