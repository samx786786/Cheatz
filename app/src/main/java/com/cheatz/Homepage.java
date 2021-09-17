package com.cheatz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Homepage extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT1 = "text";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";
    public static final String TEXT4 = "text4";
    private HomeRecyclerAdapter notificationsAdapterx;
    private List<Homemodel> NotifListx;
    FirebaseFirestore firestore;
    ProgressBar progressBar;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        progressBar=findViewById(R.id.progressBar2);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String branchname = sharedPreferences.getString(TEXT1, "");
        String subbranchname = sharedPreferences.getString(TEXT2, "");
        String year = sharedPreferences.getString(TEXT3, "");
        String sem = sharedPreferences.getString(TEXT4, "");
        imageView=findViewById(R.id.imageView6);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        if (branchname.equals(""))
        {
            Intent intent = new Intent(Homepage.this, MainActivity.class);
            startActivity(intent);
        }

        if (subbranchname.equals(""))
        {
            Intent intent = new Intent(Homepage.this, MainActivity.class);
            startActivity(intent);
        }

        if (year.equals(""))
        {
            Intent intent = new Intent(Homepage.this, MainActivity.class);
            startActivity(intent);
        }

        if (sem.equals(""))
        {
            Intent intent = new Intent(Homepage.this, MainActivity.class);
            startActivity(intent);
        }
        firestore = FirebaseFirestore.getInstance();
        progressBar.setVisibility(View.VISIBLE);
        NotifListx = new ArrayList<>();
        RecyclerView notificationList = findViewById(R.id.homepagerecyler);
        notificationsAdapterx = new HomeRecyclerAdapter(NotifListx);
        notificationList.setDrawingCacheEnabled(true);
        notificationList.setItemAnimator(null);
        notificationList.setNestedScrollingEnabled(false);
        notificationList.setLayoutManager(new LinearLayoutManager(this));
        notificationList.setAdapter(notificationsAdapterx);
       // uploaddata(branchname,subbranchname,year,sem);
        firestore.collection(branchname+subbranchname+year+sem+"subjects").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                progressBar.setVisibility(View.INVISIBLE);
                for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {
                    Homemodel notifications = doc.getDocument().toObject(Homemodel.class);
                    NotifListx.add(notifications);
                    notificationsAdapterx.notifyDataSetChanged();
                }
            }
        });
    }

    private void uploaddata(String branchname, String subbranchname, String year, String sem) {

        Map<String, String> userMap1 = new HashMap<>();
        userMap1.put("subjectname","Fluid Mechanics");
        userMap1.put("backgroundurl","https://images.unsplash.com/photo-1616712566006-3073e04f2ce9?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8Zmx1aWQlMjBkeW5hbWljc3xlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80");
        firestore.collection(branchname+subbranchname+year+sem+"subjects").add(userMap1);

        Map<String, String> userMap2 = new HashMap<>();
        userMap2.put("subjectname","Thermodynamics");
        userMap2.put("backgroundurl","https://wallpapercave.com/wp/wp8264473.jpg");
        firestore.collection(branchname+subbranchname+year+sem+"subjects").add(userMap2);

        Map<String, String> userMap3 = new HashMap<>();
        userMap3.put("subjectname","Kinematic of Machine");
        userMap3.put("backgroundurl","https://i.pinimg.com/originals/09/78/3c/09783cd7c0a11c5887a0b629cba1e737.png");
        firestore.collection(branchname+subbranchname+year+sem+"subjects").add(userMap3);

        Map<String, String> userMap4 = new HashMap<>();
        userMap4.put("subjectname","Theory of machines");
        userMap4.put("backgroundurl","https://embed-fastly.wistia.com/deliveries/4b9df36e53c862999c555f9a4e512d660d1c9363.webp?image_crop_resized=1280x720");
        firestore.collection(branchname+subbranchname+year+sem+"subjects").add(userMap4);

        Map<String, String> userMap5 = new HashMap<>();
        userMap5.put("subjectname","Cam");
        userMap5.put("backgroundurl","https://www.solidcadengineers.com/images/cad-expert.jpg");
        firestore.collection(branchname+subbranchname+year+sem+"subjects").add(userMap5);

        Map<String, String> userMap6 = new HashMap<>();
        userMap6.put("subjectname","Advance Maths iii");
        userMap6.put("backgroundurl","https://c4.wallpaperflare.com/wallpaper/842/454/516/science-fiction-mathematics-physics-wallpaper-preview.jpg");
        firestore.collection(branchname+subbranchname+year+sem+"subjects").add(userMap6);

        Map<String, String> userMap7 = new HashMap<>();
        userMap7.put("subjectname","Time Dilation");
        userMap7.put("backgroundurl","https://free4kwallpapers.com/uploads/originals/2020/11/14/interstellar-space-wallpaper.jpg");
        firestore.collection(branchname+subbranchname+year+sem+"subjects").add(userMap7);

        Map<String, String> userMap8 = new HashMap<>();
        userMap8.put("subjectname","MicroController");
        userMap8.put("backgroundurl","https://c0.wallpaperflare.com/preview/346/319/701/robotic-processor-chip-detail.jpg");
        firestore.collection(branchname+subbranchname+year+sem+"subjects").add(userMap8).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {

                Toast.makeText(Homepage.this, "Task Completed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}