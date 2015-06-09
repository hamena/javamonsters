import java.util.Random;

public class Mounstro
{
    private float p_esquivar, p_exito, p_critico, p_bloqueo;
    private int HP, fuerza, retardo, escudo;
    private String nombre;
    private static Random rnd = new Random();
    private static int totalMounstros = 0;

    public static float dispersionAtaque = 0.5f, dispersionBloqueo = 0.5f;

    // Variable para saber el progreso del turno.
    // Mayor significa mas probabilidad de atacar primero.
    private float sigturno;
    
    public Mounstro(){
	nombre = new String("Mounstro_" + (totalMounstros++));
	HP = 50;
	fuerza = 5;
	retardo = 100;
	escudo = 0;
	p_esquivar = 0.0f;
	p_exito = 0.5f;
	p_critico = 0.0f;
	p_bloqueo = 0.0f;

	sigturno = retardo;
    }
    
    public Mounstro(String n, int hp, int f, int r, int e, float pesq, float pe, float pc, float pb)
    {
	nombre = n;
	HP = hp;
	fuerza = f;
	retardo = r;
	escudo = e;
	p_esquivar = pesq;
	p_exito = pe;
	p_critico = pc;
	p_bloqueo = pb;
	
	sigturno = retardo; 
    }

    public int atacar()
    {
	if (rnd.nextFloat() < p_exito){
	    float pericia = calcularPericia(dispersionAtaque);
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
	if (rnd.nextFloat() < p_esquivar) // Consigue esquivar el golpe
	    DriverSalida.print("\t" + nombre + " esquiva el golpe! - HP: " + HP + "\n");
	else{ // Recibe el golpe
	    if (rnd.nextFloat() < p_bloqueo){ // Consigue bloquear con el escudo
		float pericia = calcularPericia(dispersionBloqueo);
		int bloqueado = (int)(escudo * pericia);
		int fuerzaTmp = (int)(fuerza - bloqueado);
		HP -= fuerzaTmp>0 ? fuerzaTmp : 0;
		DriverSalida.print("\t" + nombre + " bloquea " + bloqueado +
				   " y recibe " + (fuerzaTmp>0 ? fuerzaTmp : 0) +
				   " de daño! - HP: " + HP +"\n");
	    }else{ // No bloquea con el escudo
		HP -= fuerza;
		DriverSalida.print("\t" + nombre + " recibe " + fuerza +
				   " de daño! - HP: " + HP + "\n");
	    }
	}
    }

    public boolean vivo(){ return HP > 0; }
    
    public String obtenerNombre(){ return nombre; }
    public int obtenerHP(){ return HP; }
    public int obtenerFuerza(){ return fuerza; }
    public int obtenerRetardo(){ return retardo; }
    public int obtenerEscudo(){ return escudo; }
    public float obtenerEsquivar(){ return p_esquivar; }
    public float obtenerExito(){ return p_exito; }
    public float obtenerCritico(){ return p_critico; }
    public float obtenerBloqueo(){ return p_bloqueo; }
    public float obtenerEstadoTurno(){ return sigturno; }
    
    public void ponerNombre(String n){ nombre = n; }
    public void ponerHP(int h){ HP = h; }
    public void ponerFuerza(int f){ fuerza = f; }
    public void ponerRetardo(int r){ retardo = r; }
    public void ponerEscudo(int e){ escudo = e; }
    public void ponerEsquivar(float e){ p_esquivar = e; }
    public void ponerExito(float e){ p_exito = e; }
    public void ponerCritico(float c){ p_critico = c; }
    public void ponerBloqueo(float b){ p_bloqueo = b; }
    public void ponerEstadoTurno(float st){ sigturno = st; }

    @Override
    public String toString(){
	String str = new String(nombre+"\n");
	str += "HP: " + HP + "\n";
	str += "Fuerza: " + fuerza + "\n";
	str += "Retardo: " + retardo + "\n";
	str += "Escudo: " + escudo + "\n";
	str += "Esquivar: " + p_esquivar + "\n";
	str += "Exito: " + p_exito + "\n";
	str += "Critico: " + p_critico + "\n";
	str += "Bloqueo: " + p_bloqueo + "\n";
	return str;
    }

    private float calcularPericia(float dispersion){
	if (dispersion > 0.0f){
	    float min = 0.0f, max = dispersion*2;
	    float r = aleatorioEnRangoReal(min,max);
	    return r + (1.0f - dispersion);
	}
	else return 1;
    }

    private float aleatorioEnRangoReal(float min, float max){
	return
	    rnd.nextFloat() < 0.5 ?
	    ((1-rnd.nextFloat()) * (max-min) + min) :
	    (rnd.nextFloat() * (max-min) + min);
    }
}
