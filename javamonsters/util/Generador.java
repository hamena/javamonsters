package javamonsters.util;

import javamonsters.*;

import java.util.Random;

public class Generador{

    public static int incHP=50, incFuerza=5, incRetardo=-5, incEscudo=5;
    public static float incEsquivar=0.025f, incExito=0.025f, incCritico=0.05f, incBloqueo=0.05f;
    
    private static Random rnd = new Random();
    
    public static Mounstro generarMounstro(int nivel){
	Mounstro m = new Mounstro();
	int nParametros = 8;
	int HP=0, fuerza=0, retardo=0, escudo=0;
	float esquiva=0.0f, exito=0.0f, critico=0.0f, bloqueo=0.0f;
	for (int i=0; i<nivel; ++i){
	    int r = rnd.nextInt() % nParametros;
	    if (r == 0 && (HP/incHP > nParametros/2)){
		HP += incHP;
	    }else if (r == 1 && (fuerza/incFuerza <= nParametros/2)){
		fuerza += incFuerza;
	    }else if (r == 2 && (retardo/incRetardo <= nParametros/2)){
		retardo += incRetardo;
	    }else if (r == 3 && (escudo/incEscudo <= nParametros/2)){
		escudo += incEscudo;
	    }else if (r == 4 && (esquiva/incEsquivar <= nParametros/2)){
		esquiva += incEsquivar;
	    }else if (r == 5 && (exito/incExito <= nParametros/2)){
		exito += incExito;
	    }else if (r == 6 && (critico/incCritico <= nParametros/2)){
		critico += incCritico;
	    }else if (r == 7 && (bloqueo/incBloqueo <= nParametros/2)){
		bloqueo += incBloqueo;
	    }else
		--i; // Se vuelve a intentar
	}

	m.ponerHP(m.obtenerHP() + HP);
	m.ponerFuerza(m.obtenerFuerza() + fuerza);
	m.ponerRetardo(m.obtenerRetardo() + retardo);
	m.ponerEscudo(m.obtenerEscudo() + escudo);
	m.ponerEsquivar(m.obtenerEsquivar() + esquiva);
	m.ponerExito(m.obtenerExito() + exito);
	m.ponerCritico(m.obtenerCritico() + critico);
	m.ponerBloqueo(m.obtenerBloqueo() + bloqueo);
	m.ponerEstadoTurno(m.obtenerRetardo());

	return m;
    }
}
   
