package com.cheatz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Questionbank extends AppCompatActivity {


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT1 = "text";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";
    public static final String TEXT4 = "text4";
    Button studymaterial,textbook,modelpaper,questionpaper;
    private SynopsisRecyclerAdapter notificationsAdapterx;
    private List<SynopsisModel> NotifListx;
    FirebaseFirestore firestore;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionbank);
        firestore = FirebaseFirestore.getInstance();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String branchname = sharedPreferences.getString(TEXT1, "");
        String subbranchname = sharedPreferences.getString(TEXT2, "");
        String year = sharedPreferences.getString(TEXT3, "");
        String sem = sharedPreferences.getString(TEXT4, "");
        progressBar=findViewById(R.id.progressBar3);
        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null)
        {
            String subjectname = bundle1.get("subjectname").toString();
            progressBar.setVisibility(View.VISIBLE);
           // uploaddata(branchname,subbranchname,sem,year,subjectname);
           // uploadurl(branchname,subbranchname,sem,year,subjectname);
            NotifListx = new ArrayList<>();
            RecyclerView notificationList = findViewById(R.id.importantquetion);
            notificationsAdapterx = new SynopsisRecyclerAdapter(NotifListx);
            notificationList.setHasFixedSize(true);
            notificationList.setLayoutManager(new LinearLayoutManager(this));
            notificationList.setAdapter(notificationsAdapterx);
            firestore = FirebaseFirestore.getInstance();
            firestore.collection(branchname+subbranchname+sem+year+subjectname+"ImportantQuestion").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    progressBar.setVisibility(View.INVISIBLE);
                    for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {
                        SynopsisModel notifications = doc.getDocument().toObject(SynopsisModel.class);
                        NotifListx.add(notifications);
                        notificationsAdapterx.notifyDataSetChanged();
                    }
                }
            });


            firestore.collection(branchname+subbranchname+sem+year+subjectname+"ImportantQuestion"+"Tools").document("tools").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                            String textbookurl = task.getResult().getString("textbook");
                            String modelpaperurl = task.getResult().getString("modelpaper");
                            String questionpaperurl = task.getResult().getString("questionpaper");
                            String studymaterialurl=task.getResult().getString("studymaterial");

                            textbook=findViewById(R.id.questionbank);
                            textbook.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(textbookurl));
                                    startActivity(browserIntent);

                                }
                            });
                            modelpaper=findViewById(R.id.modelpaper);
                            modelpaper.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(modelpaperurl));
                                    startActivity(browserIntent);
                                }
                            });
                            questionpaper=findViewById(R.id.questionpaper);
                            questionpaper.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(questionpaperurl));
                                    startActivity(browserIntent);
                                }
                            });
                            studymaterial=findViewById(R.id.textbook);
                            studymaterial.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(studymaterialurl));
                                    startActivity(browserIntent);
                                }
                            });
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Questionbank.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void uploadurl(String branchname, String subbranchname, String sem, String year, String subjectname) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("textbook","https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        userMap.put("modelpaper","https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        userMap.put("questionpaper","https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        userMap.put("studymaterial","https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"ImportantQuestion"+"Tools").document("tools").set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Questionbank.this, "Task Completed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploaddata(String branchname, String subbranchname, String sem, String year, String subjectname) {

        Map<String, String> userMapy1 = new HashMap<>();
        userMapy1.put("url","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0001.jpg?alt=media&token=2aba7b5d-371c-4575-a2ef-fcf6463dca20");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"ImportantQuestion").add(userMapy1);

        Map<String, String> userMapy2 = new HashMap<>();
        userMapy2.put("url","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0002.jpg?alt=media&token=eab31597-03a3-470d-a9b7-323491118ad0");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"ImportantQuestion").add(userMapy2);

        Map<String, String> userMapy3 = new HashMap<>();
        userMapy3.put("url","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0003.jpg?alt=media&token=087d0eaa-ccb0-4fbb-8d82-c6549830a0a5");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"ImportantQuestion").add(userMapy3).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(Questionbank.this, "Task Completed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}