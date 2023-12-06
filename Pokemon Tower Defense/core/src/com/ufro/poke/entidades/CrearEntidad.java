package com.ufro.poke.entidades;

import com.ufro.poke.ExceptionPath;

public interface CrearEntidad {

    int STATE_NORMAL=0;
    void cargarTextura(String textura) throws ExceptionPath;
    void setBody();

}
