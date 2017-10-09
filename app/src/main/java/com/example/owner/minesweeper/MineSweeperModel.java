package com.example.owner.minesweeper;

import android.util.Log;

import java.util.Random;

/**
 * 1. NumSquares is set in model and view
 */

public class MineSweeperModel {

    private static MineSweeperModel mineSweeperModel = null;
    private int numSquares = 6;
    private int mineCount;

    private MineSweeperModel() {
        mineCount = 0;
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

    public int getMineCount() {
        return mineCount;
    }

    private Square[][] model = new Square[numSquares][numSquares];

    //TODO
    //generate where the mines are on the field
    private void generateMines() {
        int numMines = numSquares;
        double probability = (float) numMines / (numSquares * numSquares);
        for (int i = 0; i < numSquares; i++) {
            for (int j = 0; j < numSquares; j++) {
                boolean isBomb = decideMine(probability);
                model[i][j].setIsBomb(isBomb);
            }

        }
    }

    //TODO
    //random num generator
    private boolean decideMine(double probability) {
        Random rand = new Random();
        float randNum = rand.nextFloat();
        if (randNum <= probability) {
            mineCount++;
            return true;
        } else {
            return false;
        }
    }



    private void initializeModel() {
        for (int i = 0; i < numSquares; i++) {
            for (int j = 0; j < numSquares; j++) {
                model[i][j] = new Square();
            }

        }

    }

    public int generateNumber(int x, int y) {
        int neighborMines = 0;
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (i >= 0 && i < numSquares) {
                    if (j >= 0 && j < numSquares) {
                        if (model[i][j] != model[x][y] && model[i][j].isBomb()) {
                            neighborMines++;
                        }
                    }
                }

            }
        }
        return neighborMines;

    }

    public boolean checkIfWon() {
        int numberFlagged = 0;
        for (int i = 0; i < numSquares; i++) {
            for (int j = 0; j < numSquares; j++) {
                if (model[i][j].isBomb() && model[i][j].isFlag()) {
                    numberFlagged++;
                }
            }
        }
        if (numberFlagged == mineCount) {
            return true;
        }
        return false;
    }


    public void setClickedContent(int x, int y) {
        model[x][y].setIsClicked(true);
    }

    public void setFlagContent(int x, int y, boolean isFlag) {
        model[x][y].setIsFlag(isFlag);
    }

    public Square getFieldContent(int x, int y) {
        return model[x][y];
    }

    public void resetGame() {
        mineCount = 0;
        initializeModel();
        generateMines();
    }

    public class Square {

        private boolean isFlag;
        private boolean isBomb;
        private boolean isClicked;

        public Square() {
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
