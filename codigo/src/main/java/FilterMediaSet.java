import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterMediaSet<Media> {
    private Set<Media> items;

    public FilterMediaSet(Set<Media> items) {
        this.items = items;
    }

    public List<Media> filter(Predicate<Media> predicate) {
        return items.stream().filter(predicate).collect(Collectors.toList());
    }
}
