package modelo;

import java.io.Serializable;

import utils.Utilidades;

public class Personaje implements Serializable {

	private static final long serialVersionUID = 1L;

	private static int autoNumero = 1;

	private String nombre;
	private int vidaMaxima;
	private int vida;
	private int ataque;
	private int defensa;
	private int poderAcumulado;
	private Item[] items;

	/*
	 * Dependiedo del nivel de dificultad, el rango mínimo y máximo de valores que
	 * pueden tomar los atributos será diferente.
	 */
	public Personaje(String nombre, int rangoMinimo, int rangoMaximo) {
		this.nombre = nombre;
		this.vida = Utilidades.numeroAleatorio(rangoMinimo, rangoMaximo);
		this.ataque = Utilidades.numeroAleatorio(rangoMinimo, rangoMaximo);
		this.defensa = Utilidades.numeroAleatorio(rangoMinimo, rangoMaximo);
		this.poderAcumulado = 0;
		this.vidaMaxima = this.vida;
		this.items = new Item[3];
	}

	public Personaje(int rangoMinimo, int rangoMaximo) {
		this.nombre = "Luchador " + autoNumero++;
		this.vida = Utilidades.numeroAleatorio(rangoMinimo, rangoMaximo);
		this.ataque = Utilidades.numeroAleatorio(rangoMinimo, rangoMaximo);
		this.defensa = Utilidades.numeroAleatorio(rangoMinimo, rangoMaximo);
		this.poderAcumulado = 0;
		this.vidaMaxima = this.vida;
		this.items = new Item[3];
	}

	public int getVidaMaxima() {
		return vidaMaxima;
	}

	public void setVidaMaxima(int vidaMaxima) {
		this.vidaMaxima = vidaMaxima;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getPoderAcumulado() {
		return poderAcumulado;
	}

	public void setPoderAcumulado(int poderAcumulado) {
		this.poderAcumulado = poderAcumulado;
	}
	
	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	/**
	 * Incrementa en un punto el poder acumulado
	 */
	public void incrementarPoder() {
		if(this.poderAcumulado >= 5) {
			this.poderAcumulado = 5;
		}
		else {
			this.poderAcumulado++;
		}
	}

	/**
	 * Establece a 0 el poder acumulado
	 */
	public void vaciarPoder() {
		this.poderAcumulado = 0;
	}

	/**
	 * Movimiento de ataque del personaje. El valor mínimo dependerá del modo de
	 * dificultad elegida por el usuario.
	 * 
	 * @param rangoMinimo Valor minimo de daño que puede ejercer al atacar.
	 * @return Valor total de daño que puede ejercer al atacar.
	 */
	public int atacar() {
		return Utilidades.numeroAleatorio(1, this.getAtaque());
	}

	/**
	 * Movimiento de defensa del personaje. El valor mínimo dependerá del modo de
	 * dificultad elegida por el usuario.
	 * 
	 * @param rangoMinimo Valor mínimo que puede tomar la defensa del personaje.
	 * @return Valor total que puede tomar la defensa del personaje.
	 */
	public int defender() {
		return Utilidades.numeroAleatorio(1, this.getDefensa());
	}

	/**
	 * Determina al azar la suerte del personaje en cada movimiento, pudiendo ser
	 * nula (movimiento fallido), normal o ha tenido suerte y sus ataques o defensas
	 * duplicarán su valor (movimiento crítico).
	 * 
	 * @return Si 0 (fallida), 1 (normal) o 2 (doble).
	 */
	public int probarSuerte() {
		return Utilidades.numeroAleatorio(0, 2);
	}

	/**
	 * Genera aleatoriamente los items que puede tener (o no) un personaje
	 */
	public void rellenaItems() {
		for(int i=0; i<items.length;i++) {
			//0 no tiene item en una de las ranuras, 1 si genera un item
			switch(Utilidades.numeroAleatorio(0, 1)) { 
				case 0:
					items[i] = null;
					break;
				case 1:
					items[i]=Pociones.eligePocion();
			}
		}
	}
	
	/**
	 * Elimina un item de la pocision especificada en el array items
	 * 
	 * @param indice Posicion (item) a eliminar
	 */
	public void vaciaRanura(int indice) {
		items[indice] = null;
	}
	
	/**
	 * Metodo que devuelve el numero de items que tiene el personaje
	 * 
	 * @return Numero total de items
	 */
	public int numeroItems() {
		int numero = 0;
		for(Item i : items) {
			if(i != null) {
				numero++;
			}
		}
		return numero;
	}
	
	/**
	 * Busca un item en el array
	 * 
	 * @return Devuelve la posicion del item en el array, -1 si no existe
	 */
	public int buscaItem() {
		int posicion = -1;
		for(int i=0; i<items.length; i++) {
			if(items[i]!=null) {
				posicion=i;
				i=items.length;
			}
		}
		return posicion;
	}
	
	public void insertaItem(Item item, int indice) {
		items[indice] = item;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj != null) {
			if (this == obj) {
				result = true;
			} else {
				if (obj instanceof Personaje) {
					Personaje tmp = (Personaje) obj;
					if (this.nombre != null && this.nombre.equals(tmp.nombre)) {
						result = true;
					}
				}
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + "\nVida: " + vida + " / " + vidaMaxima + "\nAtaque: " + ataque + "\nDefensa: "
				+ defensa + "\nItems: " + numeroItems() + " / " + items.length;
	}

}
