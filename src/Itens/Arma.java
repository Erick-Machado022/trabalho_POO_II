public class Arma extends Item implements Equipavel {

    private int dano;

    public Arma(String nome, int dano) {

        super(nome);
        this.dano = dano;

    }

    @Override
    public Equipavel comoEquipavel() {
        return this;
    }

    // porque usamos o Override? Porque estamos sobrescrevendo o método da interface
    // Equipavel, que é um contrato que a classe Arma está assumindo. Isso significa
    // que a classe Arma deve fornecer uma implementação para o método getSlot()
    // definido na interface Equipavel.

    @Override
    public Slot getSlot() {
        return Slot.MAO;
    }

    @Override
    public int bonusDeDano() {
        return dano;
    }

    @Override
    public String descricao() {
        return getNome() + " (dano: " + dano + ")";
    }

}
