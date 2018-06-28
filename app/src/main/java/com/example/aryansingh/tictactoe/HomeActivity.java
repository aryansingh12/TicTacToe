package com.example.aryansingh.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {


    EditText e1;
    EditText e2;
    Button b;

    public static final String FIRST_KEY = "firstPlayer";
    public static final String SECOND_KEY = "secondPlayer";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        e1 = findViewById(R.id.firstEditText);
        e2 = findViewById(R.id.secondEditText);
        b = (Button) findViewById(R.id.start);

    }

    public void start(View view) {

        String name1 = e1.getText().toString();
        String name2 = e2.getText().toString();

        if (name1.length() == 0){
            e1.setError("Not a valid name");
        }
        else if(name2.length() == 0) {
            e2.setError("Not a valid name");
        }
        else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(FIRST_KEY, name1);
            intent.putExtra(SECOND_KEY, name2);

            startActivity(intent);
        }
    }
}
