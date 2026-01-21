package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contacto {

	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private Direccion direccion;
	private List<Telefono> telefonos = new ArrayList<>();

	public Contacto(int id, String nombre, String apellidos, String email, Direccion direccion) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.direccion = direccion;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void agregarTelefonoDesdeConsola(Scanner sc) {
		String numero = Main.leerTextoNoVacio(sc, "Número teléfono: ");
		TipoTelefono tipo = elegirTipoTelefono(sc);
		telefonos.add(new Telefono(numero, tipo));
	}

	private TipoTelefono elegirTipoTelefono(Scanner sc) {
		TipoTelefono[] valores = TipoTelefono.values();
		for (int i = 0; i < valores.length; i++)
			System.out.println((i + 1) + ") " + valores[i]);

		int opcion = Main.leerEnteroRango(sc, "Tipo teléfono: ", 1, valores.length);
		return valores[opcion - 1];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID: ").append(id).append("\n");
		sb.append(nombre).append(" ").append(apellidos).append("\n");
		if (!email.isBlank())
			sb.append("Email: ").append(email).append("\n");
		sb.append("Dirección: ").append(direccion).append("\n");
		sb.append("Teléfonos:\n");
		for (Telefono t : telefonos)
			sb.append(" - ").append(t).append("\n");
		return sb.toString();
	}
}
