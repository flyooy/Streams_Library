import java.text.SimpleDateFormat;
import java.util.Date;

class Loan {
    private Book book;
    private Date startDate;
    private Date endDate;

    public Loan(Book book, Date startDate, Date endDate) {
        this.book = book;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Book getBook() { return book; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return book.getTitle() + " ausgeliehen von " + sdf.format(startDate) + " bis " + sdf.format(endDate);
    }
}