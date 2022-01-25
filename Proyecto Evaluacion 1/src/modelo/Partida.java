package modelo;

import utils.Utilidades;

public class Partida {

	private final int MIN_ATRIBUTOS = 20;
	private final int MAX_ATRIBUTOS = 50;
	private final int MIN_FACIL = 10;
	private final int MAX_FACIL = 30;
	private final int MIN_MEDIO = 30;
	private final int MAX_MEDIO = 50;
	private final int MIN_DIFICIL = 50;
	private final int MAX_DICICIL = 70;
	private final int MIN_JEFE_FACIL = 30;
	private final int MAX_JEFE_FACIL = 50;
	private final int MIN_JEFE_MEDIO = 50;
	private final int MAX_JEFE_MEDIO = 70;
	private final int MIN_JEFE_DIFICIL = 70;
	private final int MAX_JEFE_DIFICIL = 90;

	private Personaje[] luchadores;
	private Personaje jugador;
	private String dificultad;

	public Partida(int numeroLuchadores, String dificultad) {
		this.luchadores = new Personaje[numeroLuchadores];
		this.jugador = null;
		this.dificultad = dificultad;
	}

	public Personaje[] getLuchadores() {
		return luchadores;
	}

	public void setLuchadores(Personaje[] luchadores) {
		this.luchadores = luchadores;
	}

	public Personaje getJugador() {
		return jugador;
	}

	public void setJugador(Personaje jugador) {
		this.jugador = jugador;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	/**
	 * Crea un personaje del tipo (Mago o Guerrero) especificado por el usuario
	 * junto con su nombre. Será el personaje con el que jugará el usuario. Si el
	 * usuario no introdujo ningún nombre se le asigna uno por defecto.
	 * 
	 * @param tipo   Clase a la pertenece el personaje, 1=Mago o 2=Guerrero.
	 * @param nombre Nombre que se le asignará al personaje.
	 */
	public void creaJugador(int tipoClase, String nombre) {
		if (tipoClase == 1) {
			if (nombre == "") {
				this.jugador = new Mago(MIN_ATRIBUTOS, MAX_ATRIBUTOS);
			} else {
				this.jugador = new Mago(nombre, MIN_ATRIBUTOS, MAX_ATRIBUTOS);
			}
		} else {
			if (nombre == "") {
				this.jugador = new Guerrero(MIN_ATRIBUTOS, MAX_ATRIBUTOS);
			} else {
				this.jugador = new Guerrero(nombre, MIN_ATRIBUTOS, MAX_ATRIBUTOS);
			}
		}
	}

	/**
	 * Crea y almacena luchadores random oponentes al jugador de tipo Mago o
	 * Guerrero excepto para el ultimo elemento del array que es reservado para
	 * almacenar un luchador de tipo Jefe.
	 */
	public void creaOponentes() {
		int opc = 0;
		Personaje p = null;
		for (int i = 0; i < luchadores.length - 1; i++) {
			opc = Utilidades.numeroAleatorio(1, 2); //1 personaje de tipo Mago, 2 personaje de tipo Guerrero
			if (this.dificultad == "Fácil") {
				if (opc == 1) {
					do {
						p = new Mago(MIN_FACIL, MAX_FACIL);
					} while (buscaPersonaje(p));
				} else {
					do {
						p = new Guerrero(MIN_FACIL, MAX_FACIL);
					} while (buscaPersonaje(p));
				}
			} else if (this.dificultad == "Media") {
				if (opc == 1) {
					do {
						p = new Mago(MIN_MEDIO, MAX_MEDIO);
					} while (buscaPersonaje(p));
				} else {
					do {
						p = new Guerrero(MIN_MEDIO, MAX_MEDIO);
					} while (buscaPersonaje(p));
				}
			} else {
				if (opc == 1) {
					do {
						p = new Mago(MIN_DIFICIL, MAX_DICICIL);
					} while (buscaPersonaje(p));
				} else {
					do {
						p = new Guerrero(MIN_DIFICIL, MAX_DICICIL);
					} while (buscaPersonaje(p));
				}
			}
			p.rellenaItems(); //Genera los items que pueden tener los luchadores
			luchadores[i] = p;
		}
	}

	/**
	 * Crea un Objeto de tipo Jefe y lo almacena en la ultima posicion del Array luchadores
	 */
	public void creaJefes() {
		Personaje p = null;
		if (this.dificultad == "Fácil") {
			p = new Jefe(MIN_JEFE_FACIL, MAX_JEFE_FACIL);
		} else if (this.dificultad == "Medio") {
			p = new Jefe(MIN_JEFE_MEDIO, MAX_JEFE_MEDIO);
		} else {
			p = new Jefe(MIN_JEFE_DIFICIL, MAX_JEFE_DIFICIL);
		}
		luchadores[luchadores.length - 1] = p;
		p.rellenaItems();
	}

	/**
	 * Busca un personaje en el array luchadores.
	 * 
	 * @param p Personaje a buscar.
	 * @return False si no existe y true si lo ha encontrado.
	 */
	public boolean buscaPersonaje(Personaje p) {
		boolean encontrado = false;
		if (p != null && luchadores != null) {
			for (int i = 0; i < luchadores.length && !encontrado; i++) {
				if (p.equals(luchadores[i])) {
					encontrado = true;
				}
			}
		}
		return encontrado;
	}
	
	/**
	 * Muestra toda la información de los personajes almacenados en el array de
	 * luchadores.
	 */
	public void infoPersonajes() {
		if (luchadores != null) {
			for (Personaje personaje : luchadores) {
				System.out.println(personaje + "\n");
			}
		}
	}

}
