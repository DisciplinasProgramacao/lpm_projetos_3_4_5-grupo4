import java.util.Comparator;

public class CompareQtdEpisodios implements Comparator<Serie> {
    /**
     * Compara duas séries com base em sua quantidade de episódios.
     * @param o1 primeira série a ser comparada
     * @param o2 segunda série a ser comparada
     * @return um inteiro negativo se o número de episódios da primeira série for menor que o da segunda,
     * um inteiro positivo se for maior, e zero se forem iguais.
     */
    @Override
    public int compare(Serie o1, Serie o2) {
        return o1.getQuantidadeEpisodios().compareTo(o2.getQuantidadeEpisodios());
    }
}
