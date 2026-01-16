package ejercicios;

public class Direccion {

	private TipoVia tipoVia;
	private int numero;
	private String bloque, escalera, portal, letra;

	public Direccion(TipoVia tipoVia, int numero, String bloque, String escalera, String portal, String letra) {
		this.tipoVia = tipoVia;
		this.numero = numero;
		this.bloque = bloque;
		this.escalera = escalera;
		this.portal = portal;
		this.letra = letra;
	}

	@Override
	public String toString() {
		return tipoVia + " " + numero;
	}
}
