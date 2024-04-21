package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.tools.EraserTool;
import com.mygdx.game.tools.HandTool;
import com.mygdx.game.tools.MarkerTool;
import com.mygdx.game.tools.Tool;



public class BoardScreen implements Screen {

    public final Whiteboard wb;
    public final OrthographicCamera boardCam;
    public final Array<Stroke> strokes;
    private final ScreenViewport boardViewport;
    final MarkerTool marker = new MarkerTool(this);
    final EraserTool eraser = new EraserTool(this);
    final HandTool hand = new HandTool(this);
    public final UI ui;
    Tool currentTool;


    public BoardScreen(Whiteboard board) {
        this.wb = board;
        strokes = new Array<>();
        boardCam = new OrthographicCamera();
        currentTool = hand;
        boardViewport = new ScreenViewport(boardCam);
        ui = new UI(this);
        Gdx.input.setInputProcessor(new InputP(this));
    }

    private void drawTool() {
        if (!ui.cursorOverUi && !ui.uiReleased) currentTool.useTool();
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1) ||
                Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) currentTool = hand;
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) currentTool = marker;
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) currentTool = eraser;
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            if (!strokes.isEmpty()) strokes.removeValue(strokes.get(strokes.size - 1), true);
        }
    }

    private void drawStrokes() {
        wb.shaper.setProjectionMatrix(boardViewport.getCamera().combined);
        wb.shaper.begin(ShapeRenderer.ShapeType.Filled);
        boardViewport.apply();
        for (Stroke stroke : strokes) stroke.draw();
        drawTool();
        wb.shaper.end();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0.1f, 0.1f, 1);
        drawStrokes();
        ui.draw();
    }

    @Override
    public void resize(int width, int height) {
        boardViewport.update(width, height);
        ui.viewport.update(width, height);
        ui.rect.x = ui.cam.viewportWidth / 2f - ui.rect.width/2;
        ui.offsetButtons();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        ui.dispose();
        strokes.clear();
    }
}
