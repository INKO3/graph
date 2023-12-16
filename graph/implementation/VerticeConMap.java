package graph.implementation;

import java.util.HashMap;
import java.util.Map;

public class VerticeConMap<E,T> {
    
    private Vertice<E> origen; // vértice del grafo
    
    private Map<Vertice<E>, T> mapAdy; // mapa de adyacentes del vértice origen
    
    VerticeConMap(Vertice<E> v) {
        origen = v;
        mapAdy = new HashMap<>();
    }
    
    Vertice<E> getVertice(){
        return origen;
    }
    
    Map<Vertice<E>, T> getAdy(){
        return mapAdy;
    }
    
}
