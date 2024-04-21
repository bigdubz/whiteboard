package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class Whiteboard extends Game {
	public BitmapFont font;
	public ShapeRenderer shaper;
	public ShapeRenderer UIShaper;
	public SpriteBatch batch;
	BoardScreen board;

	@Override
	public void create () {
		font = new BitmapFont();
		shaper = new ShapeRenderer();
		UIShaper = new ShapeRenderer();
		shaper.setAutoShapeType(true);
		UIShaper.setAutoShapeType(true);
		board = new BoardScreen(this);
		batch = new SpriteBatch();
		this.setScreen(board);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		board.dispose();
		shaper.dispose();
		UIShaper.dispose();
		font.dispose();
		batch.dispose();
	}
}
