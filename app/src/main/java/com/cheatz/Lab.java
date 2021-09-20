package com.cheatz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
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


public class Lab extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT1 = "text";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";
    public static final String TEXT4 = "text4";
    private labRecyclerAdapter notificationsAdapterx;
    private List<labmodel> NotifListx;
    FirebaseFirestore firestore;
    TextView title;
    ImageView record,manuel,info,back;
    ProgressBar progressBar;
    ConstraintLayout infolayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);
        progressBar=findViewById(R.id.progressBar5);
        infolayout=findViewById(R.id.infolayout);
        info=findViewById(R.id.imageView6);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infolayout.setVisibility(View.VISIBLE);
            }
        });
        back=findViewById(R.id.imageView11);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infolayout.setVisibility(View.GONE);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String branchname = sharedPreferences.getString(TEXT1, "");
        String subbranchname = sharedPreferences.getString(TEXT2, "");
        String year = sharedPreferences.getString(TEXT3, "");
        String sem = sharedPreferences.getString(TEXT4, "");
        title=findViewById(R.id.textView16);
        record=findViewById(R.id.imageView4);
        manuel=findViewById(R.id.imageView5);
        firestore = FirebaseFirestore.getInstance();
        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {
            progressBar.setVisibility(View.VISIBLE);
            String subjectname = bundle1.get("subjectname").toString();
            title.setText("Viva Questions"+"\n"+subjectname+"\n");
             // uploaddata(branchname,subbranchname,sem,year,subjectname);
            //  Uploadurl(branchname,subbranchname,sem,year,subjectname);
            NotifListx = new ArrayList<>();
            RecyclerView notificationList = findViewById(R.id.vivaquestions);
            notificationsAdapterx = new labRecyclerAdapter(NotifListx);
            notificationList.setHasFixedSize(true);
            notificationList.setLayoutManager(new LinearLayoutManager(this));
            notificationList.setAdapter(notificationsAdapterx);
            firestore = FirebaseFirestore.getInstance();
            firestore.collection(branchname+subbranchname+sem+year+subjectname+"viva").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    progressBar.setVisibility(View.INVISIBLE);
                    for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {
                        labmodel notifications = doc.getDocument().toObject(labmodel.class);
                        NotifListx.add(notifications);
                        notificationsAdapterx.notifyDataSetChanged();
                    }
                }
            });
            
            firestore.collection(branchname+subbranchname+sem+year+subjectname+"lablinks").document("lablinks").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                            String recordurl = task.getResult().getString("Record");
                            String manualurl = task.getResult().getString("manual");
                                record.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(recordurl));
                                        startActivity(browserIntent);
                                    }
                                });
                                manuel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(manualurl));
                                        startActivity(browserIntent);
                                    }
                                });
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Lab.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void Uploadurl(String branchname, String subbranchname, String sem, String year, String subjectname) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("Record","https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        userMap.put("manual","https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"lablinks").document("lablinks").set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Lab.this, "Task Completed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploaddata(String branchname, String subbranchname, String sem, String year, String subjectname) {

        Map<String, String> userMap1 = new HashMap<>();
        userMap1.put("question","Define density?");
        userMap1.put("answer","It is defined as the ratio of mass per unit volume of the fluid.");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"viva").add(userMap1);

        Map<String, String> userMap2 = new HashMap<>();
        userMap2.put("question","Define viscosity?");
        userMap2.put("answer","It is defined as the property of fluid which offers resistance to the movement of fluid over another adjacent layer of the fluid.");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"viva").add(userMap2);

        Map<String, String> userMap3 = new HashMap<>();
        userMap3.put("question","It is defined as the property of fluid which offers resistance to the movement of fluid over another adjacent layer of the fluid.");
        userMap3.put("answer","A fluid, which is in-compressible and is having no viscosity, is known as ideal fluid while the fluid, which possesses viscosity, is known as real fluid");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"viva").add(userMap3);

        Map<String, String> userMap4 = new HashMap<>();
        userMap4.put("question","What is a venturimeter?");
        userMap4.put("answer","It is a device which is used for measuring the rate of flow of fluid flowing through pipe");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"viva").add(userMap4);

        Map<String, String> userMap5 = new HashMap<>();
        userMap5.put("question","What is a notch?");
        userMap5.put("answer"," A notch is a device used for measuring the rate of flow of a fluid through a small channel or a tank");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"viva").add(userMap5);

        Map<String, String> userMap6 = new HashMap<>();
        userMap6.put("question","Define buoyancy?");
        userMap6.put("answer","When a body is immersed in a fluid, an upward force is exerted by the fluid on the body. This upward force is equal to the weight of the fluid displaced by the body.");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"viva").add(userMap6);

        Map<String, String> userMap7 = new HashMap<>();
        userMap7.put("question","Define meta-centre?");
        userMap7.put("answer","It is defined as the point about which a body starts oscillating when the body is tilted by a small angle");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"viva").add(userMap7);

        Map<String, String> userMap8 = new HashMap<>();
        userMap8.put("question","Define a pump?");
        userMap8.put("answer","The hydraulic machine which converts the mechanical energy into hydraulic energy is called a pump.");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"viva").add(userMap8).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(Lab.this, "Task Completed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}