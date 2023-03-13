package Coche;

/**
 * <p>
 * Dominio:
 * <ul>
 * <li>AUTOMATIC</li>
 * <li>MANUAL</li>
 * </ul>
 * </p>
 */
public enum TipoTansmisión {
	AUTOMATIC, MANUAL;
	
	/**
	 * <p>
	 * Método encargado de parsear un String con uno de los posibles tipos de Transmisión y devolverlo como enum.
	 * </p>
	 * @param token String con el tipo de transmisión que se quiere conseguir.
	 * @return 
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
}
