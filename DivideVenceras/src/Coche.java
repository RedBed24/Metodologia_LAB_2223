import excepciones.CarCreationException;

/**
 * <p>
 * Clase Entidad coche.
 * Guarda información sobre estos.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @version 0.01
 * @since 2023-02-27
 */
public class Coche {
	private final String modelo;
	private final TipoCombustible tipoCombustible;
	private final int numAsientos;
	private final TipoTansmisión tipoTransmision;
	/* Medido en L */
	private final double capacidadMáxima;
	/* Medido en L/100km */
	private final double consumoMedio;
	
	/* Medido en L */
	private double capacidadActual;
	
	public enum TipoCombustible { PETROL, DIESEL; };
	public enum TipoTansmisión { AUTOMÁTICO, MANUAL; };
	
	public Coche(final String modelo, final Coche.TipoCombustible tipoCombustible, final int numAsientos, final Coche.TipoTansmisión tipoTransmision, final double capacidad, final double consumoMedio) throws CarCreationException {
		super();
		this.modelo = modelo;
		this.tipoCombustible = tipoCombustible;
		if ((this.numAsientos = numAsientos) <= 0) throw new CarCreationException("El coche debe tener al menos un asiento.");
		this.tipoTransmision = tipoTransmision;
		if ((this.capacidadMáxima = capacidadMáxima) <= 0) throw new CarCreationException("La capacidad del coche debe ser superior a 0.");
		if ((this.consumoMedio = consumoMedio) <= 0) throw new CarCreationException("El consumo medio del coche debe ser superior a 0.");
	}

	public String getModelo() { return modelo; }

	public TipoCombustible getTipoCombustible() { return tipoCombustible; }

	public int getNumAsientos() { return numAsientos; }

	public TipoTansmisión getTipoTransmision() { return tipoTransmision; }

	public double getCapacidadMáxima() { return capacidadMáxima; }

	public double getConsumoMedio() { return consumoMedio; }
	
	public double getCapacidadActual() { return capacidadActual; }
	
	public void setCapacidadActual(final double litros) { capacidadActual = litros; }
	
}
