import java.util.Random;

public class Generador{

    private static Random rnd = new Random();
    
    public static Mounstro generarMounstro(int nivel){
	Mounstro m = new Mounstro();
	int nParametros = 8;
	int HP=0, fuerza=0, retardo=0, escudo=0;
	float esquiva=0.0f, exito=0.0f, critico=0.0f, bloqueo=0.0f;
	for (int i=0; i<nivel; ++i){
	    int r = rnd.nextInt() % nParametros;
	    if (r == 0 && (HP/50 > nParametros/2)){
		HP += 50;
	    }else if (r == 1 && (fuerza/5 <= nParametros/2)){
		fuerza += 5;
	    }else if (r == 2 && (retardo/5 <= nParametros/2)){
		retardo += 5;
	    }else if (r == 3 && (escudo/5 <= nParametros/2)){
		escudo += 5;
	    }else if (r == 4 && (esquiva/0.025f <= nParametros/2)){
		esquiva += 0.025f;
	    }else if (r == 5 && (exito/0.025f <= nParametros/2)){
		exito += 0.025f;
	    }else if (r == 6 && (critico/0.05f <= nParametros/2)){
		critico += 0.05f;
	    }else if (r == 7 && (bloqueo/0.05f <= nParametros/2)){
		bloqueo += 0.05f;
	    }else
		--i; // Se vuelve a intentar
	}

	m.ponerHP(m.obtenerHP() + HP);
	m.ponerFuerza(m.obtenerFuerza() + fuerza);
	m.ponerRetardo(m.obtenerRetardo() - retardo);
	m.ponerEscudo(m.obtenerEscudo() + escudo);
	m.ponerEsquivar(m.obtenerEsquivar() + esquiva);
	m.ponerExito(m.obtenerExito() + exito);
	m.ponerCritico(m.obtenerCritico() + critico);
	m.ponerBloqueo(m.obtenerBloqueo() + bloqueo);
	m.ponerEstadoTurno(m.obtenerRetardo());

	return m;
    }
}
   
