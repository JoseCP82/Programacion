package modelo;

public class PartidaVersus {

	private final int MIN_ATRIBUTOS = 40;
	private final int MAX_ATRIBUTOS = 80;

	private Personaje[] jugadores;

	public PartidaVersus() {
		this.jugadores = new Personaje[2];
	}

	public Personaje[] getJugadores() {
		return jugadores;
	}

	/**
	 * Crea un personaje del tipo (Mago o Guerrero) especificado por el usuario
	 * junto con su nombre. Será el personaje con el que jugará el usuario. Si el
	 * usuario no introdujo ningún nombre se le asigna uno por defecto.
	 * 
	 * @param tipo   Clase a la pertenece el personaje, 1=Mago o 2=Guerrero.
	 * @param nombre Nombre que se le asignará al personaje.
	 */
	public void creaJugadores(int tipoClase, String nombre, int indice) {
		if (tipoClase == 1) {
			if (nombre == "") {
				this.jugadores[indice] = new Mago(MIN_ATRIBUTOS, MAX_ATRIBUTOS);
			} else {
				this.jugadores[indice] = new Mago(nombre, MIN_ATRIBUTOS, MAX_ATRIBUTOS);
			}
		} else {
			if (nombre == "") {
				this.jugadores[indice] = new Guerrero(MIN_ATRIBUTOS, MAX_ATRIBUTOS);
			} else {
				this.jugadores[indice] = new Guerrero(nombre, MIN_ATRIBUTOS, MAX_ATRIBUTOS);
			}
		}
		jugadores[indice].rellenaItems();
	}

	/**
	 * Muestra toda la información de los personajes almacenados en el array de
	 * luchadores.
	 */
	public void infoPersonajes() {
		if (jugadores != null) {
			for (Personaje personaje : jugadores) {
				System.out.println(personaje + "\n");
			}
		}
	}
}
