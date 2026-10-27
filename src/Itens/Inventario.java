import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Item> itens; // para que serve o List<Item> itens? para armazenar os itens do inventário, que
                              // são do tipo Item, que é a classe pai de todos os itens do jogo.
    private final int capacidadeMaxima;

    public List<Item> getItens() {
        return List.copyOf(itens); // retorna uma cópia da lista de itens, para que não seja possível modificar a
                                   // lista original de fora da classe Inventario.
    }

    public Inventario(int capacidadeMaxima) {

        if (capacidadeMaxima <= 0) {
            throw new IllegalArgumentException("Capacidade máxima deve ser maior que zero.");
        }
        this.itens = new ArrayList<>();
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public boolean adicionarItem(Item item) {

        if (item == null) {
            throw new IllegalArgumentException("O item não pode ser nulo");
        }

        if (itens.size() < capacidadeMaxima) {
            itens.add(item);
            return true;
        } else {
            return false;
        }
    }

    public String listarItens() {
        String resultado = "=== INVENTÁRIO ===\n";

        for (int i = 0; i < itens.size(); i++) {
            resultado += (i + 1) + ": " + itens.get(i).getNome() + "\n";
        }

        return resultado;
    }

    public int getQuantidadeDeItens() {
        return itens.size();
    }

    public boolean removerItem(Item item) {
        return itens.remove(item);
    }

    public boolean temPocaoDeVida(){
        for(Item item : itens){
            if (item.recuperaVida()) {
                return true;
            }
        }

        return false;
    }

    public boolean temPocaoDeMana(){
        for(Item item : itens){
            if (item.recuperaMana()) {
                return true;
            }
        }

        return false;
    }

    public boolean usarPocaoDeVida(Personagem alvo){
        for (Item item : itens) {
            if (item.recuperaVida()) {
                ((Usavel) item).usar(alvo);
                itens.remove(item);
                return true;
            }
        }

        return false;
    }

    public boolean usarPocaoDeMana(Personagem alvo){
        for (Item item : itens) {
            if(item.recuperaMana()){
                ((Usavel) item).usar(alvo);
                itens.remove(item);
                return true;
            }

        }

        return false;
    }

}
