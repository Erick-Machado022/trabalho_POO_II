public interface Habilidade { 
    String getNome();

    int getCustoMana();

    Alvo getAlvo();

    String executar(Personagem conjurador, Personagem alvo); 
   default boolean podeUsar(Personagem conjurador) {
        return conjurador != null && conjurador.estaVivo() && conjurador.getManaAtual() >= getCustoMana();
    }



}
