public class FacadaDupla implements Habilidade {
    
    private final AtaqueFisico ataque;

    public FacadaDupla(){
        this.ataque = new AtaqueFisico("Facada Dupla", 5, 2.0);
    }

    @Override 
    public String getNome(){
        return ataque.getNome();
    }

    @Override
    public int getCustoMana(){
        return ataque.getCustoMana();
    }

    @Override 
    public Alvo getAlvo(){
        return ataque.getAlvo();
    }

     @Override
    public boolean podeUsar(Personagem conjurador) {
        return ataque.podeUsar(conjurador);
    }

    @Override 
    public String executar(Personagem conjurador, Personagem alvo){
        return ataque.executar(conjurador, alvo);
    }

    
}
