class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    LinkedList() {
        this.head = null;
    }

    void append(int data) {
        Node new_node = new Node(data);
        if (this.head == null) {
            this.head = new_node;
            return;
        }
        Node last_node = this.head;
        while (last_node.next != null) {
            last_node = last_node.next;
        }
        last_node.next = new_node;
    }

    void display() {
        Node current = this.head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("None");
    }
}

public class Main {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.append(1);
        linkedList.append(2);
        linkedList.append(3);
        linkedList.display();
    }
}

