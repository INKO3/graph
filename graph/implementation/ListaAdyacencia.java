package graph.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListaAdyacencia<E,T> implements Grafo<E,T> {
    private List<VerticeConMap<E,T>> listaVertices;

    public ListaAdyacencia() {
        this.listaVertices = new ArrayList<>();
    }

    @Override
    public boolean esVacio() {
        return listaVertices.isEmpty();
    }

    @Override
    public boolean estaVertice(Vertice<E> v) {
        for (VerticeConMap<E,T> verticeMap: listaVertices) {
            if (verticeMap.getVertice().equals(v)) 
                return true;
        }
        return false;
    }

    @Override
    public boolean estaArco(Arco<E, T> a) {
        for (VerticeConMap<E,T> verticeMap: listaVertices) {
            if (verticeMap.getVertice().equals(a.getOrigen())) {
                T etiquetaDestino = verticeMap.getAdy().get(a.getDestino());
                return etiquetaDestino != null && etiquetaDestino.equals(a.getEtiqueta());
            }
        }
        return false;
    }

    @Override
    public Iterator<Vertice<E>> vertices() {
        Collection<Vertice<E>> vertices = new ArrayList<>();
        for (VerticeConMap<E,T> verticeMap: listaVertices)
            vertices.add(verticeMap.getVertice());
        return vertices.iterator();
    }

    @Override
    public Iterator<Arco<E, T>> arcos() {
        Collection<Arco<E,T>> arcos = new ArrayList<>();
        for (VerticeConMap<E,T> verticeMap: listaVertices) {
            Map<Vertice<E>,T> ady = verticeMap.getAdy();
            for (Map.Entry<Vertice<E>,T> entry : ady.entrySet())
                arcos.add(new Arco(verticeMap.getVertice(), entry.getKey(), entry.getValue()));
        }
        return arcos.iterator();
    }

    @Override
    public Iterator<Vertice<E>> adyacentes(Vertice<E> v) {
        Collection<Vertice<E>> adyacentes = new ArrayList<>();
        for (VerticeConMap<E,T> verticeMap: listaVertices) {
            if (verticeMap.getVertice().equals(v)) {
                Map<Vertice<E>,T> ady = verticeMap.getAdy();
                for (Map.Entry<Vertice<E>,T> entry : ady.entrySet())
                    adyacentes.add(entry.getKey());
            }
        }
        return adyacentes.iterator();
    }

    @Override
    public void insertarVertice(Vertice<E> v) {
        if (this.estaVertice(v) == false)
            this.listaVertices.add(new VerticeConMap(v));
    }

    @Override
    public void insertarArco(Arco<E, T> a) {
        for (VerticeConMap<E,T> verticeMap: listaVertices) {
            if (verticeMap.getVertice().equals(a.getOrigen())) {
                T etiquetaDestino = verticeMap.getAdy().get(a.getDestino());
                if (etiquetaDestino == null) {
                    verticeMap.getAdy().put(a.getDestino(), a.getEtiqueta());
                    break;
                }
            }
        }
    }

    @Override
    public void eliminarVertice(Vertice<E> v) {
        for (int i = 0; i < listaVertices.size(); i++){
            if (listaVertices.get(i).getVertice().equals(v)) {
                listaVertices.remove(listaVertices.get(i));
                if (i == listaVertices.size())
                    continue;
            }
            T etiquetaDestino = listaVertices.get(i).getAdy().get(v);
            if (etiquetaDestino != null) {
                listaVertices.get(i).getAdy().remove(v);
            }
        }
    }

    @Override
    public void eliminarArco(Arco<E, T> a) {
        for (VerticeConMap<E,T> verticeMap: listaVertices) {
            if (verticeMap.getVertice().equals(a.getOrigen())) {
                T etiquetaDestino = verticeMap.getAdy().get(a.getDestino());
                if (etiquetaDestino != null && etiquetaDestino.equals(a.getEtiqueta())) 
                    verticeMap.getAdy().remove(a.getDestino());
            }
        }
    }
    
    @Override
    public void clear() {
        if (this.esVacio())
            return;
        Iterator<Vertice<E>> vertices = this.vertices();
        while (vertices.hasNext()) {
            this.eliminarVertice(vertices.next());
        }
    }
}
