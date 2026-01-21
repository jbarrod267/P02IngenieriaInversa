package ejercicios;

import java.util.Scanner;

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

	public static Direccion crearDesdeConsola(Scanner sc) {
		TipoVia tipoVia = elegirTipoVia(sc);
		int numero = Main.leerEntero(sc, "Número: ");

		String bloque = Main.leerTexto(sc, "Bloque: ");
		String escalera = Main.leerTexto(sc, "Escalera: ");
		String portal = Main.leerTexto(sc, "Portal: ");
		String letra = Main.leerTexto(sc, "Letra: ");

		return new Direccion(tipoVia, numero, bloque, escalera, portal, letra);
	}

	private static TipoVia elegirTipoVia(Scanner sc) {
		TipoVia[] valores = TipoVia.values();
		for (int i = 0; i < valores.length; i++)
			System.out.println((i + 1) + ") " + valores[i]);

		int opcion = Main.leerEnteroRango(sc, "Tipo vía: ", 1, valores.length);
		return valores[opcion - 1];
	}

	@Override
	public String toString() {
		return tipoVia + " " + numero;
	}
}
