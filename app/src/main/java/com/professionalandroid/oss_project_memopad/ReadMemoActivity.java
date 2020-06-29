package com.professionalandroid.oss_project_memopad;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ReadMemoActivity extends AppCompatActivity {

    TextView text;
    TextView TITLE_text;
    TextView title;
    TextView content;
    MainActivity.SimpleMemo MEMO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_memo);

        text = findViewById(R.id.text);
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        TITLE_text = findViewById(R.id.TITLE_text);

        MEMO = new MainActivity.SimpleMemo(this);
        SQLiteDatabase db = MEMO.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT title, content FROM MEMO" +
                "                   ORDER BY _ID DESC LIMIT 1", null);

        while(cursor.moveToNext()){
            title.setText(cursor.getString(0));
            content.setText(cursor.getString(1));
        }
        db.close();
    }
}