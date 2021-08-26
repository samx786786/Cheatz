package com.cheatz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

public class Homepage extends AppCompatActivity {

    TextView title;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT1 = "text";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";
    public static final String TEXT4 = "text4";
    private HomeRecyclerAdapter notificationsAdapterx;
    private List<Homemodel> NotifListx;
    FirebaseFirestore firestore;
    TextView cheatz;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        title=findViewById(R.id.textView17);
        cheatz=findViewById(R.id.textView19);
        progressBar=findViewById(R.id.progressBar2);
        firestore = FirebaseFirestore.getInstance();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String branchname = sharedPreferences.getString(TEXT1, "");
        String subbranchname = sharedPreferences.getString(TEXT2, "");
        String year = sharedPreferences.getString(TEXT3, "");
        String sem = sharedPreferences.getString(TEXT4, "");
        title.setText(branchname+"\n"+subbranchname+"\n"+sem+"\n"+year);
        if (branchname.equals(""))
        {
            Intent intent = new Intent(Homepage.this, MainActivity.class);
            startActivity(intent);
        }

        if (subbranchname.equals(""))
        {
            Intent intent = new Intent(Homepage.this, MainActivity.class);
            startActivity(intent);
        }

        if (year.equals(""))
        {
            Intent intent = new Intent(Homepage.this, MainActivity.class);
            startActivity(intent);
        }

        if (sem.equals(""))
        {
            Intent intent = new Intent(Homepage.this, MainActivity.class);
            startActivity(intent);
        }
        progressBar.setVisibility(View.VISIBLE);
        NotifListx = new ArrayList<>();
        RecyclerView notificationList = findViewById(R.id.homepagerecyler);
        notificationsAdapterx = new HomeRecyclerAdapter(NotifListx);
        notificationList.setHasFixedSize(true);
        notificationList.setLayoutManager(new LinearLayoutManager(this));
        notificationList.setAdapter(notificationsAdapterx);
        firestore = FirebaseFirestore.getInstance();
        firestore.collection(branchname+subbranchname+year+sem+"subjects").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                progressBar.setVisibility(View.INVISIBLE);
                for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {
                    Homemodel notifications = doc.getDocument().toObject(Homemodel.class);
                    NotifListx.add(notifications);
                    notificationsAdapterx.notifyDataSetChanged();
                }
            }
        });
    }
}