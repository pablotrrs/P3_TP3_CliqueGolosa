package tp;

import java.util.ArrayList;

public class Tupla<T1, T2>   {

	private T1 primerElemento;
	private T2 segundoElemento;

	public Tupla(T1 primerElemento, T2 segundoElemento) {
		this.primerElemento = primerElemento;
		this.segundoElemento = segundoElemento;
	}

	@Override
	public boolean equals(Object objeto) {
		if (this == objeto)
			return true;

		if (objeto == null || objeto.getClass() != this.getClass())
			return false;

		Tupla<?, ?> tupla = (Tupla<?, ?>) objeto;
		return (tupla.getPrimerElemento().equals(this.getPrimerElemento())
				&& tupla.getSegundoElemento().equals(this.getSegundoElemento()))
				|| (tupla.getPrimerElemento().equals(this.getSegundoElemento())
						&& tupla.getSegundoElemento().equals(this.getPrimerElemento()));
	}

	// Indica si el ArrayList contiene la Tupla pasada como parametro
	public static boolean contiene(ArrayList<Tupla<Integer, Integer>> aristas, Tupla<Integer, Integer> arista) {
		int cantidadIguales = (int) aristas.stream().filter(a -> a.equals(arista)).count();
		
		return cantidadIguales >= 1;
	}

	public String toString() {
		return "Tupla: {" + this.getPrimerElemento() + ", " + this.getSegundoElemento() + "}";
	}

	public T1 getPrimerElemento() {
		return primerElemento;
	}

	public T2 getSegundoElemento() {
		return segundoElemento;
	}

	public void setSegundoElemento(T2 segundoElemento) {
		this.segundoElemento = segundoElemento;
	}
}
