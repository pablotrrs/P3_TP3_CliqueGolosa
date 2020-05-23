package tests;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import grafo.Grafo;

public class verticesConPesoTest {

	@Test(expected = IllegalArgumentException.class)
	public void exepcionPesoNegativoTest() {
		Grafo grafo = new Grafo(5);

		grafo.agregarPesoVertice(2, -0.1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void exepcionObtenerPesoVerticeInexistenteTest() {
		Grafo grafo = new Grafo(5);

		grafo.obtenerPesoVertice(5);
	}

	@Test
	public void pesoVerticeNoAgregado() {
		Grafo grafo = new Grafo(5);

		assertTrue(0.0 == grafo.obtenerPesoVertice(4));
	}

	@Test
	public void pesoCorrectotest() {
		Grafo grafo = new Grafo(5);

		grafo.agregarPesoVertice(0, 10.0);
		assertTrue(10.0 == grafo.obtenerPesoVertice(0));
	}

	@Test
	public void pesoCambiadoMasDeUnaVeztest() {
		Grafo grafo = new Grafo(5);

		grafo.agregarPesoVertice(0, 10.0);
		grafo.agregarPesoVertice(0, 6.66);
		assertTrue(6.66 == grafo.obtenerPesoVertice(0));
	}
}
