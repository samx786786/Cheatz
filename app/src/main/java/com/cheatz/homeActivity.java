package com.cheatz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class homeActivity extends AppCompatActivity {

    TextView setting;
    TextView title;
    ImageView home;
    FirebaseFirestore firestore;
    private SubjectRecyclerAdapter notificationsAdapterx;
    private List<MainPageModel> NotifListx;
    ProgressBar spiner;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT1 = "text";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        title=findViewById(R.id.textViewx);
        spiner=findViewById(R.id.progressBar2);
        firestore = FirebaseFirestore.getInstance();
        setting=findViewById(R.id.textView);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {
            String branchname = bundle1.get("branch").toString();
            String subbranchname = bundle1.get("subbranch").toString();
            String sem = bundle1.get("sem").toString();
            spiner.setVisibility(View.VISIBLE);
            title.setText(branchname);
            NotifListx = new ArrayList<>();
            RecyclerView notificationList = findViewById(R.id.dynamicrecycler);
            notificationsAdapterx = new SubjectRecyclerAdapter(NotifListx);
            notificationList.setHasFixedSize(true);
            notificationList.setDrawingCacheEnabled(true);
            notificationList.setItemAnimator(null);
            notificationList.setNestedScrollingEnabled(false);
            notificationList.setLayoutManager(new LinearLayoutManager(this));
            notificationList.setAdapter(notificationsAdapterx);
            firestore.collection(branchname+subbranchname+sem+"Subjects").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    spiner.setVisibility(View.INVISIBLE);
                    for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {
                        MainPageModel notifications = doc.getDocument().toObject(MainPageModel.class);
                        NotifListx.add(notifications);
                        notificationsAdapterx.notifyDataSetChanged();
                    }
                }
            });

            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(TEXT1,branchname);
            editor.putString(TEXT2,subbranchname);
            editor.putString(TEXT3,sem);
            editor.apply();
        }
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String branchname = sharedPreferences.getString(TEXT1, "");
        String subbranchname = sharedPreferences.getString(TEXT2, "");
        String sem = sharedPreferences.getString(TEXT3, "");
        if (branchname.equals(""))
        {
            Intent intent = new Intent(homeActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if (subbranchname.equals(""))
        {
            Intent intent = new Intent(homeActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if (sem.equals(""))
        {
            Intent intent = new Intent(homeActivity.this, MainActivity.class);
            startActivity(intent);
        }
        NotifListx = new ArrayList<>();
        spiner.setVisibility(View.VISIBLE);
        RecyclerView notificationList = findViewById(R.id.dynamicrecycler);
        title.setText(branchname);
        notificationsAdapterx = new SubjectRecyclerAdapter(NotifListx);
        notificationList.setHasFixedSize(true);
        notificationList.setDrawingCacheEnabled(true);
        notificationList.setItemAnimator(null);
        notificationList.setNestedScrollingEnabled(false);
        notificationList.setLayoutManager(new LinearLayoutManager(this));
        notificationList.setAdapter(notificationsAdapterx);
        firestore.collection(branchname+subbranchname+sem+"Subjects").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                spiner.setVisibility(View.INVISIBLE);
                for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {
                    MainPageModel notifications = doc.getDocument().toObject(MainPageModel.class);
                    NotifListx.add(notifications);
                    notificationsAdapterx.notifyDataSetChanged();
                }
            }
        });
        home=findViewById(R.id.textView21);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(homeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



        /*

        Map<String, String> userMap = new HashMap<>();
        userMap.put("pdfurl","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/Applied%20Superconductivity%20Handbook%20on%20Devices%20and%20Applications%20By%20Paul%20Seidel.pdf?alt=media&token=da2e6920-315d-4efd-aada-2da4a3b8a8f9");
        userMap.put("subjectname","Test file 1");
        userMap.put("years","2022");
        userMap.put("units","-");
        userMap.put("qrcode","AZE643");
        userMap.put("notesurl","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/Handbook%20of%20Transparent%20Conductors%20by%20David%20S.%20S.%20Ginley.pdf?alt=media&token=59935574-37ed-4b7a-ba29-6e69d38cf0e2");
        userMap.put("sysnopsisurl","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/Reduced%20DC%20link%20Capacitance%20AC%20Motor%20Drives%20by%20Gaolin%20Wang%2C%20Nannan%20Zhao%2C%20Guoqiang%20Zhang%20and%20Dianguo%20Xu.pdf?alt=media&token=6d51cb18-01be-44c3-b12b-4f241302f1da");


        firestore.collection(branchname+subbranchname+sem+"Subjects").add(userMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(homeActivity.this,"hashmap updated",Toast.LENGTH_LONG).show();
            }
        });
        
         */






    }


}