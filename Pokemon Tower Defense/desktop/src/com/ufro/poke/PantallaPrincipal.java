package com.ufro.poke;

import com.ufro.poke.pantallas.Integrantes;
import com.ufro.poke.pantallas.JuegoTerminado;
import com.ufro.poke.pantallas.MenuPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPrincipal extends JFrame {

    //Creamos los objetos Panel menu principal y Panel aboutUs.
    private MenuPrincipal menuPrincipal;
    private com.ufro.poke.pantallas.Integrantes integrantes;
    private JuegoTerminado finDeJuego;

    //Creamos la ventana principal y panel principal.
    private JPanel pnPrincipal;

    //Creamos el gestor de paneles.
    private CardLayout GestordePaneles;

    //Creamos los botones.
    JButton Play;
    JButton Integrantes;
    JButton Back;
    JButton Regresar;
    JButton VolverAJugar;

    //Constructor clase PantallaPrincipal
    public PantallaPrincipal() {

        //Inicializamos los paneles.
        menuPrincipal = new MenuPrincipal();


        integrantes = new Integrantes();
        finDeJuego = new JuegoTerminado();

        //Inicializamos el carLayout y lo agregamos al JFrame.
        GestordePaneles = new CardLayout();
        initBotones();


        initJframe();

        initJpanel();





    }

    private void initJpanel(){
        //Inicializamos el panel principal y agregamos "Menu principal y About us"
        pnPrincipal = new JPanel();
        pnPrincipal.setLayout(GestordePaneles);
        pnPrincipal.add(menuPrincipal, "Menu Principal");
        pnPrincipal.add(integrantes, "About Us");
        pnPrincipal.add(finDeJuego,"Fin del Juego");
        pnPrincipal.setVisible(true);

        //Agregamos los paneles a la ventana.
        this.add(pnPrincipal);
    }

    private void initJframe(){
        //Inicializamos el Jframe.
        setTitle("PokeTowerDefence");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initBotones(){
        Play = new JButton(new ImageIcon("assets/interfaz/jugarPoke.png"));
        Integrantes = new JButton(new ImageIcon("assets/interfaz/sobrenosotrosPoke.png"));
        Back = new JButton(new ImageIcon("assets/interfaz/atrasPoke.png"));
        Regresar = new JButton(new ImageIcon("assets/interfaz/menuPoke.png"));
        VolverAJugar = new JButton(new ImageIcon("assets/interfaz/reintentarPoke.png"));

        //Definimos la posicion de los botones.
        Play.setBounds(480,350,270,145);
        Integrantes.setBounds(300,520,690,110);
        Back.setBounds(10,150,290,130);

        Regresar.setBounds(330,350,620,140);
        VolverAJugar.setBounds(400,500,500,120);





        //Los definimos como "Visibles"
        Play.setVisible(true);
        Integrantes.setVisible(true);
        Back.setVisible(true);
        Regresar.setVisible(true);
        VolverAJugar.setVisible(true);


        //Agregamos los botones a sus paneles respectivos.
        menuPrincipal.add(Play);
        menuPrincipal.add(Integrantes);
        integrantes.add(Back);
        finDeJuego.add(Regresar);
        finDeJuego.add(VolverAJugar);

        //agregamos los botones a actionListener.
        Play.addActionListener(new AccionAlternaPanel());
        Integrantes.addActionListener(new AccionAlternaPanel());
        Back.addActionListener(new AccionAlternaPanel());
        Regresar.addActionListener(new AccionAlternaPanel());
        VolverAJugar.addActionListener(new AccionAlternaPanel());

    }

    private class AccionAlternaPanel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            //Según el botón, pedimos al CardLayout que muestre un panel u otro
            if (boton.equals(Integrantes)) {

                GestordePaneles.show(pnPrincipal, "About Us");

            }
            if (boton.equals(Back)){

                GestordePaneles.show(pnPrincipal,"Menu Principal");

            }
            if (boton.equals(Play)){

                setVisible(false);

                new DesktopLauncher();
                GestordePaneles.show(pnPrincipal,"Fin del Juego");
                setVisible(true);
            }
            if (boton.equals(Regresar)){
                GestordePaneles.show(pnPrincipal,"Menu Principal");
            }
            if (boton.equals(VolverAJugar)){
                new DesktopLauncher();
                GestordePaneles.show(pnPrincipal,"Fin del Juego");
                setVisible(true);
            }
        }
    }

}
