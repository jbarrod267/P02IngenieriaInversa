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
				Contacto nuevo = crearContacto(sc, agenda.getSiguienteId());
				agenda.agregarContacto(nuevo);
				System.out.println("Contacto añadido con ID " + nuevo.getId());

			} else if (opcion == 2) {
				List<Contacto> contactos = agenda.listarContactos();
				if (contactos.isEmpty()) {
					System.out.println("La agenda está vacía.");
				} else {
					for (Contacto c : contactos) {
						System.out.println(c);
						System.out.println("---------------------------------");
					}
				}

			} else if (opcion == 3) {
				String texto = leerTextoNoVacio(sc, "Buscar por nombre/apellidos: ");
				List<Contacto> resultados = agenda.buscarPorNombre(texto);

				if (resultados.isEmpty()) {
					System.out.println("No se encontraron contactos.");
				} else {
					for (Contacto c : resultados) {
						System.out.println(c);
						System.out.println("---------------------------------");
					}
				}

			} else if (opcion == 4) {
				int id = leerEntero(sc, "ID del contacto a borrar: ");
				boolean borrado = agenda.eliminarContactoPorId(id);
				System.out.println(borrado ? "Contacto borrado." : "No existe un contacto con ese ID.");

			} else if (opcion == 5) {
				int id = leerEntero(sc, "ID del contacto al que añadir teléfono: ");
				Contacto c = agenda.obtenerPorId(id);

				if (c == null) {
					System.out.println("No existe un contacto con ese ID.");
				} else {
					String numero = leerTextoNoVacio(sc, "Número teléfono: ");
					TipoTelefono tipo = elegirTipoTelefono(sc);
					c.agregarTelefono(numero, tipo);
					System.out.println("Teléfono añadido.");
				}

			} else if (opcion == 0) {
				System.out.println("Saliendo...");
			} else {
				System.out.println("Opción no válida.");
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

	// ---------- Creación desde consola ----------

	private static Contacto crearContacto(Scanner sc, int id) {
		String nombre = leerTextoNoVacio(sc, "Nombre: ");
		String apellidos = leerTextoNoVacio(sc, "Apellidos: ");
		String email = leerTexto(sc, "Email (opcional): ");

		Direccion direccion = crearDireccion(sc);
		Contacto c = new Contacto(id, nombre, apellidos, email, direccion);

		int cuantos = leerEntero(sc, "¿Cuántos teléfonos añadir?: ");
		for (int i = 0; i < cuantos; i++) {
			String numero = leerTextoNoVacio(sc, "Número teléfono: ");
			TipoTelefono tipo = elegirTipoTelefono(sc);
			c.agregarTelefono(numero, tipo);
		}
		return c;
	}

	private static Direccion crearDireccion(Scanner sc) {
		TipoVia tipoVia = elegirTipoVia(sc);
		int numero = leerEntero(sc, "Número: ");

		String bloque = leerTexto(sc, "Bloque: ");
		String escalera = leerTexto(sc, "Escalera: ");
		String portal = leerTexto(sc, "Portal: ");
		String letra = leerTexto(sc, "Letra: ");

		return new Direccion(tipoVia, numero, bloque, escalera, portal, letra);
	}

	private static TipoVia elegirTipoVia(Scanner sc) {
		TipoVia[] valores = TipoVia.values();
		for (int i = 0; i < valores.length; i++)
			System.out.println((i + 1) + ") " + valores[i]);

		int opcion = leerEnteroRango(sc, "Tipo vía: ", 1, valores.length);
		return valores[opcion - 1];
	}

	private static TipoTelefono elegirTipoTelefono(Scanner sc) {
		TipoTelefono[] valores = TipoTelefono.values();
		for (int i = 0; i < valores.length; i++)
			System.out.println((i + 1) + ") " + valores[i]);

		int opcion = leerEnteroRango(sc, "Tipo teléfono: ", 1, valores.length);
		return valores[opcion - 1];
	}

	private static String leerTexto(Scanner sc, String msg) {
		System.out.print(msg);
		return sc.nextLine().trim();
	}

	private static String leerTextoNoVacio(Scanner sc, String msg) {
		String texto = "";
		while (texto.isBlank()) {
			System.out.print(msg);
			texto = sc.nextLine().trim();
			if (texto.isBlank())
				System.out.println("ERROR - No puede estar vacío");
		}
		return texto;
	}

	private static int leerEntero(Scanner sc, String msg) {
		while (true) {
			try {
				System.out.print(msg);
				return Integer.parseInt(sc.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("ERROR - Número inválido");
			}
		}
	}

	private static int leerEnteroRango(Scanner sc, String msg, int min, int max) {
		int n;
		do {
			n = leerEntero(sc, msg);
			if (n < min || n > max)
				System.out.println("Debe estar entre " + min + " y " + max);
		} while (n < min || n > max);
		return n;
	}
}
