package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import tp.Tupla;

public class TuplaTest {
	@SuppressWarnings("null")
	@Test(expected = NullPointerException.class)
	public void testNulls() {
		Tupla<Integer, Integer> arista1 = null;
		Tupla<Integer, Integer> arista2 = null;
		arista1.equals(arista2);
	}

	@SuppressWarnings("null")
	@Test(expected = NullPointerException.class)
	public void testACompararNull() {
		Tupla<Integer, Integer> arista1 = null;
		Tupla<Integer, Integer> arista2 = new Tupla<Integer, Integer>(12,10);
		arista1.equals(arista2);
	}

	@Test
	public void testACompararNoNull() {
		Tupla<Integer, Integer> arista1 = new Tupla<Integer, Integer>(12,10);
		Tupla<Integer, Integer> arista2 = null;
		assertFalse(arista1.equals(arista2));
	}

	@Test
	public void testIguales() {
		Tupla<Integer, Integer> arista1 = new Tupla<Integer, Integer>(12,10);
		Tupla<Integer, Integer> arista2 = new Tupla<Integer, Integer>(10,12);

		assertTrue(arista1.equals(arista2));
	}

	@Test
	public void testDiferentes() {
		Tupla<Integer, Integer> arista1 = new Tupla<Integer, Integer>(12,10);
		Tupla<Integer, Integer> arista2 = new Tupla<Integer, Integer>(9,8);

		assertFalse(arista1.equals(arista2));
	}

	@Test
	public void testComparoConSigo() {
		Tupla<Integer, Integer> arista = new Tupla<Integer, Integer>(12,10);

		assertTrue(arista.equals(arista));
	}

	@Test
	public void testClasesDistintas() {
		Tupla<Integer, Integer> arista = new Tupla<Integer, Integer>(12,10);
		Tupla<String, Integer> persona = new Tupla<String, Integer>("Juan Carlos", 18);

		assertFalse(arista.equals(persona));
	}
	
	@Test
	public void testContienePrimeraPosicion() {
		ArrayList<Tupla<Integer, Integer>> lista = crearLista();
		Tupla<Integer, Integer> arista = new Tupla<Integer, Integer>(10,12);

		assertTrue(Tupla.contiene(lista, arista));
	}

	@Test
	public void testContieneUltimaPosicion() {
		ArrayList<Tupla<Integer, Integer>> lista = crearLista2();
		Tupla<Integer, Integer> arista = new Tupla<Integer, Integer>(10,12);

		assertTrue(Tupla.contiene(lista, arista));
	}

	@Test
	public void testContieneNormal() {
		ArrayList<Tupla<Integer, Integer>> lista = crearLista3();
		Tupla<Integer, Integer> arista = new Tupla<Integer, Integer>(10,12);

		assertTrue(Tupla.contiene(lista, arista));
	}	
	
	@Test
	public void testNoContiene() {
		ArrayList<Tupla<Integer, Integer>> lista = crearLista3();
		Tupla<Integer, Integer> arista = new Tupla<Integer, Integer>(8,2);

		assertFalse(Tupla.contiene(lista, arista));
	}
	
	@Test
	public void toStringTest() {
		Tupla<Integer, Integer> tupla = new Tupla<Integer, Integer>(10, 12);
		
		String obtenido = "Tupla: {10, 12}";
		String esperado = tupla.toString();
		
		assertEquals(esperado, obtenido);
	}
	
	private ArrayList<Tupla<Integer, Integer>> crearLista(){
		ArrayList<Tupla<Integer, Integer>> lista = new ArrayList<Tupla<Integer, Integer>>();
		lista.add(new Tupla<Integer, Integer>(12,10));
		lista.add(new Tupla<Integer, Integer>(9,8));
		lista.add(new Tupla<Integer, Integer>(10,3));
		lista.add(new Tupla<Integer, Integer>(2,3));
		
		return lista;
	}
	
	private ArrayList<Tupla<Integer, Integer>> crearLista2(){
		ArrayList<Tupla<Integer, Integer>> lista = new ArrayList<Tupla<Integer, Integer>>();
		lista.add(new Tupla<Integer, Integer>(9,8));
		lista.add(new Tupla<Integer, Integer>(10,3));
		lista.add(new Tupla<Integer, Integer>(2,3));
		lista.add(new Tupla<Integer, Integer>(10,12));
		
		return lista;
	}
	
	private ArrayList<Tupla<Integer, Integer>> crearLista3(){
		ArrayList<Tupla<Integer, Integer>> lista = new ArrayList<Tupla<Integer, Integer>>();
		lista.add(new Tupla<Integer, Integer>(9,8));
		lista.add(new Tupla<Integer, Integer>(10,12));
		lista.add(new Tupla<Integer, Integer>(10,3));
		lista.add(new Tupla<Integer, Integer>(2,3));
		
		return lista;
	}
}
