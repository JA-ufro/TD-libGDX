package com.ufro.poke;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ufro.poke.entidades.enemigos.*;
import com.ufro.poke.entidades.torres.Torre;
import com.ufro.poke.entidades.torres.Torre1;
import com.ufro.poke.entidades.torres.Torre2;
import com.ufro.poke.entidades.torres.Torre3;

public class PokeTower extends ApplicationAdapter implements Screen {
	private static final float WORLD_WHIDTH=1280;
	private static final float WORLD_HEIGHT=960;

	private Camera cam;
	private SpriteBatch batch;
	private Box2DDebugRenderer box2d;
	public World mundo;
	private Viewport viewport;
	private OrthographicCamera boxCam;

	private Enemigo enemigo1;
	private Enemigo enemigo2;
	private Enemigo enemigo3;
	private Enemigo enemigo4;

	private Torre torre1;
	private Torre torre2;
	private Torre torre3;

	private Stage stage;


	public int contGame=10;
	private int temp = 50;

	public PokeTower() {
		create();
	}

	@Override
	public void create() {

		batch = new SpriteBatch();
		mundo = new World(new Vector2(0,0),true);

		//se instancian el enemigo y la torre

		enemigo1 = new Enemigo1(this,100,620f,"assets/entidades/enemigos/enemigo1.png",200,3);
		enemigo2 = new Enemigo2(this, -100f,620f,"assets/entidades/enemigos/enemigo2.png",350,3);
		enemigo3 = new Enemigo3(this,-300,620f,"assets/entidades/enemigos/enemigo3.png",500,3);
		enemigo4 = new Enemigo4(this, -1600,620f,"assets/entidades/enemigos/enemigo4.png",940,1);

		torre1 = new Torre1(this,560, 505,"assets/entidades/torres/torre1.png");
		torre2 = new Torre2(this,560,265 ,"assets/entidades/torres/torre2.png");
		torre3 = new Torre3(this, 780,220,"assets/entidades/torres/torre3.png");

		viewport = new ExtendViewport(WORLD_WHIDTH,WORLD_HEIGHT);
		viewport.update(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		stage = new Stage(viewport,batch);
		cam = viewport.getCamera();

		box2d = new Box2DDebugRenderer();
		boxCam = (OrthographicCamera) viewport.getCamera();



	}


	public void draw() {

		//dibuja el mapa en el fondo

		batch.begin();
		batch.disableBlending();

		batch.draw(new Texture("assets/interfaz/mapaPoke.png"), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		batch.enableBlending();
		batch.end();

	}




	@Override
	public void show() {
	}


	public void movimiento(){
		enemigo1.mover();
		enemigo1.update();

		enemigo2.mover();
		enemigo2.update();

		enemigo3.mover();
		enemigo3.update();

		enemigo4.mover();
		enemigo4.update();

	}

	@Override
	public void render(float delta) {

		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}

		Gdx.gl.glClearColor(.6f,0.0f,1,.2f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mundo.step(1f/60f,6,2);
		draw();
		torre1.detectEnemy(enemigo1, torre1);
		torre1.detectEnemy(enemigo1,torre2);
		torre1.detectEnemy(enemigo1,torre3);

		torre1.detectEnemy(enemigo2, torre1);
		torre1.detectEnemy(enemigo2,torre2);
		torre1.detectEnemy(enemigo2,torre3);

		torre1.detectEnemy(enemigo3, torre1);
		torre1.detectEnemy(enemigo3,torre2);
		torre1.detectEnemy(enemigo3,torre3);

		torre1.detectEnemy(enemigo4, torre1);
		torre1.detectEnemy(enemigo4,torre2);
		torre1.detectEnemy(enemigo4,torre3);



		//dibuja a las torres
		batch.begin();
		torre1.draw(batch);
		torre2.draw(batch);
		torre3.draw(batch);
		batch.end();

		//dibujan a los enemigos
		batch.begin();
		enemigo1.draw(batch);
		enemigo2.draw(batch);
		enemigo3.draw(batch);
		batch.end();

		batch.begin();
		enemigo4.draw(batch);
		batch.end();

		movimiento();

		torre1.update();
		torre2.update();
		torre3.update();


		cicloJuego();

		//muestra las shaps del mundo
		//box2d.render(mundo, boxCam.combined);

	}



	@Override
	public void hide() {
	}

	public void descVida(){
		contGame--;
	}



	public void cicloJuego(){
		if (contGame == 0) {
			temp--;
			if (temp == 0){
				Gdx.app.exit();

			}

		}
	}


	@Override
	public void pause() {
		Gdx.app.exit();

	}

	@Override
	public void dispose () {
		box2d.dispose();
		batch.dispose();

	}
}
