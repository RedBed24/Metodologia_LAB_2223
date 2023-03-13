package Coche;

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
public enum TipoCombustible {

	PETROL, DIESEL, CNG;

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
}
