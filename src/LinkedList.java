public class LinkedList {
    Node head;

    class Node<E>{
        E data;
        Node<E> next;

        Node(E data){
            this.data = data;
        }
    }
}
