
public class Usuario {
	private String nombreUsuario;
	private Integer presupuesto;
	private Double horasDisponibles;
	
	public Usuario(String nombre) {
		this.nombreUsuario = nombre;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Double getHorasDisponibles() {
		return horasDisponibles;
	}

	public void setHorasDisponibles(Double horasDisponibles) {
		this.horasDisponibles = horasDisponibles;
	}

	public Integer getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Integer presupuesto) {
		this.presupuesto = presupuesto;
	}
	
}
