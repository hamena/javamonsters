import java.util.Random;

public class Mounstro
{
    private float p_esquivar, p_exito, p_critico;
    private int HP, damage, velocidad;
    private String nombre;
    private static Random rnd = new Random();

    // Variable para saber el progreso del turno.
    // Mayor significa mas probabilidad de atacar primero.
    private float sigturno;
    
    public Mounstro(){}
    
    public Mounstro(String n, int hp, int d, int v, float pesq, float pe, float pc)
    {
	nombre = n;
	HP = hp;
	damage = d;
	velocidad = v;
	p_esquivar = pesq;
	p_exito = pe;
	p_critico = pc;
	
	sigturno = velocidad; // Estado inicial es la velocidad del propio mounstro.
    }

    public int atacar()
    {
	if (rnd.nextFloat() < p_exito){
	    float pericia = rnd.nextFloat()+0.5f;;
	    if (rnd.nextFloat() < p_critico){
		DriverSalida.print("\t" + nombre + " lanza un golpe de " +
				   (int)(damage * pericia * 1.5) + "! CRITICO\n");
		return (int) (damage * pericia * 1.5f);
	    }else{
		DriverSalida.print("\t" + nombre + " lanza un golpe de " +
				   (int)(damage * pericia) + "!\n");
		return (int) (damage * pericia);
	    }
	}
	else{
	    DriverSalida.print("\t" + nombre + " falla al realizar el ataque!\n");
	    return 0;
	}
    }

    public void recibirGolpe(int damage)
    {
	if (rnd.nextFloat() < p_esquivar)
	    DriverSalida.print("\t" + nombre + " esquiva el golpe! - HP: " + HP + "\n");
	else{
	    HP -= damage;
	    DriverSalida.print("\t" + nombre + " recibe " + damage +
			       " de daño! - HP: " + HP + "\n");
	}
    }

    public boolean vivo(){ return HP > 0; }
    
    public String obtenerNombre(){ return nombre; }
    public int obtenerHP(){ return HP; }
    public int obtenerDamage(){ return damage; }
    public int obtenerVelocidad(){ return velocidad; }
    public float obtenerEsquivar(){ return p_esquivar; }
    public float obtenerExito(){ return p_exito; }
    public float obtenerCritico(){ return p_critico; }
    public float obtenerEstadoTurno(){ return sigturno; }
    
    public void ponerNombre(String n){ nombre = n; }
    public void ponerHP(int h){ HP = h; }
    public void ponerDamage(int d){ damage = d; }
    public void ponerVelocidad(int v){ velocidad = v; }
    public void ponerEsquivar(float e){ p_esquivar = e; }
    public void ponerExito(float e){ p_exito = e; }
    public void ponerCritico(float c){ p_critico = c; }
    public void ponerEstadoTurno(float st){ sigturno = st; }
}
