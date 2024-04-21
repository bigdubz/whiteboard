package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.tools.HandTool;
import com.mygdx.game.tools.MarkerTool;

import java.text.DecimalFormat;

public class UI {

    BoardScreen bd;
    long lastShowedZoom = System.nanoTime();
    final OrthographicCamera cam;
    final ScreenViewport viewport;
    private final Array<Button> buttons;
    private final Button hand;
    private final Button marker;
    private final Button eraser;
    public final Rectangle rect;
    final Rectangle clrSelectionRect;
    final Array<Color> colors;
    final Rectangle cursor;
    boolean cursorOverUi = false;
    public boolean showColorSelection = false;
    public boolean uiReleased = false;

    UI(BoardScreen board) {
        this.bd = board;
        cam = new OrthographicCamera();
        viewport = new ScreenViewport(cam);
        buttons = new Array<>();
        hand = new Button(this, new Texture(Gdx.files.internal("hand.png")), bd.hand, 10);
        marker = new Button(this, new Texture(Gdx.files.internal("marker.png")), bd.marker, 64 + 25);
        eraser = new Button(this, new Texture(Gdx.files.internal("eraser.png")), bd.eraser, (64 + 25) * 2);
        rect = new Rectangle(0, 0, 250, 75);
        cursor = new Rectangle(0,0,1,1);
        colors = new Array<>();
        addButtons();
        addColors();
        clrSelectionRect = new Rectangle(0, 95, 405, 405f/colors.size + 5);
    }

    void offsetButtons() {
        for (Button button: buttons) button.rect.x = rect.x + button.xOffset;
    }

    void updateCursor() {
        cursorOverUi = cursor.overlaps(rect) || (showColorSelection && cursor.overlaps(clrSelectionRect));
        if (cursorOverUi || bd.currentTool instanceof HandTool || uiReleased)
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        else Gdx.graphics.setSystemCursor(Cursor.SystemCursor.None);
        cursor.x = Gdx.input.getX();
        cursor.y = cam.viewportHeight - Gdx.input.getY();
    }

    void draw() {
        clrSelectionRect.x = rect.x - (clrSelectionRect.width - rect.width)/2f - 2.5f;
        updateCursor();
        viewport.apply(true);
        bd.wb.batch.setProjectionMatrix(cam.combined);
        bd.wb.batch.begin();
        bd.wb.font.draw(bd.wb.batch, bd.currentTool.name, 50, 100);
        drawZoom();
        bd.wb.batch.end();
        drawMidUi();
    }

    private void addColors() {
        colors.add(Color.WHITE);
        colors.add(Color.RED);
        colors.add(Color.ORANGE);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.MAGENTA);
        colors.add(Color.VIOLET);
        colors.add(Color.PINK);
    }

    private void addButtons() {
        buttons.add(hand);
        buttons.add(marker);
        buttons.add(eraser);
    }

    private void drawZoom() {
        if (System.nanoTime() - lastShowedZoom <= 3e+9) {
            bd.wb.font.setColor(0, 0.7f, 0.7f, 1);
            bd.wb.font.draw(bd.wb.batch, new DecimalFormat("##.00").format(bd.boardCam.zoom),
                    cam.viewportWidth / 2f - 17.5f, cam.viewportHeight / 2f - 9);
        }
    }


    private void drawMidUi() {
        bd.wb.UIShaper.setProjectionMatrix(cam.combined);
        bd.wb.UIShaper.begin(ShapeRenderer.ShapeType.Filled);
        bd.wb.UIShaper.setColor(new Color(0, 0.7f, 0.7f, 1));
        bd.wb.UIShaper.rect(rect.x, rect.y, rect.width, rect.height);
        drawRect();
        if (showColorSelection && bd.currentTool instanceof MarkerTool) colorSelection();
        bd.wb.UIShaper.end();

        bd.wb.batch.begin();
        drawTools();
        bd.wb.batch.end();
    }

    private void colorSelection() {
        float w = clrSelectionRect.width/colors.size;
        bd.wb.UIShaper.setColor(0, 0.7f, 0.7f, 1);
        bd.wb.UIShaper.rect(
                clrSelectionRect.x - 2.5f, clrSelectionRect.y, clrSelectionRect.width + 5, clrSelectionRect.height
        );
        for (int i = 0; i < colors.size; i++) {
            Color c = colors.get(i);
            Rectangle cRect = new Rectangle(clrSelectionRect.x + i*w + 2.5f, clrSelectionRect.y + 5, w - 5, w - 5);
            if (cursor.overlaps(cRect)) {
                bd.wb.UIShaper.setColor(new Color(0, 0.3f, 0.3f, 1));
                bd.wb.UIShaper.rect(cRect.x - 2.5f, cRect.y - 2.5f, cRect.width + 5, cRect.height + 5);
                if (Gdx.input.isButtonJustPressed(0)) {
                    bd.marker.setColor(c);
                    showColorSelection = false;
                }
            }
            bd.wb.UIShaper.setColor(c);
            bd.wb.UIShaper.rect(cRect.x, cRect.y, cRect.width, cRect.height);
        }
    }

    private void drawTools() {
        for (Button button: buttons) button.draw();
    }

    private void drawRect() {
        for (Button button: buttons) button.drawRect();
    }

    public void dispose() {
        for (Button button: buttons) button.dispose();
    }
}
