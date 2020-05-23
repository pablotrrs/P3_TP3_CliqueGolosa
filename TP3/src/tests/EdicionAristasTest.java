package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import grafo.Arista;
import grafo.Grafo;

public class EdicionAristasTest {

	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeNegativoTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(-1, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeExcedidoTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(5, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeNegativoTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeExcedidoTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarLoopTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 2);
	}
	
	@Test
	public void sinAristasTest() {
		Grafo grafo = new Grafo(5);
				
		assertEquals(0, grafo.cantidadAristas());
	}
	
	@Test
	public void cantidadAristasTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3);
		
		assertEquals(1, grafo.cantidadAristas());
	}

	@Test
	public void aristaExistenteTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3);
		assertTrue(grafo.existeArista(2, 3));
	}

	@Test
	public void aristaOpuestaTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3);
		assertTrue(grafo.existeArista(3, 2));
	}

	@Test
	public void aristaInexistenteTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3);
		assertFalse(grafo.existeArista(1, 4));
	}

	@Test
	public void agregarAristaDosVecesTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(2, 3);

		assertTrue(grafo.existeArista(2, 3));
	}

	@Test
	public void eliminarAristaExistenteTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 4);

		grafo.eliminarArista(2, 4);
		assertFalse(grafo.existeArista(2, 4));
	}

	@Test
	public void eliminarAristaInexistenteTest() {
		Grafo grafo = new Grafo(5);
		grafo.eliminarArista(2, 4);
		assertFalse(grafo.existeArista(2, 4));
	}

	@Test
	public void eliminarAristaDosVecesTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 4);

		grafo.eliminarArista(2, 4);
		grafo.eliminarArista(2, 4);
		assertFalse(grafo.existeArista(2, 4));
	}
	
	@Test
	public void aristaTest() {
		Arista arista = new Arista(1,2);
		
		assertEquals(arista.getExtremo1(), 1);
		assertEquals(arista.getExtremo2(), 2);
	}
	
	@Test
	public void modificacionesAristaTest() {
		Arista arista = new Arista(1,2);
		arista.setExtremo1(6);
		arista.setExtremo2(7);
		
		Arista aristaEsperada = new Arista(6,7);
		
		assertEquals(aristaEsperada.toString(), arista.toString());
	}
	
	@Test
	public void constructorVacioTest() {
		Arista arista = new Arista();
		Arista aristaEsperada = new Arista(0,0);
		
		assertEquals(aristaEsperada.toString(), arista.toString());
	}
	
	@Test
	public void stringGrafotest() {
		Grafo grafo = grafo();

		String esperado = grafo.toString();
		String actual = "0 (9.88) N(0) = { 1 (5.6) 2 (8.99) 3 (0.0) 4 (1.75) } 1 (5.6) N(1) = { 0 (9.88) } 2 (8.99) N(2) = { 0 (9.88) } 3 (0.0) N(3) = { 0 (9.88) } 4 (1.75) N(4) = { 0 (9.88) } ";

		assertEquals(esperado, actual);
	}

	private Grafo grafo() {
		Grafo grafo = new Grafo(5);

		grafo.agregarPesoVertice(0, 10.3);
		grafo.agregarPesoVertice(1, 5.6);
		grafo.agregarPesoVertice(2, 8.99);
		grafo.agregarPesoVertice(3, 0.0);
		grafo.agregarPesoVertice(4, 1.75);
		grafo.agregarPesoVertice(0, 9.88);

		grafo.agregarArista(0, 1);
		grafo.agregarArista(0, 2);
		grafo.agregarArista(0, 3);
		grafo.agregarArista(0, 4);
		return grafo;
	}
}
