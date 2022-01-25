package modelo;

import utils.Utilidades;

public class Mago extends Personaje {

	private static final long serialVersionUID = 1L;
	
	private int poderDefensaEspecial;

	public Mago(String nombre, int rangoMinimo, int rangoMaximo) {
		super(nombre, rangoMinimo, rangoMaximo);
		this.poderDefensaEspecial = Utilidades.numeroAleatorio(rangoMinimo, rangoMaximo);
	}

	public Mago(int rangoMinimo, int rangoMaximo) {
		super(rangoMinimo, rangoMaximo);
		super.setNombre(Nombres.eligeNombre(1));
		this.poderDefensaEspecial = Utilidades.numeroAleatorio(rangoMinimo, rangoMaximo);
	}

	public int getPoderDefensaEspecial() {
		return poderDefensaEspecial;
	}

	public void setPoderDefensaEspecial(int poderDefensaEspecial) {
		this.poderDefensaEspecial = poderDefensaEspecial;
	}

	public int defensaEspecial() {
		return Utilidades.numeroAleatorio(1, poderDefensaEspecial) * 2;
	}

	@Override
	public String toString() {
		return "Mago\n" + super.toString() + "\nDefensa Especial: " + poderDefensaEspecial;
	}

}
