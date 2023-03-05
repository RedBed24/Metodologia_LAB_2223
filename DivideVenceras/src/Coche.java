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
	
	/* Variable para saber si este coche ha podido recorrer todas las distancias, se presupone que sí */
	private boolean haRecorridoTodo = true;
	
	/**
	 * <p>
	 * Dominio:
	 * <ul>
	 * <li>PETROL</li>
	 * <li>DIESEL</li>
	 * <li>CNG</li>
	 * </ul>
	 * </p>
	 */
	public enum TipoCombustible { PETROL, DIESEL, CNG; };
	
	/**
	 * <p>
	 * Método encargado de parsear un String con uno de los posibles tipos de combustibles y devolverlo como enum.
	 * </p>
	 * @param token String con el tipo de combustible que se quiere conseguir.
	 * @return instancia del Enum con el combustible obtenido.
	 */
	public static TipoCombustible parseTipoCombustible(final String token) {
		TipoCombustible comb;
		switch (token.toLowerCase()) {
			case "petrol": comb = TipoCombustible.PETROL; break;
			case "diesel": comb = TipoCombustible.DIESEL; break;
			case "cng":    comb = TipoCombustible.CNG;    break;
			default: throw new IllegalArgumentException("Unexpected TipoCombustible value: " + token);
		}
		return comb;
	}

	/**
	 * <p>
	 * Dominio:
	 * <ul>
	 * <li>AUTOMATIC</li>
	 * <li>MANUAL</li>
	 * </ul>
	 * </p>
	 */
	public enum TipoTansmisión { AUTOMATIC, MANUAL; };
	
	/**
	 * <p>
	 * Método encargado de parsear un String con uno de los posibles tipos de Transmisión y devolverlo como enum.
	 * </p>
	 * @param token String con el tipo de transmisión que se quiere conseguir.
	 * @return instancia del Enum con el transmisión obtenido.
	 */
	public static TipoTansmisión parseTipoTransmisión(final String token) {
		TipoTansmisión trans;
		switch (token.toLowerCase()) {
			case "automatic": trans = TipoTansmisión.AUTOMATIC; break;
			case "manual":    trans = TipoTansmisión.MANUAL;     break;
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
	
	public boolean isHaRecorridoTodo() { return haRecorridoTodo; }

	public void setHaRecorridoTodo(boolean haRecorridoTodo) { this.haRecorridoTodo = haRecorridoTodo; }

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
