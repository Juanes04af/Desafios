import java.util.*; // se importan las estructuras necesarias para el grafo y el recorrido

class Grafo5 {
    // Lista de adyacencia: cada nodo tiene una lista de vecinos
    private Map<String, List<String>> adjList = new HashMap<>();

    // Método para agregar una conexión de un nodo a otro
    public void agregarArista(String origen, String destino) {
        adjList.putIfAbsent(origen, new ArrayList<>()); // si el nodo no existe, se crea su lista
        adjList.get(origen).add(destino); // se agrega el destino como vecino del nodo origen
    }

    // Algoritmo de recorrido en anchura (BFS)
    public void bfs(String inicio) {
        Set<String> visitados = new HashSet<>(); // guarda los nodos que ya se recorrieron
        Queue<String> cola = new LinkedList<>(); // estructura FIFO para controlar el orden de recorrido

        cola.add(inicio); // se agrega el nodo inicial a la cola
        visitados.add(inicio); // se marca como visitado

        while (!cola.isEmpty()) {
            String nodo = cola.poll(); // se toma el siguiente nodo de la cola
            System.out.print(nodo + " "); // se imprime el nodo actual

            // se exploran sus vecinos
            for (String vecino : adjList.getOrDefault(nodo, new ArrayList<>())) {
                if (!visitados.contains(vecino)) {
                    cola.add(vecino); // se agrega el vecino a la cola
                    visitados.add(vecino); // y se marca como visitado para evitar repetirlo
                }
            }
        }
    }

    public static void main(String[] args) {
        Grafo5 grafo = new Grafo5();

        // Se agregan conexiones entre nodos
        grafo.agregarArista("A", "B");
        grafo.agregarArista("A", "C");
        grafo.agregarArista("B", "D");

        grafo.bfs("A"); // se inicia el recorrido BFS desde el nodo "A"
    }
}
