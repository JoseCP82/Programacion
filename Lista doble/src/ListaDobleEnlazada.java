
public class ListaDobleEnlazada<T> {

	private Nodo<T> primero;
	private int size;
	
	public ListaDobleEnlazada() {
		this.primero=null;
		this.size=0;
	}

	/**
	 * Inserta un nodo al final de la lista
	 * @param v el valor del nodo a insertar
	 * @return el nuevo tamaño de la lista
	 */
	public int push(T v) {
		int result=-1;
		if(v!=null) {
			if(this.size==0) {
				result=this.unshift(v);
			}
			else {
				Nodo<T> nuevo = new Nodo<>(v);
				Nodo<T> aux = this.primero;
				while(aux.siguiente!=null) {
					nuevo.anterior=aux;
				}
			}
		}
		return result;
	}
	
	/**
	 * Inserta un nodo al principio de la lista
	 * @param v el valor del nodo a insertar
	 * @return el nuevo tamaño de la lista
	 */
	public int unshift(T v) {
		int result=-1;
		if(v!=null) {
			Nodo<T> nuevo = new Nodo<>(v);
			nuevo.siguiente=this.primero;
			this.primero=nuevo;
			nuevo.anterior=this.primero;
			result=this.size+=1;
		}
		return result;
	}
	
	@Override
	public String toString() {
		String result="";
		Nodo<T> aux = this.primero;
		while(aux!=null) {
			result+=aux.getDato()+"\n";
			aux=aux.siguiente;
		}
		return result;
	}
	
}
