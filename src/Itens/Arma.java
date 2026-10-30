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
