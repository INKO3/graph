package graph.use;

import graph.implementation.Grafo;
import java.util.*;
import java.io.*;

public class EscaleraPalabras {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Solicita al usuario el nombre del archivo que contiene la lista de palabras
        System.out.print("Introduce el nombre del archivo de palabras: ");
        String nombreArchivo = sc.nextLine();

        // Lee el archivo y carga todas las palabras en una lista
        List<String> palabras = leerArchivo(nombreArchivo);

        // Crea un grafo no dirigido donde cada vértice será una palabra
        Grafo<String> grafo = new Grafo<>(false);

        // Añade cada palabra como vértice al grafo
        for (String palabra : palabras) {
            grafo.agregarVertice(palabra);
        }

        // Recorre cada par de palabras y las conecta si difieren en una sola letra
        for (String palabra1 : palabras) {
            for (String palabra2 : palabras) {
                if (!palabra1.equals(palabra2) && difiereEnUnaLetra(palabra1, palabra2)) {
                    grafo.agregarArista(palabra1, palabra2);
                }
            }
        }

        // Solicita la palabra origen y destino al usuario
        System.out.print("Introduce palabra origen: ");
        String origen = sc.nextLine();
        System.out.print("Introduce palabra destino: ");
        String destino = sc.nextLine();

        // Busca el camino más corto entre la palabra origen y destino
        List<String> camino = buscarCamino(grafo, origen, destino);

        // Muestra el resultado
        if (camino != null) {
            System.out.println("Escalera encontrada:");
            for (String palabra : camino) {
                System.out.println(palabra);
            }
        } else {
            System.out.println("No existe una escalera entre esas dos palabras.");
        }
    }

    // Método para leer las palabras desde un archivo y almacenarlas en una lista
    private static List<String> leerArchivo(String nombreArchivo) {
        List<String> palabras = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Convierte la palabra a minúsculas y elimina espacios
                palabras.add(linea.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return palabras;
    }

    // Método que verifica si dos palabras difieren en exactamente una letra
    private static boolean difiereEnUnaLetra(String palabra1, String palabra2) {
        // Si no tienen la misma longitud, no pueden diferir solo en una letra
        if (palabra1.length() != palabra2.length()) return false;

        int diferencias = 0;
        for (int i = 0; i < palabra1.length(); i++) {
            if (palabra1.charAt(i) != palabra2.charAt(i)) {
                diferencias++;
                if (diferencias > 1) return false; // Si hay más de una diferencia, no es válida
            }
        }
        return diferencias == 1;
    }

    // Algoritmo de búsqueda en anchura (BFS) para encontrar el camino más corto entre dos palabras
    private static List<String> buscarCamino(Grafo<String> grafo, String origen, String destino) {
        Queue<String> cola = new LinkedList<>();
        Map<String, String> predecesor = new HashMap<>();
        Set<String> visitados = new HashSet<>();

        cola.add(origen);
        visitados.add(origen);

        while (!cola.isEmpty()) {
            String actual = cola.poll();

            // Si llegamos al destino, reconstruimos el camino
            if (actual.equals(destino)) {
                List<String> camino = new ArrayList<>();
                for (String at = destino; at != null; at = predecesor.get(at)) {
                    camino.add(at);
                }
                Collections.reverse(camino); // Invertimos el camino para que vaya de origen a destino
                return camino;
            }

            // Recorre los vecinos del nodo actual
            for (String vecino : grafo.obtenerVecinos(actual)) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    predecesor.put(vecino, actual);
                    cola.add(vecino);
                }
            }
        }

        // Si no se encuentra camino, se devuelve null
        return null;
    }
}

