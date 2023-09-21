package net.quentin;

public class DLinkedList {
    private DNode header, trailer;
    private long size;

    public DLinkedList() {
        header = new DNode(null, null,null);
        trailer = new DNode(null, header, null);
        header.setNext(trailer);
        size = 0;
    }

    public void addFirst(DNode v) {
        v.setPrev(header);
        v.setNext(header.getNext());
        v.getNext().setPrev(v);
        header.setNext(v);
        this.size++;
    }

    public DLinkedList concat(DLinkedList M) {
        DLinkedList LM = this;
        DLinkedList Mp = M;
        Mp.getHeader().getNext().setPrev(LM.getTrailer().getPrev());
        LM.trailer.getPrev().setNext(Mp.header.getNext());
        LM.size += M.getSize();
        return LM;
    }

    public DNode getHeader() {
        return header;
    }

    public void setHeader(DNode header) {
        this.header = header;
    }

    public DNode getTrailer() {
        return trailer;
    }

    public void setTrailer(DNode trailer) {
        this.trailer = trailer;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(header.getElement());
        sb.append(" <-> ");
        DNode tmp = header;
        for (int i = 0; i < size; i++) {
            sb.append(tmp.getNext().getElement());
            sb.append(" <-> ");
            tmp = tmp.getNext();
        }
        sb.append(trailer.getElement());
        return sb.toString();
    }
}
