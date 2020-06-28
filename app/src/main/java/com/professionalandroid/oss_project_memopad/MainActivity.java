package com.professionalandroid.oss_project_memopad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView text;
    Button save;
    Button load;
    Button delete;
    EditText title_text;
    EditText maintext;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        save = findViewById(R.id.save);
        load = findViewById(R.id.load);
        delete = findViewById(R.id.delete);
        title_text = findViewById(R.id.title_text);
        maintext = findViewById(R.id.maintext);


    }

    class SimpleMemo extends SQLiteOpenHelper{

        public SimpleMemo(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE Memo(gNAME CHAR(20)PRIMARY KEY);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Memo");
            onCreate(db);
        }
    }
}