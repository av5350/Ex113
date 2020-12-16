package com.example.ex113;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    String stringText;
    TextView showView;
    EditText inputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showView = (TextView) findViewById(R.id.showView);
        inputView = (EditText) findViewById(R.id.inputView);

        readText();
        showView.setText(stringText);
    }

    public void readText()
    {
        try {
            FileInputStream fis = openFileInput("data.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String line = br.readLine();
            while (line != null) {
                sb.append(line+'\n');
                line = br.readLine();
            }
            stringText=sb.toString();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveText(View view) {
        stringText += inputView.getText().toString();

        try {
            FileOutputStream fos = openFileOutput("data.txt",MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(stringText);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        showView.setText(stringText);
    }

    public void restText(View view) {
        stringText = "";
        showView.setText(stringText);
    }

    public void exitApp(View view) {
        saveText(view);
        finish();
    }

    /**
     * Create the options menu
     *
     * @param menu the menu
     * @return ture if success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * go to credits activity if it was clicked at the menu
     *
     * @param item the item in menu that was clicked
     * @return true - if it success
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String title = (String) item.getTitle();

        // go to credits activity if clicked
        if (title.equals("Creadits"))
        {
            Intent si = new Intent(this, CreaditsActivity.class);
            startActivity(si);
        }

        return true;
    }
}