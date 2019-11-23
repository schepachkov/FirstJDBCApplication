package sample.myClass;

public class Book {
    private String author;
    private String nameBook;
    private String godIzd;
    private int kolvo;

    public Book(String author, String nameBook, String godIzd, int kolvo) {
        this.author = author;
        this.nameBook = nameBook;
        this.godIzd = godIzd;
        this.kolvo = kolvo;
    }

    public Book(String bookName) {
        nameBook = bookName;
    }

    public Book(String bookName, String bookGodIzd) {
        nameBook = bookName;
        godIzd = bookGodIzd;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getGodIzd() {
        return godIzd;
    }

    public void setGodIzd(String godIzd) {
        this.godIzd = godIzd;
    }

    public int getKolvo() {
        return kolvo;
    }

    public void setKolvo(int kolvo) {
        this.kolvo = kolvo;
    }
}
