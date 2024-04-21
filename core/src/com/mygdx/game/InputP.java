package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.tools.EraserTool;
import com.mygdx.game.tools.HandTool;
import com.mygdx.game.tools.MarkerTool;

public class InputP implements InputProcessor {

    BoardScreen board;

    InputP(BoardScreen board) {
        this.board = board;
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (board.ui.cursorOverUi) board.ui.uiReleased = true;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        board.ui.uiReleased = false;
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (board.currentTool instanceof HandTool) {
            if (amountY == 1) board.boardCam.zoom *= 1.1f;
            if (amountY == -1) board.boardCam.zoom /= 1.1f;
            board.ui.lastShowedZoom = System.nanoTime();
        }
        else if (board.currentTool instanceof EraserTool || board.currentTool instanceof MarkerTool) {
            if (amountY == 1) board.currentTool.changeSize(5);
            if (amountY == -1) board.currentTool.changeSize(-5);
        }

        return false;
    }
}
