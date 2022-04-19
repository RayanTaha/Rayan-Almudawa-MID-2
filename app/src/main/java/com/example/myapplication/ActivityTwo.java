package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class ActivityTwo extends AppCompatActivity {
    DatabaseHelper myDb;
    Button btnAdd, btnFind, btnDelete, btnView, bttnact1, bttnact3;
    EditText userName, userSurname, userPhone, userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
//        btnView = (Button) findViewById(R.id.viewBtn);
        btnAdd = (Button) findViewById(R.id.addBtn);
        bttnact1 = (Button) findViewById(R.id.act1Bttn);
        bttnact3 = (Button) findViewById(R.id.act3Bttn);

//        btnFind = (Button) findViewById(R.id.findBtn);
//        btnDelete = (Button) findViewById(R.id.deleteBtn);

        userID = (EditText) findViewById(R.id.Id);
        userName = (EditText) findViewById(R.id.userName);
        userSurname = (EditText)findViewById(R.id.userSurname);
        userPhone = (EditText) findViewById(R.id.userNationalID);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = userID.getText().toString();
                String name = userName.getText().toString();
                String surname = userSurname.getText().toString();
                String nationalID = userPhone.getText().toString();

                if (id.equals("")||name.equals("") || surname.equals("") || nationalID.equals(""))
                {
                    Toast.makeText(ActivityTwo.this,
                            "Fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!myDb.addData(name, surname, nationalID))
                    Toasty.info(getBaseContext(), "insertion failed",
                            Toast.LENGTH_SHORT, true).show();
                else
                    Toasty.info(getBaseContext(), "insertion success",
                            Toast.LENGTH_SHORT, true).show();
            }
        });

        bttnact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityTwo.this, ActivityOne.class));
            }
        });

        bttnact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityTwo.this, ActivityThree.class));

            }
        });


//        btnFind.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = userName.getText().toString();
//
//                if(name.equals("")) {
//                    Toast.makeText(MainActivity.this, "Product Name Field is empty Or not Found", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Toast.makeText(MainActivity.this, "Product Found...", Toast.LENGTH_SHORT).show();
//
//                Cursor cursor = myDb.structuredQuery(name);
//                String cID = cursor.getString(0);
//                String cName = cursor.getString(1);
//                String cPrQuant = cursor.getString(2);
//                String cPrReview = cursor.getString(3);
//
//                userID.setText(cID);
//                userSurname.setText(cPrQuant);
//                userPhone.setText(cPrReview);
//            }
//        });



    }
}