package com.ufro.poke.pantallas;

import javax.swing.*;

public class Integrantes extends JPanel{
    public Integrantes(){

        setLayout(null);
        ImageIcon fondo = new ImageIcon("assets/interfaz/fondoPoke2.png");
        ImageIcon icon = new ImageIcon("assets/interfaz/integrantesPoke.png"); // Nuestra info

        JLabel back = new JLabel(fondo);
        JLabel titulo = new JLabel(icon);

        back.setBounds(0,0,1280,720);
        back.setVisible(true);
        titulo.setBounds(60,40,730,610);
        titulo.setVisible(true);

        add(titulo);
        add(back);

    }

}
