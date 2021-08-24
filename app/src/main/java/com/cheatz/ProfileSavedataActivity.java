package com.cheatz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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


public class ProfileSavedataActivity extends AppCompatActivity {

    ImageView info;
    TextView infotextview;
    ImageButton imageButton;
    private syllabusrecycleradapter notificationsAdapterx;
    private List<syllabusmodel> NotifListx;
    FirebaseFirestore firestore;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT1 = "text";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";
    public static final String TEXT4 = "text4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_savedata);
        firestore = FirebaseFirestore.getInstance();
        info=findViewById(R.id.imageView);
        imageButton=findViewById(R.id.imageButton4);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileSavedataActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
        infotextview=findViewById(R.id.textView);
        infotextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileSavedataActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null)
        {
            String branchname = bundle1.get("branchname").toString();
            String year = bundle1.get("year").toString();
            String subbranch = bundle1.get("subbranch").toString();
            String sem = bundle1.get("sem").toString();
            NotifListx = new ArrayList<>();
            RecyclerView notificationList = findViewById(R.id.selectsemrecycler);
            notificationsAdapterx = new syllabusrecycleradapter(NotifListx);
            notificationList.setHasFixedSize(true);
            notificationList.setDrawingCacheEnabled(true);
            notificationList.setItemAnimator(null);
            notificationList.setNestedScrollingEnabled(false);
            notificationList.setLayoutManager(new LinearLayoutManager(this));
            notificationList.setAdapter(notificationsAdapterx);
            firestore = FirebaseFirestore.getInstance();
            firestore.collection(branchname+subbranch+year+sem+"syllabus").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {
                        syllabusmodel notifications = doc.getDocument().toObject(syllabusmodel.class);
                        NotifListx.add(notifications);
                        notificationsAdapterx.notifyDataSetChanged();
                    }
                }
            });

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(TEXT1,branchname );
                    editor.putString(TEXT2,subbranch );
                    editor.putString(TEXT3,sem);
                    editor.putString(TEXT4,year);
                    editor.apply();
                    Toast.makeText(ProfileSavedataActivity.this, "Student profile saved"+"\n"+branchname+"\n"+year+"\n"+subbranch+"\n"+sem, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ProfileSavedataActivity.this, Homepage.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void loaddatax(String branchname, String subbranch, String year, String sem) {

        Map<String, String> userMap1 = new HashMap<>();
        userMap1.put("syllabus","Fluid Mechanics");
        firestore.collection(branchname+subbranch+sem+year+"Subjects").add(userMap1);

        Map<String, String> userMap2 = new HashMap<>();
        userMap2.put("syllabus","Thermodynamics");
        firestore.collection(branchname+subbranch+sem+year+"Subjects").add(userMap2);

        Map<String, String> userMap3 = new HashMap<>();
        userMap3.put("syllabus","Kinematic of Machine");
        firestore.collection(branchname+subbranch+sem+year+"Subjects").add(userMap3);

        Map<String, String> userMap4 = new HashMap<>();
        userMap4.put("syllabus","Theory of machines");
        firestore.collection(branchname+subbranch+sem+year+"Subjects").add(userMap4);

        Map<String, String> userMap5 = new HashMap<>();
        userMap5.put("syllabus","Cam");
        firestore.collection(branchname+subbranch+sem+year+"Subjects").add(userMap5);

        Map<String, String> userMap6 = new HashMap<>();
        userMap6.put("syllabus","Advance Maths iii");
        firestore.collection(branchname+subbranch+sem+year+"Subjects").add(userMap6);

        Map<String, String> userMap7 = new HashMap<>();
        userMap7.put("syllabus","Time Dilation");
        firestore.collection(branchname+subbranch+sem+year+"Subjects").add(userMap7);

        Map<String, String> userMap8 = new HashMap<>();
        userMap8.put("syllabus","MicroController");
        firestore.collection(branchname+subbranch+sem+year+"Subjects").add(userMap8).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {

                Toast.makeText(ProfileSavedataActivity.this, "Task Completed", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void loaddata(String branchname, String subbranch, String year, String sem) {

        Map<String, String> userMap1 = new HashMap<>();
        userMap1.put("syllabus","Fluid Mechanics");
        firestore.collection(branchname+subbranch+year+sem+"syllabus").add(userMap1);

        Map<String, String> userMap2 = new HashMap<>();
        userMap2.put("syllabus","Thermodynamics");
        firestore.collection(branchname+subbranch+year+sem+"syllabus").add(userMap2);

        Map<String, String> userMap3 = new HashMap<>();
        userMap3.put("syllabus","Kinematic of Machine");
        firestore.collection(branchname+subbranch+year+sem+"syllabus").add(userMap3);

        Map<String, String> userMap4 = new HashMap<>();
        userMap4.put("syllabus","Theory of machines");
        firestore.collection(branchname+subbranch+year+sem+"syllabus").add(userMap4);

        Map<String, String> userMap5 = new HashMap<>();
        userMap5.put("syllabus","Cam");
        firestore.collection(branchname+subbranch+year+sem+"syllabus").add(userMap5);

        Map<String, String> userMap6 = new HashMap<>();
        userMap6.put("syllabus","Advance Maths iii");
        firestore.collection(branchname+subbranch+year+sem+"syllabus").add(userMap6);

        Map<String, String> userMap7 = new HashMap<>();
        userMap7.put("syllabus","Time Dilation");
        firestore.collection(branchname+subbranch+year+sem+"syllabus").add(userMap7);

        Map<String, String> userMap8 = new HashMap<>();
        userMap8.put("syllabus","MicroController");
        firestore.collection(branchname+subbranch+year+sem+"syllabus").add(userMap8).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {

                Toast.makeText(ProfileSavedataActivity.this, "Task Completed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}