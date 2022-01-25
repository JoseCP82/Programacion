package modelo;

import utils.Utilidades;

public class Pociones {

	private static Item pocionMenor = new Item("Poción de vida menor",20);
	private static Item pocionMayor = new Item("Poción de vida mayor",50);
	private static Item pocionCompleta = new Item("Poción de vida completa",100);
	
	/**
	 * Metodo que obtiene al azar un item de los ya predefinidos
	 * 
	 * @return 0 pocion menor, 1 pocion mayor, 2 pocion completa
	 */
	public static Item eligePocion() {
		Item pocion = null;
		switch(Utilidades.numeroAleatorio(0, 2)) {
		case 0:
			pocion = pocionMenor;
			break;
		case 1:
			pocion = pocionMayor;
			break;
		case 2:
			pocion = pocionCompleta;
			break;
		}
		return pocion;
	}
}
