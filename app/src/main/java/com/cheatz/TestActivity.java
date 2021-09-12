package com.cheatz;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.crypto.spec.IvParameterSpec;
import javax.net.ssl.HttpsURLConnection;

public class TestActivity extends AppCompatActivity {

    PDFView pdfView;
    ProgressBar progressBar;
    String pdfurl ="https://firebasestorage.googleapis.com/v0/b/cheatz-438e9.appspot.com/o/demo%20pdf%2Fwaves_quantum.pdf?alt=media&token=8e111e5e-5df0-420a-ab2a-84711aa7ee97";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        pdfView = findViewById(R.id.idPDFView);
        new RetrivePDFfromUrl().execute(pdfurl);
        progressBar=findViewById(R.id.progressBar6);
        progressBar.setVisibility(View.VISIBLE);
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
            pdfView.fromStream(inputStream).enableDoubletap(true).enableAntialiasing(true).spacing(4).load();

        }

    }







}