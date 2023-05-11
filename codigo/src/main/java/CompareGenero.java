import java.util.Comparator;

public class CompareGenero implements Comparator<Media> {
    /**
     * Compara duas instâncias de Media com base no seu gênero.
     *
     * @param o1 a primeira instância de Media a ser comparada
     * @param o2 a segunda instância de Media a ser comparada
     * @return um valor inteiro negativo, zero ou positivo, se o gênero da primeira instância for menor, igual ou maior que o gênero da segunda instância, respectivamente.
     */
    @Override
    public int compare(Media o1, Media o2) {
        return o1.getGenero().compareTo(o2.getGenero());
    }
}
