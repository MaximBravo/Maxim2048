package maximbravo.com.Chilled2048;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by wendy on 11/1/2016.
 */

public class StartActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void help(View view){
        Intent i = new Intent(StartActivity.this, HelpActivity.class);
        startActivity(i);
    }
    public void start(View view){
        Intent i = new Intent(StartActivity.this, MainActivity.class);
        startActivity(i);
    }
}
