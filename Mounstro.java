import javax.swing.*;
import java.util.Random;

public class Mounstro
{
    private float p_esquivar, p_exito, p_critico;
    private int HP, damage;
    private String nombre;
    private static Random rnd = new Random();

    
    public Mounstro(){}
    
    public Mounstro(String n, int hp, int d, float pesq, float pe, float pc)
    {
	nombre = n;
	HP = hp;
	damage = d;
	p_esquivar = pesq;
	p_exito = pe;
	p_critico = pc;
    }

    public int atacar(JTextArea logBatalla)
    {
	if (rnd.nextFloat() < p_exito){
	    float pericia = rnd.nextFloat()+0.5f;;
	    if (rnd.nextFloat() < p_critico){
		logBatalla.append(nombre + " lanza un golpe de " + (int)(damage * pericia * 1.5) + "! CRITICO\n");
		return (int) (damage * pericia * 1.5f);
	    }else{
		logBatalla.append(nombre + " lanza un golpe de " + (int)(damage * pericia) + "!\n");
		return (int) (damage * pericia);
	    }
	}
	else{
	    logBatalla.append(nombre + " falla al realizar el ataque!\n");
	    return 0;
	}
    }

    public void recibirGolpe(int damage, JTextArea logBatalla)
    {
	if (rnd.nextFloat() < p_esquivar)
	    logBatalla.append(nombre + " esquiva el golpe! - HP: " + HP + "\n");
	else{
	    HP -= damage;
	    logBatalla.append(nombre + " recibe " + damage + " de daño! - HP: " + HP + "\n");
	}
    }

    public boolean vivo(){ return HP > 0; }
    
    public String obtenerNombre(){ return nombre; }
    public int obtenerHP(){ return HP; }
    public int obtenerDamage(){ return damage; }
    public float obtenerEsquivar(){ return p_esquivar; }
    public float obtenerExito(){ return p_exito; }
    public float obtenerCritico(){ return p_critico; }
    public void ponerNombre(String n){ nombre = n; }
    public void ponerHP(int h){ HP = h; }
    public void ponerDamage(int d){ damage = d; }
    public void ponerEsquivar(float e){ p_esquivar = e; }
    public void ponerExito(float e){ p_exito = e; }
    public void ponerCritico(float c){ p_critico = c; }
}

