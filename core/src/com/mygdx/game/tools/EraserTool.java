package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.BoardScreen;
import com.mygdx.game.Stroke;

public class EraserTool extends Tool {


    Stroke stroke = null;
    Color color = new Color(0, 0.1f, 0.1f, 1);

    public EraserTool(BoardScreen board) {
        super(board);
        name = "Eraser";
        size = 30;
    }

    @Override
    public void useTool() {
        if (Gdx.input.isButtonJustPressed(0)) board.ui.showColorSelection = false;
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) clearBoard();
        if (Gdx.input.isButtonJustPressed(0)) createEraseStroke();
        if (Gdx.input.isButtonPressed(0)) erase();
        board.wb.shaper.setColor(0, 0.2f, 0.2f, 0.1f);
        board.wb.shaper.circle(
                (Gdx.input.getX() - Gdx.graphics.getWidth()/2.0f)*board.boardCam.zoom + board.boardCam.position.x,
                (Gdx.graphics.getHeight()/2.0f - Gdx.input.getY())*board.boardCam.zoom + board.boardCam.position.y,
                (size*board.boardCam.zoom)/2
        );
    }

    @Override
    public void changeSize(float diff) {
        size += diff;
        if (size < 30) size = 30;
        if (size > 100) size = 100;
    }

    /**
     * Adds a point with the color of the background to erase
     */
    private void erase() {
        if (stroke == null) return;
        stroke.addPoint(
                (Gdx.input.getX() - Gdx.graphics.getWidth()/2.0f)*board.boardCam.zoom + board.boardCam.position.x,
                (Gdx.graphics.getHeight()/2.0f - Gdx.input.getY())*board.boardCam.zoom + board.boardCam.position.y
        );
    }

    /**
     * Creates a new stroke with the color of the background
     */
    private void createEraseStroke() {
        stroke = new Stroke(board, color, size*board.boardCam.zoom);
        board.strokes.add(stroke);
    }

    private void clearBoard() {
        board.strokes.clear();
    }
}
