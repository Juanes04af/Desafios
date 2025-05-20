class HashTable2 {
    // Clase que representa una entrada con clave y valor
    static class Entry {
        int key;
        String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] table; // arreglo de entradas (cada una con clave y valor)
    private int size;

    public HashTable2(int size) {
        this.size = size;
        table = new Entry[size]; // se crea la tabla con las posiciones vacías
    }

    private int hash(int key) {
        return key % size; // función hash sencilla basada en módulo
    }

    public void insert(int key, String value) {
        int index = hash(key);

        // se aplica sonda lineal en caso de colisión
        while (table[index] != null) {
            if (table[index].key == key) {
                table[index].value = value; // si la clave ya existe, se actualiza el valor
                return;
            }
            index = (index + 1) % size;
        }

        table[index] = new Entry(key, value); // se guarda la nueva entrada
    }

    public String search(int key) {
        int index = hash(key);

        // se recorre la tabla desde el índice hasta encontrar la clave o una posición vacía
        while (table[index] != null) {
            if (table[index].key == key) {
                return table[index].value; // si se encuentra la clave, se devuelve el valor
            }
            index = (index + 1) % size;
        }

        return null; // si no se encuentra la clave
    }

    public static void main(String[] args) {
        HashTable2 hashTable2 = new HashTable2(10);
        hashTable2.insert(101, "Juan");
        hashTable2.insert(102, "Ana");
        System.out.println(hashTable2.search(101)); // imprime "Juan"
        hashTable2.insert(101, "andres");
        System.out.println(hashTable2.search(101)); // imprime "andres" (actualiza el valor)
        System.out.println(hashTable2.search(102)); // imprime "Ana"
        System.out.println(hashTable2.search(103)); // imprime null (no existe)
        System.out.println(hashTable2.search(105)); // imprime null (no existe)
    }
}
