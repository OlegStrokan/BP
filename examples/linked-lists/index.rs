struct Node {
    data: i32,
    next: Option<Box<Node>>,
}

impl Node {
    fn new(data: i32) -> Node {
        Node { data, next: None }
    }
}

struct LinkedList {
    head: Option<Box<Node>>,
}

impl LinkedList {
    fn new() -> LinkedList {
        LinkedList { head: None }
    }

    fn append(&mut self, data: i32) {
        let new_node = Box::new(Node::new(data));
        let mut current_node = &mut self.head;
        while let Some(ref mut node) = current_node {
            current_node = &mut node.next;
        }
        *current_node = Some(new_node);
    }

    fn display(&self) {
        let mut current_node = &self.head;
        while let Some(node) = current_node {
            print!("{} -> ", node.data);
            current_node = &node.next;
        }
        println!("None");
    }
}

fn main() {
    let mut linked_list = LinkedList::new();
    linked_list.append(1);
    linked_list.append(2);
    linked_list.append(3);
    linked_list.display();
}
