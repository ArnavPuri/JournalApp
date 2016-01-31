package in.teachcoder.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import in.teachcoder.journal.data.MyDB;

public class AddEntry extends AppCompatActivity {
    private EditText titleEdit, contentEdit;
    private Button save;
    private MyDB myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        titleEdit = (EditText) findViewById(R.id.idhartitle);
        contentEdit = (EditText) findViewById(R.id.idharcontent);
        save = (Button) findViewById(R.id.save);
        myDB = new MyDB(this);
        myDB.open();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDb();
            }
        });
    }

    private void saveToDb() {
        String myTitle = titleEdit.getText().toString();
        String myContent = contentEdit.getText().toString();
        long myDate = System.currentTimeMillis();
        myDB.insertEntry(myTitle, myContent, myDate);
        titleEdit.setText("");
        contentEdit.setText("");
        myDB.close();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}
