import java.util.Random;

public enum Generos {
    Acao,
    Anime,
    Aventura,
    Comédia,
    Documentário,
    Drama,
    Policial,
    Romance,
    Suspense;

    private static final Random PRNG = new Random();

    /**
     * Gerador de genero aleatorio
     * 
     * @return Genero - Genero aleatoriamente selecionado
     * 
     */
    public static Generos randomGeneros() {
        Generos[] generoSortiado = values();
        return generoSortiado[PRNG.nextInt(9)];
    }
}
