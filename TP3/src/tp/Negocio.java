package tp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

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

		IntStream.range(0, pesosVertices.size()).forEach(i -> grafo.agregarPesoVertice(i, pesosVertices.get(i)));
		aristas.stream().forEach(arista -> grafo.agregarArista(arista.getPrimerElemento(), arista.getSegundoElemento()));

		return grafo;
	}

	// Contiene los datos de la clique de mayor peso
	public static ArrayList<Tupla<Integer, Double>> cliqueSolucion(Grafo grafo) {
		ArrayList<Tupla<Integer, Double>> verticesOrdenados = ordenarVerticesPorPeso(grafo);
		ArrayList<Tupla<Integer, Double>> clique = new ArrayList<Tupla<Integer, Double>>();
		ArrayList<Integer> base = new ArrayList<Integer>();

		IntStream.range(0, grafo.tamano())
				.filter(vertice -> grafo.sonVecinos(base, verticesOrdenados.get(vertice).getPrimerElemento()))
				.forEach(vertice -> {
					base.add(verticesOrdenados.get(vertice).getPrimerElemento());
					clique.add(new Tupla<Integer, Double>(verticesOrdenados.get(vertice).getPrimerElemento(),
							verticesOrdenados.get(vertice).getSegundoElemento()));
				});

		return clique;
	}

	// Algoritmo goloso para ordenar los vertices de mayor a menor utilizando su
	// peso
	public static ArrayList<Tupla<Integer, Double>> ordenarVerticesPorPeso(Grafo grafo) {
		ArrayList<Tupla<Integer, Double>> verticesPesados = new ArrayList<Tupla<Integer, Double>>();

		IntStream.range(0, grafo.tamano())
				.forEach(vertice -> verticesPesados.add(new Tupla<Integer, Double>(vertice, grafo.obtenerPesoVertice(vertice))));

		Collections.sort(verticesPesados, (uno, otro) -> uno.getSegundoElemento() < otro.getSegundoElemento() ? 1
				: (uno.getSegundoElemento() == otro.getSegundoElemento() ? 0 : -1));

		return verticesPesados;
	}
}