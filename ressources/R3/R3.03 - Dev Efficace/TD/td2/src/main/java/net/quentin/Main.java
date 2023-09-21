package net.quentin;

public class Main {
    public static void main(String[] args) {
        /*
        SLinkedList linkedList = new SLinkedList();
        linkedList.addFirst(new SNode(2, null));
        linkedList.addFirst(new SNode(8, null));
        linkedList.addFirst(new SNode(12, null));
        linkedList.addLast(new SNode(16,null));

        System.out.println(linkedList);

        linkedList.reverse();

        System.out.println(linkedList);
        */
        DLinkedList L = new DLinkedList();
        L.addFirst(new DNode("salut",null, null));
        L.addFirst(new DNode("test", null, null));
        DLinkedList M = new DLinkedList();
        M.addFirst(new DNode("jsp",null, null));
        M.addFirst(new DNode("arbre", null, null));

        DLinkedList LM = L.concat(M);

        System.out.println(LM);
        System.out.println(LM.getHeader().getNext().getNext().getNext().getElement());
    }
}
