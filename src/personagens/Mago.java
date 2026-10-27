public class Mago extends Personagem {

    public Mago(String nome, Inventario inventario) {
        super(nome, 60, 30, new Atributos(2, 2, 9), inventario);

        
        getInventario().adicionarItem(new PocaoVida("Pocao de vida"));
        getInventario().adicionarItem(new PocaoMana("Pocao de mana"));
        getInventario().adicionarItem(new PocaoMana("Pocao de mana"));

        
        
        adicionarHabilidade(new Magia("Bola de fogo", 8, 15));

        adicionarHabilidade(new Cura("Cura", 6, 20));

    }

    @Override
    public int atacar(Combatente alvo) {

        if (alvo == null) {
            throw new IllegalArgumentException("O alvo não pode ser nulo.");
        }

        int magia = getMagiaTotal();
        int defesaAlvo = alvo.getDefesa();

        int dano = magia - defesaAlvo;

        if (dano < 1) {
            dano = 1;
        }

        return alvo.receberDano(dano);
    }

}