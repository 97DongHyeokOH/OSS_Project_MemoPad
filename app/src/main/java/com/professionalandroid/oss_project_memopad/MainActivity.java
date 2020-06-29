package com.professionalandroid.oss_project_memopad;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView text;
    Button save;
    EditText title_text;
    EditText maintext;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        save = findViewById(R.id.save);
        title_text = findViewById(R.id.title_text);
        maintext = findViewById(R.id.maintext);

    }

    public void onClick_save(View v) {
        String title = title_text.getText().toString();
        String content = maintext.getText().toString();

        SimpleMemo MEMO = new SimpleMemo(this);
        SQLiteDatabase db = MEMO.getWritableDatabase();
        db.execSQL("INSERT INTO Memo(title, content) VALUES(?,?)", new String[]{title, content});
        db.close();

        Intent intent = new Intent(this, ReadMemoActivity.class);
        startActivity(intent);
    }

    public void onClick_load(View v){
        Intent intent = new Intent(this, ReadMemoActivity.class);
        startActivity(intent);
    }

    public static class SimpleMemo extends SQLiteOpenHelper{

        public static final int VERSION = 1;
        public SimpleMemo(Context context) {
            super(context, "MEMO", null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE MEMO " + "(_ID INTEGER PRIMARY KEY AUTOINCREMENT," + "TITLE," + "CONTENT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if(newVersion == 1){
                db.execSQL("DROP TABLE MEMO");
                onCreate(db);
            }
        }
    }
}