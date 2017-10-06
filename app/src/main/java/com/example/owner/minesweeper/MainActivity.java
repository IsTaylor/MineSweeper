package com.example.owner.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private boolean flagMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MineSweeperView mineSweeperView = (MineSweeperView) findViewById(R.id.mineSweeperView);

        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.btToggle);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mineSweeperView.setFlagMode(toggleButton.isChecked());
            }
        });

        //final MineSweeperView MineSweepView = (MineSweeperView) findViewById(R.id.mineSweeperView);

    }


}
