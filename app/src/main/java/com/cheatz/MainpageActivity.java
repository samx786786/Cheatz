package com.cheatz;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainpageActivity extends AppCompatActivity {

    ImageView qr,importantsynopis,notes;
    PDFView pdfView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        importantsynopis=findViewById(R.id.importantsynopsis);
        notes=findViewById(R.id.notes);
        qr=findViewById(R.id.qr);
        pdfView=findViewById(R.id.pdfview);
        progressBar=findViewById(R.id.progressBar2);
        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null)
        {

            String qrcode = bundle1.get("qrcode").toString();
            String importanturl = bundle1.get("importanturl").toString();
            String pdfurl = bundle1.get("pdfurl").toString();
            String notesurl = bundle1.get("notesurl").toString();
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
                    Intent intent = new Intent(MainpageActivity.this, NotesActivity.class);
                    intent.putExtra("notes",notesurl);
                    startActivity(intent);
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