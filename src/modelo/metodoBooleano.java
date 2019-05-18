
package modelo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
import static modelo.inicio.lista;

public class metodoBooleano {
    public static int cantidadArchivos = 10;
    
    public String consultaBooleana(String palabraBuscar) {
        ArrayList<String> parametros = new ArrayList<>();
        String resultConsulta = "";
        String token = " ";
        StringTokenizer linea = new StringTokenizer(palabraBuscar);
        int i = 0;
        while (linea.hasMoreTokens()) {
                token = linea.nextToken();
                parametros.add(token);
        }
        
        ArrayList<String> palabraNot1 = new ArrayList<String>();
        ArrayList<String> palabraNot2 = new ArrayList<String>();
        ArrayList<String> respuesta = new ArrayList<String>();
        String palabraAux = "";
        
        //Casos donde en la consulta aparezca la operacion AND
        i = 0;
                     
        if (parametros.get(i + 1).equals("AND") && parametros.size() == 3) {
            // palabra AND palabra
            respuesta = operacionAnd(parametros.get(i), parametros.get(i + 2));
            for (String string : respuesta) {
                    resultConsulta += string + "\n";
            }
            return resultConsulta;
        }
        else if (parametros.get(i + 1).equals("AND") && parametros.get(i + 3).equals("AND") && parametros.size() == 5) {
            //palabra AND (palabra AND palabra)
            respuesta = operacionAnd(parametros.get(i+2), parametros.get(i+4));
            return operacionAnd(parametros.get(i),respuesta);
        }else if (parametros.get(i + 1).equals("AND") && parametros.get(i + 3).equals("AND") && parametros.get(i+4).equals("NOT")&&parametros.size() == 6) {
            //palabra AND (palabra AND (NOT palabra))
            palabraNot1 = operacionNot(parametros.get(i+5));
            palabraAux = operacionAnd(parametros.get(i+2), palabraNot1);
            return operacionAnd1(parametros.get(i), palabraAux);
            
        }else if (parametros.get(i + 1).equals("AND") && parametros.get(i + 3).equals("OR") && parametros.size() == 5) {
            //palabra AND (palabra OR palabra)
            respuesta = operacionOr(parametros.get(i+2), parametros.get(i+4));
            return operacionAnd(parametros.get(i), respuesta);
            
        }else if (parametros.get(i + 1).equals("AND") && parametros.get(i + 3).equals("OR")&& parametros.get(i+4).equals("NOT") && parametros.size() == 6) {
            //palabra AND (palabra OR (NOT palabra))
            palabraNot1 = operacionNot(parametros.get(i+5));
            palabraAux = operacionOr(parametros.get(i+2), palabraNot1);
            return operacionAnd1(parametros.get(i), palabraAux);
                    
        }else if (parametros.get(i + 2).equals("NOT") && parametros.get(i + 1).equals("AND") && parametros.size() == 4) {
            //palabra AND (NOT palabra)
            palabraNot1 = operacionNot(parametros.get(i + 3));
            return operacionAnd(parametros.get(i), palabraNot1);
        }else if (parametros.get(i + 2).equals("NOT") && parametros.get(i + 1).equals("AND")&& parametros.get(i+4).equals("AND")&& parametros.size() == 6) {
            //palabra AND ((NOT palabra) AND palabra)
            palabraNot1 = operacionNot(parametros.get(i + 3));
            palabraAux = operacionAnd(palabraNot1, parametros.get(i+5));
            return operacionAnd1(parametros.get(i), palabraAux);
        }else if (parametros.get(i + 2).equals("NOT") && parametros.get(i + 1).equals("AND")&& parametros.get(i+4).equals("AND")&& parametros.get(i+5).equals("NOT")&& parametros.size() == 7) {
            //palabra AND ((NOT palabra) AND (NOT palabra))
            palabraNot1 = operacionNot(parametros.get(i + 3));
            palabraNot2 = operacionNot(parametros.get(i + 6));
            palabraAux = operacionAnd(palabraNot1, palabraNot2);
            return operacionAnd1(parametros.get(i), palabraAux);
        }else if (parametros.get(i + 2).equals("NOT") && parametros.get(i + 1).equals("AND")&& parametros.get(i+4).equals("OR")&& parametros.size() == 6) {
            //palabra AND ((NOT palabra) OR palabra)
            palabraNot1 = operacionNot(parametros.get(i + 3));
            palabraAux = operacionOr(palabraNot1, parametros.get(i+5));
            return operacionAnd1(parametros.get(i), palabraAux);
        }
        else if (parametros.get(i).equals("NOT") && parametros.get(i + 2).equals("AND") && parametros.size() == 4) {
            //(NOT palabra) AND palabra
            palabraNot1 = operacionNot(parametros.get(i + 1));
            resultConsulta = operacionAnd(palabraNot1, parametros.get(i + 3));
            return resultConsulta;
        } else if (parametros.get(i).equals("NOT") && parametros.get(i + 2).equals("AND") && parametros.get(i+4).equals("AND")&& parametros.size() == 6) {
            //(NOT palabra) AND (palabra AND palabra)
            palabraNot1 = operacionNot(parametros.get(i + 1));
            respuesta = operacionAnd(parametros.get(i+3), parametros.get(i+5));
            return operacionAnd(palabraNot1, respuesta);
        } else if (parametros.get(i).equals("NOT") && parametros.get(i + 2).equals("AND")&& parametros.get(i+4).equals("AND")&& parametros.get(i+5).equals("NOT") && parametros.size() == 7) {
            //(NOT palabra) AND (palabra AND (NOT palabra))
            palabraNot1 = operacionNot(parametros.get(i + 1));
            palabraNot2 = operacionNot(parametros.get(i + 6));
            palabraAux = operacionAnd(parametros.get(i+3), palabraNot2);
            return operacionAnd1(palabraNot1, palabraAux);
        } else if (parametros.get(i).equals("NOT") && parametros.get(i + 2).equals("AND")&& parametros.get(i+4).equals("OR") && parametros.size() == 6) {
            //(NOT palabra) AND (palabra OR palabra)
            palabraNot1 = operacionNot(parametros.get(i + 1));
            respuesta = operacionOr(parametros.get(i+3), parametros.get(i+5));
            return operacionAnd(palabraNot1, respuesta);
        } else if (parametros.get(i).equals("NOT") && parametros.get(i + 2).equals("AND")&& parametros.get(i+4).equals("OR")&& parametros.get(i+5).equals("NOT") && parametros.size() == 7) {
            //(NOT palabra) AND (palabra OR (NOT palabra))
            palabraNot1 = operacionNot(parametros.get(i + 1));
            palabraNot2 = operacionNot(parametros.get(i + 6));
            palabraAux = operacionOr(parametros.get(i+3), palabraNot2);
            return operacionAnd1(palabraNot1, palabraAux);
        }
        //Casos donde en la consulta aparezca la operacion OR
        i = 0;
        if (parametros.get(i + 1).equals("OR") && parametros.size() == 3) {
            //palabra OR palabra
            respuesta = operacionOr(parametros.get(i), parametros.get(i + 2));
            for (String string : respuesta) {
                    resultConsulta += string + "\n";
            }
            return resultConsulta;
        }else if (parametros.get(i + 1).equals("OR") && parametros.get(i + 3).equals("AND") && parametros.size() == 5) {
                //(palabra OR palabra) AND palabra
                respuesta = operacionOr(parametros.get(i), parametros.get(i + 2));
                return operacionAnd(respuesta, parametros.get(i + 4));
        } else if (parametros.get(i + 1).equals("OR") && parametros.get(i + 3).equals("AND")&& parametros.get(i+4).equals("NOT") && parametros.size() == 6) {
                //(palabra OR palabra) AND (NOT palabra)
                palabraNot1 = operacionNot(parametros.get(i + 5));
                respuesta = operacionOr(parametros.get(i), parametros.get(i + 2));
                return operacionAnd(respuesta, palabraNot1);
        }else if (parametros.get(i + 1).equals("OR") && parametros.get(i + 3).equals("OR") && parametros.size() == 5) {
                //palabra OR (palabra OR palabra)
                respuesta = operacionOr(parametros.get(i+2), parametros.get(i + 4));
                return operacionOr(parametros.get(i), respuesta);
        }else if (parametros.get(i + 1).equals("OR") && parametros.get(i + 3).equals("OR") && parametros.get(i+4).equals("NOT")&& parametros.size() == 6) {
                //palabra OR (palabra OR (NOT palabra))
                palabraNot1 = operacionNot(parametros.get(i + 5));
                palabraAux = operacionOr(parametros.get(i+2), palabraNot1);
                return operacionOr1(parametros.get(i), palabraAux);
       }        
        else if (parametros.get(i).equals("NOT") && parametros.get(i + 2).equals("OR") && parametros.size() == 4) {
                //(NOT palabra) OR palabra
                palabraNot1 = operacionNot(parametros.get(i + 1));
                return operacionOr(palabraNot1, parametros.get(i + 3));
        }else if (parametros.get(i).equals("NOT") && parametros.get(i + 2).equals("OR") && parametros.get(i+4).equals("AND")&& parametros.size() == 6) {
                //((NOT palabra) OR palabra) AND palabra
                palabraNot1 = operacionNot(parametros.get(i + 1));
                palabraAux = operacionOr(palabraNot1, parametros.get(i + 3));
                return operacionAnd1(parametros.get(i+5),palabraAux);
                
        }else if (parametros.get(i).equals("NOT") && parametros.get(i + 2).equals("OR")&& parametros.get(i+4).equals("AND")&& parametros.get(i+5).equals("NOT") && parametros.size() == 7) {
                //((NOT palabra) OR palabra) AND (NOT palabra)
                palabraNot1 = operacionNot(parametros.get(i + 1));
                palabraNot2 = operacionNot(parametros.get(i + 6));
                palabraAux = operacionOr(palabraNot1, parametros.get(i + 3));
                return operacionAnd1(palabraNot2,palabraAux);
        }else if (parametros.get(i).equals("NOT") && parametros.get(i + 2).equals("OR") && parametros.get(i+4).equals("OR")&& parametros.size() == 6) {
                //((NOT palabra) OR palabra) OR palabra
                palabraNot1 = operacionNot(parametros.get(i + 1));
                palabraAux = operacionOr(palabraNot1, parametros.get(i + 3));
                return operacionOr1(parametros.get(i+5),palabraAux);
                
        }else if (parametros.get(i).equals("NOT") && parametros.get(i + 2).equals("OR") && parametros.get(i+4).equals("OR")&& parametros.get(i+5).equals("NOT")&& parametros.size() ==7 ) {
                //((NOT palabra) OR palabra) OR (NOT palabra)
                palabraNot1 = operacionNot(parametros.get(i + 1));
                palabraNot2 = operacionNot(parametros.get(i + 6));
                palabraAux = operacionOr(palabraNot1, parametros.get(i + 3));
                return operacionOr1(palabraNot2,palabraAux);
        }
        else if (parametros.get(i + 1).equals("OR") && parametros.get(i + 2).equals("NOT")&& parametros.size() ==4) {
                //palabra OR (NOT palabra)
                palabraNot1 = operacionNot(parametros.get(i + 3));
                return operacionOr(parametros.get(i), palabraNot1);
        }else if (parametros.get(i + 1).equals("OR") && parametros.get(i + 2).equals("NOT")&& parametros.get(i+4).equals("AND")&& parametros.size() ==6) {
                //(palabra OR (NOT palabra)) AND palabra
                palabraNot1 = operacionNot(parametros.get(i + 3));
                palabraAux = operacionOr(parametros.get(i),palabraNot1);
                return operacionAnd1( parametros.get(i+5),palabraAux);                
        }else if (parametros.get(i + 1).equals("OR") && parametros.get(i + 2).equals("NOT")&& parametros.get(i+4).equals("AND")&& parametros.get(i+5).equals("NOT")&& parametros.size() ==7) {
                //(palabra OR (NOT palabra)) AND (NOT palabra)
                palabraNot1 = operacionNot(parametros.get(i + 3));
                palabraNot2 = operacionNot(parametros.get(i + 6));
                palabraAux = operacionOr(parametros.get(i),palabraNot1);
                return operacionAnd1(palabraNot2,palabraAux);
        }else if (parametros.get(i + 1).equals("OR") && parametros.get(i + 2).equals("NOT")&& parametros.get(i+4).equals("OR")&& parametros.size() ==6) {
                //(palabra OR (NOT palabra)) OR palabra
                palabraNot1 = operacionNot(parametros.get(i + 3));
                palabraAux = operacionOr(parametros.get(i),palabraNot1);
                return operacionOr1(parametros.get(i+5),palabraAux);
                
        }else if (parametros.get(i + 1).equals("OR") && parametros.get(i + 2).equals("NOT")&& parametros.get(i+4).equals("OR")&& parametros.get(i+5).equals("NOT")&& parametros.size() ==7) {
                //(palabra OR (NOT palabra)) OR (NOT palabra)
                palabraNot1 = operacionNot(parametros.get(i + 3));
                palabraNot2 = operacionNot(parametros.get(i + 6));
                palabraAux = operacionOr(parametros.get(i), palabraNot1);
                return operacionOr1(palabraNot2, palabraAux);
        }
        return "";
    }
    private ArrayList<String> operacionOr(String palabra1, String palabra2) {

        HashSet<String> listAux = new HashSet<String>();
        ArrayList<String> respuesta = new ArrayList<String>();
        ArrayList<Documento> aux = new ArrayList<Documento>();
        ArrayList<Documento> aux2 = new ArrayList<Documento>();

        for (Palabra palabra : inicio.palabrasRefinadas) {
            if (palabra.getNombre().equals(palabra1)) {
                aux = palabra.getDocumentos();
                for (Documento documento : aux) {
                    listAux.add(documento.getNombre());
                }
            }
        }
        for (Palabra palabra : inicio.palabrasRefinadas) {
            if (palabra.getNombre().equals(palabra2)) {
                aux2 = palabra.getDocumentos();
                for (Documento documento : aux2) {
                    listAux.add(documento.getNombre());
                }
            }
        }
        for (String string : listAux) {
            respuesta.add(string);
        }
        return respuesta;
    }
    private ArrayList<String> operacionAnd(String palabra1, String palabra2) {

        ArrayList<String> respuesta = new ArrayList<String>();
        ArrayList<Documento> docsP1 = new ArrayList<Documento>();
        ArrayList<Documento> docsP2 = new ArrayList<Documento>();

        for (Palabra palabra : inicio.palabrasRefinadas) {
            if (palabra.getNombre().equals(palabra1)) {
                docsP1 = palabra.getDocumentos();
            }
        }
        for (Palabra palabra : inicio.palabrasRefinadas) {
            if (palabra.getNombre().equals(palabra2)) {
                docsP2 = palabra.getDocumentos();
            }
        }
        if ((docsP1 != null) && (docsP2 != null)) {
            for (Documento d1 : docsP1) {
                for (Documento d2 : docsP2) {
                    if (d1.getNombre().equals(d2.getNombre())) {
                        respuesta.add(d1.getNombre());
                    }
                }
            }
        }else{
                respuesta.add("");
        }
        return respuesta;
    }
    private ArrayList<String> operacionNot(String palabra1) {
        ArrayList<String> documentos = new ArrayList<String>();
        ArrayList<Documento> docsP = new ArrayList<Documento>();
        
        for (int i = 1; i <= cantidadArchivos; i++) {
            documentos.add("D" + i);
        }
        for (Palabra palabra : inicio.palabrasRefinadas) {
            if (palabra.getNombre().equals(palabra1)) {
                docsP = palabra.getDocumentos();
            }
        }
        if (docsP != null) {
            for (Documento d : docsP) {
                if (documentos.contains(d.getNombre())) {
                    documentos.remove(d.getNombre());
                }
            }
        }
        return documentos;
    }
    
    private String operacionAnd(ArrayList<String> palabra1, String palabra2) {
        String respuesta = "";
        ArrayList<Documento> aux2 = new ArrayList<Documento>();
        aux2 = null;
        for (Palabra palabra : inicio.palabrasRefinadas) {
            if (palabra.getNombre().equals(palabra2)) {
                aux2 = palabra.getDocumentos();
            }
        }
        if (aux2 != null) {
            for (int i = 0; i < palabra1.size(); i++) {
                for (int j = 0; j < aux2.size(); j++){
                    if (palabra1.get(i).equals(aux2.get(j).getNombre())) {
                        respuesta += palabra1.get(i) + "\n";
                    }
                }
            }
        } else{
                respuesta = "";
        }
        return respuesta;

    }
    private String operacionAnd(String palabra2, ArrayList<String> palabra1) {

        String respuesta = "";
        ArrayList<Documento> aux2 = new ArrayList<Documento>();

        for (Palabra palabra : inicio.palabrasRefinadas) {
            if (palabra.getNombre().equals(palabra2)) {
                aux2 = palabra.getDocumentos();
            }
        }

        for (int i = 0; i < palabra1.size(); i++) {
            for (int j = 0; j < aux2.size(); j++){
                if (palabra1.get(i).equals(aux2.get(j).getNombre())) {
                    respuesta += palabra1.get(i) + "\n";
                }
            }
        }
        return respuesta;
    }
    private String operacionAnd(ArrayList<String> palabra1, ArrayList<String> palabra2) {
        String respuesta = "";
        for (int i = 0; i < palabra1.size(); i++) {
            for (int j = 0; j < palabra2.size(); j++){
                if (palabra1.get(i).equals(palabra2.get(j))) {
                    respuesta += palabra2.get(i) + "\n";
                }
            }
        }
        return respuesta;
    }
    private String operacionAnd1(String palabra1, String docs){
        String respuesta = "";
        ArrayList<Documento>docsP1 = new ArrayList<Documento>();
        String[] documentos = docs.split("\n"); 
        for (Palabra palabra : inicio.palabrasRefinadas) {
            if (palabra.getNombre().equals(palabra1)) {
               docsP1 = palabra.getDocumentos();
            }
        }
        for (Documento doc : docsP1) {
            for (int i = 0; i < documentos.length ; i++) {
                if (doc.getNombre().equals(documentos[i])) {
                    respuesta += documentos[i] + "\n";
                    
                }
            }
        }
        return respuesta;
    }
    private String operacionAnd1(ArrayList<String> res , String docs){
        String respuesta = "";
        String[] documentos = docs.split("\n"); 
        for (int i = 0; i < res.size(); i++) {
           for (int j = 0; j < documentos.length ; j++) {
                if (res.get(i).equals(documentos[j])) {
                    respuesta += res.get(i) + "\n";
                }
           }
        }
        return respuesta;
    }
    
    private String operacionOr(ArrayList<String> palabra1, String palabra2) {
        String result = "";
        HashSet<String> listAux = new HashSet<String>();
        ArrayList<Documento> aux2 = new ArrayList<Documento>();

        for (int i = 0; i < palabra1.size(); i++) {
            listAux.add(palabra1.get(i));//añade los documentos del primer parametro
        }

        for (Palabra palabra : inicio.palabrasRefinadas) {
            if (palabra.getNombre().equals(palabra2)) {
                aux2 = palabra.getDocumentos();
                for (Documento documento : aux2) {
                    //añade los documentos del segundo parametro,no permite repeticiones
                    listAux.add(documento.getNombre());
                }
            }
        }
        for (String documento : listAux) {
            result += documento + "\n";
        }
        return result;
    }
    private String operacionOr(String palabra2, ArrayList<String> palabra1) {
        String result = "";
        HashSet<String> listAux = new HashSet<String>();
        ArrayList<Documento> aux2 = new ArrayList<Documento>();

        for (int i = 0; i < palabra1.size(); i++) {
            listAux.add(palabra1.get(i));
        }

        for (Palabra palabra : inicio.palabrasRefinadas) {
            if (palabra.getNombre().equals(palabra2)) {
                aux2 = palabra.getDocumentos();
                for (Documento documento : aux2) {
                    listAux.add(documento.getNombre());
                }
            }
        }

        for (String documento : listAux) {
                result += documento + "\n";
        }
        return result;
    }
    private String operacionOr(ArrayList<String> palabra1, ArrayList<String> palabra2) {
        String result = "";
        HashSet<String> listAux = new HashSet<String>();

        for (int i = 0; i < palabra1.size(); i++) {
            listAux.add(palabra1.get(i));
        }

        for (int i = 0; i < palabra2.size(); i++) {
            listAux.add(palabra2.get(i));
        }

        for (String documento : listAux) {
            result += documento + "\n";
        }
        return result;
    }
    private String operacionOr1(String palabra1, String docs){
        String respuesta = "";
        HashSet<String> listAux = new HashSet<String>();
        ArrayList<Documento>docsP1 = new ArrayList<Documento>();
        String[] documentos = docs.split("\n"); 
        for (Palabra palabra : inicio.palabrasRefinadas) {
            if (palabra.getNombre().equals(palabra1)) {
               docsP1 = palabra.getDocumentos();
            }
        }
        for (int i = 0; i < documentos.length; i++) {
            listAux.add(documentos[i]);
        }
        for (Documento doc : docsP1) {
            listAux.add(doc.getNombre());
        }
        for (String documento : listAux) {
            respuesta += documento + "\n";
        }
        return respuesta;
    }
    private String operacionOr1(ArrayList<String> res , String docs){
        String respuesta = "";
        HashSet<String> listAux = new HashSet<String>();
        String[] documentos = docs.split("\n"); 
        for (int i = 0; i < res.size(); i++) {
            listAux.add(res.get(i));
        }

        for (int i = 0; i < documentos.length; i++) {
            listAux.add(documentos[i]);
        }

        for (String documento : listAux) {
            respuesta += documento + "\n";
        }
        return respuesta;
    }
}

