package ejercicios;

import java.util.ArrayList;
import java.util.List;

public class Agenda {

	private List<Contacto> contactos = new ArrayList<>();
	private int siguienteId = 1;

	public int getSiguienteId() {
		return siguienteId++;
	}

	public void agregarContacto(Contacto c) {
		contactos.add(c);
	}

	public List<Contacto> listarContactos() {
		return new ArrayList<>(contactos);
	}

	public Contacto obtenerPorId(int id) {
		for (Contacto c : contactos) {
			if (c.getId() == id)
				return c;
		}
		return null;
	}

	public boolean eliminarContactoPorId(int id) {
		return contactos.removeIf(c -> c.getId() == id);
	}

	public List<Contacto> buscarPorNombre(String texto) {
		List<Contacto> res = new ArrayList<>();
		texto = texto.toLowerCase();

		for (Contacto c : contactos) {
			String nombre = (c.getNombre() + " " + c.getApellidos()).toLowerCase();
			if (nombre.contains(texto)) {
				res.add(c);
			}
		}
		return res;
	}
}
