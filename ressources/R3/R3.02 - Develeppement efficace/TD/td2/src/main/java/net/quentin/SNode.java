package net.quentin;

public class SNode {
    private int element;
    private SNode next;

    public SNode(int element, SNode next) {
        this.element = element;
        this.next = next;
    }

    public int getElement() {
        return element;
    }
    public void setElement(int element) {
        this.element = element;
    }
    public SNode getNext() {
        return next;
    }
    public void setNext(SNode next) {
        this.next = next;
    }
}