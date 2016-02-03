package in.teachcoder.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayFull extends AppCompatActivity {
    private TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_full);
        t1= (TextView) findViewById(R.id.displaytitle);
        t2= (TextView) findViewById(R.id.displaycontent);
        t1.setText(getIntent().getExtras().getString(Constants.TITLE_NAME));
        t2.setText(getIntent().getExtras().getString(Constants.CONTENT_NAME));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.display_full_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.share){
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
//            i.setPackage("com.whatsapp");
            i.putExtra(Intent.EXTRA_TEXT, "Here is how my day went:" + t1.getText());
            startActivity(i.createChooser(i,"Share Via"));
//            try {
//                startActivity(i);
//            } catch (android.content.ActivityNotFoundException ex) {
//                Toast.makeText(this, "WhatsApp is not found", Toast.LENGTH_SHORT).show();
//            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
