/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 *
 * @author Clariss
 */
public class metodoVectorial {
    
    public static ArrayList<Similaridad> simV = new ArrayList<Similaridad>();
    public static HashSet<Palabra> palabrasConsulta = new HashSet<Palabra>();
    public Documento Consulta;
    String consulta = "";
    public static int cantidadArchivos = 10;
    
    public HashSet<Palabra> consultarV(String consulta) throws IOException{
        HashSet<Palabra> consultaV = new HashSet<Palabra>();
        this.consulta = consulta;
        String palabraC = "";
        int repeticiones = 0;
        StringTokenizer c = new StringTokenizer(limpiarLinea(consulta));
            while (c.hasMoreElements()) {
             palabraC = c.nextToken();
             if (palabraC.length() > 3) {
                consultaV.add(new Palabra(palabraC));
             }
            }
        return consultaV;
    }
    public void llenarRepeticiones() throws IOException{
        for (Palabra palabraCon : palabrasConsulta) {
           int repeticiones = 0;
           boolean hayPalabra = false;
           String pal = "";
           StringTokenizer st = new StringTokenizer(limpiarLinea(consulta));
            while (st.hasMoreElements()) {
                pal = st.nextToken();
                if (pal.equals(palabraCon.getNombre())) { 
                        repeticiones++; 
                        hayPalabra = true;
                }
                for (Palabra palabraRefinada  : inicio.palabrasRefinadas) {
                    if((palabraRefinada.getNombre()).equals(palabraCon.getNombre())){
                        if (hayPalabra) {
                            palabraRefinada.insertarConsulta(new Documento("C" + 1, repeticiones, 0, 0));
                        }
                    }
                }
            }
            repeticiones = 0;
            hayPalabra = false;           
       }
       
   }
    
    public String similaridad(String c) throws IOException {
        String imprime = "";
            palabrasConsulta = consultarV(c);
            llenarRepeticiones();
            calculoIDF();
            calculoPESO();
            similaridad();
            frecuencia();
            Collections.sort(simV);
            for (Similaridad sim : simV) {
                imprime += "Sim(" + sim.getDocumento() + "," + sim.getConsulta() 
                        + ")" + ": " + String.format("%.3f", sim.getValor())+ "\n";
            }
        return imprime;
    }

       
    private String limpiarLinea(String linea) {
        linea = linea.toUpperCase();
        linea = linea.toLowerCase();
        String [] palabrasVacias = {"\\.",",",";","\\?","¿",":"," a ", " aca ", " ahi", " al ", " algo ", " algun ",
                                    " alguna ", " alguno ", " algunos ", " alla ", " alli ", " ambos ", " ante ", " antes ", " aquel ", " aquella ", 
                                    " aquello ", " aquellos ", " aquellas ", " aqui ", " arriba ", " asi ", " atras ", " aun ", " aunque ", " bajo ", " bastante "," bien ", 
                                    " cabe "," cada ", " casi ", " cierto "," cierta "," ciertas "," como ", " con ", " conmigo " ," conseguimos "," conseguir "," consigo ",
                                    " consigue "," consiguen "," contigo "," contra "," cual ", " cuales ", " cualquier ", " cualquiera ", " cualquieras ", 
                                    " cuan ", " cuando ", " cuanto ", " cuanta ", " cuantos ", " cuantas ", " de ", " dejar ", " del ", " demas "," demasiado "," demasiada ", " desde "," dentro ",
                                    " donde ", " dos ", " el ", " ella ", " ellas ", " ellos "," emplean "," emplear ", " en "," encima ", " entre "," era "," eras ", " eramos ", " eran "," es ",
                                    " eres ", " esa ", " ese ", " eso ", " esas ", " esos ", " esta ", " estas ", " este ", " esto ", " estos ", " entonces ", " estamos "," estoy "," les "," etc ",
                                    " fin "," fue "," fueron "," fui "," fuimos ", " ha "," hace "," haces "," hacemos "," hacen ", " hacer "," hacia ", " hago ", " hay ", " hasta ", 
                                    " incluso ", " intenta ", " intentas ", " intentamos ", " intentan ", " intentar ", " intento ", " ir ", 
                                    " jamas ", " junto ", " juntos ", " la ", " lo ", " las ", " los ", " largo ", " mas ", " menos ", " me ", " mi " , " mis " , " mia " , " mias ", " mientras ", " mio ",
                                    " mios ", " misma ", " mucha ", " mucho ", " muchas ", " muchos "," muy ", " nada ", " ni ", " ningun ", " ninguna ", " ninguno "," nosotras ",
                                    " nosotros ", " nuestra ", " nuestro ", " nuestras ", " nuestros ", " nos ", " nunca ", " otra ", " otro ", " otras ", " otros ",
                                    " para ", " pero ", " poca ", " poco ", " pocas ", " pocos ", " podemos ", " podria ", " podriamos ", " podrian ", " por ", " porque ", 
                                    " primero ", " puede ", " pueden ", " puedo ", " pues ", " que ", " quien ", " quienes ", " quiza ", "quizas ", " sabe ", " sabes "," saben ", 
                                    " segun ", " ser ", " si ", " siempre ", " siendo ", " sin ", " sino ", " son ",
                                    " sobre ", " solamente ", " solo ", " somos ", " soy ", " su ", " sus ", " suya ", " suyo ", " suyas "," suyos ", " tal ", " tales ", " tambien ",
                                    " tampoco ", " tan "," tanta "," tanto "," tantas "," tantos ", " te ", " tenemos ", " tiene ", " tienen ", " todo ", " toda ", " todas ", " todos ",
                                    " tu ", " tus ", " tuya "," tuyo "," tuyas "," tuyos ", " un ", " una ", " uno ", " unas ", " unos ", " usted ", " ustedes ",
                                    " va ", " van "," ven ", " vaya ", " vamos ", " varios ", " varias ", " verdadera "," vosotras ", " vosotros ", " voy ", " y ", " ya ", " yo"};
        for (String palabraVacia : palabrasVacias) {
            linea = linea.replaceAll(palabraVacia," ");
        }
        return linea;
    }
    private String quitarTildes(String verso){
        verso = verso.replace("á","a").replace("é","e").replace("í", "i").replace("ó","o").replace("ú","u").replace("ñ","ni");
        return verso;
    }
    
    
    public void calculoIDF() {
        float idf = 0;
        for (Palabra p : inicio.palabrasRefinadas) {
            ArrayList<Documento> d = new ArrayList<Documento>(p.getDocumentos());
            Documento con = p.getConsulta();
            idf = 0;
            float dfi = (float)d.size();
            idf = (float) Math.log10(cantidadArchivos/dfi);
            for (Documento doc : d) {
                if (doc.getRepeticiones() > 0) {
                        doc.setIdf(idf);
                }
            }
            if(con != null && con.getRepeticiones()>0){
                con.setIdf(idf);
            }
        }
    }
    public void calculoPESO() {
        for (Palabra p : inicio.palabrasRefinadas) {
            ArrayList<Documento> d = new ArrayList<Documento>(p.getDocumentos());
            Documento con = p.getConsulta();
            for (Documento doc : d) {
                if (doc.getRepeticiones()> 0) {
                    doc.setPeso(((float)doc.getRepeticiones())*doc.getIdf());
                }
            }
            if(con != null && con.getRepeticiones()>0){
                con.setPeso(((float)con.getRepeticiones())*con.getIdf());
            }
        }
    }

    public void similaridad() {
        float suma = 0;
        for (int i = 1; i <= cantidadArchivos; i++) {
            for (Palabra p : inicio.palabrasRefinadas) {
                ArrayList<Documento> d = new ArrayList<Documento>(p.getDocumentos());
                Documento con = p.getConsulta();
                for (Documento doc : d) {
                    if(doc.getNombre().equals("D" + i) && con == null){
                        suma += (doc.getPeso()*0);
                    }else if (doc.getNombre().equals("D" + i) && con.getNombre().equals("C1")) {
                        suma += (((float)doc.getPeso())*((float)con.getPeso()));                                 
                    }
                }
            }
            simV.add(new Similaridad("D"+i ,"C1", (float) suma));
            suma = 0;
        }
    }

   
    /**
     * 
     */
    public void frecuencia() {
        
        for (Palabra palabraRefinada  : inicio.palabrasRefinadas) {
            for (Palabra p : palabrasConsulta) {
                if((palabraRefinada.getNombre()).equals(p.getNombre())){
                   System.out.print(palabraRefinada.getNombre() + " - ");
                    ArrayList<Documento> d = new ArrayList<Documento>(palabraRefinada.getDocumentos());
                    Documento c = palabraRefinada.getConsulta();

                for (Documento doc : d) {
                        System.out.print(doc.getNombre() + ", ");
                        System.out.print(doc.getRepeticiones() + ", ");
                        System.out.print(doc.getPeso() + ", ");
                        System.out.print(doc.getIdf() + " - ");
                }
                if (c != null){
                    System.out.print(c.getNombre() + ", ");
                    System.out.print(c.getRepeticiones() + ", ");
                    System.out.print(c.getPeso() + ", ");
                    System.out.print(c.getIdf() + " ");
                }
                System.out.println(); 
                }
            }
        }
    }
    
}
