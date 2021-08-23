package com.cheatz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;


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
}