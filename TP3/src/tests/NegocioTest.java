package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import grafo.Grafo;
import tp.Tupla;
import tp.Negocio;

public class NegocioTest {

	@Test
	public void calcularTest() {
		ArrayList<Double> pesosVertices = pesosVertices();
		ArrayList<Tupla<Integer, Integer>> aristas = aristas();
		ArrayList<Tupla<Integer, Double>> solucionObtenida = Negocio.calcular(3, pesosVertices, aristas);
		ArrayList<Tupla<Integer, Double>> solucionEsperada = cliqueEsperada();

		assertEquals(solucionEsperada.toString(), solucionObtenida.toString());
	}

	@Test
	public void crearGrafoTest() {
		ArrayList<Double> pesosVertices = pesosVertices();
		ArrayList<Tupla<Integer, Integer>> aristas = aristas();
		Grafo grafoObtenido = Negocio.crearGrafo(3, pesosVertices, aristas);

		Grafo grafoEsperado = grafoEsperado();

		assertEquals(grafoObtenido.toString(), grafoEsperado.toString());
	}

	@Test
	public void ordenarVerticesPorPesoTest() {
		Grafo grafo = grafoEsperado();
		ArrayList<Tupla<Integer, Double>> obtenido = Negocio.ordenarVerticesPorPeso(grafo);
		ArrayList<Tupla<Integer, Double>> esperado = ordenEsperado();

		assertEquals(esperado, obtenido);
	}

	@Test
	public void cliqueSolucionTest() {
		Grafo grafo = grafoEsperado();
		ArrayList<Tupla<Integer, Double>> obtenido = Negocio.cliqueSolucion(grafo);
		ArrayList<Tupla<Integer, Double>> esperado = cliqueEsperada();

		assertEquals(esperado, obtenido);
	}

	@Test
	public void cliqueSolucionGrafoTPTest() {
		Grafo grafo = grafoTP();
		ArrayList<Tupla<Integer, Double>> obtenido = Negocio.cliqueSolucion(grafo);
		ArrayList<Tupla<Integer, Double>> esperado = cliqueEsperadaTP();

		assertEquals(esperado, obtenido);
	}

	private ArrayList<Tupla<Integer, Double>> cliqueEsperadaTP() {
		ArrayList<Tupla<Integer, Double>> esperada = new ArrayList<Tupla<Integer, Double>>();
		esperada.add(new Tupla<Integer, Double>(0, 11.0));
		esperada.add(new Tupla<Integer, Double>(3, 7.0));
		esperada.add(new Tupla<Integer, Double>(1, 5.5));

		return esperada;
	}

	private Grafo grafoTP() {
		Grafo grafoTP = new Grafo(6);

		grafoTP.agregarArista(0, 1);
		grafoTP.agregarArista(0, 3);
		grafoTP.agregarArista(1, 2);
		grafoTP.agregarArista(1, 3);
		grafoTP.agregarArista(1, 4);
		grafoTP.agregarArista(1, 5);
		grafoTP.agregarArista(2, 4);
		grafoTP.agregarArista(4, 3);
		grafoTP.agregarArista(4, 5);

		grafoTP.agregarPesoVertice(0, 11.0);
		grafoTP.agregarPesoVertice(1, 5.5);
		grafoTP.agregarPesoVertice(2, 1.1);
		grafoTP.agregarPesoVertice(3, 7.0);
		grafoTP.agregarPesoVertice(4, 2.5);
		grafoTP.agregarPesoVertice(5, 3.5);

		return grafoTP;
	}

	private ArrayList<Tupla<Integer, Integer>> aristas() {
		ArrayList<Tupla<Integer, Integer>> aristas = new ArrayList<Tupla<Integer, Integer>>();
		aristas.add(new Tupla<Integer, Integer>(0, 1));
		aristas.add(new Tupla<Integer, Integer>(1, 2));

		return aristas;
	}

	private ArrayList<Double> pesosVertices() {
		ArrayList<Double> pesosVertices = new ArrayList<Double>();
		pesosVertices.add(2.0);
		pesosVertices.add(11.0);
		pesosVertices.add(7.0);

		return pesosVertices;
	}

	private Grafo grafoEsperado() {
		Grafo grafoEsperado = new Grafo(3);

		grafoEsperado.agregarPesoVertice(0, 2.0);
		grafoEsperado.agregarPesoVertice(1, 11.0);
		grafoEsperado.agregarPesoVertice(2, 7.0);

		grafoEsperado.agregarArista(0, 1);
		grafoEsperado.agregarArista(1, 2);

		return grafoEsperado;
	}

	private ArrayList<Tupla<Integer, Double>> ordenEsperado() {
		ArrayList<Tupla<Integer, Double>> esperado = new ArrayList<Tupla<Integer, Double>>();
		esperado.add(new Tupla<Integer, Double>(1, 11.0));
		esperado.add(new Tupla<Integer, Double>(2, 7.0));
		esperado.add(new Tupla<Integer, Double>(0, 2.0));

		return esperado;
	}

	private ArrayList<Tupla<Integer, Double>> cliqueEsperada() {
		ArrayList<Tupla<Integer, Double>> esperado = new ArrayList<Tupla<Integer, Double>>();
		esperado.add(new Tupla<Integer, Double>(1, 11.0));
		esperado.add(new Tupla<Integer, Double>(2, 7.0));

		return esperado;
	}
}
