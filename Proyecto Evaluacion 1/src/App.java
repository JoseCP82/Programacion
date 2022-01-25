import controlador.Controlador;

/**
 * Proyecto primera evaluaci�n.
 * Juego de lucha para uno o dos jugadores.
 * 
 * Partida Normal, el usuario lucha contra una serie de adversarios creados por la cpu.
 * Partida 1 versus 1, dos usuarios seleccionan a sus personajes para que luchen entre s�.
 * 
 * @author Jos� M� C�mara Pasadas
 *
 */
public class App {

	public static void main(String[] args) {
		Controlador c = new Controlador();
		c.iniciaJuego();
	}
}
