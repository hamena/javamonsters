package javamonsters;

import javamonsters.util.*;

public class Main{

    public static void main(String[] args){

	//	Mounstro m1 = new Mounstro("Ortega", 100, 10, 20, 4, 0.1f, 0.9f, 0.15f, 0.5f);
	//	Mounstro m2 = new Mounstro("Pacheco", 100, 10, 20, 4, 0.1f, 0.9f, 0.15f, 0.5f);

	Mounstro m1 = Generador.generarMounstro(10);
	Mounstro m2 = Generador.generarMounstro(10);

	System.out.println(m1);
	System.out.println("\n"+m2);
	
	Arena arena = new Arena();
	arena.luchar(m1,m2);
    }
}
