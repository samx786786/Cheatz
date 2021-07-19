package com.cheatz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class Questionbank extends AppCompatActivity {


    TextView title;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT1 = "text";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";
    public static final String TEXT4 = "text4";
    Button studymaterial,textbook,modelpaper,questionpaper;
    private ImportantRecyclerAdapter notificationsAdapterx;
    private List<Importantquestionmodel> NotifListx;
    FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionbank);
        title=findViewById(R.id.textView14);
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
            title.setText(branchname+"\n"+subbranchname+"\n"+sem+"\n"+year+"\n"+subjectname);
            NotifListx = new ArrayList<>();
            RecyclerView notificationList = findViewById(R.id.importantquetion);
            notificationsAdapterx = new ImportantRecyclerAdapter(NotifListx);
            notificationList.setHasFixedSize(true);
            notificationList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            notificationList.setAdapter(notificationsAdapterx);
            firestore = FirebaseFirestore.getInstance();
            firestore.collection(branchname+subbranchname+sem+year+subjectname+"ImportantQuestion").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {
                        Importantquestionmodel notifications = doc.getDocument().toObject(Importantquestionmodel.class);
                        NotifListx.add(notifications);
                        notificationsAdapterx.notifyDataSetChanged();
                    }
                }
            });


            // check logic once 
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


}