package com.example.books;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity  {

    private RecyclerView mRecyclerView;
    private  ImageAdapter mAdapter;

    private DatabaseReference mRef;
    private List<Book>  mBooks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);


        FloatingActionButton buttonAdd =findViewById(R.id.floatingActionButton);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookListActivity.this,NewBookActivity.class));
            }
        });


        mRecyclerView = findViewById(R.id.recyclerview_books);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mBooks = new ArrayList<>();

        mRef = FirebaseDatabase.getInstance().getReference("books");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Book book = postSnapshot.getValue(Book.class);
                    mBooks.add(book);
                }
                mAdapter = new ImageAdapter(BookListActivity.this, mBooks);

                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(BookListActivity.this, databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }



}
