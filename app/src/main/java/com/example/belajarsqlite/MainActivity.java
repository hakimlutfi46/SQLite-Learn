package com.example.belajarsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    private int STORAGE_PERMISSION_CODE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("External Storage Example");
        editText = findViewById(R.id.editText);
    }

    public void next(View view){
        Intent i = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(i);
    }

    private void writeData(File myfile, String data){
        FileOutputStream fileOutputStream = null;

        try {
            System.out.println("Test");
            fileOutputStream = new FileOutputStream(myfile);
            fileOutputStream.write(data.getBytes());
            Toast.makeText(this, "Done" + myfile.getAbsolutePath(), Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveAsPublic(View view){
        String info = editText.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myfile = new File(folder, "myPublicData.txt");

        if (!info.isEmpty()){
            writeData(myfile, info);
        }else {
            Toast.makeText(this, "String tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveAsPrivate(View view){
        String info = editText.getText().toString();
        File folder = getExternalFilesDir("Hakim"); //folder name
        File myfile = new File(folder, "myPrivateData.txt"); //file name

        if (!info.isEmpty()){
            writeData(myfile, info);
            editText.setText("");
        }else {
            Toast.makeText(this, "String tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        }
    }

}