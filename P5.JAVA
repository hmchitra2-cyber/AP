XML.JAVA

<?xml version="1.0" encoding="utf-8"?>

<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <LinearLayout
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:orientation="vertical"
        android:padding="20dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <ListView
            android:id="@+id/lst"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>

MAINACTIVITY.JAVA

package com.smw.custadap;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lst;
    ArrayList<String> dataList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lst = findViewById(R.id.lst);

        writeData();
        readData();
        displayData();
    }

    // Function to write data
    void writeData() {
        String data = "Hello Android\nInternal Storage\nArray Adapter";

        try {
            FileOutputStream fos =
                    openFileOutput("data.txt", MODE_PRIVATE);

            fos.write(data.getBytes());
            fos.close();

            Toast.makeText(
                    this,
                    "Data Written Successfully",
                    Toast.LENGTH_SHORT
            ).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to read data
    void readData() {
        dataList = new ArrayList<>();

        try {
            FileInputStream fis = openFileInput("data.txt");

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(fis)
            );

            String line;

            while ((line = br.readLine()) != null) {
                dataList.add(line);
            }

            br.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to display data
    void displayData() {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dataList
        );

        lst.setAdapter(adapter);
    }
}
