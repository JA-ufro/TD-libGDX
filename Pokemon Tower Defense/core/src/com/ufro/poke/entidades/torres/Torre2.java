package com.ufro.poke.entidades.torres;

import com.ufro.poke.PokeTower;

public class Torre2 extends Torre {

    /**
     * constructor de Torre.
     * Asigna una imagen a una textura y luego una textura a un sprite.
     * Define la posicion del sprite.
     * Genera un cuerpo usando el metodo setBody().
     *
     * @param game
     * @param x
     * @param y
     * @param textura
     */
    public Torre2(PokeTower game, float x, float y, String textura) {
        super(game, x, y, textura);
    }
}
