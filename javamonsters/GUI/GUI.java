package javamonsters.GUI;

import javamonsters.*;
import javamonsters.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Locale;
 
public class GUI extends JFrame {

    static JTextArea logBatalla = new JTextArea(40,50);
    static JScrollPane scroll = new JScrollPane(logBatalla);
    static JButton opciones = new JButton("Opciones");
    static JButton luchar = new JButton("A luchar!");

    static ConfMounstro mounstro1 = new ConfMounstro("Mounstro 1");
    static ConfMounstro mounstro2 = new ConfMounstro("Mounstro 2");

    static Opciones frameOps = new Opciones("Opciones");
    static JDesktopPane desktop = new JDesktopPane();
    
    private static Arena arena = new Arena();

    public GUI(String name) {
        super(name);
        setResizable(true);
	mounstro1.ponerNombre("Ortega");
	mounstro2.ponerNombre("Pacheco");
    }
     
    public static void main(String[] args) {
	Locale.setDefault(Locale.US);
	
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

	GUI frame = new GUI("JavaMonsters");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frame.anadirComponentes();
	frame.anadirComportamiento();

	// Mostrar la ventana
        frame.pack();
        frame.setVisible(true);
    }

    public void anadirComponentes(){
	JPanel mounstros = new JPanel();
	mounstros.setLayout(new GridLayout(0,2));
	mounstros.add(mounstro1);
	mounstros.add(mounstro2);

	JPanel opcionesYLuchar = new JPanel();
	opcionesYLuchar.setLayout(new GridLayout(1,2));
	opcionesYLuchar.add(opciones);
	opcionesYLuchar.add(luchar);
	
	JPanel panelIzq = new JPanel();
	panelIzq.setLayout(new BoxLayout(panelIzq,BoxLayout.PAGE_AXIS));
	panelIzq.setPreferredSize(new Dimension(200,300));
	panelIzq.add(mounstros);
	panelIzq.add(opcionesYLuchar);

	logBatalla.setEditable(false);
	JPanel batalla = new JPanel();
	batalla.setBorder(BorderFactory.createTitledBorder("Registro de la batalla"));
	batalla.add(scroll);

	JPanel global = new JPanel();
	global.setLayout(new GridLayout(1,2));
	global.add(panelIzq);
	global.add(batalla);
	this.add(global);
    }

    public void anadirComportamiento(){
	luchar.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    JTextField[] info1 = mounstro1.obtenerCampos();
		    Mounstro m1 = new Mounstro(info1[0].getText(),
					       Integer.parseInt(info1[1].getText()),
					       Integer.parseInt(info1[2].getText()),
					       Integer.parseInt(info1[3].getText()),
					       Integer.parseInt(info1[4].getText()),
					       Float.parseFloat(info1[5].getText()),
					       Float.parseFloat(info1[6].getText()),
					       Float.parseFloat(info1[7].getText()),
					       Float.parseFloat(info1[8].getText()),
					       Float.parseFloat(info1[9].getText()));

		    JTextField[] info2 = mounstro2.obtenerCampos();
		    Mounstro m2 = new Mounstro(info2[0].getText(),
					       Integer.parseInt(info2[1].getText()),
					       Integer.parseInt(info2[2].getText()),
					       Integer.parseInt(info2[3].getText()),
					       Integer.parseInt(info2[4].getText()),
					       Float.parseFloat(info2[5].getText()),
					       Float.parseFloat(info2[6].getText()),
					       Float.parseFloat(info2[7].getText()),
					       Float.parseFloat(info2[8].getText()),
					       Float.parseFloat(info2[9].getText()));

		    try{
			logBatalla.setText("");
			DriverSalida.logBatalla = logBatalla;
			DriverSalida.GUI = true;
			arena.luchar(m1,m2);
			logBatalla = DriverSalida.logBatalla;
		    } catch(Exception ex){}
		}
	    });

	opciones.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    frameOps.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    // Mostrar la ventana
		    frameOps.pack();
		    frameOps.setVisible(true);
		}
	    });
    }
}
