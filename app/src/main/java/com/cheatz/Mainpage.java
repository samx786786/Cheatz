package com.cheatz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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


public class Mainpage extends AppCompatActivity {


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT1 = "text";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";
    public static final String TEXT4 = "text4";
    FirebaseFirestore firestore;
    private MainpageRecyclerAdapter notificationsAdapterx;
    private List<Mainpagemodel> NotifListx;
    private MainpageRecyclerAdapter notificationsAdaptery;
    private List<Mainpagemodel> NotifListy;
    private MainpageRecyclerAdapter notificationsAdapterz;
    private List<Mainpagemodel> NotifListz;
    ConstraintLayout Constrainlayouthundered;
    ImageView arrowhundred;
    ConstraintLayout Constanlayoutfifityplus;
    ImageView arrowrightfifty,arrowleftfifity;
    ConstraintLayout Constrainlayoutpass;
    ImageView arrowpass;
    ImageView notesicon,questionbankicon,importantquestionicon,youtubeicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        notesicon=findViewById(R.id.imageView7);
        questionbankicon=findViewById(R.id.imageView4);
        importantquestionicon=findViewById(R.id.imageView5);
        Constrainlayouthundered=findViewById(R.id.hunderprecentageconstrainlayout);
        arrowhundred=findViewById(R.id.imageView3);
        Constanlayoutfifityplus=findViewById(R.id.fiftyconstrainlayout);
        arrowrightfifty=findViewById(R.id.imageView4fifty);
        arrowleftfifity=findViewById(R.id.imageView3fifty);
        Constrainlayoutpass=findViewById(R.id.passconstrainlayout);
        youtubeicon=findViewById(R.id.imageView6);
        arrowpass=findViewById(R.id.imageView4pass);
        arrowhundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constrainlayouthundered.setVisibility(View.GONE);
                Constrainlayoutpass.setVisibility(View.GONE);
                Constanlayoutfifityplus.setVisibility(View.VISIBLE);
            }
        });
        arrowleftfifity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Constanlayoutfifityplus.setVisibility(View.GONE);
               Constrainlayouthundered.setVisibility(View.GONE);
               Constrainlayoutpass.setVisibility(View.VISIBLE);
            }
        });
        arrowrightfifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constanlayoutfifityplus.setVisibility(View.GONE);
                Constrainlayouthundered.setVisibility(View.VISIBLE);
                Constrainlayoutpass.setVisibility(View.GONE);
            }
        });
        arrowpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constanlayoutfifityplus.setVisibility(View.VISIBLE);
                Constrainlayouthundered.setVisibility(View.GONE);
                Constrainlayoutpass.setVisibility(View.GONE);
            }
        });
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
            questionbankicon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent Intent = new Intent(Mainpage.this, Questionbank.class);
                    Intent.putExtra("subjectname",subjectname);
                    startActivity(Intent);
                }
            });
            importantquestionicon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Mainpage.this,ImportantActivity.class);
                    intent.putExtra("subjectname",subjectname);
                    startActivity(intent);
                }
            });


            NotifListx = new ArrayList<>();
            RecyclerView notificationList = findViewById(R.id.recyclerView);
            notificationsAdapterx = new MainpageRecyclerAdapter(NotifListx);
            notificationList.setHasFixedSize(true);
            notificationList.setLayoutManager(new LinearLayoutManager(this));
            notificationList.setAdapter(notificationsAdapterx);
            firestore = FirebaseFirestore.getInstance();
            firestore.collection(branchname+subbranchname+sem+year+subjectname+"100").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {
                        Mainpagemodel notifications = doc.getDocument().toObject(Mainpagemodel.class);
                        NotifListx.add(notifications);
                        notificationsAdapterx.notifyDataSetChanged();
                    }
                }
            });
            NotifListy = new ArrayList<>();
            RecyclerView notificationList3 = findViewById(R.id.recyclerViewfifty);
            notificationsAdaptery = new MainpageRecyclerAdapter(NotifListy);
            notificationList3.setHasFixedSize(true);
            notificationList3.setLayoutManager(new LinearLayoutManager(this));
            notificationList3.setAdapter(notificationsAdaptery);
            firestore = FirebaseFirestore.getInstance();
            firestore.collection(branchname+subbranchname+sem+year+subjectname+"50+").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {
                        Mainpagemodel notifications = doc.getDocument().toObject(Mainpagemodel.class);
                        NotifListy.add(notifications);
                        notificationsAdaptery.notifyDataSetChanged();
                    }
                }
            });
            NotifListz = new ArrayList<>();
            RecyclerView notificationList2 = findViewById(R.id.recyclerViewpass);
            notificationsAdapterz = new MainpageRecyclerAdapter(NotifListz);
            notificationList2.setHasFixedSize(true);
            notificationList2.setLayoutManager(new LinearLayoutManager(this));
            notificationList2.setAdapter(notificationsAdapterz);
            firestore = FirebaseFirestore.getInstance();
            firestore.collection(branchname+subbranchname+sem+year+subjectname+"pass").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {
                        Mainpagemodel notifications = doc.getDocument().toObject(Mainpagemodel.class);
                        NotifListz.add(notifications);
                        notificationsAdapterz.notifyDataSetChanged();
                    }
                }
            });

           // uploaddata(branchname,subbranchname,sem,year,subjectname);
            firestore.collection(branchname+subbranchname+sem+year+subjectname+"Tools").document("tools").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                            String notesurl = task.getResult().getString("notesurl");
                            String youtubeurl = task.getResult().getString("youtubeurl");
                            notesicon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(notesurl));
                                    startActivity(browserIntent);
                                }
                            });

                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Mainpage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void uploaddata(String branchname, String subbranchname, String sem, String year, String subjectname) {


        Map<String, String> userMap1 = new HashMap<>();
        userMap1.put("imageurl","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0008.jpg?alt=media&token=ee9d56ce-ed1c-4a7c-94dd-d952df1e6308");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"100").add(userMap1);

        Map<String, String> userMap2 = new HashMap<>();
        userMap2.put("imageurl","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0009.jpg?alt=media&token=762c246e-55e8-46b6-8b56-faf08c82987b");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"100").add(userMap2);

        Map<String, String> userMap3 = new HashMap<>();
        userMap3.put("imageurl","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0011.jpg?alt=media&token=735b2ef6-6b29-4cfe-a884-fb07797bcff9");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"100").add(userMap3);


        Map<String, String> userMap8 = new HashMap<>();
        userMap8.put("imageurl","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0012.jpg?alt=media&token=ad7f1a78-7d49-4eba-ae19-562d192ff4d1");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"100").add(userMap8).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(Mainpage.this, "Task Completed", Toast.LENGTH_SHORT).show();
            }
        });



        Map<String, String> userMapx1 = new HashMap<>();
        userMapx1.put("imageurl","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0005.jpg?alt=media&token=e253b2ef-bf79-43d3-8e4d-50f0d1578f90");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"50+").add(userMapx1);

        Map<String, String> userMapx2 = new HashMap<>();
        userMapx2.put("imageurl","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0005.jpg?alt=media&token=e253b2ef-bf79-43d3-8e4d-50f0d1578f90");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"50+").add(userMapx2);

        Map<String, String> userMapx3 = new HashMap<>();
        userMapx3.put("imageurl","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0006.jpg?alt=media&token=c61420cd-7390-435a-a5c7-87945ee4620a");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"50+").add(userMapx3);


        Map<String, String> userMapx8 = new HashMap<>();
        userMapx8.put("imageurl","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0007.jpg?alt=media&token=c7787ba7-5e3c-4e1c-8632-58117ce0dae8");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"50+").add(userMapx8).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(Mainpage.this, "Task Completed", Toast.LENGTH_SHORT).show();
            }
        });


        Map<String, String> userMapy1 = new HashMap<>();
        userMapy1.put("imageurl","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0001.jpg?alt=media&token=2aba7b5d-371c-4575-a2ef-fcf6463dca20");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"pass").add(userMapy1);

        Map<String, String> userMapy2 = new HashMap<>();
        userMapy2.put("imageurl","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0002.jpg?alt=media&token=eab31597-03a3-470d-a9b7-323491118ad0");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"pass").add(userMapy2);

        Map<String, String> userMapy3 = new HashMap<>();
        userMapy3.put("imageurl","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0003.jpg?alt=media&token=087d0eaa-ccb0-4fbb-8d82-c6549830a0a5");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"pass").add(userMapy3);


        Map<String, String> userMapy8 = new HashMap<>();
        userMapy8.put("imageurl","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pfg%2Fquantum%20coding_page-0004.jpg?alt=media&token=5690e08b-76b0-482b-8184-4c84a5ec9684");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"pass").add(userMapy8).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(Mainpage.this, "Task Completed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}