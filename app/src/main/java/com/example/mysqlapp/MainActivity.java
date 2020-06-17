package com.example.mysqlapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper myDb;
    EditText etName, etType, etWCare, etSCare, etRepr, id;
    Button Add, View, Update, Delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DataBaseHelper(this);

        id = (EditText)findViewById(R.id.id);
        etName = (EditText)findViewById(R.id.etName);
        etType = (EditText)findViewById(R.id.etType);
        etWCare = (EditText)findViewById(R.id.etWCare);
        etSCare = (EditText)findViewById(R.id.etSCare);
        etRepr = (EditText)findViewById(R.id.etRepr);
        Add = (Button)findViewById(R.id.Add);
        View = (Button)findViewById(R.id.View);
        Update = (Button)findViewById(R.id.Update);
        Delete = (Button)findViewById(R.id.Delete);
        AddData();
        ViewAll();
        Update();
        Delete();
    }

    public void AddData(){
        Add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       boolean isInserted = myDb.insertData(etName.getText().toString(),
                                etType.getText().toString(),
                                etWCare.getText().toString(),
                                etSCare.getText().toString(),
                                etRepr.getText().toString());
                       if(isInserted)
                           Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                       else
                           Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void ViewAll(){
        View.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0){
                            showMes("Ошибка!", "Ничего не найдено.");
                            return;
                        }
                        StringBuffer buf = new StringBuffer();
                        while (res.moveToNext()){
                            buf.append("id: " + res.getString(0)+"\n");
                            buf.append("Название: " + res.getString(2)+"\n");
                            buf.append("Тип: " + res.getString(3)+"\n");
                            buf.append("Зимний уход: " + res.getString(4)+"\n");
                            buf.append("Летний уход: " + res.getString(5)+"\n");
                            buf.append("Размножение: " + res.getString(6)+"\n\n");
                        }
                        showMes("Данные: ", buf.toString());

                    }
                }
        );
    }
    public void showMes(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void Update(){
        Update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        boolean isUpdate = myDb.updateData(id.getText().toString(),
                                etName.getText().toString(),
                                etType.getText().toString(),
                                etWCare.getText().toString(),
                                etSCare.getText().toString(),
                                etRepr.getText().toString());
                        if(isUpdate)
                            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void Delete(){
        Delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        Integer delRow = myDb.deleteData(id.getText().toString());
                        if (delRow > 0)
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

}
