package graph.implementation;

public class Arco <E,T>{
    
    private Vertice<E> origen, destino;
    
    private T etiqueta;
    
    public Arco(Vertice<E> origen, Vertice<E> destino, T etiqueta) {
        this.origen = origen;
        this.destino = destino;
        this.etiqueta = etiqueta;
    }
    
    public Vertice<E> getOrigen() {
        return this.origen;
    }
    
    public Vertice<E> getDestino() {
        return this.destino;
    }
    
    public T getEtiqueta() {
        return this.etiqueta;
    }
    
}
