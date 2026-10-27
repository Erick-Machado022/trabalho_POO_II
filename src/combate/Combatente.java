public interface Combatente {

    //o que cada classe faz: getNome() retorna o nome do combatente, getVida() retorna a vida atual do combatente, getDefesa() retorna a defesa do combatente, atacar(combatente alvo) realiza um ataque no combatente alvo, receberDano(int dano) aplica o dano recebido ao combatente, e estaVivo() verifica se o combatente ainda está vivo (vida maior que zero).

    String getNome();
    int getVida();
    int getDefesa();

    int atacar(Combatente alvo);

    int receberDano(int dano);

    boolean estaVivo();
}