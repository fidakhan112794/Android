package com.example.googlemap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtsource , edtdestination;
    Button btnNavigation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtsource = findViewById(R.id.source);
        edtdestination = findViewById(R.id.destination);
        btnNavigation = findViewById(R.id.btnNavigation);

        btnNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String source = edtsource.getText().toString();
                String destination = edtdestination.getText().toString();
                
                if (source.equals("") && destination.equals("")){
                    Toast.makeText(MainActivity.this, "Enter Both Sourse and Destination", Toast.LENGTH_SHORT).show();
                }
                else {

                    Uri uri = Uri.parse("https://www.google.com/maps/dir/"+ source + "/" + destination);
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    intent.setPackage("com.google.android.apps.maps");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }
        });
    }
}