package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

public class Stroke {

    private final Array<int[]> points;
    private final BoardScreen board;
    private final float size;
    Color color;

    public Stroke(BoardScreen board, Color color, float size) {
        this.board = board;
        this.color = color;
        this.size = size;
        points = new Array<>();
    }

    /**
     * Draws a filled circle at each point in the stroke, then draws a rectangular line between each point
     */
    void draw() {
        board.wb.shaper.setColor(color);
        for (int i = 0; i < points.size; i++) {
            board.wb.shaper.circle(points.get(i)[0], points.get(i)[1], size /2f);
            if (i < points.size - 1) {
                board.wb.shaper.rectLine(
                        points.get(i)[0], points.get(i)[1], points.get(i + 1)[0], points.get(i + 1)[1], size
                );
            }
        }
    }

    /**
     * Adds a point to the strokes list
     * @param x The x-coordinate of the point
     * @param y The y-coordinate of the point
     */
    public void addPoint(float x, float y) {
//        try {Thread.sleep(15);} catch (Exception ignored) {}
        points.add(new int[]{(int) x, (int) y});
    }
}
