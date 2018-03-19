package maximbravo.com.Chilled2048;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
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
    private static LinearLayout m;
    private static TextView overlay;
    private static LinearLayout l;
    public static boolean canRemove = false;
    public static int score = 0;
    private static TextView gameOverTextView;
    private static TextView helpCount;
    public static boolean hasNotAdded = true;
    private static FileManipulator fileManipulator;
    private MediaPlayer mpl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mpl = MediaPlayer.create(this, R.raw.bloop);
        helpCount = (TextView) findViewById(R.id.helpCount);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        fileManipulator = new FileManipulator(this);
        Bundle extras = getIntent().getExtras();
        b.initializeBoard(fileManipulator.getStringInFile());
        if(extras != null){
            if(extras.getBoolean("refresh")){
                refresh();
            }
            if( extras.getIntArray("row1") != null) {
                b.convertIntArrayToBoard(0, extras.getIntArray("row1"));
                b.convertIntArrayToBoard(1, extras.getIntArray("row2"));
                b.convertIntArrayToBoard(2, extras.getIntArray("row3"));
                b.convertIntArrayToBoard(3, extras.getIntArray("row4"));
            }

        }

        r = (LinearLayout) findViewById(R.id.r);
        m = (LinearLayout) findViewById(R.id.m);
        overlay = (TextView) findViewById(R.id.overlay);
        b.drawBoard();
        updateScore();

        helpCount.setText("Help Left: " + helpLeft);
        overlay.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {

            public void onSwipeTop() {
                //Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();

                if(b.updateBoard(0)) {
                    if(!gameOver) {
                        b.drawBoard();
                    }
                    b.drawBoard();
//                    if(b.getHighestSquare()){
//                        helpLeft+=3;
//                        updateHelpCount();
//                        hasNotAdded = false;
//                    }
                    //playSound();
                }
                if(boardLocked()){
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
//                    if(b.getHighestSquare()){
//                        helpLeft+=3;
//                        updateHelpCount();
//
//                        hasNotAdded = false;
//                    }
                    //playSound();
                }
                if(boardLocked()){
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
//                    if(b.getHighestSquare()){
//                        helpLeft+=3;
//                        updateHelpCount();
//
//                        hasNotAdded = false;
//                    }
                    //playSound();
                }
                if(boardLocked()){
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
//                    if(b.getHighestSquare()){
//                        helpLeft+=3;
//                        updateHelpCount();
//
//                        hasNotAdded = false;
//                    }
                   // playSound();
                }
                if(boardLocked()){
                    gameOver();
                }
            }

        });
    }

    public void playSound(){

        mpl.start();
    }

    public boolean boardLocked(){
        Matrix m = new Matrix(b.getSquareArrayBoard());
        if(m.getEmptySpaceCount() >= 1){
            return false;
        }

        for(int i = 0; i < 4; i++){
            for(int j =0; j < 3; j++){
                if( b.get(i,j).getId() == b.get(i, j+1).getId()){
                    return false;
                }
            }
        }

        for(int i = 0; i < 3; i++){
            for(int j =0; j < 4; j++){
                if(b.get(i,j).getId() == b.get(i+1,j).getId()){
                    return false;
                }
            }
        }
        return true;
    }
    public void gameOver(){
        Intent i = new Intent(MainActivity.this, Restart.class);
        i.putExtra("gameover", true);
        i.putExtra("score", score);
        MediaPlayer mp = MediaPlayer.create(this, R.raw.game_over);
        mp.start();
        startActivity(i);
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
            case R.id.help:
                Intent i = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private static int[] getIntArrayOfRow(int row){
        int[] result = new int[4];
        for(int i = 0; i < 4; i++){
            result[i] = b.get(row, i).getId();
        }
        return result;
    }
    public static void addToScore(int toAdd){
        score+= toAdd;
    }
    public static void updateScore(){
        TextView scoreText = (TextView) m.findViewById(R.id.score);
        scoreText.setText("" + score);
    }
    private static int stage = 0;
    private static int size = 30;
    private static boolean help = false;
    private static boolean gameOver = false;
    public static int helpLeft = 3;
    public static void addToHelpLeft(int i){
        helpLeft += i;
        updateHelpCount();
    }
    public static void updateHelpCount(){
        helpCount.setText("Help Left: " + helpLeft);
    }
    public static void drawButtonAt(Square s, int id) {

        //r.setBackgroundColor(Color.BLACK);
        TextView l = (TextView) r.findViewById(id);

        l.setTextSize(size);
        l.setTextColor(Color.BLACK);

//        if(help)
//        {
//            l.setEnabled(s.getJustAdded());
//            if(s.getJustAdded() == true){
//                l.setBackgroundColor(Color.DKGRAY);
//            }
//            help = false;
//            //l.setBackgroundColor(Color.BLACK);
//        } else {
            l.setEnabled(true);
            String col = s.getSquareColor();
            int c = Color.parseColor(col);

            l.setBackgroundColor(c);

//        }
        //l.setTextColor(Color.WHITE);
        if(s.getId()>999){
            l.setTextSize(16);
        }
        if(s.getId() != 0) {
            l.setText("" + s.getId());
        } else {
            l.setText("");
        }
        updateHelpCount();

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

    public void restart(View view){
       Intent i = new Intent(MainActivity.this, Restart.class);
       startActivity(i);
    }
    public void refresh(){
        b.initializeBoard();
       // Toast.makeText(getApplicationContext(), "refreshed!!!", Toast.LENGTH_LONG).show();
        //gameOverTextView.setText("");
        hasNotAdded = true;
        helpLeft = 3;
        gameOver = false;
        canRemove = false;
        help = false;
        helpCount = (TextView) findViewById(R.id.helpCount);
        helpCount.setText("Help Left: " + helpLeft);
        Button helpButton = (Button) findViewById(R.id.help);
        helpButton.setEnabled(true);
        score = 0;
        b.resetHighest();
        updateScore();
        b.drawBoard();
    }
    public void help(View view){
        overlay.setVisibility(View.GONE);
        if(helpLeft > 0) {
            if(!help){
                Button button = (Button) view;
                button.setText("Use Help");
                ColorDrawable cd = new ColorDrawable(Color.GRAY);
                view.setBackground(cd);
                b.drawBoard();
                Toast t = Toast.makeText(this, "Please select the square to remove", Toast.LENGTH_SHORT);
                t.show();
                help = true;
            } else {
                b.drawBoard();
                Button button = (Button) view;
                overlay.setVisibility(View.VISIBLE);
                button.setText("Use Help");
                ColorDrawable cd = new ColorDrawable(getResources().getColor(R.color.grey_background));
                view.setBackground(cd);
                help = false;
            }

        } else {
            Toast t = Toast.makeText(this, "You have no more helps left", Toast.LENGTH_SHORT);
            t.show();
            view.setEnabled(false);
            overlay.setVisibility(View.VISIBLE);
        }


    }
    public void remove(View view){

        int[] rowAndColumn = getRowAndColumn(view.getId());
        Square tapedon = b.get(rowAndColumn[0], rowAndColumn[1]);

        if(tapedon.getId() != 0) {
            if(tapedon.getId() <= 8){
                b.set(rowAndColumn[0], rowAndColumn[1], 0);
                helpLeft--;
                helpCount = (TextView) findViewById(R.id.helpCount);
                helpCount.setText("Help Left: " + helpLeft);
//        TextView t = (TextView) findViewById(R.id.printtext);
//        t.setText("" + tapedon.getJustAdded());
                overlay.setVisibility(View.VISIBLE);
                Button helpButton = (Button) findViewById(R.id.help);
                ColorDrawable cd = new ColorDrawable(getResources().getColor(R.color.grey_background));
                helpButton.setBackground(cd);
                b.drawBoard();
            }
        } else {
            overlay.setVisibility(View.VISIBLE);
            Button helpButton = (Button) findViewById(R.id.help);
            ColorDrawable cd = new ColorDrawable(getResources().getColor(R.color.grey_background));
            helpButton.setBackground(cd);
            b.drawBoard();
        }
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
        if(hasNotAdded) {
            fileManipulator.updateStringInFile(b.toString(helpLeft, score, 1));
        } else {
            fileManipulator.updateStringInFile(b.toString(helpLeft, score, 0));
        }
        super.onStop();

    }
}
