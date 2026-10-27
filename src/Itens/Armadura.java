public class Armadura extends Item implements Equipavel {

    private int defesa;
    private Slot slot;

    public Armadura(String nome, int defesa, Slot slot) {
        super(nome);
        this.defesa = defesa;

        if (slot == Slot.CABECA || slot == Slot.CORPO) {
            this.slot = slot;
        } else {
            throw new IllegalArgumentException("Slot inválido para armadura. Deve ser CABECA ou CORPO.");
        }
    }

    public int getDefesa() {
        return defesa;
    }

    @Override
    public Equipavel comoEquipavel() {
        return this;
    }

    @Override
    public Slot getSlot() {
        return slot;
    }

    @Override
    public int bonusDeDefesa() {
        return defesa;
    }

    @Override
    public String descricao() {
        return getNome() + " (defesa: " + defesa + ")";
    }
}
