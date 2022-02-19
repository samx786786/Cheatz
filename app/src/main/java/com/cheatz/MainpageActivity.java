package com.cheatz;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class MainpageActivity extends AppCompatActivity {

    ImageView qr,importantsynopis,notes;
    PDFView pdfView;
    ProgressBar progressBar;
    ConstraintLayout playlistlayout;
    TextView hide;

    private  playlistrecycleradapter notificationsAdapterx;
    private List<playlistmodel> NotifListx;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firestore = FirebaseFirestore.getInstance();
        importantsynopis=findViewById(R.id.importantsynopsis);
        notes=findViewById(R.id.notes);
        qr=findViewById(R.id.qr);
        playlistlayout=findViewById(R.id.playlistlayout);
        hide=findViewById(R.id.textView18);
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playlistlayout.setVisibility(View.GONE);
            }
        });
        pdfView=findViewById(R.id.pdfview);
        progressBar=findViewById(R.id.progressBar2);
        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null)
        {

            String qrcode = bundle1.get("qrcode").toString();
            String importanturl = bundle1.get("importanturl").toString();
            String pdfurl = bundle1.get("pdfurl").toString();
            new RetrivepdfStream().execute(pdfurl);
            qr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainpageActivity.this, QRActivity.class);
                    intent.putExtra("qrcode",qrcode);
                    startActivity(intent);
                }
            });
            importantsynopis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainpageActivity.this, SynopsisActivity.class);
                    intent.putExtra("synopsis",importanturl);
                    startActivity(intent);

                }
            });
            notes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playlistlayout.setVisibility(View.VISIBLE);
                }
            });

            
            NotifListx = new ArrayList<>();
            RecyclerView notificationList = findViewById(R.id.playlistrecycler);
            notificationsAdapterx = new playlistrecycleradapter(NotifListx);
            notificationList.setHasFixedSize(true);
            notificationList.setDrawingCacheEnabled(true);
            notificationList.setItemAnimator(null);
            notificationList.setNestedScrollingEnabled(false);
            LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(MainpageActivity.this, LinearLayoutManager.HORIZONTAL, false);
            notificationList.setLayoutManager(horizontalLayoutManagaer);
            notificationList.setAdapter(notificationsAdapterx);
            firestore.collection("playlist"+qrcode).addSnapshotListener(this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {
                        playlistmodel notifications = doc.getDocument().toObject(playlistmodel.class);
                        NotifListx.add(notifications);
                        notificationsAdapterx.notifyDataSetChanged();
                    }
                }
            });
        }
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    class RetrivepdfStream extends AsyncTask<String,Void, InputStream>{
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream=null;
            progressBar.setVisibility(View.VISIBLE);
            try {
                URL uRL=new URL (strings[0]);
                HttpURLConnection urlConnection=(HttpURLConnection)uRL.openConnection();
                if (urlConnection.getResponseCode()==200)
                {
                    inputStream=new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            catch (IOException e)
            {
                return null;
            }
            return inputStream;
        }
        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).onError(new OnErrorListener() {
                @Override
                public void onError(Throwable t) {
                    Toast.makeText(MainpageActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            }).onRender(new OnRenderListener() {
                @Override
                public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }).load();
        }
    }
}