public class Buff implements Habilidade {
    private String nome;
    private int custoMana;
    private int bonusDefesa;
    private int duracao;

    public Buff(String nome, int custoMana, int bonusDefesa, int duracao){
        this.nome = nome;
        this.custoMana = custoMana;
        this.bonusDefesa = bonusDefesa;
        this.duracao = duracao;


    }

    @Override 
    public String getNome(){
        return nome;
    }

    @Override 
    public int getCustoMana(){
        return custoMana;
    }

    @Override 
    public Alvo getAlvo(){
        return Alvo.SI_MESMO;
    }

    @Override 
    public String executar(Personagem conjurador, Personagem alvo){
        if (!podeUsar(conjurador)) {
            return conjurador.getNome() + " não possui mana suficiente para usar " + nome + "!";
        }

        
        conjurador.gastarMana(custoMana);

        conjurador.aplicarBuff(bonusDefesa, duracao);


        return conjurador.getNome() + " usou " + nome + " e recebeu " + bonusDefesa + " de defesa por " + duracao + " turnos.";


    }
    
}
