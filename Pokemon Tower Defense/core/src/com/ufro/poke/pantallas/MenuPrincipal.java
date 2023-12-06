package com.ufro.poke.pantallas;

import javax.swing.*;

public class MenuPrincipal extends JPanel {

    public MenuPrincipal(){

        setLayout(null);

        ImageIcon icon = new ImageIcon("assets/interfaz/tituloPoke.png");
        ImageIcon fondo = new ImageIcon("assets/interfaz/fondoPoke.png");


        JLabel titulo = new JLabel(icon);
        JLabel back = new JLabel(fondo);

        back.setBounds(0,0,1280,720);
        back.setVisible(true);
        titulo.setBounds(300,50,680,240);
        titulo.setVisible(true);

        add(titulo);
        add(back);

    }

}
