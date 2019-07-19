package com.example.books;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NewBookActivity extends AppCompatActivity {
    EditText editTextTitle, editTextMessage, editTextPicture;
    Button SaveButton, CancelButton;

    ProgressDialog pd;

    FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);

        editTextTitle = findViewById(R.id.InsertTitle);
        editTextMessage = findViewById(R.id.InsertMessage);
        editTextPicture = findViewById(R.id.Insertpicture);
        SaveButton = findViewById(R.id.AddButton);
        CancelButton = findViewById(R.id.CancelButton);

        //progress dialog
        pd = new ProgressDialog(this);

        //firestore
        db = FirebaseFirestore.getInstance();

        //Click Button Add data
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String picture = editTextPicture.getText().toString().trim();
                String title = editTextTitle.getText().toString().trim();
                String message = editTextMessage.getText().toString().trim();

                uploadData(picture, title, message);
            }
        });

        //Click Button Cancel


    }

    private void uploadData(String picture, String title, String message) {

        pd.setTitle("Adding Data to Firestore");

        pd.show();

        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id",id); //id of data
        doc.put("picture",picture);
        doc.put("title", title);
        doc.put("message", message);

        db.collection("books").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        //this will be called when data is added successfully

                        pd.dismiss();
                        Toast.makeText(NewBookActivity.this,"Uploaded", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        //this will be called if there is any error while uploading

                    }
                });



    }


}
