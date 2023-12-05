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
    static final int STATE_NORMAL=0;

    int state;

    protected BodyDef bodyDef;
    public Body bodyEntity;

    protected FixtureDef fixtureDef;
    protected PokeTower game;
    protected Sprite sprite;
    protected Texture texture;

    static boolean R= false;
    static boolean L= false;
    static boolean D= false;
    static float X;
    static float Y;
    protected final float health;
    protected float vida;
    public int reSpawn;

    public Enemigo(PokeTower game, float x, float y,String s,float h,int rS) {
        this.game=game;
        this.health=h;
        this.vida=h;
        this.reSpawn=rS;
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

    /**
     * Crea un nuevo BodyDef().
     * Asigna el tipo DynamicBody a bodyDef.
     * Le asigna la posision del sprite a bodyDef.
     * Crea body en el mundo.
     * Crea un nuevo PolygonShape.
     * Se le asigna las dimenciones al shape y
     * se asigna la posicion del shape
     * Se le asigna un escala al sprite
     *
     * se genera la shape
     */
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

    //medodo que dibuja al sprite

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void update(){

        /*batch.begin();
        batch.draw(spEnemy.getKeyFrame(statetime),pos.x,pos.y,120,120);
        batch.end();*/

        if (R==true){
            bodyEntity.applyLinearImpulse(
                    new Vector2(200f,0),
                    bodyEntity.getLocalCenter(),true);

        }else if (L==true) {
            bodyEntity.applyLinearImpulse(
                    new Vector2(-200f,0),
                    bodyEntity.getLocalCenter(),true);
        } else if (D==true) {
            bodyEntity.applyLinearImpulse(
                    new Vector2(0, -200f),
                    bodyEntity.getLocalCenter(), true);
        }



        sprite.setPosition(bodyEntity.getPosition().x, bodyEntity.getPosition().y);

    }

    public void mover() {

        //intento de movimineto de el enemigo

        if (this.bodyEntity.getPosition().x <= 650f) {
            R = true;

        } else if (this.bodyEntity.getPosition().x == 650) {
            R = false;

        } else if (this.bodyEntity.getPosition().y >= 150) {
            R = false;
            D = true;
        } else if (this.bodyEntity.getPosition().x <= 1200) {
            R = true;
            D = false;
        }else if (this.bodyEntity.getPosition().x > 1210){

            this.eliminarEnemigo(this);
        }else{
            sacarDelMapa();
        }

    }

    //traslada al enemigo
    public void eliminarEnemigo(Enemigo entity){

        bodyEntity.setLinearVelocity(0,0);
        bodyEntity.setTransform(-150,620,0);

    }
    public void sacarDelMapa() {
        bodyEntity.setLinearVelocity(0,0);
        bodyEntity.setTransform(-1000,-1000,0);
    }

    //disminuye la vida del enemigo
    public void hacerDaño(){
        this.vida--;
        if (this.vida<=0){
            this.reSpawn--;

            this.vida=health;
            game.descVida();
            if (this.reSpawn==0){
                this.sacarDelMapa();

            }else {
                this.eliminarEnemigo(this);
            }
        }
    }

}
