package com.cheatz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class Zoomimage extends AppCompatActivity {

    ImageView imageView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomimage);
        imageView=findViewById(R.id.zoom);
        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {
            String Imageurl = bundle1.get("imageurl").toString();
            Picasso.get().load(Imageurl).into(imageView);
        }
    }
}