public abstract class Item { // classe é abstrata pois ela vai servir como base para outros itens
    private String nome;

    public Item(String nome) {

        this.nome = nome;

    }

    public String getNome() {
        return nome;
    }

    public abstract String descricao();

    public boolean recuperaVida() {
        return false;
    }

    public boolean recuperaMana() {
        return false;
    }

    public Equipavel comoEquipavel() {
        return null;
    }

}

