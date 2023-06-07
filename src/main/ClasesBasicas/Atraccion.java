package main.ClasesBasicas;

import main.Enums.TipoAtraccion;

public class Atraccion {
	private String descripcion;
	private double costo;
	private int horasDuracion;
	private int cupos;
	private TipoAtraccion tipoAtraccion;
	
	public Atraccion(String descripcion, double costo, int horasDuracion, int cupos, TipoAtraccion tipoAtraccion) {
		this.descripcion = descripcion;
		this.costo = costo;
		this.horasDuracion = horasDuracion;
		this.cupos = cupos;
		this.tipoAtraccion = tipoAtraccion;
	}

	@Override
	public String toString() {
		return "Atraccion [descripcion=" + descripcion + "]";
	}
	
}
