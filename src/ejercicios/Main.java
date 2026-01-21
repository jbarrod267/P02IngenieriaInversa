package ejercicios;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Agenda agenda = new Agenda();
		int opcion;

		do {
			mostrarMenu();
			opcion = leerEntero(sc, "Opción: ");

			if (opcion == 1) {
				Contacto c = agenda.crearContacto(sc);
				agenda.agregarContacto(c);
				System.out.println("Contacto añadido con ID " + c.getId());

			} else if (opcion == 2) {
				List<Contacto> contactos = agenda.listarContactos();
				if (contactos.isEmpty())
					System.out.println("La agenda está vacía.");
				else
					for (Contacto c : contactos) {
						System.out.println(c);
						System.out.println("---------------------------");
					}

			} else if (opcion == 3) {
				String texto = leerTextoNoVacio(sc, "Buscar por nombre/apellidos: ");
				for (Contacto c : agenda.buscarPorNombre(texto))
					System.out.println(c);

			} else if (opcion == 4) {
				int id = leerEntero(sc, "ID a borrar: ");
				System.out.println(agenda.eliminarContactoPorId(id) ? "Contacto borrado." : "No existe ese ID.");

			} else if (opcion == 5) {
				int id = leerEntero(sc, "ID del contacto: ");
				Contacto c = agenda.obtenerPorId(id);
				if (c != null) {
					c.agregarTelefonoDesdeConsola(sc);
					System.out.println("Teléfono añadido.");
				} else {
					System.out.println("No existe ese contacto.");
				}
			}

		} while (opcion != 0);

		sc.close();
	}

	private static void mostrarMenu() {
		System.out.println("\n1) Añadir contacto");
		System.out.println("2) Listar contactos");
		System.out.println("3) Buscar contacto");
		System.out.println("4) Borrar contacto");
		System.out.println("5) Añadir teléfono");
		System.out.println("0) Salir");
	}

	// ---------- MÉTODOS DE LECTURA ----------

	public static String leerTexto(Scanner sc, String msg) {
		System.out.print(msg);
		return sc.nextLine().trim();
	}

	public static String leerTextoNoVacio(Scanner sc, String msg) {
		String texto = "";
		while (texto.isBlank()) {
			System.out.print(msg);
			texto = sc.nextLine().trim();
			if (texto.isBlank())
				System.out.println("ERROR - No puede estar vacío");
		}
		return texto;
	}

	public static int leerEntero(Scanner sc, String msg) {
		while (true) {
			try {
				System.out.print(msg);
				return Integer.parseInt(sc.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("ERROR - Número inválido");
			}
		}
	}

	public static int leerEnteroRango(Scanner sc, String msg, int min, int max) {
		int n;
		do {
			n = leerEntero(sc, msg);
			if (n < min || n > max)
				System.out.println("Debe estar entre " + min + " y " + max);
		} while (n < min || n > max);
		return n;
	}
}
