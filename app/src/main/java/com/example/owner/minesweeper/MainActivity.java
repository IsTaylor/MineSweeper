package com.example.owner.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etGuess = (EditText) findViewById(R.id.etGuess);
        Button startBtn = (Button) findViewById(R.id.startBtn);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etGuess.getText())) {
                    int numSquares = Integer.parseInt(etGuess.getText().toString());

                    Intent intentDetails = new Intent();
                    intentDetails.setClass(MainActivity.this, StartScreen.class);
                    intentDetails.putExtra(getString(R.string.KEY_NUMSQUARES), numSquares);

                    startActivity(intentDetails);

                } else {
                    etGuess.setError(getString(R.string.error_no_user_input));
                }


            }
        });


    }

}

