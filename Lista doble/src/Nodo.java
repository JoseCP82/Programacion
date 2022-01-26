
public class Nodo<T> {
	
	private T dato; 
	public Nodo<T> siguiente; 
	public Nodo<T> anterior; 
		
	public Nodo(T dato) {
		this.dato=dato;
		this.siguiente=null;
		this.anterior=null;
	}

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}
}
