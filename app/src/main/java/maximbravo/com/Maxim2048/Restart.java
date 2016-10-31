package maximbravo.com.Maxim2048;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by wendy on 10/31/2016.
 */

public class Restart extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        Bundle b = getIntent().getExtras();
        if(b != null){
            if(b.getBoolean("gameover")){
                TextView textView = (TextView) findViewById(R.id.prompt);
                textView.setText("Game over! \n Your score was: " + b.getInt("score") + "\nWould you like to restart");
                Button button = (Button) findViewById(R.id.cancel);
                button.setText("no");
            }
        }
        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.6), (int) (height*0.33));
    }

    public void refresh(View view){
        Intent i = new Intent(Restart.this, MainActivity.class);
        i.putExtra("refresh", true);
        startActivity(i);
    }
    public void cancel (View view){
        Intent i = new Intent(Restart.this, MainActivity.class);
        i.putExtra("refresh", false);
        startActivity(i);
    }
}
