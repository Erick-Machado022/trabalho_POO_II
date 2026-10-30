public interface Combatente {

    
    String getNome();
    int getVida();
    int getDefesa();

    int atacar(Combatente alvo);

    int receberDano(int dano);

    boolean estaVivo();
}