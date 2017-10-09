package com.example.owner.minesweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by owner on 9/27/17.
 */

public class MineSweeperView extends View {

    private Paint paintBg;
    private Paint paintLine;
    private Paint paintBgLine;
    private Paint paintO;
    private Paint paintX;
    private Paint paintMine;
    private int numSquares;
    private int flagCount;
    private static Context context;

    public boolean flagMode;

    public MineSweeperView(Context c, @Nullable AttributeSet attrs) {
        super(c, attrs);
        context = c;

        //TODO
        //if user wins, but flagcount > mineCount
        flagCount = 0;

        flagMode = false;

        numSquares = MineSweeperModel.getInstance().getNumSquares();

        paintBg = new Paint();
        paintBg.setColor(Color.BLACK);
        paintBg.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(7);

        paintBgLine = new Paint();
        paintBgLine.setColor(Color.parseColor("#A9A9A9"));
        paintBgLine.setStyle(Paint.Style.STROKE);
        paintBgLine.setStrokeWidth(7);

        paintO = new Paint();
        paintO.setColor(Color.parseColor("#b4eeb4"));
        paintO.setStyle(Paint.Style.STROKE);
        paintO.setStrokeWidth(7);

        paintX = new Paint();
        paintX.setColor(Color.parseColor("#efb7b7"));
        paintX.setStyle(Paint.Style.FILL);
        paintX.setStrokeWidth(7);

        paintMine = new Paint();
        paintMine.setColor(Color.WHITE);
        paintMine.setStyle(Paint.Style.FILL);

    }

    public void setFlagMode(boolean flagMode) {
        this.flagMode = flagMode;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //background
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);

        //cells
        drawGameArea(canvas);

        //draw selected
        drawSelected(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int tX = ((int) event.getX()) / (getWidth() / numSquares);
            int tY = ((int) event.getY()) / (getHeight() / numSquares);

            if (flagMode) {

                if (MineSweeperModel.getInstance().getFieldContent(tX, tY).isFlag()) {
                    MineSweeperModel.getInstance().setFlagContent(tX, tY, false);
                    flagCount--;
                } else {
                    MineSweeperModel.getInstance().setFlagContent(tX, tY, true);
                    flagCount++;
                }

                if (flagCount > MineSweeperModel.getInstance().getMineCount()) {
                    ((MainActivity) getContext()).setTooManyFlagsMessage();
                } else {
                    ((MainActivity) getContext()).clearTooManyFlagsMessage();
                    if (MineSweeperModel.getInstance().checkIfWon()) {
                        gameWon();
                    }
                }


            } else {
                MineSweeperModel.Square curSquare = MineSweeperModel.getInstance().getFieldContent(tX, tY);
                MineSweeperModel.getInstance().setClickedContent(tX, tY);
                if (curSquare.isFlag()) {
                    curSquare.setIsFlag(false);
                    flagCount--;
                    if (flagCount <= MineSweeperModel.getInstance().getMineCount()) {
                        ((MainActivity) getContext()).clearTooManyFlagsMessage();
                    }
                }

                if (curSquare.isBomb()) {
                    gameLost();
                }
            }
        }

        invalidate();

        return super.

                onTouchEvent(event);

    }

    private void drawSelected(Canvas canvas) {
        int numSquares = MineSweeperModel.getInstance().getNumSquares();
        for (int i = 0; i < numSquares; i++) {
            for (int j = 0; j < numSquares; j++) {
                MineSweeperModel.Square curSquare = MineSweeperModel.getInstance().getFieldContent(i, j);

                if (curSquare.isClicked()) {

                    if (curSquare.isBomb()) {
                        paintMine.setTextSize(100);
                        canvas.drawText("M",
                                i * (getWidth() / numSquares),
                                (j + 1) * (getHeight() / numSquares),
                                paintMine);

                    } else {

                        int numToDisplay = MineSweeperModel.getInstance().generateNumber(i, j);

                        paintX.setTextSize(100);
                        canvas.drawText(String.valueOf(numToDisplay),
                                i * (getWidth() / numSquares),
                                (j + 1) * (getHeight() / numSquares),
                                paintX);

                    }
                } else if (MineSweeperModel.getInstance().getFieldContent(i, j).isFlag()) {
                    //if you'e gotten all flags, you win!

                    int centerX = i * getWidth() / numSquares + getWidth() / (2 * numSquares);
                    int centerY = j * getHeight() / numSquares + getHeight() / (2 * numSquares);
                    canvas.drawCircle(centerX,
                            centerY,
                            getWidth() / numSquares / 3,
                            paintO);

                }
            }
        }
    }

    private void drawGameArea(Canvas canvas) {

        //border
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);

        //vertical lines
        for (int i = 1; i < numSquares + 1; i++) {
            canvas.drawLine(i * (getWidth() / numSquares), 0, i * (getWidth() / numSquares), getHeight(), paintLine);
        }

        //horizontal lines
        for (int i = 1; i < numSquares + 1; i++) {
            canvas.drawLine(0, i * (getHeight() / numSquares), getWidth(), i * (getHeight() / numSquares), paintLine);
        }


    }


    //TODO
    //change false to hardcoded variable
    private void gameLost() {
        ((MainActivity) getContext()).setWinnerMessage(false);
    }

    private void gameWon() {
        ((MainActivity) getContext()).setWinnerMessage(true);
    }


    public void clearBoard() {
        MineSweeperModel.getInstance().resetGame();
        ((MainActivity) getContext()).clearTooManyFlagsMessage();
        flagMode = false;
        invalidate();
    }

}
