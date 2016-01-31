package in.teachcoder.journal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

import in.teachcoder.journal.data.DBHelper;
import in.teachcoder.journal.data.MyDB;
import in.teachcoder.journal.model.Diary;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private MyDB myDB;
    private DBHelper dbhelper;
    private SimpleCursorAdapter adapter;
    ArrayList<Diary> diaries = new ArrayList<>();
    String title;
    String content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDB = new MyDB(MainActivity.this);
        displayListView();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final Intent it = new Intent(this,AddEntry.class);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(it);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void displayListView() {
        Cursor c = myDB.getDiaries();

        int[] to = new int[]{
                R.id.title,
                R.id.description,
                R.id.date
        };


        adapter = new SimpleCursorAdapter(this, R.layout.row, c,
                new String[]{Constants.TITLE_NAME, Constants.CONTENT_NAME, Constants.DATE_NAME}, to, 0);
        lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor c = (Cursor) parent.getAdapter().getItem(position);
                title = c.getString(c.getColumnIndexOrThrow(Constants.TITLE_NAME));
                content = c.getString(c.getColumnIndexOrThrow(Constants.CONTENT_NAME));
                Log.d("clicked,", title);
                Intent fullintent= new Intent(MainActivity.this,DisplayFull.class);
                fullintent.putExtra(Constants.TITLE_NAME,title);
                fullintent.putExtra(Constants.CONTENT_NAME,content);
                startActivity(fullintent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
