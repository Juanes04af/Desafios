import java.util.*; // se importan las colecciones necesarias: Map, List, Set, Queue...

class Grafo2 {
    // Mapa que almacena la lista de adyacencia de cada nodo
    private Map<String, List<String>> adjList = new HashMap<>();

    // Método para agregar una conexión (arista) entre dos nodos
    public void agregarArista(String origen, String destino) {
        adjList.putIfAbsent(origen, new ArrayList<>()); // si el nodo no tiene lista aún, se la crea
        adjList.get(origen).add(destino); // se agrega el nodo destino como vecino del origen
    }

    // Algoritmo BFS (búsqueda en anchura)
    public void bfs(String inicio) {
        Set<String> visitados = new HashSet<>(); // conjunto para evitar visitar un nodo más de una vez
        Queue<String> cola = new LinkedList<>(); // estructura FIFO para procesar los nodos en orden de descubrimiento

        cola.add(inicio); // se agrega el nodo inicial a la cola
        visitados.add(inicio); // se marca como visitado

        while (!cola.isEmpty()) {
            String nodo = cola.poll(); // se saca el primer nodo de la cola
            System.out.print(nodo + " "); // se imprime el nodo actual

            // se recorren los vecinos del nodo actual
            for (String vecino : adjList.getOrDefault(nodo, new ArrayList<>())) {
                if (!visitados.contains(vecino)) {
                    cola.add(vecino); // se agrega a la cola para visitar más adelante
                    visitados.add(vecino); // se marca como visitado
                }
            }
        }
    }

    public static void main(String[] args) {
        Grafo2 grafo = new Grafo2();

        // Se agregan las conexiones entre nodos
        grafo.agregarArista("A", "B");
        grafo.agregarArista("A", "E");
        grafo.agregarArista("B", "D");

        grafo.bfs("A"); // se inicia el recorrido BFS desde el nodo "A"
    }
}
