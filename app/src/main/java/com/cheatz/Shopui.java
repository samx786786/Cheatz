package com.cheatz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

public class Shopui extends AppCompatActivity {

    ImageView cart,Info;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopui);
        cart=findViewById(R.id.imageView3);
        searchView=findViewById(R.id.search_field);
        Info=findViewById(R.id.imageView);
        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Shopui.this,InfoActivity.class);
                startActivity(intent);
            }
        });
    }
}