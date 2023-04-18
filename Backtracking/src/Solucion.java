import vaca.Vaca;

public class Solucion implements Comparable<Solucion> {
	private Vaca[] vacas;

	private int consumoEspacio = 0;
	private double producciónLeche = 0;
	

	public Solucion(final int size) {
		super();
		this.vacas = new Vaca[size];
	}
	
	public Solucion(final Solucion copia) {
		super();
		this.vacas = new Vaca[copia.length()];
		for (int i = 0; i < vacas.length; i++) {
			this.añadirVaca(copia.getVaca(i), i);
		}
	}
	
	public Vaca getVaca(final int posición) {
		return vacas[posición];
	}

	public void añadirVaca(final Vaca nueva, final int posición) {
		final Vaca anterior = vacas[posición];

		if (anterior != null) {
			consumoEspacio -= anterior.getOcupaEspacio();
			producciónLeche -= anterior.getProducciónLeche();
		}

		vacas[posición] = nueva;

		if (nueva != null) {
			consumoEspacio += nueva.getOcupaEspacio();
			producciónLeche += nueva.getProducciónLeche();
		}
	}
	
	@Override
	public int compareTo(final Solucion otra) {
		return Double.compare(producciónLeche, otra.getProducciónLeche());
	}
	
	public int getConsumoEspacio() { return consumoEspacio; }

	public double getProducciónLeche() { return producciónLeche; }
	
	public int length() { return vacas.length; }
	
	@Override
	public String toString() {
		String str = "[Solución: Vacas (id): {";
		boolean primera = true;
		
		for (Vaca vaca : vacas) {
			if (vaca != null) {
				str += (primera ? "" : ", ") + vaca.getCódigo();
				primera = false;
			}
		}
		
		return String.format("%s}, Leche: %.3f, Espacio: %d]", str, producciónLeche, consumoEspacio);
	}

}
