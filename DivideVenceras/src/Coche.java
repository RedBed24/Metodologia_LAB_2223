
public class Coche {
	private String modelo;
	private String tipoCombustible;
	private double numAsientos;
	private String tipoTransmision;
	private double capacidad;
	private double consumoMedio;
	
	public Coche(final String modelo, final String combustible, final double asientos, final String transmision, final double capacidad, final double consumo) {
		this.modelo = modelo;
		tipoCombustible = combustible;
		numAsientos = asientos;
		tipoTransmision = transmision;
		this.capacidad = capacidad;
		consumoMedio = consumo;
	}
	
}
