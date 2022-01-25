package utils;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import modelo.Personaje;

public class Storage {
	
	/**
	 * Guarda un objeto de tipo Personaje en un archivo en disco
	 * 
	 * @param aguardar Personaje a guardar
	 * @param file Nombre del archivo donde guardar la informacion
	 * @return True o false si se realizó con exito o no
	 */
	public static boolean savePersonaje(Personaje aguardar, String file) {
		boolean result = false;
		try {
			FileOutputStream f = new FileOutputStream(new File(file));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(aguardar);
			o.close();
			f.close();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// Metodo modificado, comentarlo en clase (Carlos o M.Carmen)
	
	/**
	 * Lee informacion de un fichero
	 * 
	 * @param file Fichero en el cual se encuentra la informacion
	 * @return Objeto de tipo Personaje con o sin informacion 
	 */
	public static Personaje readPersonaje(String file) {
		Personaje result = null;
		try (ObjectInputStream i = new ObjectInputStream(new FileInputStream(file))) {
			while (true) {
				result = (Personaje) i.readObject();
			}
		} catch (FileNotFoundException e) {
			// No hacemos nada
			// e.printStackTrace();
		} catch (EOFException e) {
			// No hacemos nada, solo salta la excepción
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
}
