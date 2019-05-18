
package modelo;

public class Documento {
    
        private String nombre;
	private int repeticiones;
	private float peso;
	private float idf;

	public Documento(String nombre, int repeticiones, float peso, float idf) {
		this.nombre = nombre;
		this.repeticiones = repeticiones;
		this.peso = peso;
		this.idf = idf;
	}
        public Documento(String nombre){
            this.nombre = nombre;
        }

    	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getRepeticiones() {
		return repeticiones;
	}
	public void setRepeticiones(int repeticiones) {
		this.repeticiones = repeticiones;
	}

	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getIdf() {
		return idf;
	}
        public void setIdf(float idf) {
		this.idf = idf;
	}
        
        @Override
	public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
            result = prime * result + repeticiones;
            return result;
	}

	@Override
	public boolean equals(Object obj) {
            if (this == obj) {
                    return true;
            }
            if (obj == null) {
                    return false;
            }
            if (!(obj instanceof Documento)) {
                    return false;
            }
            Documento other = (Documento) obj;
            if (nombre == null) {
                    if (other.nombre != null) {
                            return false;
                    }
            } else if (!nombre.equals(other.nombre)) {
                    return false;
            }
            if (repeticiones != other.repeticiones) {
                    return false;
            }
            return true;
	}
}
