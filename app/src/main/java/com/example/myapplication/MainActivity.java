package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button btnAdd, btnFind, btnDelete;
    EditText productName, productQuantity, productReview;
    TextView productID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


//        Toasty.info(getBaseContext(), "This is an info toast.",
//                Toast.LENGTH_SHORT, true).show();
//        Toasty.error(getBaseContext(), "This is an error toast.",
//                Toast.LENGTH_SHORT, true).show();
//        Toasty.success(getBaseContext(), "Success.", Toast.LENGTH_SHORT,
//                true).show();


        myDb = new DatabaseHelper(this);

        btnAdd = (Button) findViewById(R.id.addBtn);
        btnFind = (Button) findViewById(R.id.findBtn);
        btnDelete = (Button) findViewById(R.id.deleteBtn);

        productID = (TextView) findViewById(R.id.productID);
        productName = (EditText) findViewById(R.id.productName);
        productQuantity = (EditText)findViewById(R.id.productQuantity);
        productReview = (EditText) findViewById(R.id.productReview);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String prodName = productName.getText().toString();
                String prodQuan = productQuantity.getText().toString();
                String prodReview = productReview.getText().toString();


                if (prodName.equals("") || prodQuan.equals("") || prodReview.equals(""))
                {
                    Toast.makeText(MainActivity.this,
                            "Fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!myDb.addData(prodName, prodQuan, prodReview))
                    Toast.makeText(MainActivity.this,
                            "Insertion Failed", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,
                            "Insertion Success", Toast.LENGTH_SHORT).show();


            }
        });

//        btnFind.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String productNameInput = productName.getText().toString();
//
//                if(productNameInput.equals("")) {
//                    Toast.makeText(MainActivity.this, "Product Name Field is empty Or not Found", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Toast.makeText(MainActivity.this, "Product Found...", Toast.LENGTH_SHORT).show();
//
//                Cursor cursor = myDb.structuredQuery(productNameInput);
//                String cID = cursor.getString(0);
//                String cName = cursor.getString(1);
//                String cPrQuant = cursor.getString(2);
//                String cPrReview = cursor.getString(3);
//
//                productID.setText(cID);
//                productQuantity.setText(cPrQuant);
//                productReview.setText(cPrReview);
//
//            }
//        });

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ID = productID.getText().toString();

                if (ID.equals(""))
                {
                    Toast.makeText(MainActivity.this,
                            "Find the product before deleting it",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (myDb.deleteData(ID))
                {
                    Toast.makeText(MainActivity.this,
                            "Product Deleted", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(MainActivity.this,
                            "Delete Unsuccessful", Toast.LENGTH_SHORT).show();
                }
                productID.setText("");
                productName.setText("");
                productQuantity.setText("");
                productReview.setText("");

            }
        });
    }
}