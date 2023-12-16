package graph.use;

import graph.implementation.Arco;
import graph.implementation.Grafo;
import graph.implementation.ListaAdyacencia;
import graph.implementation.Vertice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import map.implementation.ArrayListMap;
import map.implementation.Map;

public class EscaleraPalabras {

    private static Grafo<String, Integer> grafo = new ListaAdyacencia<>();
    
    private static String reemplazarPorGuion(String palabra, int posicion) {
        String output = "";
        for (int i = 0; i < palabra.length(); i++) {
            output += i == posicion ? "_" : palabra.charAt(i) + "";     
        }
        return output;
    }
    
    public static Map<String, List<String>> construirDiccionario(List<String> palabras) {
        Map<String, List<String>> dictionary = new ArrayListMap<>();
        String palabraModificada = "";
        
        for (String palabra : palabras) {
            for (int i = 0; i < palabra.length(); i++) {
                List<String> listaAux = new ArrayList<>();
                palabraModificada = reemplazarPorGuion(palabra, i);
                if (dictionary.get(palabraModificada) != null) {
                    listaAux = dictionary.get(palabraModificada);
                }
                if (!listaAux.contains(palabra)) {
                    listaAux.add(palabra);
                }
                dictionary.remove(palabraModificada);
                dictionary.insertar(palabraModificada, listaAux);
            }
        }
        return dictionary;
    }

    public static Grafo<String, Integer> construirGrafo(List<String> palabras) {
        Map<String, List<String>> diccionario = construirDiccionario(palabras);
        Vertice<String> vertice;
        for (String palabra : palabras) {
            vertice = new Vertice(palabra);
            grafo.insertarVertice(vertice);
        }
        Iterator<List<String>> iteratorValues = diccionario.getValues();
        List<String> listaPalabrasRelacionadas = new ArrayList<>();
        Arco<String, Integer> arco;
        Vertice<String> vertice1, vertice2;
        while (iteratorValues.hasNext()) {
            listaPalabrasRelacionadas = iteratorValues.next();
            for (String palabraRelacionada1 : listaPalabrasRelacionadas) {
                for (String palabraRelacionada2 : listaPalabrasRelacionadas) {
                    if (palabraRelacionada1.equals(palabraRelacionada2)) {
                        continue;
                    }
                    vertice1 = new Vertice<>(palabraRelacionada1);
                    vertice2 = new Vertice<>(palabraRelacionada2);
                    arco = new Arco<>(vertice1, vertice2, 0);
                    if (!grafo.estaArco(arco)) {
                        grafo.insertarArco(arco);
                    }
                }    
            }
        }
        return grafo;
    }
    
}
