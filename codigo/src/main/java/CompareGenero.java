import java.util.Comparator;

public class CompareGenero implements Comparator<Media> {
    @Override
    public int compare(Media o1, Media o2) {
        return o1.getGenero().compareTo(o2.getGenero());
    }
}
