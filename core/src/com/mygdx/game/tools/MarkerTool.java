package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.BoardScreen;
import com.mygdx.game.Stroke;

public class MarkerTool extends Tool{


    Color color = Color.WHITE;

    public MarkerTool(BoardScreen board) {
        super(board);
        size = 5;
        name = "Marker";
    }

    @Override
    public void useTool() {
        if (Gdx.input.isButtonJustPressed(0)) {
            board.ui.showColorSelection = false;
            addStroke();
        }
        if (Gdx.input.isButtonPressed(0)) draw();
        if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) changeSize(1);
        if (Gdx.input.isKeyJustPressed(Input.Keys.U)) changeSize(-1);
        board.wb.shaper.setColor(color);
        board.wb.shaper.circle(
                (Gdx.input.getX() - Gdx.graphics.getWidth()/2.0f)*board.boardCam.zoom + board.boardCam.position.x,
                (Gdx.graphics.getHeight()/2.0f - Gdx.input.getY())*board.boardCam.zoom + board.boardCam.position.y,
                (size)/2
        );
    }

    /**
     * Creates a new stroke when LMB is just pressed
     */
    private void addStroke() {
        board.strokes.add(new Stroke(board, color, size));
    }

    /**
     * Adds a point on the board relative to the position of the cursor
     */
    private void draw() {
        int index = board.strokes.size - 1;
        if (index == -1) return;
        board.strokes.get(index).addPoint(
                (Gdx.input.getX() - Gdx.graphics.getWidth()/2.0f)*board.boardCam.zoom + board.boardCam.position.x,
                (Gdx.graphics.getHeight()/2.0f - Gdx.input.getY())*board.boardCam.zoom + board.boardCam.position.y
        );
    }


    /**
     * Changes the color of the stroke
     * @param color The color to change to
     */
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void changeSize(float diff) {
        size += diff;
        if (size < 1) size = 1;
        if (size > 50) size = 50;
    }
}
