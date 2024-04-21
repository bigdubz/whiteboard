package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.tools.MarkerTool;
import com.mygdx.game.tools.Tool;


public class Button {

    final UI ui;
    final Texture image;
    Rectangle rect;
    Tool tool;
    float xOffset;

    Button(UI ui, Texture image, Tool tool, float xOffset) {
        this.ui = ui;
        this.image = image;
        this.tool = tool;
        this.xOffset = xOffset;
        rect = new Rectangle(0, 5, 64, 64);
    }

    void draw() {
        ui.bd.wb.batch.draw(image, rect.x, rect.y);
        if (ui.cursor.overlaps(rect) && Gdx.input.isButtonJustPressed(0)) {
            if (tool instanceof MarkerTool) ui.showColorSelection = true;
            ui.bd.currentTool = tool;
        }
    }

    void drawRect() {
        if (ui.cursor.overlaps(rect)) {
            if (Gdx.input.isButtonPressed(0)) ui.bd.wb.UIShaper.setColor(new Color(0, 0.1f, 0.1f, 1));
            else ui.bd.wb.UIShaper.setColor(new Color(0, 0.3f, 0.3f, 1));
            ui.bd.wb.UIShaper.rect(rect.x - 5, rect.y - 2.5f, rect.width + 10, rect.height + 5);
        }
    }

    void dispose() {
        image.dispose();
    }
}
