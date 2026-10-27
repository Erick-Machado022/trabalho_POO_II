public class LutadorFisico extends Personagem {

    public LutadorFisico(String nome, int vidaMaxima, int manaMaxima, Atributos atributos, Inventario inventario) {
        super(nome, vidaMaxima, manaMaxima, atributos, inventario);

        for (int i = 0; i < 3; i++) {
            getInventario().adicionarItem(new PocaoVida("Poção de Vida"));
        }

         for (int i = 0; i < 2; i++) {
            getInventario().adicionarItem(new PocaoMana("Poção de Mana"));
        }

    }
}
