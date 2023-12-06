package com.ufro.poke;

import com.ufro.poke.pantallas.Integrantes;
import com.ufro.poke.pantallas.JuegoTerminado;
import com.ufro.poke.pantallas.MenuPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPrincipal extends JFrame {

    private MenuPrincipal menuPrincipal;
    private com.ufro.poke.pantallas.Integrantes integrantes;
    private JuegoTerminado juegoTerminado;
    private JPanel pnPrincipal;
    private CardLayout GestordePaneles;
    JButton Jugar;
    JButton Integrantes;
    JButton Regresar;
    JButton VolverAMenu;
    JButton Reintentar;

    public PantallaPrincipal() {

        menuPrincipal = new MenuPrincipal();

        integrantes = new Integrantes();
        juegoTerminado = new JuegoTerminado();

        GestordePaneles = new CardLayout();
        initBotones();
        initJframe();
        initJpanel();

    }

    private void initJpanel(){

        pnPrincipal = new JPanel();
        pnPrincipal.setLayout(GestordePaneles);
        pnPrincipal.add(menuPrincipal, "Menu Principal");
        pnPrincipal.add(integrantes, "Sobre Nosotros");
        pnPrincipal.add(juegoTerminado,"Fin del Juego");
        pnPrincipal.setVisible(true);

        this.add(pnPrincipal);
    }

    private void initJframe(){

        setTitle("PokeTowerDefence");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initBotones(){
        Jugar = new JButton(new ImageIcon("assets/interfaz/jugarPoke.png"));
        Integrantes = new JButton(new ImageIcon("assets/interfaz/sobrenosotrosPoke.png"));
        Regresar = new JButton(new ImageIcon("assets/interfaz/atrasPoke.png"));
        VolverAMenu = new JButton(new ImageIcon("assets/interfaz/menuPoke.png"));
        Reintentar = new JButton(new ImageIcon("assets/interfaz/reintentarPoke.png"));

        Jugar.setBounds(480,350,270,145);
        Integrantes.setBounds(300,520,690,110);
        Regresar.setBounds(10,150,290,130);

        VolverAMenu.setBounds(330,350,620,140);
        Reintentar.setBounds(400,500,500,120);

        Jugar.setVisible(true);
        Integrantes.setVisible(true);
        Regresar.setVisible(true);
        VolverAMenu.setVisible(true);
        Reintentar.setVisible(true);

        menuPrincipal.add(Jugar);
        menuPrincipal.add(Integrantes);
        integrantes.add(Regresar);
        juegoTerminado.add(VolverAMenu);
        juegoTerminado.add(Reintentar);

        Jugar.addActionListener(new AccionAlternaPanel());
        Integrantes.addActionListener(new AccionAlternaPanel());
        Regresar.addActionListener(new AccionAlternaPanel());
        VolverAMenu.addActionListener(new AccionAlternaPanel());
        Reintentar.addActionListener(new AccionAlternaPanel());

    }

    private class AccionAlternaPanel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();

            if (boton.equals(Integrantes)) {

                GestordePaneles.show(pnPrincipal, "Sobre Nosotros");

            }
            if (boton.equals(Regresar)){

                GestordePaneles.show(pnPrincipal,"Menu Principal");

            }
            if (boton.equals(Jugar)){

                setVisible(false);

                new DesktopLauncher();
                GestordePaneles.show(pnPrincipal,"Fin del Juego");
                setVisible(true);
            }
            if (boton.equals(VolverAMenu)){
                GestordePaneles.show(pnPrincipal,"Menu Principal");
            }
            if (boton.equals(Reintentar)){
                new DesktopLauncher();
                GestordePaneles.show(pnPrincipal,"Fin del Juego");
                setVisible(true);
            }
        }
    }

}
