package com.cheatz;

import android.content.Intent;
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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProfileActivity extends AppCompatActivity {

    ImageView info;
    TextView infotextview;
    private Selectorrecycleradapter notificationsAdapterx;
    private List<Selectormodel> NotifListx;
    FirebaseFirestore firestore;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firestore = FirebaseFirestore.getInstance();
        info=findViewById(R.id.imageView);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
        infotextview=findViewById(R.id.textView);
        infotextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null)
        {
           String branchname = bundle1.get("branchname").toString();
           String year = bundle1.get("year").toString();
           String subbranch = bundle1.get("subbranch").toString();
           NotifListx = new ArrayList<>();
           RecyclerView notificationList = findViewById(R.id.selectsemrecycler);
           notificationsAdapterx = new Selectorrecycleradapter(NotifListx,branchname,year,subbranch);
           notificationList.setHasFixedSize(true);
           notificationList.setDrawingCacheEnabled(true);
           notificationList.setItemAnimator(null);
           notificationList.setNestedScrollingEnabled(false);
           notificationList.setLayoutManager(new LinearLayoutManager(this));
           notificationList.setAdapter(notificationsAdapterx);
           firestore.collection(branchname+subbranch+year+"Sem").orderBy("sem", Query.Direction.ASCENDING).addSnapshotListener(this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {
                        Selectormodel notifications = doc.getDocument().toObject(Selectormodel.class);
                        NotifListx.add(notifications);
                        notificationsAdapterx.notifyDataSetChanged();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
    }

    private void loaddata(String branchname, String subbranch, String year) {
        Map<String, String> userMap1 = new HashMap<>();
        userMap1.put("sem","semester 3");
        userMap1.put("imageurl","https://www.uib.no/sites/w3.uib.no/files/styles/content_main_wide_1x/public/motivasjonstips_colourbox43420433.png?itok=k-bSiGkc&timestamp=1588256422");
        firestore.collection(branchname+subbranch+year+"Sem").add(userMap1);

        Map<String, String> userMap2 = new HashMap<>();
        userMap2.put("sem","semester 4");
        userMap2.put("imageurl","https://www.openmicuk.co.uk/wp-content/uploads/2020/03/Benefits-of-Listening-to-Music-While-Studying--e1584542039801.jpg");
        firestore.collection(branchname+subbranch+year+"Sem").add(userMap2);



        Map<String, String> userMap3 = new HashMap<>();
        userMap3.put("sem","semester 5");
        userMap3.put("imageurl","https://www.floridacareercollege.edu/wp-content/uploads/sites/4/2020/02/The-Do%E2%80%99s-and-Don%E2%80%99ts-for-Successful-Studying-Florida-Career-College.jpg");
        firestore.collection(branchname+subbranch+year+"Sem").add(userMap3);


        Map<String, String> userMap4 = new HashMap<>();
        userMap4.put("sem","semester 6");
        userMap4.put("imageurl","https://www.timeshighereducation.com/student/sites/default/files/styles/default/public/istock-1093929298.jpg?itok=ZobHKThf");
        firestore.collection(branchname+subbranch+year+"Sem").add(userMap4);


        Map<String, String> userMap5 = new HashMap<>();
        userMap5.put("sem","semester 7");
        userMap5.put("imageurl","https://i.guim.co.uk/img/media/43fc2fe0498e9b06dc038fbc07216b8ebfc74ab8/0_102_3500_2102/master/3500.jpg?width=445&quality=45&auto=format&fit=max&dpr=2&s=2767c01e3f8a3c1117ef7bd8e840443a");
        firestore.collection(branchname+subbranch+year+"Sem").add(userMap5);

        Map<String, String> userMap6 = new HashMap<>();
        userMap6.put("sem","semester 8");
        userMap6.put("imageurl","https://st.depositphotos.com/2931363/5063/i/600/depositphotos_50630607-stock-photo-confident-young-man-making-research.jpg");
        firestore.collection(branchname+subbranch+year+"Sem").add(userMap6).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {

                Toast.makeText(ProfileActivity.this, "Task Completed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}