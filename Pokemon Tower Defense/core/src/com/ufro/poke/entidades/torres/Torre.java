package com.ufro.poke.entidades.torres;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.ufro.poke.ExceptionPath;
import com.ufro.poke.PokeTower;
import com.ufro.poke.entidades.CrearEntidad;
import com.ufro.poke.entidades.enemigos.Enemigo;

public abstract class Torre implements CrearEntidad {

    int state;
    protected BodyDef bodyDef;
    public Body bodyTower;
    protected FixtureDef fixtureDef;
    protected PokeTower game;
    protected Sprite sprite;
    protected Texture texture;
    protected float rad=300;

    public Torre(PokeTower game, float x, float y,String textura) {
        this.game = game;

        try {
            cargarTextura(textura);
        } catch (ExceptionPath e) {
            throw new RuntimeException(e);
        }
        sprite = new Sprite(texture);
        sprite.setPosition(x,y);

        setBody();

    }

    public void cargarTextura(String textura) throws ExceptionPath {
        texture = new Texture(textura);
    }

    public void setBody() {
        this.state = STATE_NORMAL;

        bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(sprite.getX(),sprite.getY());

        bodyTower = this.game.mundo.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(rad);

        sprite.setScale(2);

        fixtureDef=new FixtureDef();
        fixtureDef.shape=shape;
        fixtureDef.density=0;
        fixtureDef.isSensor = true;

        bodyTower.createFixture(fixtureDef);

        shape.dispose();

    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);

    }

    public float getRad(){

        return this.rad;
    }

    public void  update(){
        sprite.setPosition(
                bodyTower.getPosition().x,
                bodyTower.getPosition().y);
    }

    public void detectarEnemigo(Enemigo enemigo, Torre torre){
        float x1 = enemigo.bodyEntity.getPosition().x;
        float y1 = enemigo.bodyEntity.getPosition().y;

        float x2 = torre.bodyTower.getPosition().x;

        float y2 = torre.bodyTower.getPosition().y;

        int x = (int) Math.abs(x2-x1);
        int y = (int) Math.abs(y2-y1);

        int distance = (int)(Math.hypot(x,y));
        if (distance<=torre.getRad()){
            enemigo.recibirDaño();

        }
    }

}
