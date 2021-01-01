package ro.ase.proiect_draft.data;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Repository<Content extends Indexable> {

    ArrayList<Content> objects;

    public void add(Content ct) {
        objects.add(ct);
        makeSqlQuery(ct.getInsertQuery());
    }

    public Content get(String id) {
        return objects.stream().filter(o -> o.getId().equals(id)).findAny().orElse(null);
    }

    public void delete(String id) {
        Content content = get(id);
        if (content != null) {
            objects.removeIf(ct -> ct.getId().equals(id));
            makeSqlQuery(content.getDeleteQuery());
        }
    }

    public void change(String id, Consumer<Content> consumer) {
        Content content = get(id);
        if (content != null) {
            consumer.accept(content);
            delete(id);
            add(content);
        }
    }

    public Stream<Content> stream() {
        return objects.stream();
    }

    private void makeSqlQuery(String query) {

    }

}

