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
	
	public enum TipoCombustible { PETROL, DIESEL, CNG; };
	
	public static TipoCombustible parseTipoCombustible(final String token) {
		Coche.TipoCombustible comb;
		switch (token.toLowerCase()) {
			case "petrol": comb = Coche.TipoCombustible.PETROL; break;
			case "diesel": comb = Coche.TipoCombustible.DIESEL; break;
			case "cng":    comb = Coche.TipoCombustible.CNG;    break;
			default: throw new IllegalArgumentException("Unexpected TipoCombustible value: " + token);
		}
		return comb;
	}

	public enum TipoTansmisión { AUTOMÁTICO, MANUAL; };
	
	public static TipoTansmisión parseTipoTransmisión(final String token) {
		Coche.TipoTansmisión trans;
		switch (token.toLowerCase()) {
			case "automatic": trans = Coche.TipoTansmisión.AUTOMÁTICO; break;
			case "manual":    trans = Coche.TipoTansmisión.MANUAL;     break;
			default: throw new IllegalArgumentException("Unexpected TipoTransmisión value: " + token);
		}
		return trans;
	}
	
	public Coche(final String modelo, final Coche.TipoCombustible tipoCombustible, final int numAsientos, final Coche.TipoTansmisión tipoTransmision, final double capacidadMáxima, final double consumoMedio) throws CarCreationException {
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
	
	/**
	 * <p>
	 * Recorre la distancia especificada, actualizando el depósito
	 * <p>
	 * <p>
	 * Es posible que se haya especificado más distancia a recorrer que la posible (indicada por la capacidad de fuel actual).
	 * Por ello se devuelve cuánto se ha recorrido en realidad.
	 * </p>
	 * @param kilómetros Distancia en kilómetros que se desean recorrer
	 * @return Distancia en kilómetos que se ha recorrido en realidad
	 */
	public double recorrer(final double kilómetros) {
		/* calculamos los litros consumidos */
		final double consumido = consumoMedio * kilómetros / 100;

		/* calculamos lo recorrido en función de si tenemos suficiente fuel o no */
		final double recorrido = consumido <= capacidadActual ? kilómetros : capacidadActual * 100 / consumoMedio;
		
		/* actualizamos el depósito */
		capacidadActual = consumido <= capacidadActual ? capacidadActual - consumido : 0;
		
		return recorrido;
	}
}
