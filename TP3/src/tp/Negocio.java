package tp;

import java.util.ArrayList;
import java.util.Collections;
import grafo.Grafo;

public class Negocio {

	// Calcula la clique de mayor peso
	public static ArrayList<Tupla<Integer, Double>> calcular(int cantidadVertices, ArrayList<Double> pesosVertices,
			ArrayList<Tupla<Integer, Integer>> aristas) {
		Grafo grafo = crearGrafo(cantidadVertices, pesosVertices, aristas);
		return cliqueSolucion(grafo);
	}

	// Crea un grafo con los datos pasados como parametros
	public static Grafo crearGrafo(int cantidadVertices, ArrayList<Double> pesosVertices,
			ArrayList<Tupla<Integer, Integer>> aristas) {
		Grafo grafo = new Grafo(cantidadVertices);

		for (int i = 0; i < pesosVertices.size(); i++) {
			grafo.agregarPesoVertice(i, pesosVertices.get(i));
		}

		for (Tupla<Integer, Integer> tupla : aristas) {
			grafo.agregarArista(tupla.getPrimerElemento(), tupla.getSegundoElemento());
		}

		return grafo;
	}

	// Contiene los datos de la clique de mayor peso
	public static ArrayList<Tupla<Integer, Double>> cliqueSolucion(Grafo grafo) {
		ArrayList<Tupla<Integer, Double>> verticesOrdenados = ordenarVerticesPorPeso(grafo);
		ArrayList<Tupla<Integer, Double>> clique = new ArrayList<Tupla<Integer, Double>>();
		ArrayList<Integer> base = new ArrayList<Integer>();

		for (int i = 0; i < grafo.tamano(); i++) {
			if (grafo.sonVecinos(base, verticesOrdenados.get(i).getPrimerElemento())) {
				base.add(verticesOrdenados.get(i).getPrimerElemento());
				clique.add(new Tupla<Integer, Double>(verticesOrdenados.get(i).getPrimerElemento(),
						verticesOrdenados.get(i).getSegundoElemento()));
			}
		}

		return clique;
	}

	// Algoritmo goloso para ordenar los vertices de mayor a menor utilizando su peso
	public static ArrayList<Tupla<Integer, Double>> ordenarVerticesPorPeso(Grafo grafo) {
		ArrayList<Tupla<Integer, Double>> verticesPesados = new ArrayList<Tupla<Integer, Double>>();

		for (int i = 0; i < grafo.tamano(); i++) {
			verticesPesados.add(new Tupla<Integer, Double>(i, grafo.obtenerPesoVertice(i)));

		}

		Collections.sort(verticesPesados, (uno, otro) -> uno.getSegundoElemento() < otro.getSegundoElemento() ? 1
				: (uno.getSegundoElemento() == otro.getSegundoElemento() ? 0 : -1));

		return verticesPesados;
	}
}