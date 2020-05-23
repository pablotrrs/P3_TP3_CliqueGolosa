package grafo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import tp.Tupla;

public class Grafo {
	private Arista[] aristas;
	private Tupla<Integer, Double>[] vertices;

	private int puntero;
	private int cantidadVertices;
	private int cantidadAristas;

	@SuppressWarnings("unchecked")
	public Grafo(int v) {
		puntero = 0;
		cantidadAristas = 0;
		cantidadVertices = v;
		aristas = new Arista[1000];

		vertices = new Tupla[v];
		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = new Tupla<Integer, Double>(i, 0.0);
		}
	}

	public void agregarPesoVertice(int vertice, double peso) {
		verificarVerticePesado(vertice, peso);

		vertices[vertice].setSegundoElemento(peso);
	}

	public int obtenerVertice(int i) {
		verificarVertice(i, i);
		return this.vertices[i].getPrimerElemento();
	}

	public void agregarArista(int origen, int destino) {
		verificarVertice(origen, destino);
		verificarLoop(origen, destino);

		if (!existeArista(origen, destino)) {
			aristas[puntero] = new Arista(origen, destino);
			puntero++;
			cantidadAristas++;
		}
	}

	public void eliminarArista(int origen, int destino) {
		verificarVertice(origen, destino);
		verificarLoop(origen, destino);

		for (int i = 0; i < puntero; i++) {
			if (aristas[i] != null) {
				if ((aristas[i].getExtremo1() == origen && aristas[i].getExtremo2() == destino)) {
					aristas[i] = null;
					cantidadAristas--;
				}
			}
		}
	}

	public boolean existeArista(int origen, int destino) {
		verificarVertice(origen, destino);
		verificarLoop(origen, destino);
		boolean bool = false;
		for (int i = 0; i < puntero; i++) {
			if (aristas[i] != null) {
				if ((aristas[i].getExtremo1() == origen && aristas[i].getExtremo2() == destino)
						|| ((aristas[i].getExtremo1() == destino && aristas[i].getExtremo2() == origen))) {
					bool = true;
				}
			}
		}
		return bool;
	}

	// Retorna un conjunto con los vecinos del vertice pasado como parametro
	public ArrayList<Integer> vecinos(int origen) {
		verificarVertice(origen, origen);
		ArrayList<Integer> ret = new ArrayList<Integer>();

		for (int i = 0; i < puntero; i++) {
			if (aristas[i] != null && aristas[i].getExtremo1() == origen) {
				ret.add(aristas[i].getExtremo2());
			}
			if (aristas[i] != null && aristas[i].getExtremo2() == origen) {
				ret.add(aristas[i].getExtremo1());
			}
		}

		return ret;
	}

	// Devuelve true si un vertice es vecino de un conjunto de vertices.
	public boolean sonVecinos(ArrayList<Integer> vertices, int vertice) {
		ArrayList<Integer> vecinosDelPosibleVecino = vecinos(vertice);
		boolean ret = true;

		for (int i = 0; i < vertices.size(); i++) {
			ret &= (vecinosDelPosibleVecino.contains(vertices.get(i)));
		}
		
		return ret;
	}

	public int tamano() {
		return cantidadVertices;
	}

	public int cantidadAristas() {
		return cantidadAristas;
	}

	public double obtenerPesoVertice(int vertice) {
		verificarVertice(vertice, vertice);

		return vertices[vertice].getSegundoElemento();
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < cantidadVertices; i++) {
			s += i + " (" + obtenerPesoVertice(i) + ") N(" + i + ") = {";

			for (int vecino : vecinos(i)) {
				s += " " + vecino + " (" + obtenerPesoVertice(vecino) + ")";
			}
			s += " } ";
		}

		return s;
	}

	public boolean esConexo(Integer s) {
		Set<Integer> marcados = new HashSet<Integer>();
		Queue<Integer> L = new LinkedList<Integer>();

		if (tamano() == 0)
			return true;

		L.add(s);

		while (!L.isEmpty()) {

			s = L.remove();
			marcados.add(s);
			agregarVecinosPendientes(this, s, L, marcados);
		}
		return tamano() == marcados.size();

	}

	private void agregarVecinosPendientes(Grafo g, Integer s, Queue<Integer> L, Set<Integer> marcados) {
		for (Integer vertice : g.vecinos(s)) {
			if (!L.contains(vertice) && !marcados.contains(vertice)) {
				L.add(vertice);
			}
		}
	}

	// Indica si dos vertices se encuentran a distancia dos en un grafo
	public boolean distanciaDos(Integer v1, Integer v2) {
		if (tamano() == 0)
			return true;

		boolean ret = false;
		for (Integer vertice : vecinos(v1)) {
			for (Integer vecinosV2 : vecinos(vertice)) {
				if (vecinosV2 == v2 && !existeArista(v1, vecinosV2)) {
					ret |= true;
					break;
				}
			}
		}
		return ret;
	}

	// Indica la cantidad de triangulos que hay en un grafo
	public Integer cantidadTriangulos(Integer s) {
		Set<Integer> marcados = new HashSet<Integer>();
		Queue<Integer> L = new LinkedList<Integer>();
		Integer cantidad = 0;

		L.add(s);

		while (!L.isEmpty()) {
			ArrayList<Integer> vecinos = new ArrayList<Integer>();
			s = L.remove();
			for (Integer vertice : vecinos(s)) {
				if (!L.contains(vertice) && !marcados.contains(vertice)) {
					L.add(vertice);
				}
				vecinos.add(vertice);
			}
			for (int i = 0; i < vecinos.size(); i++) {
				for (int j = i + 1; j < vecinos.size(); j++) {
					if (existeArista(vecinos.get(i), vecinos.get(j)) && !marcados.contains(vecinos.get(i)))
						cantidad++;
				}
			}
			marcados.add(s);
		}
		return cantidad;
	}

	public boolean esUniversal(Integer s) {
		Set<Integer> marcados = new HashSet<Integer>();
		Queue<Integer> L = new LinkedList<Integer>();
		boolean ret = true;
		Integer aux = s;
		L.add(s);

		while (!L.isEmpty()) {
			s = L.remove();
			if (s != aux)
				ret &= vecinos(s).contains(aux);
			marcados.add(s);
		}

		return ret;
	}

	public int distancia(int vertice1, int vertice2) {
		Queue<Integer> L = new LinkedList<Integer>();
		int[] distancias = new int[tamano()];
		Arrays.fill(distancias, -1);

		if (tamano() == 0)
			return -1;

		L.add(vertice1);
		distancias[vertice1] = 0;

		while (!L.isEmpty()) {

			vertice1 = L.remove();
			for (Integer vertice : vecinos(vertice1)) {
				if (distancias[vertice] == -1) {
					distancias[vertice] = distancias[vertice1] + 1;
					L.add(vertice);
				}
			}

		}
		return distancias[vertice2];
	}

	private void verificarLoop(int origen, int destino) {
		if (origen == destino) {
			throw new IllegalArgumentException("No se permiten loops: (" + origen + ", " + destino + ")");
		}
	}

	private void verificarVertice(int origen, int destino) {
		if (origen >= cantidadVertices || destino >= cantidadVertices || origen < 0 || destino < 0) {
			throw new IllegalArgumentException("Los vertices deben estar dentro de 0 y |V|-1.");
		}
	}

	private void verificarVerticePesado(int vertice, double peso) {
		if (peso < 0)
			throw new IllegalArgumentException("El peso del vertice no puede ser negativo!");
		verificarVertice(vertice, vertice);
	}
}
