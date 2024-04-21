package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.BoardScreen;

public class HandTool extends Tool {

    public HandTool(BoardScreen board) {
        super(board);
        name = "Hand Tool";
    }

    /**
     * Drag camera
     */
    @Override
    public void useTool() {
        if (Gdx.input.isButtonJustPressed(0)) board.ui.showColorSelection = false;

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            board.boardCam.translate(
                    -Gdx.input.getDeltaX() * board.boardCam.zoom,
                    Gdx.input.getDeltaY() * board.boardCam.zoom
            );
    }

    @Override
    public void changeSize(float diff) {

    }
}
