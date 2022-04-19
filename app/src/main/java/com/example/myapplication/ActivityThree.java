package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class ActivityThree extends AppCompatActivity {
DatabaseHelper myDb;
    TextView viewdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        myDb = new DatabaseHelper(this);
        EditText delText = (EditText) findViewById(R.id.deleteTxt);
        Button btnView = (Button) findViewById(R.id.viewbttn);
        Button btnDelete = (Button) findViewById(R.id.deleteBttn);
        viewdb = (TextView) findViewById(R.id.showdatabase);


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = myDb.getListContents();
                StringBuffer buffer = new StringBuffer();

                while(cur.moveToNext()){
                    buffer.append("id: "+cur.getString(0)+"\n");
                    buffer.append("Name: "+cur.getString(1)+"\n");
                    buffer.append("Salary: "+cur.getString(2)+"\n\n");
                }
              viewdb.setText(buffer.toString());
            AlertDialog.Builder builder = new AlertDialog.Builder(ActivityThree.this);
            builder.setCancelable(true);
            builder.setTitle("All Users");
            builder.setMessage(buffer.toString());
            builder.show();
            }
        });


                btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txt = delText.getText().toString();

                if (txt.equals(""))
                {
                    Toasty.info(getBaseContext(), "Write what u want to delete",
                            Toast.LENGTH_SHORT, true).show();
                    return;
                }
                if (myDb.deleteData(txt))
                {
                    Toasty.info(getBaseContext(), "Delete Succefull",
                            Toast.LENGTH_SHORT, true).show();

                }else
                {
                    Toasty.error(getBaseContext(), "Delete Failed",
                            Toast.LENGTH_SHORT, true).show();
                }
               delText.setText("");
            }
        });
    }
}