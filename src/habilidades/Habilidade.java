public interface Habilidade { // o que isso é? criamos essa interface para que todas as habilidades tenham os mesmos métodos, e assim possamos criar habilidades diferentes para cada classe de personagem, mas com a mesma estrutura de métodos.
    String getNome();

    int getCustoMana();

    Alvo getAlvo();

    String executar(Personagem conjurador, Personagem alvo); //Personagem conjurador representa quem usa a habilidade, enquanto Combatente alvo permite que o alvo seja qualquer combatente, inclusive um monstro.
   default boolean podeUsar(Personagem conjurador) {
        return conjurador != null && conjurador.estaVivo() && conjurador.getManaAtual() >= getCustoMana();
    }



}
