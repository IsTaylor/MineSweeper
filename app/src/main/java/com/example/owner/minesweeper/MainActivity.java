package com.example.owner.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private TextView winnerMessage;
    private TextView tooManyFlagsMessage;
    private LinearLayout layoutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MineSweeperView mineSweeperView = (MineSweeperView) findViewById(R.id.mineSweeperView);
        final Button btnClear = (Button) findViewById(R.id.btClear);
        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.btToggle);
        winnerMessage = (TextView) findViewById(R.id.winnerMessage);
        tooManyFlagsMessage = (TextView) findViewById(R.id.tooManyFlagsMessage);
        layoutContent = (LinearLayout) findViewById(R.id.layoutContent);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mineSweeperView.clearBoard();
                winnerMessage.setText("");
                toggleButton.setChecked(false);
            }
        });

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mineSweeperView.setFlagMode(toggleButton.isChecked());
            }
        });
    }

    //TODO
    //extract string resources
    public void setWinnerMessage(boolean isWinner) {
        String text;
        if (isWinner) {
            text = "Congratulations Winner!";
            Snackbar.make(layoutContent, "Winner!", Snackbar.LENGTH_LONG).show();
        } else {
            text = "Awh, you lost :(";
            Snackbar.make(layoutContent, "Loser", Snackbar.LENGTH_LONG).show();
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

}

