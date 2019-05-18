
package modelo;

import java.util.ArrayList;

public class Palabra implements Comparable<Palabra>{
	
    private String nombre;
    private ArrayList<Documento> documentos;
    private Documento consulta;

    public Palabra(String palabra, ArrayList<Documento> documentos) {
            super();
            this.nombre = palabra;
            this.documentos = documentos;
    }

    public Palabra(String palabra, ArrayList<Documento> documentos, Documento consulta){
        super();
        this.nombre = palabra;
        this.documentos = documentos;
        this.consulta = consulta;
    }

    public Palabra(String palabra) {
            this.nombre = palabra;
            this.documentos = new ArrayList<Documento>();
    }

    public Palabra() {}

    public void insertarDocumento(Documento documento) {
            this.documentos.add(documento);
    }

    public String getPalabra() {
            return nombre;
    }
    public void setPalabra(String palabra) {
            this.nombre = palabra;
    }

    public ArrayList<Documento> getRepeticiones() {
            return documentos;
    }
    public void setRepeticiones(ArrayList<Documento> repeticiones) {
            this.documentos = repeticiones;
    }

    public void setDocumentos(ArrayList<Documento> documentos) {
            this.documentos = documentos;
    }

    public String getNombre() {
            return nombre;
    }
    public void setNombre(String nombre) {
            this.nombre = nombre;
    }

    public ArrayList<Documento> getDocumentos() {
            return documentos;
    }

    //usos para modelo vectorial
    public void insertarConsulta(Documento consulta) {
            this.consulta = consulta;
    }
    public Documento getRepeticionesConsulta(){
        return consulta;
    }
    public void setRepeticionesConsulta(Documento repeticiones) {
            this.consulta = repeticiones;
    }
    public void setConsulta(Documento consulta) {
            this.consulta = consulta;
    }
    public Documento getConsulta() {
            return consulta;
    }



    @Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
        if (!(obj instanceof Palabra)) {
                return false;
        }
        Palabra other = (Palabra) obj;
        if (nombre == null) {
                if (other.nombre != null) {
                        return false;
                }
        } else if (!nombre.equals(other.nombre)) {
                return false;
        }
        return true;
    }

    @Override
    public int compareTo(Palabra o) {
        return getNombre().compareTo(o.getNombre());
        
    }
 }
