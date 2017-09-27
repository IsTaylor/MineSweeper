package com.example.owner.minesweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private int numSquares;

    public MineSweeperView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

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
        }

        invalidate();

        return super.onTouchEvent(event);
    }

    private void drawSelected(Canvas canvas) {
        for (int i = 0; i < numSquares; i++) {

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
