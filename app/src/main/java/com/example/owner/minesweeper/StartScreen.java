package com.example.owner.minesweeper;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class StartScreen extends AppCompatActivity {


    private TextView winnerMessage;
    private TextView tooManyFlagsMessage;
    private LinearLayout layoutContent;
    private int numSquares;
    MineSweeperView mineSweeperView;
    ToggleButton toggleButton;
    final boolean FLAGMODE = false;
    final boolean TRYMODE = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        if (getIntent().hasExtra(getString(R.string.KEY_NUMSQUARES))) {
            numSquares = getIntent().getIntExtra(getString(R.string.KEY_NUMSQUARES), 0);
        }

        mineSweeperView = (MineSweeperView) findViewById(R.id.mineSweeperView);
        setNumSquares();

        final Button btnClear = (Button) findViewById(R.id.btClear);
        toggleButton = (ToggleButton) findViewById(R.id.btToggle);
        winnerMessage = (TextView) findViewById(R.id.winnerMessage);
        tooManyFlagsMessage = (TextView) findViewById(R.id.tooManyFlagsMessage);
        layoutContent = (LinearLayout) findViewById(R.id.layoutContent);

        reset();

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mineSweeperView.setFlagMode(toggleButton.isChecked());
            }
        });
    }

    private void reset() {
        mineSweeperView.clearBoard();
        winnerMessage.setVisibility(View.GONE);
        toggleButton.setChecked(FLAGMODE);
    }

    public void setWinnerMessage(boolean isWinner) {
        String text;
        if (isWinner) {
            text = getString(R.string.winner_text);
            Snackbar.make(layoutContent, R.string.winner_snackbar, Snackbar.LENGTH_LONG).show();
        } else {
            text = getString(R.string.loser_text);
            Snackbar.make(layoutContent, R.string.loser_snackbar, Snackbar.LENGTH_LONG).show();
        }
        winnerMessage.setText(text);
    }

    //TODO
    //extract string resources
    public void setTooManyFlagsMessage() {
        tooManyFlagsMessage.setText("Too many flags!");
    }

    public void clearTooManyFlagsMessage() {
        tooManyFlagsMessage.setText("");

    }

    public void setNumSquares() {
        MineSweeperView.setNumSquares(numSquares);
    }

}

