import java.util.LinkedList; // se importa LinkedList para manejar listas enlazadas dentro de cada posición de la tabla

class HashTable {

    // Clase interna que representa cada elemento de la tabla con una clave y un valor
    static class Node {
        int key;
        String value;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Node>[] table; // arreglo de listas enlazadas donde se almacenan los nodos
    private int size; // tamaño de la tabla

    public HashTable(int size) {
        this.size = size;
        table = new LinkedList[size]; // se inicializa el arreglo
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>(); // se crea una lista vacía en cada posición
        }
    }

    // Función hash que calcula la posición en la tabla a partir de la clave
    private int hash(int key) {
        return key % size;
    }

    // Inserta un nuevo nodo en la tabla usando la clave y el valor
    public void insert(int key, String value) {
        int index = hash(key);
        table[index].add(new Node(key, value)); // se añade el nodo a la lista correspondiente
    }

    // Busca un nodo por su clave y devuelve su valor si lo encuentra
    public String search(int key) {
        int index = hash(key);
        for (Node node : table[index]) {
            if (node.key == key) {
                return node.value;
            }
        }
        return null; // si no se encuentra la clave, se devuelve null
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10); // se crea una tabla hash con 10 posiciones
        hashTable.insert(101, "Producto A");
        hashTable.insert(102, "Producto B");

        System.out.println(hashTable.search(101));  // imprime "Producto A" si la clave existe
    }
}
