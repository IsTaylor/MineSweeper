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
    private boolean isDone = false;
    public static int numSquares;
    private int flagCount;
    private static Context context;

    public boolean flagMode;

    public MineSweeperView(Context c, @Nullable AttributeSet attrs) {
        super(c, attrs);
        context = c;

        flagCount = 0;
        flagMode = false;

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

        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);

        drawGameArea(canvas);

        drawSelected(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!isDone) {

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
                        ((StartScreen) getContext()).setTooManyFlagsMessage();
                    } else {
                        ((StartScreen) getContext()).clearTooManyFlagsMessage();
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
                            ((StartScreen) getContext()).clearTooManyFlagsMessage();
                        }
                    }

                    if (curSquare.isBomb()) {
                        gameLost();
                    }
                }
            }

            invalidate();

            return super.onTouchEvent(event);

        } else {
            return true;
        }
    }

    private void drawSelected(Canvas canvas) {
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

        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);

        for (int i = 1; i < numSquares + 1; i++) {
            canvas.drawLine(i * (getWidth() / numSquares), 0, i * (getWidth() / numSquares), getHeight(), paintLine);
        }

        for (int i = 1; i < numSquares + 1; i++) {
            canvas.drawLine(0, i * (getHeight() / numSquares), getWidth(), i * (getHeight() / numSquares), paintLine);
        }


    }

    private void gameLost() {
        ((StartScreen) getContext()).setWinnerMessage(false);
        isDone = true;
    }

    private void gameWon() {
        ((StartScreen) getContext()).setWinnerMessage(true);
        isDone = true;
    }

    public static void setNumSquares(int num) {
        numSquares = num;
        MineSweeperModel.getInstance().setNumSquares(num);
    }


    public void clearBoard() {
        MineSweeperModel.getInstance().resetGame();
        ((StartScreen) getContext()).clearTooManyFlagsMessage();
        flagMode = false;
        flagCount = 0;
        isDone = false;
        invalidate();
    }

}
