package javamonsters;

import javamonsters.util.*;
    
public class Arena
{
    public Arena(){
    }
    
    public String luchar(Mounstro m1, Mounstro m2)

    {
	String logBatalla = new String("");
	Mounstro[] mounstros = new Mounstro[2];
	mounstros[0] = m1;
	mounstros[1] = m2;
	
	int cantidad_dano = 0, contador=1, turno = 0, elotro = 0;

	DriverSalida.print("\t"+mounstros[0].obtenerNombre()+" vs "+
			   mounstros[1].obtenerNombre()+"\n\n");

	while (mounstros[0].vivo() && mounstros[1].vivo()){
	    turno = decidirTurno(mounstros[0],mounstros[1]);
	    elotro = obtenerElOtro(turno);
	    
	    DriverSalida.print("\nLe toca a " + mounstros[turno].obtenerNombre() + "\n");
	    if ((cantidad_dano = mounstros[turno].atacar()) > 0)
		mounstros[elotro].recibirGolpe(cantidad_dano);
	}

	int ganador;
	if (mounstros[0].vivo())
	    ganador = 0;
	else
	    ganador = 1;
	
	DriverSalida.print(mounstros[ganador].obtenerNombre()+
			   " gana la batalla con "+mounstros[ganador].obtenerHP()+" HP!\n");
	
	return logBatalla;
    }

    private int decidirTurno(Mounstro m1, Mounstro m2){
	int ret1 = m1.obtenerRetardo();
	int ret2 = m2.obtenerRetardo();
	float estado1 = m1.obtenerEstadoTurno();
	float estado2 = m2.obtenerEstadoTurno();

	if (estado1 < estado2){ // Le toca al mounstro 1
	    estado2 -= estado1;
	    m2.ponerEstadoTurno(estado2);
	    m1.ponerEstadoTurno(ret1);
	    return 0;
	}else{ // Le toca al mounstro 2
	    estado1 -= estado2;
	    m1.ponerEstadoTurno(estado1);
	    m2.ponerEstadoTurno(ret2);
	    return 1;
	}
    }

    private int obtenerElOtro(int turno){
	if (turno == 0) return 1;
	else if (turno == 1) return 0;
	else System.out.println("## Error en Arena::obtenerElOtro(int)");
	return -1;
    }
}
