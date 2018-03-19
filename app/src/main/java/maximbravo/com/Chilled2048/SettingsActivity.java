package maximbravo.com.Chilled2048;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by wendy on 10/17/2016.
 */

public class SettingsActivity extends AppCompatActivity {

    private int[] row1;
    private int[] row2;
    private int[] row3;
    private int[] row4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Bundle extras = getIntent().getExtras();
        row1 = extras.getIntArray("row1");
        row2 = extras.getIntArray("row2");
        row3 = extras.getIntArray("row3");
        row4 = extras.getIntArray("row4");
    }

    public void onRadioButtonClicked(View view){
        switch (view.getId()) {
            case R.id.colorTheme0:
                Rules.scale = 0;
                break;
            case R.id.colorTheme1:
                Rules.scale = 1;
                break;
            case R.id.colorTheme2:
                Rules.scale = 2;
                break;
            case R.id.colorTheme3:
                Rules.scale = 3;
                break;
            case R.id.colorTheme4:
                Rules.scale = 4;
                break;
            case R.id.colorTheme5:
                Rules.scale = 5;
                break;
            case R.id.colorTheme6:
                Rules.scale = 6;
                break;
            case R.id.colorTheme7:
                Rules.scale = 7;
                break;
            case R.id.colorTheme8:
                Rules.scale = 8;
                break;
            case R.id.colorTheme9:
                Rules.scale = 9;
                break;
        }
    }
    public void finish(View view){

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("row1", row1);
        intent.putExtra("row2", row2);
        intent.putExtra("row3", row3);
        intent.putExtra("row4", row4);
        startActivity(intent);
    }
}
