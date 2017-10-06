package com.example.owner.minesweeper;

import android.util.Log;

/**
 * 1. NumSquares is set in model and view
 */

public class MineSweeperModel {

    private static MineSweeperModel mineSweeperModel = null;
    private int numSquares = 5;

    private MineSweeperModel() {
        initializeModel();
        generateMines();
    }

    public static MineSweeperModel getInstance() {
        if (mineSweeperModel == null) {
            mineSweeperModel = new MineSweeperModel();
        }

        return mineSweeperModel;
    }

    public int getNumSquares() {
        return numSquares;
    }

    private square[][] model = new square[numSquares][numSquares];

    public void resetGame() {
        initializeModel();
        generateMines();
    }

    private void initializeModel() {
        for (int i = 0; i < numSquares; i++) {
            for (int j = 0; j < numSquares; j++) {
                model[i][j] = new square();
            }

        }

    }

    //TODO
    //adjust clicked content
    public void setClickedContent(int x, int y) {
        model[x][y].setIsClicked(true);
    }


    //TODO
    //generate where the mines are on the field
    private void generateMines() {
        model[1][1].setIsBomb(true);
        for (int i = 0; i < numSquares; i++) {
            model[i][2].setIsBomb(true);
        }
    }

    public square getFieldContent(int x, int y) {
        return model[x][y];
    }

    public class square {

        private boolean isFlag;
        private boolean isBomb;
        private boolean isClicked;

        public square() {
            isFlag = false;
            isClicked = false;
            isBomb = false;
        }

        public boolean isFlag() {
            return isFlag;
        }

        public boolean isBomb() {
            return isBomb;
        }

        public boolean isClicked() {
            return isClicked;
        }

        public void setIsFlag(boolean isFlag) {
            this.isFlag = isFlag;
        }

        public void setIsBomb(boolean isBomb) {
            this.isBomb = isBomb;
        }

        public void setIsClicked(boolean isClicked) {
            this.isClicked = isClicked;
        }
    }
}
