package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import grafo.Grafo;

public class VecinosTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void verticeNegativoTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.vecinos(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void verticeExcedidoTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.vecinos(5);
	}

	@Test
	public void todosAisladosTest()
	{
		Grafo grafo = new Grafo(5);
		assertEquals(0, grafo.vecinos(2).size());
	}
	@Test
	public void SonVecinosTest() {
		
		Grafo g = new Grafo(3);
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);
		g.agregarArista(1, 2);
		ArrayList<Integer> conjuntoDeVertices = new ArrayList<Integer>();
		conjuntoDeVertices.add(g.obtenerVertice(0));
		
		assertTrue(g.sonVecinos(conjuntoDeVertices, 1));
	}
	
	@Test
	public void verticeUniversalTest()
	{
		Grafo grafo = new Grafo(4);
		grafo.agregarArista(1, 0);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(1, 3);
		
		ArrayList<Integer> esperado = new ArrayList<Integer>();
		esperado.add(0);
		esperado.add(2);
		esperado.add(3);
		
		assertEquals(esperado, grafo.vecinos(1));
	}
	
	@Test
	public void verticeNormalTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(1, 3);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(2, 4);
		
		ArrayList<Integer> esperados = new ArrayList<Integer>();
		esperados.add(1);
		esperados.add(2);
		
		assertEquals(esperados, grafo.vecinos(3));
	}
}
