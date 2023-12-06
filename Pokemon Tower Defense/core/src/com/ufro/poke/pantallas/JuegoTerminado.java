package com.ufro.poke.pantallas;

import javax.swing.*;
import java.awt.*;

public class JuegoTerminado extends JPanel {
    public JuegoTerminado(){

        this.setLayout(null);

        this.setBackground(Color.BLACK);
        ImageIcon fondo = new ImageIcon("assets/interfaz/fondoPoke3.png");

        JLabel label = new JLabel("Fin del Juego");
        JLabel back = new JLabel(fondo);

        label.setBounds(400,400,200,100);
        this.add(label);
        label.setVisible(true);

        back.setBounds(0,0,1280,720);
        back.setVisible(true);

        JLabel imagenTxt = new JLabel(new ImageIcon("assets/interfaz/juegoTerminadoPoke.png"));
        imagenTxt.setBounds( 280,150,740,160);
        this.add(imagenTxt);
        imagenTxt.setVisible(true);
        add(back);
    }
}
