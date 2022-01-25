package modelo;

import utils.Utilidades;

public class Jefe extends Personaje {

	private static final long serialVersionUID = 1L;
	
	private int poderDefensaEspecial;
	private int poderAtaqueEspecial;

	public Jefe(int rangoMinimo, int rangoMaximo) {
		super(rangoMinimo, rangoMaximo);
		super.setNombre(Nombres.eligeNombre(3));
		this.poderAtaqueEspecial = Utilidades.numeroAleatorio(rangoMinimo, rangoMaximo);
		this.poderDefensaEspecial = Utilidades.numeroAleatorio(rangoMinimo, rangoMaximo);
	}

	public int ataqueEspecial() {
		return Utilidades.numeroAleatorio(1, poderAtaqueEspecial);
	}

	public int defensaEspecial() {
		return Utilidades.numeroAleatorio(1, poderDefensaEspecial);
	}

	@Override
	public String toString() {
		return "Final Boss\n" + super.toString() + "\nAtaque Especial: " + poderAtaqueEspecial + "\nDefensa Especial: "
				+ poderDefensaEspecial;
	}
}
