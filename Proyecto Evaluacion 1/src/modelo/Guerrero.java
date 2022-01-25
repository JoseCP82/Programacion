package modelo;

import utils.Utilidades;

public class Guerrero extends Personaje {

	private static final long serialVersionUID = 1L;
	
	private int poderAtaqueEspecial;

	public Guerrero(String nombre, int rangoMinimo, int rangoMaximo) {
		super(nombre, rangoMinimo, rangoMaximo);
		this.poderAtaqueEspecial = Utilidades.numeroAleatorio(rangoMinimo, rangoMaximo);
	}

	public Guerrero(int rangoMinimo, int rangoMaximo) {
		super(rangoMinimo, rangoMaximo);
		super.setNombre(Nombres.eligeNombre(2));
		this.poderAtaqueEspecial = Utilidades.numeroAleatorio(rangoMinimo, rangoMaximo);
	}

	public int getPoderAtaqueEspecial() {
		return poderAtaqueEspecial;
	}

	public void setPoderAtaqueEspecial(int poderAtaqueEspecial) {
		this.poderAtaqueEspecial = poderAtaqueEspecial;
	}

	public int ataqueEspecial() {
		return Utilidades.numeroAleatorio(1, poderAtaqueEspecial) * 2;
	}

	@Override
	public String toString() {
		return "Guerrero\n" + super.toString() + "\nAtaque Especial: " + poderAtaqueEspecial;
	}
}
