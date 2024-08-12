import java.util.List;

class Book {
    private String title;
    private int publicationYear;
    private int pages;
    private List<Author> authors;

    public Book(String title, int publicationYear, int pages, List<Author> authors) {
        this.title = title;
        this.publicationYear = publicationYear;
        this.pages = pages;
        this.authors = authors;
    }

    public String getTitle() { return title; }
    public int getPublicationYear() { return publicationYear; }
    public int getPages() { return pages; }
    public List<Author> getAuthors() { return authors; }

    @Override
    public String toString() {
        return title + " (" + publicationYear + "), " + pages + " Seiten, Autoren: " + authors;
    }
}