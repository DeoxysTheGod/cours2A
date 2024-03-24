package net.uzaki.reservation;

public class Reservation {
    protected String id;
    protected String reference;
    protected String date;

    public Reservation(){}

    public Reservation(String id, String reference, String date){
        this.id = id;
        this.reference = reference;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString(){
        return "Reservation{" +
                "id='" + id + '\'' +
                ", reference='" + reference + '\'' +
                ", date='" + date + '\'' +
                "}";
    }
}
