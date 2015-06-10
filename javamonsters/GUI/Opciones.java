package javamonsters.GUI;

import javamonsters.*;
import javamonsters.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Locale;

public class Opciones extends JFrame{

    private static final int nCampos = 8;
    private static JTextField[] texts = new JTextField[nCampos];
    private static JTextField dispAtaque, dispBloqueo;
    private static JButton guardar = new JButton("Guardar cambios");

    private static final String[] campos = {"HP:", "FUERZA:", "RETARDO DE ATAQUE:",
				     "VALOR DE ESCUDO:", "PROB. ESQUIVAR", "PROB. EXITO",
				     "PROB. CRITICO", "PROB. BLOQUEO"};

    
    public Opciones(String name) {
	//	super(name, true, true, true, true);
	super(name);
	Locale.setDefault(Locale.US);

	anadirComponentes();
	iniciarValoresCampos();
	anadirComportamiento();
    }

    public void anadirComponentes(){

	JPanel incrementos = new JPanel();
	incrementos.setLayout(new BoxLayout(incrementos,BoxLayout.PAGE_AXIS));
	incrementos.setBorder(BorderFactory.createTitledBorder("Incrementos"));
	
	for (int i=0; i<nCampos; ++i){
	    texts[i] = new JTextField(10);
	    JPanel aux = new JPanel();
	    aux.setBorder(BorderFactory.createTitledBorder(campos[i]));
	    aux.add(texts[i]);
	    incrementos.add(aux);
	}
	
	dispAtaque = new JTextField(10);
	dispBloqueo = new JTextField(10);
	JPanel panelAtaque = new JPanel();
	panelAtaque.setBorder(BorderFactory.createTitledBorder("DISPERSION ATAQUE:"));
	panelAtaque.add(dispAtaque);
	JPanel panelBloqueo = new JPanel();
	panelBloqueo.setBorder(BorderFactory.createTitledBorder("DISPERSION BLOQUEO:"));
	panelBloqueo.add(dispBloqueo);

	JPanel factores = new JPanel();
	factores.setLayout(new BoxLayout(factores,BoxLayout.PAGE_AXIS));
	factores.setBorder(BorderFactory.createTitledBorder("Factores"));
	factores.add(panelAtaque);
	factores.add(panelBloqueo);
	
	JPanel opciones = new JPanel();
	opciones.add(incrementos);
	opciones.add(factores);

	JPanel global = new JPanel();
	global.setLayout(new BoxLayout(global,BoxLayout.PAGE_AXIS));
	global.add(opciones);
	global.add(guardar);

	this.add(global);
    }

    public void iniciarValoresCampos(){
	texts[0].setText(Integer.toString(Generador.incHP));
	texts[1].setText(Integer.toString(Generador.incFuerza));
	texts[2].setText(Integer.toString(Generador.incRetardo));
	texts[3].setText(Integer.toString(Generador.incEscudo));

	texts[4].setText(Float.toString(Generador.incEsquivar));
	texts[5].setText(Float.toString(Generador.incExito));
	texts[6].setText(Float.toString(Generador.incCritico));
	texts[7].setText(Float.toString(Generador.incBloqueo));

	dispAtaque.setText(Float.toString(Mounstro.dispersionAtaque));
	dispBloqueo.setText(Float.toString(Mounstro.dispersionBloqueo));
    }

    public void anadirComportamiento(){
	guardar.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    Generador.incHP = Integer.parseInt(texts[0].getText());
		    Generador.incFuerza = Integer.parseInt(texts[1].getText());
		    Generador.incRetardo = Integer.parseInt(texts[2].getText());
		    Generador.incEscudo = Integer.parseInt(texts[3].getText());
		    Generador.incEsquivar = Float.parseFloat(texts[4].getText());
		    Generador.incExito = Float.parseFloat(texts[5].getText());
		    Generador.incCritico = Float.parseFloat(texts[6].getText());
		    Generador.incBloqueo = Float.parseFloat(texts[7].getText());
		    
		    Mounstro.dispersionAtaque = Float.parseFloat(dispAtaque.getText());
		    Mounstro.dispersionBloqueo = Float.parseFloat(dispBloqueo.getText());
		}
	    });
    }
}
