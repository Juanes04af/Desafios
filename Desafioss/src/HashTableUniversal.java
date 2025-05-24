import java.util.*;

class HashTableUniversal {
    private String[] table; // arreglo donde se guardan las claves
    private Random rand = new Random(); // generador de números aleatorios
    private int a, b; // coeficientes aleatorios usados en la función hash

    public HashTableUniversal(int size) {
        this.table = new String[size];

        // Se generan dos constantes aleatorias que hacen que la función hash varíe cada vez
        this.a = rand.nextInt(size - 1) + 1; // 'a' debe ser positivo y diferente de 0
        this.b = rand.nextInt(size);        // 'b' puede ser 0

        // Con estos valores se implementa una forma de hash universal
    }

    // Función hash universal: más flexible y menos predecible que hashCode()
    public int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            // Se aplica una fórmula polinómica con los coeficientes aleatorios
            hash = (a * hash + key.charAt(i)) + b;
        }
        return Math.abs(hash) % table.length; // se asegura que el índice esté dentro del arreglo
    }

    public void insert(String key) {
        int index = hash(key);

        // Si la posición ya está ocupada, se informa de la colisión
        if (table[index] != null) {
            System.out.println("Colisión detectada.");
        } else {
            table[index] = key; // si no hay colisión, se guarda la clave
        }
    }

    public String search(String key) {
        int index = hash(key);
        return table[index]; // retorna el valor almacenado en la posición (puede ser null si no está)
    }

    public static void main(String[] args) {
        HashTableUniversal ht = new HashTableUniversal(10); // se crea la tabla con 10 posiciones

        // Se insertan claves distintas (cambios mínimos como mayúsculas influyen en el índice)
        ht.insert("Estudiante1");
        ht.insert("Estudiante2");
        ht.insert("estudiante1"); // aunque parece similar, puede caer en otro índice

        // Se busca una de las claves
        System.out.println(ht.search("Estudiante1")); // imprime la clave si no hubo colisión
    }
}
