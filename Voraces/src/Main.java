import java.io.FileNotFoundException;
import java.util.Vector;

import Vaca.Vaca;

public class Main {
	
	public static int obtenerDatos(final String pathname, final Vector<Vaca> vacas) throws FileNotFoundException {
		IO.leerVacas(pathname, vacas);

		//aux.leer(0, vacas.size());
		return -1;
	}

	public static void main(String[] args) {
		final Vector<Vaca> vacas = new Vector<>(30);

		try {
			final int vacasASeleccionar = obtenerDatos(Constantes.PATHNAME_VACAS, vacas);;
		
			for (Vaca vaca : vacas) 
				System.out.println(vaca);
		} catch (Exception e) {
			System.err.println("Ha ocurrido un error inesperado. " + e);
		}
	}

}
