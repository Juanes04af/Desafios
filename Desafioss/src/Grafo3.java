import java.util.*; // se importan estructuras como Map, List, Set y PriorityQueue

class Grafo3 {
    // Lista de adyacencia donde cada nodo se asocia a una lista de pares [vecino, peso]
    private Map<Integer, List<int[]>> adjList = new HashMap<>();

    // Método para agregar una arista con peso entre dos nodos
    public void agregarArista(int origen, int destino, int peso) {
        adjList.putIfAbsent(origen, new ArrayList<>());
        adjList.get(origen).add(new int[]{destino, peso});
    }

    // Algoritmo de Prim para construir el árbol de expansión mínima (MST) desde un nodo inicial
    public void prim(int inicio) {
        // Cola de prioridad para elegir la arista de menor peso en cada paso
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        Set<Integer> visitados = new HashSet<>(); // nodos que ya han sido incluidos en el MST

        pq.add(new int[]{inicio, 0}); // se inicia desde el nodo inicial, con peso 0

        while (!pq.isEmpty()) {
            int[] arista = pq.poll(); // se toma la arista de menor peso disponible
            int nodo = arista[0], peso = arista[1];

            if (visitados.contains(nodo)) continue; // si el nodo ya fue visitado, se omite

            visitados.add(nodo); // se marca el nodo como visitado
            System.out.println("Nodo: " + nodo + ", Peso: " + peso); // se imprime el nodo agregado al MST

            // se recorren los vecinos del nodo actual
            for (int[] vecino : adjList.getOrDefault(nodo, new ArrayList<>())) {
                if (!visitados.contains(vecino[0])) {
                    pq.add(vecino); // se agregan aristas conectadas al nodo a la cola de prioridad
                }
            }
        }
    }

    public static void main(String[] args) {
        Grafo3 grafo = new Grafo3();

        // Se agregan aristas con sus respectivos pesos
        grafo.agregarArista(0, 1, 10);
        grafo.agregarArista(0, 2, 6);
        grafo.agregarArista(0, 3, 5);
        grafo.agregarArista(1, 2, 5);
        grafo.agregarArista(2, 3, 4);

        grafo.prim(0); // se ejecuta el algoritmo de Prim desde el nodo 0
    }
}
