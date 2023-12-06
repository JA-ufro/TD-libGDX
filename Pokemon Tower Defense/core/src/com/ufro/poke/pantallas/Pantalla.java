package com.ufro.poke.pantallas;

import com.ufro.poke.PokeTower;

public class Pantalla extends com.badlogic.gdx.Game{
    @Override
    public void create() {
        setScreen(new PokeTower());
    }
}
