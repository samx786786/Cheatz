package com.cheatz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RecycleActivity extends AppCompatActivity {

    EditText name,contact,address;
    Button recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        name=findViewById(R.id.editTextTextPersonName2);
        contact=findViewById(R.id.editTextTextPersonName3);
        address=findViewById(R.id.editTextTextPersonName4);
        recycle=findViewById(R.id.button4);
        recycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do the push logic here
            }
        });
    }
}