package com.cheatz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class RecycleActivity extends AppCompatActivity {

    EditText name,contact,address;
    Button recycle;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        name=findViewById(R.id.editTextTextPersonName2);
        contact=findViewById(R.id.editTextTextPersonName3);
        address=findViewById(R.id.editTextTextPersonName4);
        firestore = FirebaseFirestore.getInstance();
        recycle=findViewById(R.id.button4);
        recycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=name.getText().toString();
                String contactnumber=contact.getText().toString();
                String useraddress=address.getText().toString();

                Map<String, String> userMap = new HashMap<>();
                userMap.put("name", username);
                userMap.put("contact",contactnumber);
                userMap.put("address",useraddress);

                firestore.collection("Recycle").add(userMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        // do the task like chat app

                    }
                });
            }
        });
    }
}