public class Magia implements Habilidade {

    private String nome;
    private int custoMana;
    private int poder;

    public Magia(String nome, int custoMana, int poder){
        this.nome = nome;
        this.custoMana = custoMana;
        this.poder = poder;
    }

    public String getNome(){
        return nome;
    }

    @Override 
    public int getCustoMana(){
        return custoMana;
    }

    public int getPoder(){
        return poder;
    }

    public Alvo getAlvo(){
        return Alvo.INIMIGO;
    }


    @Override
    public String executar(Personagem conjurador, Personagem alvo){
        if (!podeUsar(conjurador)) {
            return conjurador.getNome() + " não possui mana suficiente para usar " + nome + "!";
        }

        
        conjurador.gastarMana(custoMana);

        int danoBruto = conjurador.getMagiaTotal() + poder;

        alvo.receberDano(danoBruto);

        return conjurador.getNome() + " Usou " + nome + 
                                    " em " + alvo.getNome() + 
                                    " causando " + danoBruto + 
                                    " de dano magico ";
    }

    
}
