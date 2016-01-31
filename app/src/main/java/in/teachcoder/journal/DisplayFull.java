package in.teachcoder.journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
}
