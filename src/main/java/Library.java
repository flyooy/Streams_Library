import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

class Library {
    private List<Book> books = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void loanBook(Book book, Date startDate, Date endDate) {
        loans.add(new Loan(book, startDate, endDate));
    }

    // 1. Übung
    public List<Book> getBooksSortedByPublicationYear() {
        return books.stream()
                .sorted(Comparator.comparing(Book::getPublicationYear))
                .collect(Collectors.toList());
    }

    // 2. Übung
    public List<Author> getAuthorsSortedByBirthYear() {
        return books.stream()
                .flatMap(book->book.getAuthors().stream())
                .distinct()
                .sorted(Comparator.comparing(Author::getBirthYear))
                .collect(Collectors.toList());
    }

    // 3. Übung
    public List<Book> filterBooksByPageCount(int minPages, int maxPages) {
        return books.stream()
                .filter(b-> minPages<=b.getPages()&&maxPages>=b.getPages())
                .collect(Collectors.toList());
    }

    // 4. Übung
    public Map<Author, List<Book>> groupBooksByAuthor() {
        return null;

    }

    // 5. Übung
    public Optional<Author> getOldestAuthor() {
        return books.stream()
                .flatMap(book->book.getAuthors().stream())
                .distinct()
                .sorted(Comparator.comparing(Author::getBirthYear).reversed())
                .findFirst();
    }

    // 6. Übung
    public List<Book> getBooksByTitleKeyword(String keyword) {
        return books.stream()
                .filter(b->b.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // 7. Übung
    public List<Book> getMostLoanedBooks() {
        return  null;//loans.stream();
//                .map(loan->loan.getBook())
//                .max(Comparator.comparing(book -> book.))

    }

    // 8. Übung
    public Map<Book, Long> getLoanCountPerBook() {
        return null;
    }

    // 9. Übung
    public List<Book> getBooksLoanedByAuthor(Author author) {
        return null;
    }

    // 10. Übung
    public double getAverageLoanDuration() {

        return loans.stream()
                .mapToInt(loan -> (int) ChronoUnit.DAYS.between(loan.getStartDate().toInstant(), loan.getEndDate().toInstant()) )
                .average()
                .getAsDouble();
    }

    // Bonus: 11. Übung
    public String getMostPopularAuthorByBorrowedBooksCount() { return null;}

}