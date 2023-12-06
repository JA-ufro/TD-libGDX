package com.ufro.poke.entidades.enemigos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.ufro.poke.ExceptionPath;
import com.ufro.poke.PokeTower;
import com.ufro.poke.entidades.CrearEntidad;

public abstract class Enemigo implements CrearEntidad {

    int state;
    protected BodyDef bodyDef;
    public Body bodyEntity;
    protected FixtureDef fixtureDef;
    protected PokeTower game;
    protected Sprite sprite;
    protected Texture texture;
    static boolean Derecha = false;
    static boolean Izquierda = false;
    static boolean Abajo = false;
    static float X;
    static float Y;
    protected final float vidaMax;
    protected float vidaActual;
    public int reaparecer;

    public Enemigo(PokeTower game, float x, float y,String s,float h,int rS) {
        this.game=game;
        this.vidaMax =h;
        this.vidaActual =h;
        this.reaparecer =rS;
        this.X=x;
        this.Y=y;

        try {
            cargarTextura(s);
        } catch (ExceptionPath e) {

            System.out.println("Se ha encontado una excepcion al cargar una textura");
            System.exit(0);

        }

        sprite = new Sprite(texture);
        sprite.setPosition(x,y);

        setBody();
    }

    public void cargarTextura(String s)throws ExceptionPath {
        texture = new Texture(s);
    }

    public void setBody() {

        this.state = STATE_NORMAL;

        bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(sprite.getX(), sprite.getY());

        bodyEntity = this.game.mundo.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(22,50,new Vector2(sprite.getWidth() / 2,
                sprite.getHeight() / 2),0);
        sprite.setScale(2);

        fixtureDef=new FixtureDef();
        fixtureDef.shape=shape;
        fixtureDef.density=0;
        fixtureDef.isSensor=true;

        bodyEntity.createFixture(fixtureDef);

        shape.dispose();
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void update(){


        if (Derecha ==true){
            bodyEntity.applyLinearImpulse(
                    new Vector2(200f,0),
                    bodyEntity.getLocalCenter(),true);

        }else if (Izquierda ==true) {
            bodyEntity.applyLinearImpulse(
                    new Vector2(-200f,0),
                    bodyEntity.getLocalCenter(),true);
        } else if (Abajo ==true) {
            bodyEntity.applyLinearImpulse(
                    new Vector2(0, -200f),
                    bodyEntity.getLocalCenter(), true);
        }



        sprite.setPosition(bodyEntity.getPosition().x, bodyEntity.getPosition().y);

    }

    public void mover() {

        if (this.bodyEntity.getPosition().x <= 650f) {
            Derecha = true;

        } else if (this.bodyEntity.getPosition().x == 650) {
            Derecha = false;

        } else if (this.bodyEntity.getPosition().y >= 150) {
            Derecha = false;
            Abajo = true;
        } else if (this.bodyEntity.getPosition().x <= 1200) {
            Derecha = true;
            Abajo = false;
        }else if (this.bodyEntity.getPosition().x > 1210){

            this.reaparecerEnemigo(this);
        }else{
            eliminarEnemigo();
        }

    }

    public void reaparecerEnemigo(Enemigo entity){

        bodyEntity.setLinearVelocity(0,0);
        bodyEntity.setTransform(-150,620,0);

    }
    public void eliminarEnemigo() {
        bodyEntity.setLinearVelocity(0,0);
        bodyEntity.setTransform(-1000,-1000,0);
    }

    public void recibirDaño(){
        this.vidaActual--;
        if (this.vidaActual <=0){
            this.reaparecer--;

            this.vidaActual = vidaMax;
            game.descVida();
            if (this.reaparecer ==0){
                this.eliminarEnemigo();

            }else {
                this.reaparecerEnemigo(this);
            }
        }
    }

}
