class Node {
    String key;
    String value;
    Node next;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

class HashTable {
    private Node[] table;
    private int capacity;

    public HashTable() {
        this.capacity = 10;
        this.table = new Node[capacity];
    }

    private int hashFunction(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void insert(String key, String value) {
        int index = hashFunction(key);
        Node newNode = new Node(key, value);

        if (table[index] == null) {
            table[index] = newNode;
        } else {
            Node current = table[index];
            Node prev = null;

            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                prev = current;
                current = current.next;
            }
            prev.next = newNode;
        }
    }

    public String search(String key) {
        int index = hashFunction(key);
        Node current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return "Tidak ditemukan";
    }

    public void remove(String key) {
        int index = hashFunction(key);
        Node current = table[index];
        Node prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                System.out.println("Kunci '" + key + "' berhasil dihapus.");
                return;
            }
            prev = current;
            current = current.next;
        }
        System.out.println("Kunci '" + key + "' tidak ditemukan untuk dihapus.");
    }

    public void display() {
        System.out.println("\n=== ISI HASH TABLE ===");
        for (int i = 0; i < capacity; i++) {
            System.out.print("Indeks " + i + ": ");
            Node current = table[i];
            
            if (current == null) {
                System.out.print("null");
            } else {
                while (current != null) {
                    System.out.print("[" + current.key + " -> " + current.value + "]");
                    if (current.next != null) {
                        System.out.print(" -> ");
                    }
                    current = current.next;
                }
            }
            System.out.println();
        }
        System.out.println("======================");
    }
}

// Class ini HARUS public dan nama file WAJIB Main.java
public class Main {
    public static void main(String[] args) {
        HashTable myHashTable = new HashTable();

        myHashTable.insert("ID01", "Budi");
        myHashTable.insert("ID02", "Siti");
        myHashTable.insert("ID12", "Andi"); // Menguji tabrakan (chaining)
        myHashTable.insert("ID03", "Roni");
        myHashTable.insert("ID22", "Dewi"); // Menguji tabrakan (chaining)

        myHashTable.display();

        System.out.println("\nPencarian Kunci:");
        System.out.println("Cari ID02: " + myHashTable.search("ID02"));
        System.out.println("Cari ID99: " + myHashTable.search("ID99"));

        System.out.println("\nPenghapusan Kunci:");
        myHashTable.remove("ID12");
        
        myHashTable.display();
    }
}