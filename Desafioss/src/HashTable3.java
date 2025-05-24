class HashTable3 {
    private String[] table;

    public HashTable3(int size) {
        this.table = new String[size];
    }

    public int hash(String key) {
        return key.hashCode() % table.length;
    }

    public void insert(String key) {
        int index = hash(key);
        if (table[index] != null) {
            System.out.println("Colisión detectada, el hash no es perfecto para esta clave.");
        } else {
            table[index] = key;
        }
    }

    public String search(String key) {
        int index = hash(key);
        return table[index];
    }

    public static void main(String[] args) {
        HashTable3 ht = new HashTable3(10);
        ht.insert("Producto123");
        ht.insert("Producto456");
        System.out.println(ht.search("Producto123"));
    }
}