package graph.implementation;

public class Vertice<E> {
    
    private E etiqueta;
    
    public Vertice(E etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    public E getEtiqueta() {
        return this.etiqueta;
    }

    public void setEtiqueta(E etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    public boolean equals(Object o) {
        Vertice<E> v = (Vertice<E>)o;
        return this.etiqueta.equals(v.getEtiqueta());
    }
    
}
