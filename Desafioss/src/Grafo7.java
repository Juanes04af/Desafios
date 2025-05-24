import java.util.*; // se importan las estructuras necesarias: Map, List, Set, PriorityQueue

class Grafo7 {
    // Lista de adyacencia: asocia cada nodo con una lista de pares [destino, peso]
    private Map<Integer, List<int[]>> adjList = new HashMap<>();

    // Método para agregar una arista dirigida con un peso entre dos nodos
    public void agregarArista(int origen, int destino, int peso) {
        adjList.putIfAbsent(origen, new ArrayList<>()); // si el nodo origen no existe, se crea su lista
        adjList.get(origen).add(new int[]{destino, peso}); // se agrega el nodo destino y su peso como un arreglo
    }

    // Algoritmo de Prim para construir un Árbol de Expansión Mínima (MST)
    public void prim(int inicio) {
        // Cola de prioridad que ordena las aristas por el peso (menor primero)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        Set<Integer> visitados = new HashSet<>(); // conjunto para registrar los nodos ya procesados
        pq.add(new int[]{inicio, 0}); // se inicia el algoritmo desde el nodo de partida

        while (!pq.isEmpty()) {
            int[] arista = pq.poll(); // se toma la arista de menor peso disponible
            int nodo = arista[0], peso = arista[1];

            if (visitados.contains(nodo)) continue; // si el nodo ya fue visitado, se ignora

            visitados.add(nodo); // se marca como visitado
            System.out.println("Nodo: " + nodo + ", Peso: " + peso); // se imprime el nodo y el peso de la arista que lo conectó

            // se recorren los vecinos del nodo actual
            for (int[] vecino : adjList.getOrDefault(nodo, new ArrayList<>())) {
                if (!visitados.contains(vecino[0])) {
                    pq.add(vecino); // se agregan a la cola las aristas que llevan a nodos no visitados
                }
            }
        }
    }

    public static void main(String[] args) {
        Grafo7 grafo = new Grafo7();

        // Se agregan aristas con sus respectivos pesos
        grafo.agregarArista(0, 1, 10);
        grafo.agregarArista(0, 2, 6);
        grafo.agregarArista(1, 2, 5);

        grafo.prim(0); // se ejecuta el algoritmo de Prim desde el nodo 0
    }
}
