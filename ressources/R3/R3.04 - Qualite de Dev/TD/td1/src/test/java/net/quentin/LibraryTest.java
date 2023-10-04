package net.quentin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LibraryTest {

    @Test
    void showBooks() throws Exception {
        Library l1 = new Library("Lib de détraqué", "jsp");
        l1.addBooks(new Book("Harry Potta", "JK Rolling", "jsp", 450),
                new Book("MHA", "Kohei Hoprikoshi", "Shueisha", 200));
        assertEquals("Book{title='Harry Potta', author='JK Rolling', editor='jsp', nbPage=450}\n" +
                "Book{title='MHA', author='Kohei Hoprikoshi', editor='Shueisha', nbPage=200}", l1.showBooks());
    }

    @Test
    void showBooksOfTwoLibraries() throws Exception {
        Library l1 = new Library("lib de fifou", "jsp");
        l1.addBooks(new Book("random", "pipou", "jsp", 150),
                new Book("Crot1", "KK", "jsp", 900));
        Library l2 = new Library("lib de fada", "jsp");
        l2.addBooks(new Book("L'aventure de pierrot", "Pierrot", "jsp", 450),
                new Book("India Jhon's", "Jhon Marston", "jsp", 487));

        assertEquals("Book{title='random', author='pipou', editor='jsp', nbPage=150}\n" +
                        "Book{title='Crot1', author='KK', editor='jsp', nbPage=900}\n" +
                        "Book{title='L'aventure de pierrot', author='Pierrot', editor='jsp', nbPage=450}\n" +
                        "Book{title='India Jhon's', author='Jhon Marston', editor='jsp', nbPage=487}",
                l1.showBooksOfLibraries(l2));
    }

    @Test
    void removeBookWhichExist() throws Exception {
        Library l1 = new Library("Lib de zinzolin", "jsp");
        l1.addBooks(new Book("Harry Potta", "JK Rolling", "jsp", 450),
                new Book("MHA", "Kohei Hoprikoshi", "Shueisha", 200));
        l1.removeBook(0);
        assertEquals("[Book{title='MHA', author='Kohei Hoprikoshi', editor='Shueisha', nbPage=200}]",
                l1.getBooks().toString());
    }

    @Test
    void removeBookWhichNotExist() throws Exception {
        Library l1 = new Library("Lib de zinzolin", "jsp");
        l1.addBooks(new Book("Harry Potta", "JK Rolling", "jsp", 450),
                new Book("MHA", "Kohei Hoprikoshi", "Shueisha", 200));
        Exception exception = assertThrows(Exception.class, () -> l1.removeBook(10));
        assertEquals("This index is not valid!", exception.getMessage());
    }

    @Test
    void removeDuplicates() throws Exception {
        Library l1 = new Library("Lib de dingue", "jsp");
        l1.addBooks(new Book("Harry Potta", "JK Rolling", "jsp", 450),
                new Book("MHA", "Kohei Hoprikoshi", "Shueisha", 200),
                new Book("Harry Potta", "JK Rolling", "jsp", 450),
                new Book("MHA", "Kohei Hoprikoshi", "Shueisha", 200));
        Library l2 = new Library("Lib de malade", "jsp");
        l2.addBooks(new Book("Harry Potta", "JK Rolling", "jsp", 450),
                new Book("MHA", "Kohei Hoprikoshi", "Shueisha", 200));;
        l1.removeDuplicate();
        assertEquals(l2.getBooks(), l1.getBooks());
    }

    @Test
    void addBook() throws Exception {
        Library l1 = new Library("Lib de foufou", "je sais pas");
        l1.addBooks(new Book("Harry Potta", "JK Rolling", "jsp", 450),
                new Book("MHA", "Kohei Hoprikoshi", "Shueisha", 200));
        assertEquals("[Book{title='Harry Potta', author='JK Rolling', editor='jsp', nbPage=450}, " +
                "Book{title='MHA', author='Kohei Hoprikoshi', editor='Shueisha', nbPage=200}]",
                l1.getBooks().toString());
    }

    @Test
    void addTooManyBookThrowAnException() throws Exception {
        Library l1 = new Library("Lib de zinzin", "jsp");
        for (int i = 0; i < l1.getMax()-1; i++) {
            l1.addBooks(new Book("Harry Potta", "JK", "Personne ne sait", 150));
        }
        Exception exception = assertThrows(Exception.class,
                () -> l1.addBooks(new Book("Harry Potta", "JK", "Personne ne sait", 150)));
        assertEquals("You have reached the limit! The limit is " + l1.getMAX_BOOKS(), exception.getMessage());
    }

    @Test
    void sortByAuthor() throws Exception {
        Library l1 = new Library("Lib de fandjo", "jsp");
        l1.addBooks(new Book("Harry Potta", "JK Rolling", "jsp", 450),
                new Book("MHA", "Kohei Hoprikoshi", "Shueisha", 200),
                new Book("L'aventure de pierrot", "Pierrot", "jsp", 450),
                new Book("India Jhon's", "Jhon Marston", "jsp", 487));
        l1.sortByAuthor();
        assertEquals("Book{title='Harry Potta', author='JK Rolling', editor='jsp', nbPage=450}\n" +
                "Book{title='India Jhon's', author='Jhon Marston', editor='jsp', nbPage=487}\n" +
                "Book{title='MHA', author='Kohei Hoprikoshi', editor='Shueisha', nbPage=200}\n" +
                "Book{title='L'aventure de pierrot', author='Pierrot', editor='jsp', nbPage=450}",l1.showBooks());
    }
}