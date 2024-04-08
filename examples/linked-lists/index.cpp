#include <iostream>

struct Node {
    int data;
    Node* next;
    Node(int val) : data(val), next(nullptr) {}
};

class LinkedList {
private:
    Node* head;
public:
    LinkedList() : head(nullptr) {}
    void append(int data) {
        Node* new_node = new Node(data);
        if (!head) {
            head = new_node;
            return;
        }
        Node* last_node = head;
        while (last_node->next) {
            last_node = last_node->next;
        }
        last_node->next = new_node;
    }
    void display() {
        Node* current = head;
        while (current) {
            std::cout << current->data << " -> ";
            current = current->next;
        }
        std::cout << "None" << std::endl;
    }
};

int main() {
    LinkedList linked_list;
    linked_list.append(1);
    linked_list.append(2);
    linked_list.append(3);
    linked_list.display();
    return 0;
}

