import javax.swing.*;

public class DriverSalida{

    public static JTextArea logBatalla;
    public static boolean GUI = false;

    public static void print(String str){
	if (GUI)
	    logBatalla.append(str);
	else
	    System.out.print(str);
    }
}
