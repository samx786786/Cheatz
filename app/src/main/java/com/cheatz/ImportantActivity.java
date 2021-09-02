package com.cheatz;

import android.content.SharedPreferences;
import android.os.Bundle;
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

public class ImportantActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT1 = "text";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";
    public static final String TEXT4 = "text4";
    FirebaseFirestore firestore;
    private SynopsisRecyclerAdapter notificationsAdapterx;
    private List<SynopsisModel> NotifListx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important);
        firestore = FirebaseFirestore.getInstance();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String branchname = sharedPreferences.getString(TEXT1, "");
        String subbranchname = sharedPreferences.getString(TEXT2, "");
        String year = sharedPreferences.getString(TEXT3, "");
        String sem = sharedPreferences.getString(TEXT4, "");
        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null)
        {
            String subjectname = bundle1.get("subjectname").toString();
           // uploaddata(branchname,subbranchname,sem,year,subjectname);
            NotifListx = new ArrayList<>();
            RecyclerView notificationList = findViewById(R.id.imporatantrecycleradapter);
            notificationsAdapterx = new SynopsisRecyclerAdapter(NotifListx);
            notificationList.setHasFixedSize(true);
            notificationList.setLayoutManager(new LinearLayoutManager(this));
            notificationList.setAdapter(notificationsAdapterx);
            firestore = FirebaseFirestore.getInstance();
            firestore.collection(branchname+subbranchname+sem+year+subjectname+"Synopsis").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                    for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {
                        SynopsisModel notifications = doc.getDocument().toObject(SynopsisModel.class);
                        NotifListx.add(notifications);
                        notificationsAdapterx.notifyDataSetChanged();
                    }
                }
            });
        }
    }
    private void uploaddata(String branchname, String subbranchname, String sem, String year, String subjectname) {

        Map<String, String> userMapy1 = new HashMap<>();
        userMapy1.put("url","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0001.jpg?alt=media&token=2aba7b5d-371c-4575-a2ef-fcf6463dca20");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"Synopsis").add(userMapy1);

        Map<String, String> userMapy2 = new HashMap<>();
        userMapy2.put("url","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0002.jpg?alt=media&token=eab31597-03a3-470d-a9b7-323491118ad0");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"Synopsis").add(userMapy2);

        Map<String, String> userMapy3 = new HashMap<>();
        userMapy3.put("url","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0003.jpg?alt=media&token=087d0eaa-ccb0-4fbb-8d82-c6549830a0a5");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"Synopsis").add(userMapy3).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(ImportantActivity.this, "Task Completed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}