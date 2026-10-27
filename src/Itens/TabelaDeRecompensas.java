import java.util.List;
import java.util.Random;

public class TabelaDeRecompensas {
    private final List<Item> itens;
    private final Random random;

    public TabelaDeRecompensas(List<Item> itens, Random random) {
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("A lista de recompensas não pode estar vazia");
        }

        if (random == null) {
            throw new IllegalArgumentException("O Random não pode ser nulo");
        }

        this.itens = List.copyOf(itens);
        this.random = random;
    }

    public Item sortear() {
        int indice = random.nextInt(itens.size());
        return itens.get(indice);
    }
}
