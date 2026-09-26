SQLITE.XML

<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="20dp">

    <EditText
        android:id="@+id/id"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter ID"
        android:inputType="number" />

    <EditText
        android:id="@+id/name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter Name" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <Button
            android:id="@+id/insert"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Insert" />

        <Button
            android:id="@+id/display"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Display" />

    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <Button
            android:id="@+id/update"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Update" />

        <Button
            android:id="@+id/delete"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Delete" />

    </LinearLayout>

    <TextView
        android:id="@+id/result"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="10dp"
        android:textSize="18sp" />

</LinearLayout>


SQLITE ACTIVITY.JAVA

package com.example.sqliteactivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SQLiteActivity extends AppCompatActivity {

    EditText id, name;
    TextView result;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_sqlite);

        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        result = findViewById(R.id.result);

        Button insert = findViewById(R.id.insert);
        Button display = findViewById(R.id.display);
        Button update = findViewById(R.id.update);
        Button delete = findViewById(R.id.delete);

        // Create or open database
        db = openOrCreateDatabase(
                "StudentDB2",
                MODE_PRIVATE,
                null
        );

        // Create table
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS student (" +
                "id INTEGER PRIMARY KEY, " +
                "name TEXT)"
        );

        // INSERT
        insert.setOnClickListener(v -> {
            try {
                db.execSQL(
                        "INSERT INTO student VALUES(?, ?)",
                        new String[]{
                                id.getText().toString(),
                                name.getText().toString()
                        }
                );

                Toast.makeText(
                        this,
                        "Inserted Successfully",
                        Toast.LENGTH_SHORT
                ).show();

            } catch (Exception e) {
                Toast.makeText(
                        this,
                        "ID already exists",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        // DISPLAY
        display.setOnClickListener(v -> {

            Cursor c = db.rawQuery(
                    "SELECT * FROM student",
                    null
            );

            StringBuilder data = new StringBuilder();

            while (c.moveToNext()) {
                data.append(c.getInt(0))
                        .append(" - ")
                        .append(c.getString(1))
                        .append("\n");
            }

            c.close();

            if (data.length() == 0) {
                result.setText("No records found");
            } else {
                result.setText(data.toString());
            }
        });

        // UPDATE
        update.setOnClickListener(v -> {

            db.execSQL(
                    "UPDATE student SET name=? WHERE id=?",
                    new String[]{
                            name.getText().toString(),
                            id.getText().toString()
                    }
            );

            Toast.makeText(
                    this,
                    "Updated Successfully",
                    Toast.LENGTH_SHORT
            ).show();
        });

        // DELETE
        delete.setOnClickListener(v -> {

            db.execSQL(
                    "DELETE FROM student WHERE id=?",
                    new String[]{
                            id.getText().toString()
                    }
            );

            Toast.makeText(
                    this,
                    "Deleted Successfully",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
