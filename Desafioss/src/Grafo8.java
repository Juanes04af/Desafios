import java.util.*;

class Grafo8 {
    // Clase interna que representa un vecino, con su destino y el tiempo de viaje hacia él
    static class Vecino {
        String destino;
        int tiempo;

        public Vecino(String destino, int tiempo) {
            this.destino = destino;
            this.tiempo = tiempo;
        }
    }

    // Lista de adyacencia que guarda cada estación y sus vecinos (conexiones)
    private Map<String, List<Vecino>> adjList = new HashMap<>();

    // Método para agregar una ruta de una estación origen a una destino con un tiempo específico
    public void agregarRuta(String origen, String destino, int tiempo) {
        // Si la estación origen no está en el mapa, la agrego con una lista vacía
        adjList.putIfAbsent(origen, new ArrayList<>());
        // Luego agrego el vecino (estación destino + tiempo) a la lista del origen
        adjList.get(origen).add(new Vecino(destino, tiempo));
    }

    // Implementación del algoritmo de Dijkstra para encontrar el camino más rápido en tiempo
    public void dijkstra(String inicio, String destino) {
        // Mapa para guardar las distancias mínimas encontradas hasta cada estación
        Map<String, Integer> distancias = new HashMap<>();
        // Para guardar el predecesor de cada estación y así reconstruir la ruta
        Map<String, String> predecesores = new HashMap<>();
        // Cola de prioridad para procesar las estaciones en orden de distancia mínima
        PriorityQueue<NodoDistancia> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.distancia));

        distancias.put(inicio, 0); // La distancia al inicio es 0
        pq.add(new NodoDistancia(inicio, 0)); // Inicio en la cola de prioridad

        while (!pq.isEmpty()) {
            NodoDistancia actualNodo = pq.poll();
            String actual = actualNodo.nombre;
            int distancia = actualNodo.distancia;

            // Si ya llegué al destino, termino la búsqueda
            if (actual.equals(destino)) break;

            // Reviso todos los vecinos de la estación actual
            for (Vecino vecino : adjList.getOrDefault(actual, new ArrayList<>())) {
                int nuevaDistancia = distancia + vecino.tiempo;
                // Si encuentro una distancia menor a la conocida, actualizo y agrego a la cola
                if (nuevaDistancia < distancias.getOrDefault(vecino.destino, Integer.MAX_VALUE)) {
                    distancias.put(vecino.destino, nuevaDistancia);
                    predecesores.put(vecino.destino, actual);
                    pq.add(new NodoDistancia(vecino.destino, nuevaDistancia));
                }
            }
        }

        // Si hay ruta, imprimo la distancia y la ruta; si no, aviso que no hay ruta
        if (distancias.containsKey(destino)) {
            System.out.println("Distancia más corta desde " + inicio + " a " + destino + ": " + distancias.get(destino));
            System.out.print("Ruta: ");
            imprimirRuta(predecesores, destino);
            System.out.println();
        } else {
            System.out.println("No hay ruta disponible.");
        }
    }

    // Método auxiliar para imprimir la ruta desde el inicio al destino usando los predecesores
    private void imprimirRuta(Map<String, String> predecesores, String destino) {
        if (predecesores.containsKey(destino)) {
            imprimirRuta(predecesores, predecesores.get(destino));
        }
        System.out.print(destino + " ");
    }

    // Método BFS para encontrar la ruta con menor número de paradas (sin importar tiempos)
    public void bfs(String inicio, String destino) {
        Map<String, Integer> distancias = new HashMap<>();  // Para contar paradas
        Queue<String> queue = new LinkedList<>();           // Cola para BFS
        Map<String, String> predecesores = new HashMap<>(); // Para reconstruir ruta

        distancias.put(inicio, 0);  // La estación inicio tiene 0 paradas
        queue.add(inicio);

        while (!queue.isEmpty()) {
            String nodo = queue.poll();

            // Exploro vecinos del nodo actual
            for (Vecino vecino : adjList.getOrDefault(nodo, new ArrayList<>())) {
                // Si el vecino no ha sido visitado, lo agrego a la cola y registro su predecesor
                if (!distancias.containsKey(vecino.destino)) {
                    distancias.put(vecino.destino, distancias.get(nodo) + 1);
                    predecesores.put(vecino.destino, nodo);
                    queue.add(vecino.destino);
                    // Si llegué al destino, no necesito seguir explorando
                    if (vecino.destino.equals(destino)) {
                        break;
                    }
                }
            }
        }

        // Si encontré la ruta, imprimo el número de paradas y la ruta; si no, aviso que no hay ruta
        if (distancias.containsKey(destino)) {
            System.out.println("Número de paradas desde " + inicio + " a " + destino + ": " + distancias.get(destino));
            System.out.print("Ruta: ");
            imprimirRuta(predecesores, destino);
            System.out.println();
        } else {
            System.out.println("No hay ruta disponible.");
        }
    }

    // Método para mostrar todas las estaciones que están directamente conectadas a una dada
    public void estacionesCercanas(String estacion) {
        if (adjList.containsKey(estacion)) {
            System.out.println("Estaciones cercanas a " + estacion + ": ");
            for (Vecino vecino : adjList.get(estacion)) {
                System.out.println(vecino.destino);
            }
        } else {
            System.out.println("La estación no existe.");
        }
    }

    // Clase auxiliar para manejar nodos junto con su distancia para la cola de prioridad
    static class NodoDistancia {
        String nombre;
        int distancia;

        public NodoDistancia(String nombre, int distancia) {
            this.nombre = nombre;
            this.distancia = distancia;
        }
    }

    public static void main(String[] args) {
        Grafo8 grafo = new Grafo8();

        // Agrego rutas entre estaciones con sus respectivos tiempos
        grafo.agregarRuta("Estación A", "Estación B", 5);
        grafo.agregarRuta("Estación A", "Estación C", 8);
        grafo.agregarRuta("Estación B", "Estación D", 3);
        grafo.agregarRuta("Estación C", "Estación D", 2);
        grafo.agregarRuta("Estación D", "Estación E", 4);

        // Pruebo el algoritmo de Dijkstra para camino más corto en tiempo
        grafo.dijkstra("Estación A", "Estación E");
        // Pruebo BFS para la ruta con menos paradas
        grafo.bfs("Estación A", "Estación E");
        // Muestro estaciones directamente conectadas a "Estación A"
        grafo.estacionesCercanas("Estación A");
    }
}
