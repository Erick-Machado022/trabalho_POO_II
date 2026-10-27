public class PocaoMana extends Consumivel {
    private static final int QUANTIDADE_MANA = 15;

    public PocaoMana(String nome){
        super(nome);
    }

    @Override
    public String descricao() {
        return getNome() + " (mana: " + QUANTIDADE_MANA + ")";
    }

    @Override 
    public void usar(Personagem alvo){
        alvo.recuperarMana(QUANTIDADE_MANA);
    }

    public boolean recuperaMana(){
        return true;
    }

}
