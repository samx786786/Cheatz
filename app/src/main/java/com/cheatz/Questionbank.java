package com.cheatz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class Questionbank extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT1 = "text";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";
    public static final String TEXT4 = "text4";
    Button studymaterial,textbook,modelpaper,questionpaper;
    PDFView pdfView;
    FirebaseFirestore firestore;
    ProgressBar progressBar;
    TextView loadingstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionbank);
        progressBar=findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.VISIBLE);
        loadingstatus=findViewById(R.id.textView14);
        loadingstatus.setVisibility(View.VISIBLE);
        loadingstatus.setText("Fetching Urls...");
        firestore = FirebaseFirestore.getInstance();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String branchname = sharedPreferences.getString(TEXT1, "");
        String subbranchname = sharedPreferences.getString(TEXT2, "");
        String year = sharedPreferences.getString(TEXT3, "");
        String sem = sharedPreferences.getString(TEXT4, "");
        pdfView = findViewById(R.id.idPDFView);

        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null)
        {
            String subjectname = bundle1.get("subjectname").toString();
            firestore.collection(branchname+subbranchname+sem+year+subjectname+"questionbank").document("tools").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                            String textbookurl = task.getResult().getString("textbook");
                            String modelpaperurl = task.getResult().getString("modelpaper");
                            String questionpaperurl = task.getResult().getString("questionpaper");
                            String studymaterialurl=task.getResult().getString("studymaterial");
                            String pdfurl=task.getResult().getString("Pdfurl");
                            loadpdf(pdfurl);
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

    private void loadpdf(String pdfurl) {
        loadingstatus.setText("rendering pdf...");
        new RetrivePDFfromUrl().execute(pdfurl);
    }

    private void uploadurl(String branchname, String subbranchname, String sem, String year, String subjectname) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("textbook","https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        userMap.put("modelpaper","https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        userMap.put("questionpaper","https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        userMap.put("studymaterial","https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        userMap.put("Pdfurl","https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        firestore.collection(branchname+subbranchname+sem+year+subjectname+"questionbank").document("tools").set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Questionbank.this, "Task Completed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
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
            loadingstatus.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            pdfView.fromStream(inputStream).enableDoubletap(true).enableAntialiasing(true).spacing(4).load();
        }
    }

}