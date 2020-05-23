package grafo;

public class Arista {
	private int extremo1; // Vertice origen
	private int extremo2; // Vertice destino

	public Arista() {

	}

	public Arista(int origen, int destino) {
		this.extremo1 = origen;
		this.extremo2 = destino;
	}

	public int getExtremo1() {
		return extremo1;
	}

	public void setExtremo1(int origen) {
		this.extremo1 = origen;
	}

	public int getExtremo2() {
		return extremo2;
	}

	public void setExtremo2(int destino) {
		this.extremo2 = destino;
	}

	public String toString() {
		return extremo1 + " <-> " + extremo2;
	}
};
