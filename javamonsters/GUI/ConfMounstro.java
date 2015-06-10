package javamonsters.GUI;

import javamonsters.*;
import javamonsters.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConfMounstro extends JPanel{

    private static final int maxHP=100000, maxFuerza=10000, maxRetardo=10000, maxEscudo=10000;
    private static final int minHP=50, minFuerza=5, minRetardo=1, minEscudo=0;
    private static final float maxEsquivar=0.95f, maxExito=1.f, maxCritico=1.f, maxBloqueo=1.f;
    private static final float minEsquivar=0.f, minExito=0.025f, minCritico=0.f, minBloqueo=0.f;
    private static final String[] campos = {"NOMBRE:", "HP:", "FUERZA:", "RETARDO DE ATAQUE:",
				     "VALOR DE ESCUDO:", "PROB. ESQUIVAR", "PROB. EXITO",
				     "PROB. CRITICO", "PROB. BLOQUEO"};

    private JPanel panelGeneral, panelControl;
    private JTextField[] texts = new JTextField[nCampos];
    private JButton[] botones = new JButton[nCampos*2];

    private JTextField nivel, restantes;
    private JButton reiniciar, generar;
    
    public static final int nCampos = 9;
    
    public ConfMounstro(String nombre){
	super();
	this.setBorder(BorderFactory.createTitledBorder(nombre));
	this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
	//	this.setPreferredSize(new Dimension(300, 600));
	panelGeneral = new JPanel();
	//	panelGeneral.setBorder(BorderFactory.createTitledBorder("Estadisticas"));
	
	panelGeneral.setLayout(new BoxLayout(panelGeneral,BoxLayout.PAGE_AXIS));

	for (int i=0; i<nCampos; ++i){
	    JPanel fila = new JPanel();
	    fila.setBorder(BorderFactory.createTitledBorder(campos[i]));
	    fila.setLayout(new BoxLayout(fila,BoxLayout.PAGE_AXIS));
	    JPanel aux = new JPanel();
	    aux.setLayout(new GridLayout(0,2));
	    texts[i] = new JTextField(10);
	    if (i > 0){
		botones[i*2] = new JButton("+");
		botones[i*2 + 1] = new JButton("-");
		aux.add(botones[i*2]);
		aux.add(botones[i*2 + 1]);
	    }
	    
	    //	    fila.add(new JLabel(campos[i]));
	    fila.add(texts[i]);
	    fila.add(aux);
	    panelGeneral.add(fila);
	}

	this.add(panelGeneral);

	panelControl = new JPanel();
	//	panelControl.setBorder(BorderFactory.createTitledBorder("Control"));
	panelControl.setLayout(new GridLayout(3,2));

	panelControl.add(new JLabel("Nivel:"));
	nivel = new JTextField(10);
	nivel.setText("10");
	panelControl.add(nivel);
	panelControl.add(new JLabel("Pts. restantes:"));
	restantes = new JTextField(10);
	restantes.setText("10");
	restantes.setEditable(false);
	panelControl.add(restantes);
	reiniciar = new JButton("Reiniciar");
	generar = new JButton("Generar aleat.");
	panelControl.add(reiniciar);
	panelControl.add(generar);

	this.add(panelControl);
	
	/* VALOR INICIAL DE LOS CAMPOS DE TEXTO */
	reiniciarCampos();
	
	/* COMPORTAMIENTO DE LOS BOTONES */
	botones[1*2].addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if ( Integer.parseInt(texts[1].getText()) + Generador.incHP <= maxHP &&
			 Integer.parseInt(restantes.getText()) > 0 ){
			incrementarCampo(texts[1],Generador.incHP);
			decrementarNivel();
		    }
		}
	    });
	botones[1*2 + 1].addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if ( Integer.parseInt(texts[1].getText()) - Generador.incHP >= minHP ){
			decrementarCampo(texts[1],Generador.incHP);
			incrementarNivel();
		    }else
			texts[1].setText(Integer.toString(minHP));
		}
	    });
    	botones[2*2].addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if ( Integer.parseInt(texts[2].getText()) + Generador.incFuerza <= maxFuerza
			 && Integer.parseInt(restantes.getText()) > 0 ){
			incrementarCampo(texts[2],Generador.incFuerza);
			decrementarNivel();
		    }
		}
	    });
    	botones[2*2 + 1].addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if ( Integer.parseInt(texts[2].getText()) - Generador.incFuerza >= minFuerza
			 ){
			decrementarCampo(texts[2],Generador.incFuerza);
			incrementarNivel();
		    }else
			texts[2].setText(Integer.toString(minFuerza));
		}
	    });
    	botones[3*2].addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if ( Integer.parseInt(texts[3].getText())+Generador.incRetardo <= maxRetardo
			 && Integer.parseInt(restantes.getText()) > 0 ){
			incrementarCampo(texts[3],Generador.incRetardo);
			decrementarNivel();
		    }
		}
	    });
    	botones[3*2 + 1].addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if ( Integer.parseInt(texts[3].getText())-Generador.incRetardo >= minRetardo ){
			decrementarCampo(texts[3],Generador.incRetardo);
			incrementarNivel();
		    }else
			texts[3].setText(Integer.toString(minRetardo));
		}
	    });
    	botones[4*2].addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if ( Integer.parseInt(texts[4].getText()) + Generador.incEscudo <= maxEscudo
			 && Integer.parseInt(restantes.getText()) > 0 ){
			incrementarCampo(texts[4],Generador.incEscudo);
			decrementarNivel();
		    }
		}
	    });
    	botones[4*2 + 1].addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if ( Integer.parseInt(texts[4].getText()) - Generador.incEscudo >= minEscudo ){
			decrementarCampo(texts[4],Generador.incEscudo);
			incrementarNivel();
		    }else
			texts[4].setText(Integer.toString(minEscudo));
		}
	    });
    
	botones[5*2].addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if (Float.parseFloat(texts[5].getText())+Generador.incEsquivar <=maxEsquivar
			&& Integer.parseInt(restantes.getText()) > 0){
			incrementarCampo(texts[5],Generador.incEsquivar);
			decrementarNivel();
		    }
		}
	    });
	botones[5*2 + 1].addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if (Float.parseFloat(texts[5].getText())-Generador.incEsquivar >= minEsquivar){
			decrementarCampo(texts[5],Generador.incEsquivar);
			incrementarNivel();
		    }else
			texts[5].setText(Float.toString(minEsquivar));
		}
	    });
    	botones[6*2].addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if ( Float.parseFloat(texts[6].getText())+Generador.incExito <= maxExito
			 && Integer.parseInt(restantes.getText()) > 0 ){
			incrementarCampo(texts[6],Generador.incExito);
			decrementarNivel();
		    }
		}
	    });
    	botones[6*2 + 1].addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if ( Float.parseFloat(texts[6].getText())-Generador.incExito >= minExito ){
			decrementarCampo(texts[6],Generador.incExito);
			incrementarNivel();
		    }else
			texts[6].setText(Float.toString(minExito));
		}
	    });
    	botones[7*2].addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if ( Float.parseFloat(texts[7].getText())+Generador.incCritico <= maxCritico
			 && Integer.parseInt(restantes.getText()) > 0 ){
			incrementarCampo(texts[7],Generador.incCritico);
			decrementarNivel();
		    }
		}
	    });
    	botones[7*2 + 1].addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if ( Float.parseFloat(texts[7].getText())-Generador.incCritico >= minCritico ){
			decrementarCampo(texts[7],Generador.incCritico);
			incrementarNivel();
		    }else
			texts[7].setText(Float.toString(minCritico));
		}
	    });
    	botones[8*2].addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if ( Float.parseFloat(texts[8].getText())+Generador.incBloqueo <= maxBloqueo
			 && Integer.parseInt(restantes.getText()) > 0 ){
			incrementarCampo(texts[8],Generador.incBloqueo);
			decrementarNivel();
		    }
		}
	    });
    	botones[8*2 + 1].addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if ( Float.parseFloat(texts[8].getText())-Generador.incBloqueo >= minBloqueo ){
			decrementarCampo(texts[8],Generador.incBloqueo);
			incrementarNivel();
		    }else
			texts[8].setText(Float.toString(minBloqueo));
		}
	    });

	reiniciar.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    reiniciarCampos();
		}
	    });
	
	generar.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    int n = Integer.parseInt(nivel.getText());
		    Mounstro aux = Generador.generarMounstro(n);
		    texts[1].setText(Integer.toString(aux.obtenerHP()));
		    texts[2].setText(Integer.toString(aux.obtenerFuerza()));
		    texts[3].setText(Integer.toString(aux.obtenerRetardo()));
		    texts[4].setText(Integer.toString(aux.obtenerEscudo()));
		    texts[5].setText(String.format("%.3f",aux.obtenerEsquivar()));
		    texts[6].setText(String.format("%.3f",aux.obtenerExito()));
		    texts[7].setText(String.format("%.3f",aux.obtenerCritico()));
		    texts[8].setText(String.format("%.3f",aux.obtenerBloqueo()));
		    restantes.setText(Integer.toString(0));
		}
	    });
    }

    public JTextField[] obtenerCampos(){ return texts; }
    public void ponerNombre(String nombre){ texts[0].setText(nombre); }
    
    private void reiniciarCampos(){
	Mounstro aux = new Mounstro();
	texts[1].setText(Integer.toString(aux.obtenerHP()));     // HP
	texts[2].setText(Integer.toString(aux.obtenerFuerza())); // Fuerza
	texts[3].setText(Integer.toString(aux.obtenerRetardo()));// Retardo
	texts[4].setText(Integer.toString(aux.obtenerEscudo())); // Escudo
	texts[5].setText(Float.toString(aux.obtenerEsquivar())); // Esquivar
	texts[6].setText(Float.toString(aux.obtenerExito()));    // Exito
	texts[7].setText(Float.toString(aux.obtenerCritico()));  // Critico
	texts[8].setText(Float.toString(aux.obtenerBloqueo()));  // Bloqueo

	restantes.setText(nivel.getText());
    }

    private void incrementarCampo(JTextField campo, int valor){
	campo.setText(Integer.toString(Integer.parseInt(campo.getText()) + valor));
    }

    private void incrementarCampo(JTextField campo, float valor){
	campo.setText(String.format("%.3f",Float.parseFloat(campo.getText()) + valor));
    }

    private void decrementarCampo(JTextField campo, int valor){
	campo.setText(Integer.toString(Integer.parseInt(campo.getText()) - valor));
    }

    private void decrementarCampo(JTextField campo, float valor){
	campo.setText(String.format("%.3f",Float.parseFloat(campo.getText())  - valor));
    }
    
    private void incrementarNivel(){
	restantes.setText(Integer.toString(Integer.parseInt(restantes.getText())+1));
    }

    private void decrementarNivel(){
	restantes.setText(Integer.toString(Integer.parseInt(restantes.getText())-1));
    }
}
