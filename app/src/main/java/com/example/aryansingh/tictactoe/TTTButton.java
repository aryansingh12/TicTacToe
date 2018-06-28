package com.example.aryansingh.tictactoe;

import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.widget.Button;
import android .content.Context;
/**
 * Created by Aryan Singh on 6/18/2018.
 */

    public class TTTButton extends AppCompatButton {

     // new class to store the info of a new button
     // getter and setter for the player

    private int player = MainActivity.noPlayer;

        public TTTButton(Context context){
            super(context);
        }

        public void setPlayer(int player){

            this.player = player;

            if(player == MainActivity.playerX){
                setBackgroundResource(R.drawable.x);
            }

            else if(player == MainActivity.playerO){
                setBackgroundResource(R.drawable.o);
            }


            setEnabled(false); // cant click twice
        }

        public int getPlayer(){
            return this.player;
        }


        public boolean isEmpty(){
            return this.player == MainActivity.noPlayer;
        }

    }
