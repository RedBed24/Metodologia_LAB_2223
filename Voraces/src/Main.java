import java.util.Vector;

import Vaca.Vaca;

public class Main {

	public static void main(String[] args) {
		final Vector<Vaca> vacas = new Vector<>(30);

		try {
			IO.leerVacas(Constantes.PATHNAME_VACAS, vacas);
			for (Vaca vaca : vacas) 
				System.out.println(vaca);
		} catch (Exception e) {
			System.err.println("Ha ocurrido un error inesperado. " + e);
		}
	}

}
