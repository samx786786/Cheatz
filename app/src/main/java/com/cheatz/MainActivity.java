package com.cheatz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import java.util.ArrayList;
import java.util.List;
import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    TextView info;
    private MainActivityAdapter notificationsAdapterx;
    private List<MainModel> NotifListx;
    FirebaseFirestore firestore;
    ProgressBar spiner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOrientation(MainActivity.this);
        info=findViewById(R.id.textView);
        spiner=findViewById(R.id.progressBar2);
        firestore = FirebaseFirestore.getInstance();
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Intent intent = new Intent(MainActivity.this, infoActivity.class);
                 startActivity(intent);

            }
        });
        NotifListx = new ArrayList<>();
        RecyclerView notificationList = findViewById(R.id.recyclerView);
        notificationsAdapterx = new MainActivityAdapter(NotifListx);
        notificationList.setHasFixedSize(true);
        spiner.setVisibility(View.VISIBLE);
        notificationList.setDrawingCacheEnabled(true);
        notificationList.setItemAnimator(null);
        notificationList.setNestedScrollingEnabled(false);
        notificationList.setLayoutManager(new LinearLayoutManager(this));
        notificationList.setAdapter(notificationsAdapterx);
        FirebaseMessaging.getInstance().subscribeToTopic("news")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.msg_subscribed);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.msg_subscribe_failed);
                        }
                        Log.d(TAG, msg);
                    }
                });
        firestore.collection("branch").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                spiner.setVisibility(View.INVISIBLE);
                for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {
                    MainModel notifications = doc.getDocument().toObject(MainModel.class);
                    NotifListx.add(notifications);
                    notificationsAdapterx.notifyDataSetChanged();
                }
            }
        });
    }


    private void setOrientation(MainActivity context) {
        if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.O)
        {
            context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }


}