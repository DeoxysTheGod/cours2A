package net.quentin;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @org.junit.jupiter.api.Test
    void testEquals() {
        Book b1 = new Book("Harry Potta", "JK", "Personne ne sait", 150);
        Book b2 = new Book("Harry Potta", "JK", "Personne ne sait", 150);
        assertTrue(b1.equals(b2));
    }

    @org.junit.jupiter.api.Test
    void getTitle() {
        Book b1 = new Book("Harry Potta", "JK", "Personne ne sait", 150);
        assertEquals("Harry Potta", b1.getTitle());
    }

    @org.junit.jupiter.api.Test
    void setTitle() {
        Book b1 = new Book("Harry Potta", "JK", "Personne ne sait", 150);
        b1.setTitle("Harry Potdebeur");
        assertEquals("Harry Potdebeur", b1.getTitle());
    }

    @org.junit.jupiter.api.Test
    void getAuthor() {
        Book b1 = new Book("Harry Potta", "JK", "Personne ne sait", 150);
        assertEquals("JK", b1.getAuthor());
    }

    @org.junit.jupiter.api.Test
    void setAuthor() {
        Book b1 = new Book("Harry Potta", "JK", "Personne ne sait", 150);
        b1.setAuthor("JK Rolling Stone");
        assertEquals("JK Rolling Stone", b1.getAuthor());
    }

    @org.junit.jupiter.api.Test
    void getEditor() {
        Book b1 = new Book("Harry Potta", "JK", "Personne ne sait", 150);
        assertEquals("Personne ne sait", b1.getEditor());
    }

    @org.junit.jupiter.api.Test
    void setEditor() {
        Book b1 = new Book("Harry Potta", "JK", "Personne ne sait", 150);
        b1.setEditor("On sait tjrs pas");
        assertEquals("On sait tjrs pas", b1.getEditor());
    }

    @org.junit.jupiter.api.Test
    void getNbPage() {
        Book b1 = new Book("Harry Potta", "JK", "Personne ne sait", 150);
        assertEquals(150, b1.getNbPage());
    }

    @org.junit.jupiter.api.Test
    void setNbPage() {
        Book b1 = new Book("Harry Potta", "JK", "Personne ne sait", 150);
        b1.setNbPage(800);
        assertEquals(800, b1.getNbPage());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Book b1 = new Book("Harry Potta", "JK", "Personne ne sait", 150);
        assertEquals("Book{title='Harry Potta', author='JK', editor='Personne ne sait', nbPage=150}", b1.toString());
    }
}