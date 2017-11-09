package com.example.owner.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.facebook.shimmer.ShimmerFrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etGuess = (EditText) findViewById(R.id.etGuess);
        Button startBtn = (Button) findViewById(R.id.startBtn);
        Button infoBtn = findViewById(R.id.infoBtn);

        ShimmerFrameLayout shimmerView = findViewById(R.id.shimmer_view);
        shimmerView.startShimmerAnimation();

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intentInfo = new Intent();
                intentInfo.setClass(MainActivity.this, Main2Activity.class);

                startActivity(intentInfo);
            }
        });


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etGuess.getText())) {
                    int numSquares = Integer.parseInt(etGuess.getText().toString());

                    final Intent intentDetails = new Intent();
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

