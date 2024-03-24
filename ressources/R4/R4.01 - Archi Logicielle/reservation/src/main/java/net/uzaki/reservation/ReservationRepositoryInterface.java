package net.uzaki.reservation;

import java.util.*;

public interface ReservationRepositoryInterface {
    public void close();
    public Reservation getReservation(String reference);
    public ArrayList<Reservation> getAllReservations();

    boolean registerReservation(String id, String reference);

    boolean releaseReservation(String reference);
}
