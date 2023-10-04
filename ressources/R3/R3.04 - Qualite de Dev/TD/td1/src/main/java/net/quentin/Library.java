package net.quentin;

import java.util.ArrayList;
import java.util.Comparator;

public class Library {
    private final int MAX_BOOKS = 50;

    private String name;
    private String address;
    private int max;
    private ArrayList<Book> books;

    public Library(String name, String address) {
        this.name = name;
        this.address = address;
        this.max = MAX_BOOKS;
        this.books = new ArrayList<>();
    }

    public int getMAX_BOOKS() {
        return MAX_BOOKS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public String showBooks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getBooks().size(); i++) {
            sb.append(getBooks().get(i).toString());
            if (i < getBooks().size()-1) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    public String showBooksOfLibraries(Library lib) {
        StringBuilder sb = new StringBuilder(showBooks());
        sb.append('\n');
        sb.append(lib.showBooks());

        return sb.toString();
    }

    public void removeBook(int index) throws Exception {
        try {
            getBooks().remove(index);
        } catch (Exception e) {
            throw new Exception("This index is not valid!");
        }
    }

    public void removeDuplicate() {
        ArrayList<Book> tmp = new ArrayList<>();
        for (Book book : getBooks()) {
            if (!tmp.contains(book)) {
                tmp.add(book);
            }
        }
        setBooks(tmp);
    }

    public void addBook(Book book) throws Exception {
        if (getBooks().size() < MAX_BOOKS-1) {
            getBooks().add(book);
        } else {
            throw new Exception("You have reached the limit! The limit is " + MAX_BOOKS);
        }
    }

    public void addBooks(Book... books) throws Exception {
        for (Book book : books) {
            if (getBooks().size() < MAX_BOOKS - 1) {
                getBooks().add(book);
            } else {
                throw new Exception("You have reached the limit! The limit is " + MAX_BOOKS);
            }
        }
    }

    public void sortByAuthor() {
        getBooks().sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getAuthor().compareTo(o2.getAuthor());
            }
        });
    }

    @Override
    public String toString() {
        return "Librairy{" +
                "MAX_BOOKS=" + MAX_BOOKS +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", max=" + max +
                ", books=" + books +
                '}';
    }
}
