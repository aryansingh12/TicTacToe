package com.example.aryansingh.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout rootLayout;

    public static final int playerX = 1;
    public static final int playerO = 0;
    public static final int noPlayer = -1;

    public static final int incomplete = 1;
    public static final int xWon = 2;
    public static final int oWon = 3;
    public static final int draw = 4;

    public static  int currentStatus;

    public int currentPlayer ;

    public int size = 3;

    public ArrayList<LinearLayout> rows;
    public TTTButton[][] board;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupBoard();
        TextView textView1 = findViewById(R.id.tv1);
        TextView textView2 = findViewById(R.id.tv2);

        Intent intent = getIntent();
        String Fname = intent.getStringExtra(HomeActivity.FIRST_KEY);
        String Sname = intent.getStringExtra(HomeActivity.SECOND_KEY);

        textView1.setText(Fname);
        textView2.setText(Sname);

    }

    public void setupBoard(){ // can also use for reset

        currentStatus = incomplete;

        rows = new ArrayList<>();

        rootLayout = findViewById(R.id.rootLayout);
        board = new TTTButton[size][size];
        rootLayout.removeAllViews();

        for(int i=0;i<size;i++){
            LinearLayout linearLayout = new LinearLayout(this );
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);

            linearLayout.setLayoutParams(layoutParams);

            rootLayout.addView(linearLayout); // adding to the root layout
            rows.add(linearLayout);

        }

        for(int i=0;i<size;i++){ // row

            for(int j=0;j<size;j++){ // column

                TTTButton button = new TTTButton(this);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);


                button.setLayoutParams(layoutParams);
                button.setOnClickListener(this);

                LinearLayout row = rows.get(i);
                row.addView(button);
                board[i][j] = button;
                

            }
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        if(id == R.id.reset){
            setupBoard();
            togglePlayer();
        }
        else if(id == R.id.size3){
            size = 3;
            setupBoard();
        }
        else if(id == R.id.size4){
        size = 4;
        setupBoard();
        }
        else if(id == R.id.size5){
        size = 5;
        setupBoard();
        }
        return super.onOptionsItemSelected(item);
    }
    // sub menu inside a menu which gives you the option of choosing 3x3, 4x4 and 5x5


    @Override
    public void onClick(View view) {
        //only when the game is incomplete
        if(currentStatus == incomplete) {
            TTTButton button = (TTTButton) view;
            button.setPlayer(currentPlayer);
            togglePlayer();
            gameOver();
        }
    }

    public void togglePlayer(){
        if(currentPlayer == playerO)
            currentPlayer = playerX;

        else
            currentPlayer = playerO;

    }

    public void gameOver(){

        //rows
        for(int i=0;i<size;i++){
            boolean rowSame = true;
            TTTButton first = board[i][0];

            for(int j=0;j<size;j++){
                // we have to check the player by using getPlayer();
                TTTButton button = board[i][j];
                if(button.isEmpty() || button.getPlayer() != first.getPlayer())
                    rowSame = false;
            }
            if(rowSame){
                int playerWon = first.getPlayer();
                checkStatus(playerWon);
                return;
            }
        }


        //columns
            for(int j=0;j<size;j++) {
                boolean colSame = true;
                TTTButton first = board[0][j];

                for (int i = 0; i < size; i++) {
                    TTTButton button = board[i][j];
                    if(button.isEmpty() || button.getPlayer() != first.getPlayer())
                        colSame = false;

                }

                if (colSame) {
                    int playerWon = first.getPlayer();
                    checkStatus(playerWon);
                    return;
                }
            }

            //1st diagonal


        boolean diag1Same = true;
        TTTButton first1 = board[0][0];
                for (int j = 0; j < size; j++) {
                    TTTButton button = board[j][j];

                    if (button.isEmpty() || button.getPlayer() != first1.getPlayer()){
                        diag1Same = false;
                        break;
                    }
                }
            if(diag1Same){
                int playerWon = first1.getPlayer();
                checkStatus(playerWon);
                return;
            }

            //2nd diagonal
        boolean diag2Same = true;
        TTTButton first2 = board[0][size-1];

        for(int i=0;i<size;i++) {
            TTTButton button = board[i][size - 1 - i];

            if (button.isEmpty() || button.getPlayer() != first2.getPlayer()) {
                diag2Same = false;
                break;
            }
        }
            if(diag2Same){
                int playerWon = first2.getPlayer();
                checkStatus(playerWon);
                return;
            }


        // checking if something is empty or not
        for(int i=0;i<size;i++) {
            for (int j = 0; j < size; j++) {
                TTTButton button = board[i][j];
            if(button.isEmpty())
                return ;
            }
        }

        // this will work when none of the function returns the method.

        Toast.makeText(this,"Game Draw",Toast.LENGTH_LONG).show();
    }

    public void checkStatus(int playerWon){
        if(playerWon == playerX){
            currentStatus = xWon;
            Toast.makeText(this,"Player X Won",Toast.LENGTH_LONG).show();
        }
        else{
            currentStatus = oWon;
            Toast.makeText(this,"Player O won",Toast.LENGTH_LONG).show();
        }
    }
}

