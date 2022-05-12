package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserDetailEntry extends AppCompatActivity {

    EditText Name,City,Contact;
    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_entry);
        Spinner spinner = findViewById(R.id.spinner);
        Name=findViewById(R.id.name);
        City=findViewById(R.id.city);
        Contact=findViewById(R.id.contact);
        Submit=findViewById(R.id.submit);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.BloodGroup, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("Donor");
                DatabaseReference usersRef = ref.child("users");
                HashMap<String, Object> m = new HashMap<String , Object>();
                m.put ("Name" ,Name.getText().toString());
                m.put ("City" ,City.getText ().toString());
                m.put ("Contact" ,Contact.getText ().toString());
                m.put ("Blood Group" ,spinner.getSelectedItem().toString());
                usersRef.child("User").push().setValue(m);
            }
        });

    }
}