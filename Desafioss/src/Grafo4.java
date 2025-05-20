import java.util.*; // se importan estructuras como Map, List, Set y Stack

class Grafo4 {
    // Lista de adyacencia: asocia cada nodo con su lista de vecinos
    private Map<String, List<String>> adjList = new HashMap<>();

    // Método para agregar una conexión entre nodos
    public void agregarArista(String origen, String destino) {
        adjList.putIfAbsent(origen, new ArrayList<>()); // si el nodo no tiene lista, se crea
        adjList.get(origen).add(destino); // se agrega el destino como vecino del origen
    }

    // Recorrido en profundidad (DFS) usando pila en lugar de recursión
    public void dfsIterativo(String inicio) {
        Set<String> visitados = new HashSet<>(); // para llevar control de los nodos ya visitados
        Stack<String> stack = new Stack<>(); // pila para manejar el recorrido
        stack.push(inicio); // se agrega el nodo inicial

        while (!stack.isEmpty()) {
            String nodo = stack.pop(); // se toma el último nodo agregado
            if (!visitados.contains(nodo)) {
                visitados.add(nodo); // se marca como visitado
                System.out.print(nodo + " "); // se imprime el nodo

                // se agregan sus vecinos no visitados a la pila
                for (String vecino : adjList.getOrDefault(nodo, new ArrayList<>())) {
                    if (!visitados.contains(vecino)) {
                        stack.push(vecino);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Grafo4 grafo = new Grafo4();

        // Se definen las conexiones entre nodos
        grafo.agregarArista("A", "B");
        grafo.agregarArista("A", "C");
        grafo.agregarArista("B", "D");

        grafo.dfsIterativo("A"); // se inicia el recorrido DFS desde el nodo "A"
    }
}
