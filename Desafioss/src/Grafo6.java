import java.util.*;

class Grafo6 {
    // Estructura para representar cada conexión: destino y peso
    static class Vecino {
        String destino;
        int peso;

        public Vecino(String destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    // Lista de adyacencia: para cada nodo se guarda una lista de sus vecinos con peso
    private Map<String, List<Vecino>> adjList = new HashMap<>();

    // Agrega una arista entre dos nodos, con un peso asociado
    public void agregarArista(String origen, String destino, int peso) {
        adjList.putIfAbsent(origen, new ArrayList<>());
        adjList.get(origen).add(new Vecino(destino, peso));
    }

    // Algoritmo de Dijkstra para encontrar las distancias mínimas desde el nodo inicial
    public void dijkstra(String inicio) {
        Map<String, Integer> distancias = new HashMap<>(); // guarda la mejor distancia encontrada a cada nodo
        PriorityQueue<NodoDistancia> cola = new PriorityQueue<>(Comparator.comparingInt(a -> a.distancia));

        distancias.put(inicio, 0); // la distancia al nodo inicial es 0
        cola.add(new NodoDistancia(inicio, 0)); // se agrega el nodo inicial a la cola

        while (!cola.isEmpty()) {
            NodoDistancia actual = cola.poll(); // se toma el nodo con menor distancia conocida
            String nodo = actual.nombre;
            int distancia = actual.distancia;

            // Si el nodo no tiene vecinos, se omite
            if (!adjList.containsKey(nodo)) continue;

            for (Vecino vecino : adjList.get(nodo)) {
                int nuevaDistancia = distancia + vecino.peso;

                // Si se encontró un camino más corto, se actualiza
                if (nuevaDistancia < distancias.getOrDefault(vecino.destino, Integer.MAX_VALUE)) {
                    distancias.put(vecino.destino, nuevaDistancia);
                    cola.add(new NodoDistancia(vecino.destino, nuevaDistancia));
                }
            }
        }

        // Se imprime el resultado: la distancia más corta desde el nodo inicial hacia los demás
        distancias.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    // Clase auxiliar para manejar la cola de prioridad con nombre del nodo y su distancia actual
    static class NodoDistancia {
        String nombre;
        int distancia;

        public NodoDistancia(String nombre, int distancia) {
            this.nombre = nombre;
            this.distancia = distancia;
        }
    }

    public static void main(String[] args) {
        Grafo6 grafo = new Grafo6();

        // Se agregan las conexiones con sus pesos
        grafo.agregarArista("A", "B", 10);
        grafo.agregarArista("B", "C", 5);

        grafo.dijkstra("A"); // salida: B: 10, C: 15
    }
}
