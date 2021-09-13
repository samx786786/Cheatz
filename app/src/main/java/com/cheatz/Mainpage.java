package com.cheatz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


public class Mainpage extends AppCompatActivity {


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT1 = "text";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";
    public static final String TEXT4 = "text4";
    FirebaseFirestore firestore;
    PDFView pdfView;
    ConstraintLayout Constrainlayouthundered;
    ImageView arrowhundred;
    ConstraintLayout Constanlayoutfifityplus;
    ImageView arrowrightfifty,arrowleftfifity;
    ConstraintLayout Constrainlayoutpass;
    ImageView arrowpass;
    ImageView notesicon,questionbankicon,importantquestionicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        firestore = FirebaseFirestore.getInstance();

        notesicon=findViewById(R.id.imageView7);
        questionbankicon=findViewById(R.id.imageView4);
        importantquestionicon=findViewById(R.id.imageView5);
        Constrainlayouthundered=findViewById(R.id.hunderprecentageconstrainlayout);
        arrowhundred=findViewById(R.id.imageView3);
        Constanlayoutfifityplus=findViewById(R.id.fiftyconstrainlayout);
        arrowrightfifty=findViewById(R.id.imageView4fifty);
        arrowleftfifity=findViewById(R.id.imageView3fifty);
        Constrainlayoutpass=findViewById(R.id.passconstrainlayout);
        arrowpass=findViewById(R.id.imageView4pass);
        pdfView = findViewById(R.id.idPDFView);
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
           // uploadurl(branchname,subbranchname,sem,year,subjectname);
            firestore.collection(branchname+subbranchname+sem+year+subjectname+"Tools").document("tools").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                            String notesurl = task.getResult().getString("notesurl");
                            String pdfpass = task.getResult().getString("pdfpass");
                            String pdf50 = task.getResult().getString("pdf50");
                            String pdf100 = task.getResult().getString("pdf100");
                            notesicon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(notesurl));
                                    startActivity(browserIntent);
                                }
                            });
                            new RetrivePDFfromUrlimp().execute(pdf100);

                            arrowhundred.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Constrainlayouthundered.setVisibility(View.GONE);
                                    Constrainlayoutpass.setVisibility(View.GONE);
                                    Constanlayoutfifityplus.setVisibility(View.VISIBLE);
                                    // load 50 in pdf view

                                    new RetrivePDFfromUrlimp().execute(pdf50);
                                }
                            });
                            arrowleftfifity.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Constanlayoutfifityplus.setVisibility(View.GONE);
                                    Constrainlayouthundered.setVisibility(View.GONE);
                                    Constrainlayoutpass.setVisibility(View.VISIBLE);
                                    // load pass in pdf view

                                    new RetrivePDFfromUrlimp().execute(pdfpass);

                                }
                            });
                            arrowrightfifty.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Constanlayoutfifityplus.setVisibility(View.GONE);
                                    Constrainlayouthundered.setVisibility(View.VISIBLE);
                                    Constrainlayoutpass.setVisibility(View.GONE);
                                    // load 100 in pdf view

                                    new RetrivePDFfromUrlimp().execute(pdf100);

                                }
                            });
                            arrowpass.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Constanlayoutfifityplus.setVisibility(View.VISIBLE);
                                    Constrainlayouthundered.setVisibility(View.GONE);
                                    Constrainlayoutpass.setVisibility(View.GONE);
                                    // load 50 in pdf view

                                    new RetrivePDFfromUrlimp().execute(pdf50);

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

    private void uploadurl(String branchname, String subbranchname, String sem, String year, String subjectname) {
        String  pdfurl50="https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pdf%2FM8-Selected-pages-Basic-Aerodynamics.pdf?alt=media&token=9f21661c-c3d5-42ce-b14a-9debe503d782";
        String  pdfurl100="https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pdf%2Fbasic%20thermodynaics.pdf?alt=media&token=34c88be2-7e9c-4768-80b4-0fa8c17eb1a8";
        String  pdfurlpass="https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pdf%2Fwaves_quantum.pdf?alt=media&token=8e111e5e-5df0-420a-ab2a-84711aa7ee97";
        Map<String, String> userMap = new HashMap<>();
        userMap.put("notesurl","https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pdf%2Fdemosdsad.pdf?alt=media&token=3aa1ec39-ded0-490c-9c60-d429bf2e625b");
        userMap.put("pdfpass",pdfurlpass);
        userMap.put("pdf50",pdfurl50);
        userMap.put("pdf100",pdfurl100);
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"Tools").document("tools").set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Mainpage.this, "Task Completed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    class RetrivePDFfromUrlimp extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {

            pdfView.fromStream(inputStream).enableDoubletap(true).enableAntialiasing(true).spacing(4).load();
        }
    }

}