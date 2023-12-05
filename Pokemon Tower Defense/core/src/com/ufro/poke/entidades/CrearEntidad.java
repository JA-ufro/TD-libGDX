package com.ufro.poke.entidades;

import com.ufro.poke.ExceptionPath;

public interface CrearEntidad {


    void cargarTextura(String textura) throws ExceptionPath;
    void setBody();

}
