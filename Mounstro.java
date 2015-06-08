import java.util.Random;

public class Mounstro
{
    private float p_esquivar, p_exito, p_critico;
    private int HP, fuerza, retardo;
    private String nombre;
    private static Random rnd = new Random();

    // Variable para saber el progreso del turno.
    // Mayor significa mas probabilidad de atacar primero.
    private float sigturno;
    
    public Mounstro(){}
    
    public Mounstro(String n, int hp, int f, int r, float pesq, float pe, float pc)
    {
	nombre = n;
	HP = hp;
	fuerza = f;
	retardo = r;
	p_esquivar = pesq;
	p_exito = pe;
	p_critico = pc;
	
	sigturno = retardo; 
    }

    public int atacar()
    {
	if (rnd.nextFloat() < p_exito){
	    float pericia = rnd.nextFloat()+0.5f;;
	    if (rnd.nextFloat() < p_critico){
		DriverSalida.print("\t" + nombre + " lanza un golpe de " +
				   (int)(fuerza * pericia * 1.5) + "! CRITICO\n");
		return (int) (fuerza * pericia * 1.5f);
	    }else{
		DriverSalida.print("\t" + nombre + " lanza un golpe de " +
				   (int)(fuerza * pericia) + "!\n");
		return (int) (fuerza * pericia);
	    }
	}
	else{
	    DriverSalida.print("\t" + nombre + " falla al realizar el ataque!\n");
	    return 0;
	}
    }

    public void recibirGolpe(int fuerza)
    {
	if (rnd.nextFloat() < p_esquivar)
	    DriverSalida.print("\t" + nombre + " esquiva el golpe! - HP: " + HP + "\n");
	else{
	    HP -= fuerza;
	    DriverSalida.print("\t" + nombre + " recibe " + fuerza +
			       " de daño! - HP: " + HP + "\n");
	}
    }

    public boolean vivo(){ return HP > 0; }
    
    public String obtenerNombre(){ return nombre; }
    public int obtenerHP(){ return HP; }
    public int obtenerFuerza(){ return fuerza; }
    public int obtenerRetardo(){ return retardo; }
    public float obtenerEsquivar(){ return p_esquivar; }
    public float obtenerExito(){ return p_exito; }
    public float obtenerCritico(){ return p_critico; }
    public float obtenerEstadoTurno(){ return sigturno; }
    
    public void ponerNombre(String n){ nombre = n; }
    public void ponerHP(int h){ HP = h; }
    public void ponerFuerza(int f){ fuerza = f; }
    public void ponerRetardo(int r){ retardo = r; }
    public void ponerEsquivar(float e){ p_esquivar = e; }
    public void ponerExito(float e){ p_exito = e; }
    public void ponerCritico(float c){ p_critico = c; }
    public void ponerEstadoTurno(float st){ sigturno = st; }
}
