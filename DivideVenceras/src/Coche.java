import excepciones.CarCreationException;

/**
 * <p>
 * Clase Entidad coche.
 * Guarda información sobre estos.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @version 1
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

	/**
	 * <p>
	 * Crea un nuevo coche con los parametros especificados.
	 * Rellena el tanque.
	 * </p>
	 * @param modelo
	 * @param tipoCombustible
	 * @param numAsientos
	 * @param tipoTransmision
	 * @param capacidadMáxima
	 * @param consumoMedio
	 * @throws CarCreationException
	 */
	public Coche(final String modelo, final TipoCombustible tipoCombustible, final int numAsientos, final TipoTansmisión tipoTransmision, final double capacidadMáxima, final double consumoMedio) throws CarCreationException {
		super();
		this.modelo = modelo;
		this.tipoCombustible = tipoCombustible;
		if ((this.numAsientos = numAsientos) <= 0) throw new CarCreationException("El coche debe tener al menos un asiento.");
		this.tipoTransmision = tipoTransmision;
		if ((this.capacidadMáxima = capacidadMáxima) <= 0) throw new CarCreationException("La capacidad del coche debe ser superior a 0.");
		if ((this.consumoMedio = consumoMedio) <= 0) throw new CarCreationException("El consumo medio del coche debe ser superior a 0.");
		
		capacidadActual = capacidadMáxima;
	}

	public String getModelo() { return modelo; }

	public TipoCombustible getTipoCombustible() { return tipoCombustible; }

	public int getNumAsientos() { return numAsientos; }

	public TipoTansmisión getTipoTransmision() { return tipoTransmision; }

	public double getCapacidadMáxima() { return capacidadMáxima; }

	public double getConsumoMedio() { return consumoMedio; }
	
	public double getCapacidadActual() { return capacidadActual; }
	
	public void setCapacidadActual(final double litros) { capacidadActual = litros; }
	
	public double getCapacidadLibre() { return capacidadMáxima - capacidadActual; }
	
	/**
	 * <p>
	 * Recorre la distancia especificada, actualizando el depósito
	 * <p>
	 * @param kilómetros Distancia en kilómetros que se desean recorrer
	 */
	public void recorrer(final double kilómetros) {
		/* actualizamos el depósito */
		capacidadActual -= consumoMedio * kilómetros / 100;
	}

	@Override
	public String toString() { return String.format("%-35s : %6.3fL", modelo, getCapacidadLibre()); }
	
}
