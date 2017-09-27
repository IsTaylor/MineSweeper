package com.example.owner.minesweeper;

/**
 * 1. NumSquares is set in model and view
 */

public class MineSweeperModel {

    private static MineSweeperModel mineSweeperModel = null;

    private int numSquares;

    private MineSweeperModel() {
        numSquares = 5;
    }

    public int getNumSquares() {
        return numSquares;
    }

    public static MineSweeperModel getInstance() {
        if (mineSweeperModel == null) {
            mineSweeperModel = new MineSweeperModel();
        }

        return mineSweeperModel;
    }

    private square[][] model = new square[numSquares][numSquares];

    //TODO
    //generate where the mines are on the field
    private void generateMines() {
        for (int i = 0; i < numSquares; i++) {
            model[i][3].setIsBomb(true);
        }
    }

    private class square {

        private boolean isFlag;
        private boolean isBomb;
        private boolean isClicked;

        public square() {
            isFlag = false;
            isClicked = false;
            isBomb = false;
        }

        public boolean getIsFlag() {
            return isFlag;
        }

        public boolean getIsBomb() {
            return isBomb;
        }

        public boolean getIsClicked() {
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
