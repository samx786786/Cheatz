package com.cheatz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.mig35.carousellayoutmanager.CarouselLayoutManager;
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.mig35.carousellayoutmanager.CenterScrollListener;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    ImageView info;
    private Selectorrecycleradapter notificationsAdapterx;
    private List<Selectormodel> NotifListx;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        firestore = FirebaseFirestore.getInstance();
        info = findViewById(R.id.imageView);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {
            String branchname = bundle1.get("branchname").toString();
            String moduels = bundle1.get("moduels").toString();
            String year = bundle1.get("year").toString();
            String subbranch = bundle1.get("subbranch").toString();
            NotifListx = new ArrayList<>();
            final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.VERTICAL, true);
            layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
            RecyclerView recyclerView = findViewById(R.id.selectsemrecycler);
            recyclerView.setLayoutManager(layoutManager);
            notificationsAdapterx = new Selectorrecycleradapter(NotifListx, branchname, year, subbranch);
            recyclerView.setAdapter(notificationsAdapterx);
            recyclerView.addOnScrollListener(new CenterScrollListener());
            firestore.collection(branchname+subbranch+year+"Sem").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                        Selectormodel notifications = doc.getDocument().toObject(Selectormodel.class);
                        NotifListx.add(notifications);
                        notificationsAdapterx.notifyDataSetChanged();
                    }
                }
            });
        }
    }
}
