
public class Coche {
	private String modelo;
	private TipoCombustible tipoCombustible;
	private int numAsientos;
	private TipoTansmisión tipoTransmision;
	private double capacidad;
	private double consumoMedio;
	
	public enum TipoCombustible { PETROL, DIESEL; };
	public enum TipoTansmisión { AUTOMÁTICO, MANUAL; };
	
	public Coche(final String modelo, final Coche.TipoCombustible tipoCombustible, final int numAsientos, final Coche.TipoTansmisión tipoTransmision, final double capacidad, final double consumoMedio) {
		super();
		this.modelo = modelo;
		this.tipoCombustible = tipoCombustible;
		this.numAsientos = numAsientos;
		this.tipoTransmision = tipoTransmision;
		this.capacidad = capacidad;
		this.consumoMedio = consumoMedio;
	}

	public String getModelo() { return modelo; }

	public void setModelo(String modelo) { this.modelo = modelo; }

	public TipoCombustible getTipoCombustible() { return tipoCombustible; }

	public void setTipoCombustible(TipoCombustible tipoCombustible) { this.tipoCombustible = tipoCombustible; }

	public int getNumAsientos() { return numAsientos; }

	public void setNumAsientos(int numAsientos) { this.numAsientos = numAsientos; }

	public TipoTansmisión getTipoTransmision() { return tipoTransmision; }

	public void setTipoTransmision(TipoTansmisión tipoTransmision) { this.tipoTransmision = tipoTransmision; }

	public double getCapacidad() { return capacidad; }

	public void setCapacidad(double capacidad) { this.capacidad = capacidad; }

	public double getConsumoMedio() { return consumoMedio; }

	public void setConsumoMedio(double consumoMedio) { this.consumoMedio = consumoMedio; }
	
}
