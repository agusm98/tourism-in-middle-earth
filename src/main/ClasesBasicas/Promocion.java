package main.ClasesBasicas;

import java.util.List;

public abstract class Promocion {
	
	private List<Atraccion> atracciones;

	public Promocion(List<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}
}
