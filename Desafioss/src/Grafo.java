import java.util.*; // se importan las estructuras necesarias: Map, List, Set, etc.

class Grafo {
    // Mapa que guarda la lista de adyacencia: cada nodo se asocia a una lista de vecinos
    private Map<String, List<String>> adjList = new HashMap<>();

    // Método para agregar una conexión entre dos nodos
    public void agregarArista(String origen, String destino) {
        adjList.putIfAbsent(origen, new ArrayList<>()); // si el nodo de origen no existe, se crea su lista
        adjList.get(origen).add(destino); // se agrega el nodo destino a la lista de adyacencia del origen
    }

    // Método que inicia el recorrido DFS desde un nodo dado
    public void dfs(String inicio) {
        Set<String> visitados = new HashSet<>(); // conjunto para llevar registro de los nodos ya visitados
        dfsHelper(inicio, visitados); // se llama al método auxiliar recursivo
    }

    // Método recursivo que hace el recorrido en profundidad
    private void dfsHelper(String nodo, Set<String> visitados) {
        if (visitados.contains(nodo)) return; // si el nodo ya fue visitado, se detiene la llamada

        System.out.print(nodo + " "); // se imprime el nodo visitado
        visitados.add(nodo); // se marca el nodo como visitado

        // se recorre cada vecino del nodo actual y se aplica DFS sobre ellos
        for (String vecino : adjList.getOrDefault(nodo, new ArrayList<>())) {
            dfsHelper(vecino, visitados);
        }
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo(); // se crea una instancia del grafo

        // Se agregan algunas conexiones (aristas) entre personas
        grafo.agregarArista("Juan", "Ana");
        grafo.agregarArista("Ana", "Pedro");
        grafo.agregarArista("Pedro", "Luis");

        grafo.dfs("Juan"); // se inicia el recorrido en profundidad desde "Juan"
    }
}
