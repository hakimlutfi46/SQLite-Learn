package com.example.belajarsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;

public class MainActivity2 extends AppCompatActivity {

    TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setTitle("External Storage Example");

        showText = findViewById(R.id.getText);
    }

    public void back(View view){
        Intent i = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(i);
    }

    public String getData(File myfile){
        FileInputStream fileInputStream = null;

        try{
            fileInputStream = new FileInputStream(myfile);
            int i = -1;
            StringBuffer buffer = new StringBuffer();

            while ((i = fileInputStream.read()) != -1){
                buffer.append((char)i);
            }
            return buffer.toString();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void getPublic(View view){
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS); //folder name
        File myfile = new File(folder,"myPublicData.txt"); //file name
        String text = getData(myfile);

        if (text != null){
            showText.setText(text);
        }else{
            showText.setText("No Data");
        }

    }

    public void getPrivate(View view){
        File folder = getExternalFilesDir("Hakim"); //folder name
        File myfile = new File(folder, "myPrivateData.txt"); //file name
        String text = getData(myfile);

        if (text != null){
            showText.setText(text);
        }else{
            showText.setText("No Data");
        }
    }
}