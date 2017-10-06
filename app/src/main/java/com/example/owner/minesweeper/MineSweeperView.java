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
    private int numSquares;

    public boolean flagMode;

    public MineSweeperView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

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
        paintX.setStyle(Paint.Style.STROKE);
        paintX.setStrokeWidth(7);

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
                MineSweeperModel.getInstance().setFlagContent(tX, tY);
            } else {

                MineSweeperModel.getInstance().setClickedContent(tX, tY);
            }
        }

        invalidate();

        return super.onTouchEvent(event);
    }

    private void drawSelected(Canvas canvas) {
        int numSquares = MineSweeperModel.getInstance().getNumSquares();
        for (int i = 0; i < numSquares; i++) {
            for (int j = 0; j < numSquares; j++) {

                if (MineSweeperModel.getInstance().getFieldContent(i, j).isClicked()) {

                    canvas.drawLine((i * getWidth() / numSquares) + 5,
                            (j * getHeight() / numSquares) + 5,
                            ((i + 1) * getWidth() / numSquares) - 5,
                            ((j + 1) * getHeight() / numSquares) - 5,
                            paintX);


                } else if (MineSweeperModel.getInstance().getFieldContent(i, j).isFlag()) {
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

}
