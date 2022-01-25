import controlador.Controlador;

/**
 * Proyecto primera evaluación.
 * Juego de lucha para uno o dos jugadores.
 * 
 * Partida Normal, el usuario lucha contra una serie de adversarios creados por la cpu.
 * Partida 1 versus 1, dos usuarios seleccionan a sus personajes para que luchen entre sí.
 * 
 * @author José Mª Cámara Pasadas
 *
 */
public class App {

	public static void main(String[] args) {
		Controlador c = new Controlador();
		c.iniciaJuego();
	}
}
