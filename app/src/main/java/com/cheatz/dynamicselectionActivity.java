package com.cheatz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
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


public class dynamicselectionActivity extends AppCompatActivity {


    ProgressBar progressBar;
    TextView home,title;
    ImageView back;
    private SubbranchRecyclerAdapter notificationsAdaptery;
    private List<SubBranch> NotifListy;
    FirebaseFirestore firestore;
    ProgressBar spiner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamicselection);
        firestore = FirebaseFirestore.getInstance();
        progressBar=findViewById(R.id.progressbar);
        spiner=findViewById(R.id.progressBar2);
        home=findViewById(R.id.textView);
        title=findViewById(R.id.textView16);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dynamicselectionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        back=findViewById(R.id.imageView8);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {
            progressBar.setProgress(25);
            spiner.setVisibility(View.VISIBLE);
            String branch = bundle1.get("branch").toString();
            title.setText(branch);
            NotifListy = new ArrayList<>();
            RecyclerView notificationList = findViewById(R.id.dynamicrecycler);
            notificationsAdaptery = new SubbranchRecyclerAdapter(NotifListy,branch);
            notificationList.setHasFixedSize(true);
            notificationList.setDrawingCacheEnabled(true);
            notificationList.setItemAnimator(null);
            notificationList.setNestedScrollingEnabled(false);
            notificationList.setLayoutManager(new LinearLayoutManager(this));
            notificationList.setAdapter(notificationsAdaptery);
            firestore.collection(branch+"Subbranch").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    spiner.setVisibility(View.INVISIBLE);
                    for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {
                        SubBranch notifications = doc.getDocument().toObject(SubBranch.class);
                        NotifListy.add(notifications);
                        notificationsAdaptery.notifyDataSetChanged();
                    }
                }
            });





          /*

            Map<String, String> userMap = new HashMap<>();
            userMap.put("Subbranch","Test files");
            userMap.put("semrange","2022");
            userMap.put("intent","-");


            firestore.collection(branch+"Subbranch").add(userMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(dynamicselectionActivity.this,"hashmap updated",Toast.LENGTH_LONG).show();
                }
            });

           */


        }
    }
}