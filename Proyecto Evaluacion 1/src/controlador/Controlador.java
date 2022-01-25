package controlador;

import modelo.Guerrero;
import modelo.Jefe;
import modelo.Mago;
import modelo.Partida;
import modelo.PartidaVersus;
import modelo.Personaje;
import utils.Storage;
import utils.Utilidades;
import vista.Vista;

public class Controlador {

	static String archivo = "jugadorSave.jcp";
	static final int PUNTOS_BONUS = 5;

	/**
	 * Metodo principal en el cual empieza el juego 
	 */
	public void iniciaJuego() {
		int opcPrincipal = 0, opcNormal = 0, opcVersus = 0, result = -2;
		Partida partida = null;
		PartidaVersus versus = null;
		Personaje jugador = null;

		do {
			Utilidades.saltoLinea();
			Vista.menuPrincipal();
			opcPrincipal = Utilidades.validaEnteroPositivo("    Elija una opción: ");
			Utilidades.saltoLinea();
			switch (opcPrincipal) {
			case 1:
				do {
					Vista.menuNormal();
					opcNormal = Utilidades.validaEnteroPositivo("    Elija una opción: ");
					Utilidades.saltoLinea();
					switch (opcNormal) {
					case 1:
						partida = creaPartida();
						jugador = partida.getJugador();
						// Seteamos la vida del jugador su nivel maximo por si hemos importado un
						// personaje con un valor inferior de ésta.
						jugador.setVida(jugador.getVidaMaxima());
						for (int i = 0; i < partida.getLuchadores().length; i++) {
							Personaje oponente = partida.getLuchadores()[i];
							Vista.muestraOponente(oponente);
							Utilidades.saltoLinea();
							Vista.mensajeBatalla();
							Utilidades.pulsaIntro();
							result = lucha(jugador, oponente);

							// Ambos combatientes son eliminados (empatan)
							if (result == -1) {
								i = partida.getLuchadores().length;
								Vista.mensajeEmpate();
								Vista.mensajeFinPartida();
							}
							// Gana el oponente
							else if (result == 0) {
								i = partida.getLuchadores().length;
								Vista.personajeEliminado(jugador);
								Vista.mensajeFinPartida();
							}
							// Gana el usuario
							else {
								Vista.personajeEliminado(oponente);
								Utilidades.saltoLinea();
								if (oponente instanceof Jefe) {
									Utilidades.saltoLinea();
									Vista.mensajeHasGanado();
								}
								Utilidades.pulsaIntro();
								// Bonificamos al jugador con los items que tuviera el oponente eliminado
								recibeItems(jugador, oponente);
								Utilidades.saltoLinea();
								Utilidades.pulsaIntro();
								// Bonificamos al jugador con X puntos para elevar el nivel de atributos
								subeNivel(jugador);
							}
							Utilidades.saltoLinea();
							Utilidades.pulsaIntro();
						}
						break;
					case 2:
						Vista.infoPartidaNormal();
						Utilidades.saltoLinea();
						Utilidades.pulsaIntro();
						break;
					case 0:
						break;
					default:
						Vista.mensajeOpcionIncorrecta();
					}
				} while (opcNormal != 0);
				break;
			case 2:
				do {
					Vista.menuVersus();
					opcVersus = Utilidades.validaEnteroPositivo("    Elija una opción: ");
					Utilidades.saltoLinea();
					switch (opcVersus) {
					case 1:
						versus = creaPartidaVersus();
						Vista.mensajeBatalla();
						Utilidades.pulsaIntro();
						result = luchaVersus(versus.getJugadores());
						if (result == -1) {
							Vista.mensajeEmpate();
						} else if (result == 0) {
							Vista.personajeEliminado(versus.getJugadores()[0]);
						} else {
							Vista.personajeEliminado(versus.getJugadores()[1]);
						}
						Utilidades.saltoLinea();
						Vista.mensajeFinPartida();
						Utilidades.saltoLinea();
						Utilidades.pulsaIntro();
						break;
					case 2:
						Vista.infoPartidaVersus();
						Utilidades.saltoLinea();
						Utilidades.pulsaIntro();
						break;
					case 0:
						break;
					default:
						Vista.mensajeOpcionIncorrecta();
					}
				} while (opcVersus != 0);
				break;
			case 0:
				Vista.mensajeFinPrograma();
				break;
			default:
				Vista.mensajeOpcionIncorrecta();
			}
			Utilidades.saltoLinea();
		} while (opcPrincipal != 0);
	}
	// *************** Fin método main *****************

	
	
	// *********** Comienzo metodos Partida Versus *************

	/**
	 * Crea una partida de un jugador contra otro
	 * 
	 * @return Partidad creada
	 */
	public PartidaVersus creaPartidaVersus() {
		int opcClase = 0;
		char continuar = ' ';
		PartidaVersus versus = new PartidaVersus();
		do {
			for (int i = 0; i < versus.getJugadores().length; i++) {
				Vista.turnoJugador(i + 1);
				Utilidades.saltoLinea();
				Vista.menuClase();
				opcClase = Utilidades.validaEntero("    Elija una opción: ", 1, 2);
				Utilidades.saltoLinea();
				Vista.introduceNombreJugador();
				String nombre = Utilidades.leeString("    --> ");
				versus.creaJugadores(opcClase, nombre, i);
				Utilidades.saltoLinea();
			}
			for (int i = 0; i < versus.getJugadores().length; i++) {
				Vista.muestraTurno(versus.getJugadores()[i], i + 1);
				Utilidades.saltoLinea();
			}
			continuar = Utilidades.validarSalida("¿Desea continuar (S/N)? ");
		} while (continuar == 'N' || continuar == 'n');
		Utilidades.saltoLinea();
		return versus;
	}

	/**
	 * Metodo en el que dos luchadores realizan acciones elegidas por los usuarios
	 * (2)
	 * 
	 * @param jugadores Array con datos de los dos personajes elegidos por los
	 *                  usuarios
	 * @return -1 si hubo empate, 0 si ganó el jugador 1 o 1 si ganó el jugador 2
	 */
	public int luchaVersus(Personaje[] jugadores) {
		int accion[] = { 0, 0 }, ataque[] = { 0, 0 }, defensa[] = { 0, 0 }, especial[] = { 0, 0 }, suerte[] = { 1, 1 },
				total[] = { 0, 0 }, totalSuerte[] = { 0, 0 }, result = -2, empieza = 0;
		Personaje aux = null;

		// determinamos al azar que jugador empieza la partida
		empieza = Utilidades.numeroAleatorio(1, 2);
		if (empieza == 2) {
			aux = jugadores[0];
			jugadores[0] = jugadores[1];
			jugadores[1] = aux;
		}
		Vista.empiezaPrimero(jugadores[0]);
		Utilidades.saltoLinea();

		while (jugadores[0].getVida() > 0 && jugadores[1].getVida() > 0) {
			for (int i = 0; i < jugadores.length; i++) {
				// Turno del jugador [i]
				Vista.muestraTurno(jugadores[i]);
				Utilidades.saltoLinea();
				accion[i] = muestraMenuAcciones(jugadores[i].getPoderAcumulado(), 1);
				Utilidades.saltoLinea();
				suerte[i] = jugadores[i].probarSuerte(); // Generamos la suerte del jugador [i]
				// El usuario elige atacar
				if (accion[i] == 1) {
					ataque[i] = jugadores[i].atacar();
					Vista.muestraMovimiento(jugadores[i].getNombre(), 1, ataque[i]);
					ataque[i] *= suerte[i];
					// Determinamos que el total de ataque conseguido en la jugada no sea mayor
					// que el ataque máximo predefinido del personaje
					if (ataque[i] > jugadores[i].getAtaque()) {
						ataque[i] = jugadores[i].getAtaque();
					}
					totalSuerte[i] = ataque[i];
				}
				// El usuario elige defenderse
				else if (accion[i] == 2) {
					defensa[i] = jugadores[i].defender();
					Vista.muestraMovimiento(jugadores[i].getNombre(), 2, defensa[i]);
					defensa[i] *= suerte[i];
					// Determinamos que el total de defensa conseguida en la jugada no sea mayor
					// que la defensa máxima predefinida del personaje
					if (defensa[i] > jugadores[i].getDefensa()) {
						defensa[i] = jugadores[i].getDefensa();
					}
					totalSuerte[i] = defensa[i];
				}
				// El usuario elige usar poder especial
				else if (accion[i] == 3) {
					if (jugadores[i] instanceof Mago) {
						especial[i] = ((Mago) jugadores[i]).defensaEspecial();
					} else {
						especial[i] = ((Guerrero) jugadores[i]).ataqueEspecial();
					}
					Vista.muestraMovimiento(jugadores[i].getNombre(), 3, especial[i]);
					jugadores[i].vaciarPoder();
					especial[i] *= suerte[i];
					// Determinamos que el total de especial conseguido en la jugada no sea mayor
					// que el especial máximo predefinido del personaje
					if (jugadores[i] instanceof Mago) {
						if (especial[i] > ((Mago) jugadores[i]).getPoderDefensaEspecial()) {
							especial[i] = ((Mago) jugadores[i]).getPoderDefensaEspecial();
						}
					} else {
						if (especial[i] > ((Guerrero) jugadores[i]).getPoderAtaqueEspecial()) {
							especial[i] = ((Guerrero) jugadores[i]).getPoderAtaqueEspecial();
						}
					}
					totalSuerte[i] = especial[i];
				}
				// El usuario elige usar algun item
				else if (accion[i] == 0) {
					// Eligió no usar item por lo tanto vuelve el menu de acciones para que elija
					// otra
					if (eligeItem(jugadores[i],0) == 0) {
						i--;
					}
				}
				Utilidades.saltoLinea();
			}

			// Evaluando daños
			Vista.mensajeResultado();
			Utilidades.saltoLinea();
			Vista.muestraSuerte(jugadores[0], suerte[0], accion[0], totalSuerte[0]);

			// daño que inflinge el oponente a jugador
			total[1] = ataque[1] - defensa[0];
			if (total[1] > 0) {
				jugadores[1].incrementarPoder();
				Vista.recibeDanios(jugadores[0], jugadores[1], 1, total[1]);
				jugadores[0].setVida(jugadores[0].getVida() - total[1]);
			} else {
				if (accion[1] != 2) {
					Vista.recibeDanios(jugadores[0], jugadores[1], 0, total[1]);
				} else {
					Vista.recibeDanios(jugadores[0], jugadores[1], -1, total[1]);
				}
			}
			Utilidades.saltoLinea();

			// daño que inflige jugador a oponente
			Vista.muestraSuerte(jugadores[1], suerte[1], accion[1], totalSuerte[1]);

			total[0] = ataque[0] - defensa[1];
			if (total[0] > 0) {
				jugadores[0].incrementarPoder();
				Vista.recibeDanios(jugadores[1], jugadores[0], 1, total[0]);
				jugadores[1].setVida(jugadores[1].getVida() - total[0]);
			} else {
				if (accion[0] != 2) {
					Vista.recibeDanios(jugadores[1], jugadores[0], 0, total[0]);
				} else {
					Vista.recibeDanios(jugadores[1], jugadores[0], -1, total[0]);
				}
			}
			Utilidades.saltoLinea();

			//Valoramos el desenlace del combate
			if (jugadores[0].getVida() <= 0) { //Pierde el usuario
				result = 0;
			} 
			if (jugadores[1].getVida() <= 0) { //Pierde el oponente
				result = 1;				
			} 
			if(jugadores[0].getVida() <= 0 && jugadores[1].getVida() <= 0) { //Hay un empate
				result = -1;
			}

			for (int i = 0; i < 2; i++) {
				accion[i] = 0;
				ataque[i] = 0;
				defensa[i] = 0;
				especial[i] = 0;
				suerte[i] = 1;
				total[i] = 0;
			}
		}
		return result;
	}
	// *********** Fin métodos Partida Versus ***********

	
	
	// *********** Comienzo métodos partida Normal ************

	/**
	 * Crea una partida normal
	 * 
	 * @return Partida creada
	 */
	public Partida creaPartida() {
		final int NUM_OPONENTES = 3;
		Partida partida = null;
		int opcDificultad = 0, opcClase = 0;
		char continuar = ' ';

		do {
			Vista.menuDificultad();
			opcDificultad = Utilidades.validaEntero("    Elija una opción: ", 1, 3);
			Utilidades.saltoLinea();
			if (opcDificultad == 1) {
				partida = new Partida(NUM_OPONENTES, "Fácil");
			} else if (opcDificultad == 2) {
				partida = new Partida(NUM_OPONENTES, "Media");
			} else {
				partida = new Partida(NUM_OPONENTES, "Difícil");
			}

			// Posibilidad de importar un personaje ya creado
			// Si no, lo creamos de cero.
			Personaje importado = Storage.readPersonaje(archivo);
			if (importado != null) {
				importado.setVida(importado.getVidaMaxima());
				importado.setPoderAcumulado(0);
				Vista.existePersonaje();
				Utilidades.saltoLinea();
				Vista.muestraJugador(importado);
				Utilidades.saltoLinea();
				char opc = Utilidades.validarSalida("¿Desea importarlo (S/N)? ");
				if (opc == 's' || opc == 'S') {
					partida.setJugador(importado);
				} else {
					Utilidades.saltoLinea();
					String nombre = Utilidades.leeString("Introduce el nombre para tu personaje: ");
					Utilidades.saltoLinea();
					Vista.menuClase();
					opcClase = Utilidades.validaEntero("    Elija una opcion: ", 1, 2);
					partida.creaJugador(opcClase, nombre);
				}
			} else {
				String nombre = Utilidades.leeString("Introduce el nombre para tu personaje: ");
				Utilidades.saltoLinea();
				Vista.menuClase();
				opcClase = Utilidades.validaEntero("    Elija una opcion: ", 1, 2);
				partida.creaJugador(opcClase, nombre);
			}

			Utilidades.saltoLinea();
			Vista.muestraDatosSeleccion(partida);
			continuar = Utilidades.validarSalida("¿Desea continuar (S/N)? ");
			Utilidades.saltoLinea();
		} while (continuar == 'n' || continuar == 'N');

		// Guardamos la información del personaje creado en un archivo
		guardaPersonaje(partida.getJugador());
		Utilidades.saltoLinea();

		// Creamos a los opoenentes y jefe final de forma aleatoria
		partida.creaOponentes();
		partida.creaJefes();

		return partida;
	}

	/**
	 * Metodo donde un personaje controlado por el usuario combate contra otros
	 * personaje controlados por la cpu
	 * 
	 * @param jugador  Personaje correspondiente al usuario
	 * @param oponente Personaje correspondiente a la cpu
	 * @return -2 si hubo empate, -1 si perdió el usuario o >=0 (indice array) si
	 *         perdío la cpu
	 */
	public int lucha(Personaje jugador, Personaje oponente) {
		int accionJugador = 0, ataqueJugador = 0, defensaJugador = 0, especialJugador = 0, suerteJugador = 1,
				accionOponente = 0, ataqueOponente = 0, defensaOponente = 0, especialOponente = 0, suerteOponente = 1,
				result = -2, total = 0, totalSuerteJugador = 0, totalSuerteOponente = 0;

		while (jugador.getVida() > 0 && oponente.getVida() > 0) {
			boolean salida = false;
			// Turno del jugador
			do {
				Vista.muestraTurno(jugador);
				Utilidades.saltoLinea();
				
				//Mostramos un menu de acciones u otro dependiendo de si poder acumulado está al maximo
				//Si el turno es del oponente no se muestra ningun menú
				accionJugador = muestraMenuAcciones(jugador.getPoderAcumulado(), 1);
				
				Utilidades.saltoLinea();
				suerteJugador = jugador.probarSuerte(); // Generamos la suerte del jugador
				if (accionJugador == 1) {
					ataqueJugador = jugador.atacar();
					Vista.muestraMovimiento(jugador.getNombre(), 1, ataqueJugador);
					ataqueJugador *= suerteJugador;
					// Determinamos que el total de ataque conseguido en la jugada no sea mayor
					// que el ataque máximo predefinido del personaje
					if (ataqueJugador > jugador.getAtaque()) {
						ataqueJugador = jugador.getAtaque();
					}
					totalSuerteJugador = ataqueJugador;
					salida = true;
				} else if (accionJugador == 2) {
					defensaJugador = jugador.defender();
					Vista.muestraMovimiento(jugador.getNombre(), 2, defensaJugador);
					defensaJugador *= suerteJugador;
					// Determinamos que el total de defensa conseguida en la jugada no sea mayor
					// que la defensa máxima predefinida del personaje
					if (defensaJugador > jugador.getDefensa()) {
						defensaJugador = jugador.getDefensa();
					}
					totalSuerteJugador = defensaJugador;
					salida = true;
				} else if (accionJugador == 3) {
					if (jugador instanceof Mago) {
						especialJugador = ((Mago) jugador).defensaEspecial();
					} else {
						especialJugador = ((Guerrero) jugador).ataqueEspecial();
					}
					Vista.muestraMovimiento(jugador.getNombre(), 3, especialJugador);
					jugador.vaciarPoder();
					especialJugador *= suerteJugador;
					// Determinamos que el total de especial conseguido en la jugada no sea mayor
					// que el especial máximo predefinido del personaje
					if (jugador instanceof Mago) {
						if (especialJugador > ((Mago) jugador).getPoderDefensaEspecial()) {
							especialJugador = ((Mago) jugador).getPoderDefensaEspecial();
						}
					} else {
						if (especialJugador > ((Guerrero) jugador).getPoderAtaqueEspecial()) {
							especialJugador = ((Guerrero) jugador).getPoderAtaqueEspecial();
						}
					}
					totalSuerteJugador = especialJugador;
					salida = true;
				}
				// El usuario elige usar algun item
				else if (accionJugador == 0) {
					// Eligió no usar item por lo tanto vuelve el menu de acciones para que elija otra
					if (eligeItem(jugador,0) == 0) {
						salida = false;
					}
					else {
						salida = true;
					}
					Utilidades.saltoLinea();
				}
			} while (!salida);
			Utilidades.saltoLinea();
			Utilidades.pulsaIntro();

			salida = false;
			// Turno del oponente(cpu)
			do {
				Vista.muestraTurno(oponente);
				Utilidades.saltoLinea();
				accionOponente = muestraMenuAcciones(oponente.getPoderAcumulado(), 2);
				suerteOponente = oponente.probarSuerte(); // Generamos la suerte del oponente
				if (accionOponente == 1) {
					ataqueOponente = oponente.atacar();
					Vista.muestraMovimiento(oponente.getNombre(), 1, ataqueOponente);
					ataqueOponente *= suerteOponente;
					// Determinamos que el total de ataque conseguido en la jugada no sea mayor
					// que el ataque máximo predefinido del personaje
					if (ataqueOponente > oponente.getAtaque()) {
						ataqueOponente = oponente.getAtaque();
					}
					totalSuerteOponente = ataqueOponente;
					salida = true;
				} else if (accionOponente == 2) {
					defensaOponente = oponente.defender();
					Vista.muestraMovimiento(oponente.getNombre(), 2, defensaOponente);
					defensaOponente *= suerteOponente;
					// Determinamos que el total de defensa conseguida en la jugada no sea mayor
					// que la defensa máxima predefinida del personaje
					if (defensaOponente > oponente.getDefensa()) {
						defensaOponente = oponente.getDefensa();
					}
					totalSuerteOponente = defensaOponente;
					salida = true;
				} else if (accionOponente == 3) {
					if (oponente instanceof Mago) {
						especialOponente = ((Mago) oponente).defensaEspecial();
					} else if (oponente instanceof Guerrero) {
						especialOponente = ((Guerrero) oponente).ataqueEspecial();
					} else {
						ataqueOponente = ((Jefe) oponente).ataqueEspecial();
						defensaOponente = ((Jefe) oponente).defensaEspecial();
					}
					oponente.vaciarPoder();
					if (oponente instanceof Jefe) {
						Vista.muestraMovimiento(oponente.getNombre(), 4, ataqueOponente);
						Vista.muestraMovimiento(oponente.getNombre(), 5, defensaOponente);
						ataqueOponente *= suerteOponente;
						defensaOponente *= suerteOponente;
					} else {
						Vista.muestraMovimiento(oponente.getNombre(), 4, especialOponente);
						especialOponente *= suerteOponente;
						// Determinamos que el total de especial conseguido en la jugada no sea mayor
						// que el especial máximo predefinido del personaje
						if (oponente instanceof Mago) {
							if (especialOponente > ((Mago) oponente).getPoderDefensaEspecial()) {
								especialOponente = ((Mago) oponente).getPoderDefensaEspecial();
							}
						} else {
							if (especialOponente > ((Guerrero) oponente).getPoderAtaqueEspecial()) {
								especialOponente = ((Guerrero) oponente).getPoderAtaqueEspecial();
							}
						}
						totalSuerteOponente = especialOponente;
					}
					salida = true;
				}
				// El oponente elige usar algun item
				else if (accionOponente == 0) {
					// Eligió no usar item por lo tanto vuelve el menu de acciones para que elija otra
					if (eligeItem(oponente,1) == 0) {
						salida = false;
					}
					else {
						salida = true;
					}
				}
			} while (!salida);
			Utilidades.saltoLinea();
			Utilidades.pulsaIntro();

			// Evaluando daños
			Vista.mensajeResultado();
			Utilidades.saltoLinea();
			
			// daño que inflinge el oponente a jugador
			
			if(accionJugador!=0) {
				Vista.muestraSuerte(jugador, suerteJugador, accionJugador, totalSuerteJugador);
			}
			total = ataqueOponente - defensaJugador;
			if (total > 0) {
				oponente.incrementarPoder();
				Vista.recibeDanios(jugador, oponente, 1, total);
				jugador.setVida(jugador.getVida() - total);
			} else {
				if (accionOponente != 2) {
					Vista.recibeDanios(jugador, oponente, 0, total);
				} else {
					Vista.recibeDanios(jugador, oponente, -1, total);
				}
			}
			Utilidades.saltoLinea();

			// daño que inflinge jugador a oponente
			if(accionOponente!=0) {
				Vista.muestraSuerte(oponente, suerteOponente, accionOponente, totalSuerteOponente);
			}
			total = ataqueJugador - defensaOponente;
			if (total > 0) {
				jugador.incrementarPoder();
				Vista.recibeDanios(oponente, jugador, 1, total);
				oponente.setVida(oponente.getVida() - total);
			} else {
				if (accionJugador != 2) {
					Vista.recibeDanios(oponente, jugador, 0, total);
				} else {
					Vista.recibeDanios(oponente, jugador, -1, total);
				}
			}

			Utilidades.saltoLinea();
			Utilidades.pulsaIntro();
			//Valoramos el desenlace del combate
			if (jugador.getVida() <= 0) { //Pierde el usuario
				result = 0;
			} 
			if (oponente.getVida() <= 0) { //Pierde el oponente
				result = 1;				
			} 
			if(jugador.getVida() <= 0 && oponente.getVida() <= 0) { //Hay un empate
				result = -1;
			}

			//Variables a su valor por defecto para emplearlas en el siguiente turno y no acumular valores
			ataqueJugador = 0;
			defensaJugador = 0;
			especialJugador = 0;
			suerteJugador = 1;
			ataqueOponente = 0;
			defensaOponente = 0;
			especialOponente = 0;
			suerteOponente = 1;
		}
		return result;
	}

	/**
	 * Intercambia los items del opoente al jugador
	 * 
	 * @param jugador Personaje que recibe los items
	 * @param oponente Personaje al cual retiramos los items
	 */
	public void recibeItems(Personaje jugador, Personaje oponente) {
		if(jugador.numeroItems()<3) {
			if(oponente.numeroItems()!=0) {
				for(int i=0; i < 3; i++) {
					if(jugador.getItems()[i]==null) {
						for(int j=0; j < 3; j++) {
							if(oponente.getItems()[j]!=null) {
								jugador.insertaItem(oponente.getItems()[j], i);
								oponente.vaciaRanura(j);
								j=3;
							}
						}
					}
				}
				Vista.itemsRecibidos(jugador);
			}
			else {
				// oponente no tiene items
				Vista.inventarioVacio(oponente);
			}
		}
		else {
			// jugador no tiene espacio
			Vista.invetarioLleno(jugador);
		}
	}
	
	/**
	 * Metodo que muestra un menu donde se pueden elegir que atributos subir de
	 * Valor al derrotar a un adversario
	 * 
	 * @param jugador Personaje del usuario el cual será modificado
	 */
	public void subeNivel(Personaje jugador) {
		int opc = 0, vida = 0, ataque = 0, defensa = 0, especial = 0;
		boolean maximo = false, viMax = false, atMax = false, deMax = false, esMax = false;
		char continuar = ' ';

		// Determinamos el valor máximo de los atributos del jugador para que no se
		// sobrepasen al usar los puntos extra ganados en cada combate
		// Serán 100 como tope máximo
		int vidaMax = jugador.getVidaMaxima(), ataqueMax = jugador.getAtaque(), defensaMax = jugador.getDefensa(),
				especialMax = 0;
		if (jugador instanceof Mago) {
			especialMax = ((Mago) jugador).getPoderDefensaEspecial();
		} else {
			especialMax = ((Guerrero) jugador).getPoderAtaqueEspecial();
		}

		// Empleamos los puntos extra en caso de que se pueda
		Vista.mensajeBonificacion(PUNTOS_BONUS);
		Utilidades.saltoLinea();
		Utilidades.pulsaIntro();
		if (vidaMax >= 100 && ataqueMax >= 100 && defensaMax >= 100 && especialMax >= 100) {
			Vista.jugadorMaximo(jugador);
		} else {
			do {
				Vista.muestraJugador(jugador);
				Utilidades.saltoLinea();
				for (int i = 5; i > 0 && !maximo; i--) {
					Vista.mensajePuntos(i);
					Utilidades.saltoLinea();
					Vista.menuMejora();
					opc = Utilidades.validaEntero("    Elija una opción: ", 1, 4);
					Utilidades.saltoLinea();
					if (opc == 1) {
						if (vidaMax < 100) {
							vida++;
						} else {
							Vista.habilidadMaximo();
							Utilidades.saltoLinea();
							Utilidades.pulsaIntro();
							viMax = true;
							i++;
						}
					} else if (opc == 2) {
						if (ataqueMax < 100) {
							ataque++;
						} else {
							Vista.habilidadMaximo();
							atMax = true;
							i++;
						}
					} else if (opc == 3) {
						if (defensaMax < 100) {
							defensa++;
						} else {
							Vista.habilidadMaximo();
							deMax = true;
							i++;
						}
					} else {
						if (especialMax < 100) {
							especial++;
						} else {
							Vista.habilidadMaximo();
							esMax = true;
							i++;
						}
					}
					if (viMax && atMax && deMax && esMax) {
						maximo = true;
					}
				}
				continuar = Utilidades.validarSalida("¿Desea guardar cambios (S/N)? ");
				if (continuar == 'n' || continuar == 'N') {
					vida = ataque = defensa = especial = 0;
					viMax = atMax = deMax = esMax = false;
				}
			} while (continuar == 'n' || continuar == 'N');

			// Seteamos los valores una vez que el usuario queda conforme
			jugador.setVidaMaxima(jugador.getVidaMaxima() + vida);
			jugador.setAtaque(jugador.getAtaque() + ataque);
			jugador.setDefensa(jugador.getDefensa() + defensa);
			if (jugador instanceof Mago) {
				((Mago) jugador).setPoderDefensaEspecial(((Mago) jugador).getPoderDefensaEspecial() + especial);
			} else {
				((Guerrero) jugador).setPoderAtaqueEspecial(((Guerrero) jugador).getPoderAtaqueEspecial() + especial);
			}
			Utilidades.saltoLinea();

			// Mostramos al jugador actualizado
			Vista.muestraJugador(jugador);
			Utilidades.saltoLinea();
			// Guardamos la información del personaje creado
			guardaPersonaje(jugador);
		}
	}
	// ************* Fin métodos partida Normal *************

	
	
	// ************ Comienzo métodos usados para ambas partidas **************

	/**
	 * Muestra por consola los menus (dependiendo del atributo poderAcumulado)con
	 * los movimientos que puede hacer el personaje (para el jugador). La cpu
	 * (oponente) los elije aleatoriamente.
	 * 
	 * @param poderAcumulado Menor de 5 muestra menu simple, mayor de 5 muestra menu
	 *                       de poder especial
	 * @param turno          1 para turno del jugador, 2 para turno del oponente
	 *                       (cpu)
	 * @return Opción elejida por el usuario u opción aleatoria para cpu
	 */
	public int muestraMenuAcciones(int poderAcumulado, int turno) {
		int result = 0;
		if (poderAcumulado < 5) { // Menu simple
			if (turno == 1) { // Turno del jugador
				Vista.menuMovimientosSimple();
				result = Utilidades.validaEntero("    Elija una opción: ", 0, 2);
			} else { // Turno del oponente
				result = Utilidades.numeroAleatorio(0, 2);
			}
		} else { // Menu con poder especial visible
			if (turno == 1) { // Turno del jugador
				Vista.menuMovimientosEspecial();
				result = Utilidades.validaEntero("    Elija una opción: ", 0, 3);
			} else { // Turno del oponente
				result = Utilidades.numeroAleatorio(0, 3);
			}
		}
		return result;
	}

	/**
	 * Guarda todos los datos de un Personaje en un archivo en hdd
	 * 
	 * @param p Personaje a guardar
	 */
	public void guardaPersonaje(Personaje p) {
		if (Storage.savePersonaje(p, archivo)) {
			Vista.jugadorGuardado(true);
		} else {
			Vista.jugadorGuardado(false);
		}
	}

	/**
	 * Muestra los items que tenga el personaje para elegir (o no) uno por turno
	 * 
	 * @param p Personaje con el que interactuar
	 * @param jugador Determina a quien pertenece el personaje, 0=usuario, 1=cpu
	 * @return 0 si no se eligió ningún ítem, 1 si sí se usó alguno
	 */
	public int eligeItem(Personaje p, int jugador) {
		int result = 1;
		boolean salida = false;
		if(jugador==0) {
			do {
				if (p.numeroItems() == 0) {
					Vista.inventarioVacio();
					salida = true;
					result = 0;
				} else {
					Vista.muestraItems(p);
					Utilidades.saltoLinea();
					switch (Utilidades.validaEntero("    Elija una opción: ", 0, 3)) {
					case 1:
						if (usaItem(p, 0)) {
							salida = true;
						}
						break;
					case 2:
						if (usaItem(p, 1)) {
							salida = true;
						}
						break;
					case 3:
						if (usaItem(p, 2)) {
							salida = true;
						}
						break;
					case 0:
						result = 0;
						salida = true;
						break;
					}
				}
			} while (!salida);
		}
		else {
			if(p.numeroItems()!=0) {
				usaItem(p, p.buscaItem());
			}
			else {
				Vista.inventarioVacio(p);
				result = 0;
			}
		}
		return result;
	}

	/**
	 * Aumenta la vida del personaje en el valor que tenga el item y lo elimina
	 * 
	 * @param p      Personaje sobre el que se aplica la accion
	 * @param ranura Indice del array de items donde se almacena el item
	 * @return True o false en el caso de haber usado el item o de que no exista
	 */
	public boolean usaItem(Personaje p, int ranura) {
		boolean result = false;
		int efectoPocion = 0;

		if (p.getItems()[ranura] == null) {
			Utilidades.saltoLinea();
			Vista.ranuraVacia();
			Utilidades.saltoLinea();
		} else {
			efectoPocion = p.getVida() + p.getItems()[ranura].getCapacidad();
			if (efectoPocion > p.getVidaMaxima()) {
				p.setVida(p.getVidaMaxima());
			} else {
				p.setVida(efectoPocion);
			}
			Utilidades.saltoLinea();
			Vista.restauraVida(p, p.getItems()[ranura].getCapacidad());
			// elinina el item del array de items del personaje una vez usado
			p.vaciaRanura(ranura);
			result = true;
		}
		return result;
	}
	// ************ fin métodos usados para ambas partidas **************
}
