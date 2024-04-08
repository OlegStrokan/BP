class Node {
    data: number;
    next: Node | null;
    constructor(data: number) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    head: Node | null;
    constructor() {
        this.head = null;
    }

    append(data: number): void {
        const new_node = new Node(data);
        if (!this.head) {
            this.head = new_node;
            return;
        }
        let last_node = this.head;
        while (last_node.next) {
            last_node = last_node.next;
        }
        last_node.next = new_node;
    }

    display(): void {
        let current = this.head;
        while (current) {
            process.stdout.write(`${current.data} -> `);
            current = current.next;
        }
        console.log("None");
    }
}

const linkedList = new LinkedList();
linkedList.append(1);
linkedList.append(2);
linkedList.append(3);
linkedList.display();

