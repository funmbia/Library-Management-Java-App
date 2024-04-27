package iterator;

import java.util.Iterator;

public class SearchIterator implements Iterator<Book> {
    private Iterator<Book> iterator;

    public SearchIterator(BookCollection bookCollection) {
        this.iterator = bookCollection.iterator();
    }

    @Override
    public Book next() {
        if (!hasNext()) {
            return null;
        }
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
}
