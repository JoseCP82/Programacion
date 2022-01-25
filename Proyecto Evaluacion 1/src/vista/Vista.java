package vista;

import modelo.Partida;
import modelo.Personaje;

public class Vista {

	public static void menuPrincipal() {
		System.out.println("*****   Bienvenido   *****");
		System.out.println();
		System.out.println("    1.- Partida Normal");
		System.out.println("    2.- 1 Versus 1");
		System.out.println("    0.- Salir");
		System.out.println();
	}

	public static void menuNormal() {
		System.out.println("*****   Partida Normal   *****");
		System.out.println();
		System.out.println("    1.- Nueva partida");
		System.out.println("    2.- ¿Cómo jugar?");
		System.out.println("    0.- Volver");
		System.out.println();
	}

	public static void menuVersus() {
		System.out.println("*****   Partida Versus   *****");
		System.out.println();
		System.out.println("    1.- Comenzar partida");
		System.out.println("    2.- ¿Cómo jugar?");
		System.out.println("    0.- Volver");
		System.out.println();
	}

	public static void menuDificultad() {
		System.out.println("*****   Nivel de dificultad   *****");
		System.out.println();
		System.out.println("    1.- Fácil");
		System.out.println("    2.- Media");
		System.out.println("    3.- Difícil");
		System.out.println();
	}

	public static void menuClase() {
		System.out.println("*****   Selecciona clase   *****");
		System.out.println();
		System.out.println("    1.- Mago");
		System.out.println("    2.- Guerrero");
		System.out.println();
	}

	public static void menuMovimientosSimple() {
		System.out.println("*****   Acciones   *****");
		System.out.println();
		System.out.println("    1.- Atacar");
		System.out.println("    2.- Defender");
		System.out.println();
		System.out.println("    0.- Usar Items");
		System.out.println();
	}

	public static void menuMovimientosEspecial() {
		System.out.println("*****   Acciones   *****");
		System.out.println();
		System.out.println("    1.- Atacar");
		System.out.println("    2.- Defender");
		System.out.println("    3.- Poder Especial");
		System.out.println("    0.- Usar Items");
		System.out.println();
	}

	public static void menuMejora() {
		System.out.println("*****   Mejora   *****");
		System.out.println();
		System.out.println("    1.- Vida");
		System.out.println("    2.- Ataque");
		System.out.println("    3.- Defensa");
		System.out.println("    4.- Poder Especial");
		System.out.println();
	}

	public static void mensajeBatalla() {
		System.out.println("*****   Comienza la batalla...   *****");
		System.out.println();
	}

	/**
	 * Muestra un mensaje de puntos d habilidad conseguidos
	 * 
	 * @param puntos Puntos totales a mostrar
	 */
	public static void mensajeBonificacion(int puntos) {
		System.out.println("*****   Bonificación   *****");
		System.out.println();
		System.out.println("Consigues " + puntos + " puntos para subir habilidades...");
	}

	public static void existePersonaje() {
		System.out.println("Existe un personaje ya creado...");
	}

	/**
	 * Muestra por consola los mensajes si se guardo o no el objeto
	 * 
	 * @param exito Recibe true o false si hubo exito o no en el momento de guadar un objeto en fichero
	 */
	public static void jugadorGuardado(boolean exito) {
		if (exito) {
			System.out.println("Personaje guardado exitosamente.");
		} else {
			System.out.println("Ocurrió un error, no se pudo guardar el personaje.");
		}
	}

	/**
	 * Muestra por pantalla el nombre de un Personaje junto con la frase
	 * 
	 * @param p Personaje a mostrar
	 */
	public static void jugadorMaximo(Personaje p) {
		System.out.println("¡¡¡ "+ p.getNombre() + " ha alcanzado el nivel máximo !!!");
	}

	public static void habilidadMaximo() {
		System.out.println("¡¡¡ Habilidad al máximo. No se puede subir de nivel !!!");
	}

	/**
	 * Muestra por pantalla todos los datos de un Personaje
	 * 
	 * @param p Personaje a mostrar
	 */
	public static void muestraJugador(Personaje p) {
		System.out.println(p);
	}

	/**
	 * Muestra un mensaje con puntos de habilidad restantes
	 * 
	 * @param puntos Puntos a mostrar
	 */
	public static void mensajePuntos(int puntos) {
		System.out.println("Puntos restantes " + puntos);
	}

	/**
	 * Muestra un mesaje con el jugador que empieza la partida
	 * 
	 * @param p Personaje a mostrar
	 */
	public static void empiezaPrimero(Personaje p) {
		System.out.println(p.getNombre() + " empieza primero...");
	}

	/**
	 * Muestra el turno del jugador 1 o 2
	 * 
	 * @param numeroJugador Turno a mostrar (1 o 2)
	 */
	public static void turnoJugador(int numeroJugador) {
		System.out.println("Jugador " + numeroJugador);
	}

	public static void introduceNombreJugador() {
		System.out.println("Introduce un nombre para tu personaje...");
	}

	public static void mensajeEmpate() {
		System.out.println("¡¡¡ Ambos luchadores han sido eliminados !!!");
	}

	/**
	 * Muestra el nombre del Personaje que ha sido eliminado
	 * 
	 * @param p Personaje a mostrar
	 */
	public static void personajeEliminado(Personaje p) {
		System.out.println("¡¡¡ " + p.getNombre() + " ha sido eliminado !!!");
	}

	public static void mensajeFinPartida() {
		System.out.println("*****   ¡¡¡ Fin de la partida !!!   *****");
	}

	public static void mensajeHasGanado() {
		System.out.println("*****   ¡¡¡ Has ganado todos los combates !!!   *****");
	}

	public static void mensajeOpcionIncorrecta() {
		System.out.println("¡¡¡ Opción incorrecta !!!");
	}

	public static void mensajeFinPrograma() {
		System.out.println("*****   ¡¡¡ Programa finalizado. Hasta pronto !!!   *****");
	}

	public static void mensajeResultado() {
		System.out.println("*****   Mostrando resultados...   *****");
	}

	/**
	 * Muestra por consola que personaje ha sido atacado, que resultado se ha
	 * obtenido y quien lo provoca
	 * 
	 * @param p1     Personaje el cual recibe el ataque
	 * @param p2     Personaje atacante
	 * @param opcion -1 si bloquea el ataque, 0 si no recibe daños y 1 si ha
	 *               recibido daños
	 * @param puntos Número de puntos de daños recibidos
	 */
	public static void recibeDanios(Personaje p1, Personaje p2, int opcion, int puntos) {
		if (opcion == -1) {
			System.out.println(p1.getNombre() + " ha bloqueado el ataque de " + p2.getNombre());
		} else if (opcion == 0) {
			System.out.println(p1.getNombre() + " no recibe daños de " + p2.getNombre());
		} else {
			System.out.println(p1.getNombre() + " ha recibido daños de " + p2.getNombre() + 
					" de " + puntos + " puntos.");
		}
	}

	/**
	 * Muestra por pantalla el nombre del jugador junto con la accion realizada 
	 * y puntos de accion conseguidos
	 * 
	 * @param nombre Nombre del personaje a mostrar
	 * @param tipoMovimiento Accion a mostrar (ataque, defensa o poder especial)
	 * @param valor Puntos conseguidos a mostrar
	 */
	public static void muestraMovimiento(String nombre, int tipoMovimiento, int valor) {
		if (tipoMovimiento == 1) {
			System.out.println(nombre + " consigue " + valor + " puntos de Ataque.");
		} else if (tipoMovimiento == 2) {
			System.out.println(nombre + " consigue " + valor + " puntos de Defensa.");
		} else if (tipoMovimiento == 3) {
			System.out.println(nombre + " consigue " + valor + " puntos de Poder Especial.");
		} else if (tipoMovimiento == 4) {
			System.out.println(nombre + " consigue " + valor + " puntos de Poder de Ataque Especial.");
		} else {
			System.out.println(nombre + " consigue " + valor + " puntos de Poder de Defensa Especial.");
		}
	}

	/**
	 * Muestra que nivel de suerte tienen un personaje en ataque o defensa
	 * 
	 * @param p Personaje a mostrar (su nombre) 
	 * @param suerte Valorque determina 0 fallido, 1 normal o 2 doble
	 * @param accion Accion seleccionada para mostrar
	 * @param valor Puntos conseguidos en la accion
	 */
	public static void muestraSuerte(Personaje p, int suerte, int accion, int valor) {
		// Si la suerte es 0 fallará en la acción
		if (suerte == 0) {
			if (accion == 1) {
				System.out.println("¡¡¡ " + p.getNombre() + " ha fallado en el ataque !!!");
			} else if (accion == 2) {
				System.out.println("¡¡¡ A " + p.getNombre() + " le ha fallado la defensa !!!");
			} else if(accion == 3) {
				System.out.println("¡¡¡ " + p.getNombre() + " ha fallado en el poder especial !!!");
			}
		}
		// Si no (será 2), consigue el doble de puntos en la acción
		else if (suerte == 2) {
			if (accion == 1) {
				System.out.println("¡¡¡ " + p.getNombre() + " ha tenido suerte y consigue un golpe crítico de " +
						valor +" puntos en el ataque !!!");
			} else if (accion == 2) {
				System.out.println(
						"¡¡¡ " + p.getNombre() + " ha tenido suerte, consigue " + valor + " puntos de defensa !!!");
			} else {
				System.out.println("¡¡¡ " + p.getNombre() + " ha tenido suerte, consigue " + valor
						+ " de puntos en el poder especial !!!");
			}
		}
	}

	/**
	 * Muestra el turno de un personaje junto con Nivel de vida maxima y actual y poder acumulado
	 * 
	 * @param p Personaje a mostrar
	 */
	public static void muestraTurno(Personaje p) {
		System.out.println("Turno de " + p.getNombre() + "\nNivel de Vida: " + p.getVida() + " / " + p.getVidaMaxima()
				+ " --> Poder Acumulado: " + p.getPoderAcumulado());
	}

	/**
	 * Muestra numero del jugador junto con sus atributos 
	 * 
	 * @param p Personaje a mostrar
	 * @param indice Numero de jugador (1 o 2)
	 */
	public static void muestraTurno(Personaje p, int indice) {
		System.out.println("Jugador " + indice);
		System.out.println(p);
	}

	/**
	 * Muestra datos de la partida creada (Personaje del jugador y nivel de dificultad)
	 * 
	 * @param partida Partida creada con datos del personaje y partida en sí
	 */
	public static void muestraDatosSeleccion(Partida partida) {
		System.out.println("Tu personaje...");
		System.out.println(partida.getJugador());
		System.out.println("Nivel de dificultad: " + partida.getDificultad() + "\n");
	}

	/**
	 * Muestra los datos del oponente
	 * 
	 * @param p Personaje a mostrar
	 */
	public static void muestraOponente(Personaje p) {
		System.out.println("Tu oponente...");
		System.out.println(p);
	}

	public static void muestraItems(Personaje p) {
		System.out.println("*****   Inventario   *****");
		System.out.println();
		for(int i=0; i<p.getItems().length;i++) {
			if(p.getItems()[i] == null) {
				System.out.println("   "+(i+1)+".- Ranura vacía");
			}
			else {
				System.out.println("   "+(i+1)+".- "+p.getItems()[i]);
			}
		}
		System.out.println("   0.- Volver");
	}
	
	public static void itemsRecibidos(Personaje p) {
		System.out.println("¡¡¡ " + p.getNombre() + " a conseguido items !!!");
	}
	
	public static void invetarioLleno(Personaje p) {
		System.out.println(p.getNombre() + " no tiene espacio libre en su inventario.");
	}
	
	public static void inventarioVacio(Personaje p) {
		System.out.println(p.getNombre() + " no tiene items en su inventario.");
	}
	
	public static void inventarioVacio() {
		System.out.println("No tienes items en tu inventario.");
	}
	
	public static void ranuraVacia() {
		System.out.println("No existen items en esta ranura.");
	}
	
	public static void restauraVida(Personaje p, int puntos) {
		System.out.println("¡¡¡ " + p.getNombre() + " consigue " + puntos + " de vida !!!");
	}
	
	public static void infoPartidaNormal() {
		System.out.println("Éste modo de juego consiste en derrotar a varios adversarios hasta conseguir llegar "
				+ "al jefe final\nDerrota a tus oponentes y consigue puntos de habilidad y ataques "
				+ "mejorados con cada batalla.");
	}

	public static void infoPartidaVersus() {
		System.out.println("En éste modo de juego podrás luchar contra un amigo.\nCread vuestros personajes "
				+ "y que gane el mejor!!!");
	}
}
