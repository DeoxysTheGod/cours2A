package net.quentin;

import java.util.Objects;

public class Book {

    private String title;
    private String author;
    private String editor;
    private int nbPage;

    public Book(String title, String author, String editor, int nbPage) {
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.nbPage = nbPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return nbPage == book.nbPage && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(editor, book.editor);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public int getNbPage() {
        return nbPage;
    }

    public void setNbPage(int nbPage) {
        this.nbPage = nbPage;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", editor='" + editor + '\'' +
                ", nbPage=" + nbPage +
                '}';
    }
}
