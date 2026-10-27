public class Cura implements Habilidade {
    private String nome;
    private int custoMana;
    private int quantidade;

    public Cura(String nome, int custoMana, int quantidade) {
        this.nome = nome;
        this.custoMana = custoMana;
        this.quantidade = quantidade;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getCustoMana() {
        return custoMana;
    }

    @Override
    public Alvo getAlvo() {
        return Alvo.SI_MESMO;
    }

    @Override
    public String executar(Personagem conjurador, Personagem alvo) {

        if (!conjurador.getHabilidades().contains(this)) {
            throw new IllegalArgumentException(conjurador.getNome() + " não possui a habilidade " + nome + ".");
        }

        if (!podeUsar(conjurador)) {
            return conjurador.getNome() + " não possui mana suficiente para usar " + nome + "!";
        }

        conjurador.gastarMana(custoMana);

        int cura = quantidade + conjurador.getMagiaTotal();

        alvo.recuperarVida(cura);

        return conjurador.getNome() + " usou " + nome + " e recuperou " + cura + " pontos de vida";
    }

}
