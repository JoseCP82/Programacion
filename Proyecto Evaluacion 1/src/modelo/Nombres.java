package modelo;

import utils.Utilidades;

public class Nombres {

	private static String[] nombreMagos = { "Harry Potter", "Panoramix", "Gandalf", "Hermione", "Constantine", "Zelda",
			"Merlín", "Horadrim", "Bruja Escarlata", "Sakura" };
	private static String[] nombreGuerreros = { "Aren", "Bjorn", "Haakon", "Ragnar", "Ivar", "Helge", "Sigurd", "Thor", "Odín",
			"Jensen" };
	private static String[] nombreJefes = { "La reina Valkyria", "Rey Dado", "Ornstein & Smoug", "Isshin Ashina", "Gill",
			"General RAAM", "Némesis", "The End", "Dark Samus", "Malus" };

	/**
	 * Selecciona un nombre al azar recogido de uno de los distintos arrays
	 * 
	 * @param opcion Array del cual queremos obtener el nombre
	 * @return Nombre seleccionado aleatoriamente
	 */
	public static String eligeNombre(int opcion) {
		String nombre = "";
		switch (opcion) {
		case 1:
			nombre = nombreMagos[Utilidades.numeroAleatorio(0, 9)];
			break;
		case 2:
			nombre = nombreGuerreros[Utilidades.numeroAleatorio(0, 9)];
			break;
		case 3:
			nombre = nombreJefes[Utilidades.numeroAleatorio(0, 9)];
			break;
		default:
			System.out.println("Error inesperado.");
			break;
		}
		return nombre;
	}
}
