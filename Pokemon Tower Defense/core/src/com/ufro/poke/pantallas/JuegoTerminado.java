package com.ufro.poke.pantallas;

import javax.swing.*;
import java.awt.*;

public class JuegoTerminado extends JPanel {
    public JuegoTerminado(){

        this.setLayout(null);

        this.setBackground(Color.BLACK);
        JLabel label = new JLabel("Fin del Juego");
        label.setBounds(400,400,200,100);
        this.add(label);
        label.setVisible(true);

        JLabel fondo = new JLabel(new ImageIcon("assets/interfaz/juegoTerminadoPoke.png"));
        fondo.setBounds( 50,150,740,160);
        this.add(fondo);
        fondo.setVisible(true);
    }
}
