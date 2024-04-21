package com.mygdx.game.tools;

import com.mygdx.game.BoardScreen;

import java.text.DecimalFormat;

public abstract class Tool {

    BoardScreen board;
    public String name;
    float size;

    public Tool(BoardScreen board) {
        this.board = board;
    }

    /**
     * Handles input and output of the tool
     */
    public abstract void useTool();

    /**
     * Changes the tool size
     * @param diff Difference in size
     */
    public abstract void changeSize(float diff);


}
