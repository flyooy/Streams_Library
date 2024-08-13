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
        return books.stream()
                .flatMap(book->book.getAuthors().stream().map(
                        author -> new AbstractMap.SimpleEntry<>(author,book)))
                .collect(Collectors.groupingBy(Map.Entry::getKey,Collectors.mapping(Map.Entry::getValue,Collectors.toList())));


    }

    // 5. Übung
    public Optional<Author> getOldestAuthor() {
        return books.stream()
                .flatMap(book->book.getAuthors().stream())
                .distinct()
                .sorted(Comparator.comparing(Author::getBirthYear))
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
        return  loans.stream()
                .map(Loan::getBook)
                .collect(Collectors.groupingBy(book-> book, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Book, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .limit(2)
                .toList();

    }

    // 8. Übung
    public Map<Book, Long> getLoanCountPerBook() {
        return loans.stream()
                .map(Loan::getBook)
                .collect(Collectors.groupingBy(book-> book, Collectors.counting()));
    }

    // 9. Übung
    public List<Book> getBooksLoanedByAuthor(Author author) {
        return loans.stream()
                .filter(l->l.getBook().getAuthors().contains(author))
                        .map(Loan::getBook)
                        .distinct()
                        .collect(Collectors.toList());
    }

    // 10. Übung
    public double getAverageLoanDuration() {

        return loans.stream()
                .mapToInt(loan -> (int) ChronoUnit.DAYS.between(loan.getStartDate().toInstant(), loan.getEndDate().toInstant()) )
                .average()
                .getAsDouble();
    }

    // Bonus: 11. Übung
    public String getMostPopularAuthorByBorrowedBooksCount() {
        return loans.stream()
                .flatMap(l->l.getBook().getAuthors().stream())
                .collect(Collectors.groupingBy(a->a.getName(),Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

}