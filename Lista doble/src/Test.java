
public class Test {

	public static void main(String[] args) {
		
		ListaDobleEnlazada<Integer> lista = new ListaDobleEnlazada<>();
		
		System.out.println(lista.unshift(9));
		System.out.println(lista.unshift(21));
	
		System.out.println(lista);
	}

}
