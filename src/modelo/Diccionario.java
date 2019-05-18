
package modelo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Diccionario {
    HashSet<Palabra> listaPalabrasRefinadas = new HashSet<Palabra>();
    public static int cantidadArchivos = 10;
     
    public HashSet<Palabra> listaPalabras()throws IOException{
        for (int i = 1; i <= cantidadArchivos; i++) {
            String archivo = "D:\\Clariss\\Universidad\\1-2019\\Recuperacion de la información\\Modelos de RI\\src\\textos\\documento"+i+".txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "utf-8"));
            String t = "";
            while (br.ready()) {                
               //Lee la proxima linea
               String linea = br.readLine();
               linea = quitarTildes(linea);//cambiamos las tildes                  
               StringTokenizer st = new StringTokenizer(limpiarLinea(linea));
               while (st.hasMoreElements()) {
                t = st.nextToken();
                if (t.length() >= 3) {
                   listaPalabrasRefinadas.add(new Palabra(t));
                }
               }
            }
        }
        recorrerDocumentos();
     return listaPalabrasRefinadas;
    }
    
    public void recorrerDocumentos() throws IOException{
       for (Palabra palabra : listaPalabrasRefinadas) {
           int repeticiones = 0;
           boolean hayPalabra = false;
           for (int i = 1; i <= cantidadArchivos ; i++) {
            String archivo = "D:\\Clariss\\Universidad\\1-2019\\Recuperacion de la información\\Modelos de RI\\src\\textos\\documento"+i+".txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "utf-8"));
                String pal="";
                while (br.ready()) {
                    String linea = br.readLine();
                    StringTokenizer st = new StringTokenizer(limpiarLinea(linea));
                    while (st.hasMoreElements()) {
                        pal = st.nextToken();
                        if (pal.equals(palabra.getNombre())) { 
                                repeticiones++; // qtd vezes palavra repete
                                hayPalabra = true;
                        }
                    }
                }
                br.close();
                if (hayPalabra) {
                        palabra.insertarDocumento(new Documento("D" + i, repeticiones, 0, 0));
                }
                repeticiones = 0;
                hayPalabra = false;
           }
       }
       
   }
    public String limpiarLinea(String linea) {
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
    public String quitarTildes(String verso){
        verso = verso.replace("á","a").replace("é","e").replace("í", "i").replace("ó","o").replace("ú","u").replace("ñ","ni");
        return verso;
    }
    
}    
