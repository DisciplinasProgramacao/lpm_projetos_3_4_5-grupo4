import java.util.Comparator;

public class CompareIdioma implements Comparator<Media> {
    @Override
    public int compare(Media o1, Media o2) {
        return o1.getIdioma().compareTo(o2.getIdioma());
    }
}
