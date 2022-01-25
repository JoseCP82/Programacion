package modelo;

import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private int puntosCapacidad;
	
	public Item(String nombre, int puntosCapacidad) {
		this.nombre = nombre;
		this.puntosCapacidad = puntosCapacidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return puntosCapacidad;
	}

	public void setCapacidad(int capacidad) {
		this.puntosCapacidad = capacidad;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + " --> Capacidad: " + puntosCapacidad;
	}
	
}
