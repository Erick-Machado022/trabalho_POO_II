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
/*
 * Por que dessa forma?
 * - private String nome: protege o estado interno do item.
 * - Item(String nome): permite informar o nome ao criar um item concreto.
 * - getNome(): permite consultar o nome sem liberar alteração direta do
 * atributo.
 * - abstract class: indica que Item é uma base para outros tipos de item, e não
 * algo que precisamos instanciar diretamente.
 */
