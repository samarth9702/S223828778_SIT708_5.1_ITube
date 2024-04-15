package com.example.itube;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button btnPlay, btnAdd, btnView, btnSignOut;
    TextInputEditText url;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnSignOut = findViewById(R.id.btn_logout);
        btnAdd = findViewById(R.id.btn_add_to_playlst);
        btnView = findViewById(R.id.btn_view_playlist);
        btnPlay = findViewById(R.id.btn_play);
        url = findViewById(R.id.yt_url);

        myDB = new DatabaseHelper(MainActivity.this);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "LogOut Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LoginScreen.class));
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url.clearFocus();
                if(url.getText().toString().isEmpty() ){
                    url.setError("This field cannot be empty");
                } else {

                    boolean isInserted = myDB.insertData(url.getText().toString());

                    if (isInserted){
                        Toast.makeText(MainActivity.this, "Added to Playlist", Toast.LENGTH_SHORT).show();
                        url.setText("");

                    } else
                        Toast.makeText(MainActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewPlaylistScreen.class);
                startActivity(intent);

            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VideoPlayScreen.class);
                intent.putExtra("url", url.getText().toString());
                startActivity(intent);
            }
        });
    }
}