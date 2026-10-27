public class PocaoVida extends Consumivel {

    private static final int QUANTIDADE_CURA = 30;

    public PocaoVida(String nome){
        super(nome);
    
    }

    @Override 
    public boolean recuperaVida(){
        return true;
    }

    @Override
    public String descricao() {
        return getNome() + " (cura: " + QUANTIDADE_CURA + ")";
    }
    
    @Override 
    public void usar(Personagem alvo){
        alvo.recuperarVida(QUANTIDADE_CURA);
    }
}
 