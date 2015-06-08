import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class GUI extends JFrame {

    private JTextField textNombre1,textHP1,textFuerza1, textRet1, textEsquivar1, textExito1, textCritico1;
    private JTextField textNombre2,textHP2,textFuerza2, textRet2, textEsquivar2, textExito2, textCritico2;
    private JTextArea logBatalla = new JTextArea(40,50);

    private Arena arena = new Arena();
    
    public GUI(String name) {
        super(name);
        setResizable(true);
    }
     
    public void anadirComponentes(final Container pane) {
	JPanel panelInput1 = new JPanel();
	panelInput1.setLayout(new GridLayout(0,2));
	panelInput1.add(new JLabel("Nombre:"));
	panelInput1.add(textNombre1 = new JTextField(10));
	panelInput1.add(new JLabel("HP:"));
	panelInput1.add(textHP1 = new JTextField(10));
	panelInput1.add(new JLabel("FUERZA:"));
	panelInput1.add(textFuerza1 = new JTextField(10));
	panelInput1.add(new JLabel("RETARDO DE ATAQUE:"));
	panelInput1.add(textRet1 = new JTextField(10));
	panelInput1.add(new JLabel("P_ESQUIVAR:"));
	panelInput1.add(textEsquivar1 = new JTextField(10));
	panelInput1.add(new JLabel("P_EXITO:"));
	panelInput1.add(textExito1 = new JTextField(10));
	panelInput1.add(new JLabel("P_CRITICO:"));
	panelInput1.add(textCritico1 = new JTextField(10));
	
	JPanel panelInput2 = new JPanel();
	panelInput2.setLayout(new GridLayout(0,2));
	panelInput2.add(new JLabel("Nombre:"));
	panelInput2.add(textNombre2 = new JTextField(10));
	panelInput2.add(new JLabel("HP:"));
	panelInput2.add(textHP2 = new JTextField(10));
	panelInput2.add(new JLabel("FUERZA:"));
	panelInput2.add(textFuerza2 = new JTextField(10));
	panelInput2.add(new JLabel("RETARDO DE ATAQUE:"));
	panelInput2.add(textRet2 = new JTextField(10));
	panelInput2.add(new JLabel("P_ESQUIVAR:"));
	panelInput2.add(textEsquivar2 = new JTextField(10));
	panelInput2.add(new JLabel("P_EXITO:"));
	panelInput2.add(textExito2 = new JTextField(10));
	panelInput2.add(new JLabel("P_CRITICO:"));
	panelInput2.add(textCritico2 = new JTextField(10));
	
	JPanel panelMounstros = new JPanel()
	    ;	panelMounstros.setLayout(new GridLayout(1,2));
	panelMounstros.add(panelInput1);
	panelMounstros.add(panelInput2);

	JButton botonLuchar = new JButton("A luchar!");
	JPanel panelControl = new JPanel();
	panelControl.setLayout(new GridLayout(2,1));
	panelControl.add(panelMounstros);

	panelControl.add(botonLuchar);

	logBatalla.setEditable(false);
	JScrollPane scroll = new JScrollPane(logBatalla);
	JPanel panelMain = new JPanel();
	panelMain.setLayout(new GridLayout(1,2));
	panelMain.add(panelControl);
	panelMain.add(scroll); 

        botonLuchar.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    
		    Mounstro m1 = new Mounstro(textNombre1.getText(),
					       Integer.parseInt(textHP1.getText()),
					       Integer.parseInt(textFuerza1.getText()),
					       Integer.parseInt(textRet1.getText()),
					       Float.parseFloat(textEsquivar1.getText()),
					       Float.parseFloat(textExito1.getText()),
					       Float.parseFloat(textCritico1.getText()) );

		    Mounstro m2 = new Mounstro(textNombre2.getText(),
					       Integer.parseInt(textHP2.getText()),
					       Integer.parseInt(textFuerza2.getText()),
					       Integer.parseInt(textRet2.getText()),
					       Float.parseFloat(textEsquivar2.getText()),
					       Float.parseFloat(textExito2.getText()),
					       Float.parseFloat(textCritico2.getText())      
					       );

		    try{
			DriverSalida.logBatalla = logBatalla;
			DriverSalida.GUI = true;
			arena.luchar(m1,m2);
			logBatalla = DriverSalida.logBatalla;
		    } catch(Exception ex){}
		}
	    });
	
        pane.add(panelMain);
    }
     
    public static void main(String[] args) {
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
        frame.anadirComponentes(frame.getContentPane());
	// Mostrar la ventana
        frame.pack();
        frame.setVisible(true);
    }
}
