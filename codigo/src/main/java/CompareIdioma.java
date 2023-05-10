import java.util.Comparator;

public class CompareIdioma implements Comparator<Media> {
    /**
     * Compara duas instâncias de Media com base no seu idioma.
     *
     * @param o1 a primeira instância de Media a ser comparada
     * @param o2 a segunda instância de Media a ser comparada
     * @return um valor inteiro negativo, zero ou positivo, se o idioma da primeira instância for menor, igual ou maior que o idioma da segunda instância, respectivamente.
     */
    @Override
    public int compare(Media o1, Media o2) {
        return o1.getIdioma().compareTo(o2.getIdioma());
    }
}
