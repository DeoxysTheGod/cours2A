package net.quentin;

public class SLinkedList {
    private SNode head;
    private long size;

    public SLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void addFirst(SNode v) {
        v.setNext(head);
        this.head = v;
        this.size++;
    }

    public void addLast(SNode v) {
        v.setNext(null);
        getTail().setNext(v);
        this.size++;
    }

    public SNode getHead() {
        return head;
    }

    public SNode getTail() {
        return getTail(this.head);
    }

    private SNode getTail(SNode v) {
        if (v.getNext() == null) {
            return v;
        }
        return getTail(v.getNext());
    }

    public void reverse() {
        SNode current = head;
        SNode prev = null, next = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        head = prev;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        SNode tmp = head;
        for (int i = 0; i < size-1; i++) {
            sb.append(tmp.getElement());
            sb.append(" - ");
            tmp = tmp.getNext();
        }
        sb.append(getTail().getElement());
        return sb.toString();
    }
}
