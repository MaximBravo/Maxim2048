package maximbravo.com.Maxim2048;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static Board b = new Board();
    private static LinearLayout r;
    private static LinearLayout l;
    private static int score = 0;
    private static TextView gameOverTextView;
    private static FileManipulator fileManipulator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        fileManipulator = new FileManipulator(this);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            if( extras.getIntArray("row1") != null) {
                b.convertIntArrayToBoard(0, extras.getIntArray("row1"));
                b.convertIntArrayToBoard(1, extras.getIntArray("row2"));
                b.convertIntArrayToBoard(2, extras.getIntArray("row3"));
                b.convertIntArrayToBoard(3, extras.getIntArray("row4"));
            }

        }

        r = (LinearLayout) findViewById(R.id.r);
        gameOverTextView = (TextView) r.findViewById(R.id.game_over);
        b.initializeBoard(fileManipulator.getStringInFile());
        b.drawBoard();
        updateScore();
        TextView helpCount = (TextView) findViewById(R.id.helpCount);
        helpCount.setText("Help Left: " + helpLeft);
        r.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                //Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
                if(b.updateBoard(0)) {
                    if(!gameOver) {
                        b.drawBoard();
                    }
                    b.drawBoard();
                } else {
                    gameOver();
                }
            }
            public void onSwipeRight() {
                //Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                if(b.updateBoard(1)) {
                    if (!gameOver) {
                        b.drawBoard();
                    }
                    b.drawBoard();
                } else {
                    gameOver();
                }
            }
            public void onSwipeLeft() {
                //Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
                if(b.updateBoard(3)) {
                    if(!gameOver) {
                        b.drawBoard();
                    }
                    b.drawBoard();
                } else {
                    gameOver();
                }
            }
            public void onSwipeBottom() {
                //Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                if(b.updateBoard(2)) {
                    if(!gameOver) {
                        b.drawBoard();
                    }
                    b.drawBoard();
                } else {
                    gameOver();
                }
            }

        });
    }
    public static void gameOver(){
        gameOverTextView.setText("Game Over!\n Your score is: " + score);
        gameOver = true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.preferences:
                Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
                int[] row1 = getIntArrayOfRow(0);
                int[] row2 = getIntArrayOfRow(1);
                int[] row3 = getIntArrayOfRow(2);
                int[] row4 = getIntArrayOfRow(3);
                intent.putExtra("row1", row1);
                intent.putExtra("row2", row2);
                intent.putExtra("row3", row3);
                intent.putExtra("row4", row4);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private static int[] getIntArrayOfRow(int row){
        int[] result = new int[4];
        for(int i = 0; i < 3; i++){
            result[i] = b.get(row, i).getId();
        }
        return result;
    }
    public static void addToScore(int toAdd){
        score+= toAdd;
    }
    public static void updateScore(){
        TextView scoreText = (TextView) r.findViewById(R.id.score);
        scoreText.setText("" + score);
    }
    private static int stage = 0;
    private static int size = 30;
    private static boolean help = false;
    private static boolean gameOver = false;
    public static int helpLeft = 3;
    public static void drawButtonAt(Square s, int id) {

        //r.setBackgroundColor(Color.BLACK);
        Button l = (Button) r.findViewById(id);

        l.setTextSize(size);
        l.setTextColor(Color.BLACK);

        if(help)
        {
            l.setEnabled(s.getJustAdded());
            if(s.getJustAdded() == true){
                l.setBackgroundColor(Color.DKGRAY);
            }
            //l.setBackgroundColor(Color.BLACK);
        } else {
            l.setEnabled(false);
            String col = s.getSquareColor();
            int c = Color.parseColor(col);
            l.setBackgroundColor(c);
        }
        //l.setTextColor(Color.WHITE);
        l.setText("" + s.getId());
    }



    private static String printValues(int[] values) {
        String result = "";
        for (int i = 0; i < values.length; i++) {
            if (values[i] > 9) {
                result += values[i] + "   ";
                stage = 1;
                //size = 47;
            } else if (stage == 1) {
                result += values[i] + "     ";
            }
            if (values[i] > 99) {
                result += values[i] + "  ";
                stage = 2;
                //size+=2;
            } else if (stage == 2) {
                result += values[i] + "   ";
            }
            if(stage == 0){
                result += values[i] + "   ";
            }
        }
        return result;
    }

    public void refresh(View view){
        b.initializeBoard();
        gameOverTextView.setText("");
        helpLeft = 3;
        gameOver = false;
        help = false;
        TextView helpCount = (TextView) findViewById(R.id.helpCount);
        helpCount.setText("Help Left: " + helpLeft);
        Button helpButton = (Button) findViewById(R.id.help);
        helpButton.setEnabled(true);
        score = 0;
        updateScore();
        b.drawBoard();
    }
    public void help(View view){

        if(helpLeft > 0) {
            help = true;
            b.drawBoard();
            Toast t = Toast.makeText(this, "Please select the square to remove", Toast.LENGTH_SHORT);
            t.show();
            help = false;
        } else {
            Toast t = Toast.makeText(this, "You have no more helps left", Toast.LENGTH_SHORT);
            t.show();
            view.setEnabled(false);
        }
    }
    public void remove(View view){
        int[] rowAndColumn = getRowAndColumn(view.getId());
        Square tapedon = b.get(rowAndColumn[0], rowAndColumn[1]);
        b.set(rowAndColumn[0], rowAndColumn[1], 0);
        helpLeft--;
        TextView helpCount = (TextView) findViewById(R.id.helpCount);
        helpCount.setText("Help Left: " + helpLeft);
//        TextView t = (TextView) findViewById(R.id.printtext);
//        t.setText("" + tapedon.getJustAdded());
        b.drawBoard();
        //t.setText("row: " + rowAndColumn[0] + ", column: " + rowAndColumn[1]);
        //b.drawBoard();
    }

    public int[] getRowAndColumn(int buttonId){
        int[] result = new int[2];
        switch (buttonId){
            case R.id.id1:
                result[0] = 0;
                result[1] = 0;
                break;
            case R.id.id2:
                result[0] = 0;
                result[1] = 1;
                break;
            case R.id.id3:
                result[0] = 0;
                result[1] = 2;
                break;
            case R.id.id4:
                result[0] = 0;
                result[1] = 3;
                break;
            case R.id.id5:
                result[0] = 1;
                result[1] = 0;
                break;
            case R.id.id6:
                result[0] = 1;
                result[1] = 1;
                break;
            case R.id.id7:
                result[0] = 1;
                result[1] = 2;
                break;
            case R.id.id8:
                result[0] = 1;
                result[1] = 3;
                break;
            case R.id.id9:
                result[0] = 2;
                result[1] = 0;
                break;
            case R.id.id10:
                result[0] = 2;
                result[1] = 1;
                break;
            case R.id.id11:
                result[0] = 2;
                result[1] = 2;
                break;
            case R.id.id12:
                result[0] = 2;
                result[1] = 3;
                break;
            case R.id.id13:
                result[0] = 3;
                result[1] = 0;
                break;
            case R.id.id14:
                result[0] = 3;
                result[1] = 1;
                break;
            case R.id.id15:
                result[0] = 3;
                result[1] = 2;
                break;
            case R.id.id16:
                result[0] = 3;
                result[1] = 3;
                break;
        }
        return result;
    }


    @Override
    public void onStop() {
        fileManipulator.updateStringInFile(b.toString(helpLeft));
        super.onStop();

    }
}
