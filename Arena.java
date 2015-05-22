import javax.swing.*;

public class Arena
{
    private Mounstro[] mounstros = new Mounstro[2];

    public Arena(){
	mounstros[0] = new Mounstro();
	mounstros[1] = new Mounstro();
    }
    
    public void luchar(JTextArea logBatalla)

    {
	int cantidad_dano = 0, contador=1;

	logBatalla.setText("\t"+mounstros[0].obtenerNombre()+" vs "+mounstros[1].obtenerNombre()+"\n\n");

	while (mounstros[0].vivo() && mounstros[1].vivo()){
	    if ((cantidad_dano = mounstros[0].atacar(logBatalla)) > 0)
		mounstros[1].recibirGolpe(cantidad_dano, logBatalla);
	    if (mounstros[1].vivo())
		if ((cantidad_dano = mounstros[1].atacar(logBatalla)) > 0)
		    mounstros[0].recibirGolpe(cantidad_dano, logBatalla);

	    logBatalla.append(" # # # # FIN DEL TURNO "+ (contador++) +" # # # # \n\n");
	}

	int ganador;
	if (mounstros[0].vivo())
	    ganador = 0;
	else
	    ganador = 1;

	logBatalla.append("\t"+mounstros[ganador].obtenerNombre()+" gana la batalla con "+mounstros[ganador].obtenerHP()+" HP!\n");
    }

    public void ponerNombre1(String n){	mounstros[0].ponerNombre(n); }
    public void ponerHP1(int hp){ mounstros[0].ponerHP(hp); }
    public void ponerDamage1(int d){ mounstros[0].ponerDamage(d); }
    public void ponerEsquivar1(float e){ mounstros[0].ponerEsquivar(e); }
    public void ponerExito1(float e){ mounstros[0].ponerExito(e); }
    public void ponerCritico1(float c){ mounstros[0].ponerCritico(c); }
    public void ponerNombre2(String n){ mounstros[1].ponerNombre(n); }
    public void ponerHP2(int hp){ mounstros[1].ponerHP(hp); }
    public void ponerDamage2(int d){ mounstros[1].ponerDamage(d); }
    public void ponerEsquivar2(float e){ mounstros[1].ponerEsquivar(e); }
    public void ponerExito2(float e){ mounstros[1].ponerExito(e); }
    public void ponerCritico2(float c){ mounstros[1].ponerCritico(c); }
}
