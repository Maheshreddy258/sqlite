package com.example.mahesh.sqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText dbeditid,dbeditName , dbeditSurname, dbeditMarks;
    Button btnadd,btnupdate,btnview,btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb= new DatabaseHelper(this);

        dbeditid= (EditText)findViewById(R.id.editId);
        dbeditName = (EditText)findViewById(R.id.editName);
        dbeditSurname = (EditText)findViewById(R.id.editSurname);
        dbeditMarks = (EditText)findViewById(R.id.editMarks);
        btnadd = (Button) findViewById(R.id.btnadd);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        btnview = (Button) findViewById(R.id.btnview);
        btndelete = (Button) findViewById(R.id.btndlt);
        AddData();
        update();
        view();
        deleteData();
        }
        public void AddData(){
        btnadd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    boolean isInserted = myDb.insertData(dbeditName.getText().toString(),
                                         dbeditSurname.getText().toString(),
                                         dbeditMarks.getText().toString());

                    if(isInserted == true){
                        Toast.makeText(MainActivity.this, " Data Inserted", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, " Data  not Inserted", Toast.LENGTH_SHORT).show();
                    }
                    }
                }
        );

        }



        public void view(){
            btnview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Cursor cursor = myDb.getData();
                    if (cursor.getCount() == 0){
                        Toast.makeText(MainActivity.this, " Nothing to show", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while(cursor.moveToNext()){
                        buffer.append("ID:"+cursor.getString(0)+"\n");
                        buffer.append("NAME:"+cursor.getString(1)+"\n\n");
                        buffer.append("SURNAME:"+cursor.getString(2)+"\n\n");
                        buffer.append("MARKS:"+cursor.getString(3)+"\n");
                    }

                    showMessage("Data",buffer.toString());
                }
            });
        }


        public void update(){
          btnupdate.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  boolean isUpdate = myDb.updatedata(dbeditid.getText().toString(),dbeditName.getText().toString(),
                          dbeditSurname.getText().toString(),
                          dbeditMarks.getText().toString());
                  if (isUpdate==true)
                  {
                      Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                  }
                  else{
                      Toast.makeText(MainActivity.this,"Data could not be Updated",Toast.LENGTH_LONG).show();
                  }

              }
          });
        }

    public void deleteData(){
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = myDb.deletedata(dbeditid.getText().toString());
                if(deleteRows > 0){
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Data could not be deleted",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
