import java.util.Comparator;

public class CompareQtdEpisodios implements Comparator<Serie> {
    @Override
    public int compare(Serie o1, Serie o2) {
        return o1.getQuantidadeEpisodios().compareTo(o2.getQuantidadeEpisodios());
    }
}
