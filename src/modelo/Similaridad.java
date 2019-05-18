
package modelo;

public class Similaridad  implements Comparable<Similaridad>{
     public String documento;
    public String consulta;
    public float valor;

    public Similaridad(String documento, String consulta, float valor) {
        this.documento = documento;
        this.consulta = consulta;
        this.valor = valor;
    }

    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getConsulta() {
        return consulta;
    }
    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public int compareTo(Similaridad o) {
         if (valor > o.valor) {
                return -1;
            }
            if (valor < o.valor) {
                return 1;
            }
            return 0;
    }
}
