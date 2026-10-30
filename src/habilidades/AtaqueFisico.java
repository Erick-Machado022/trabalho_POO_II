public class AtaqueFisico implements Habilidade {
    private String nome;
    private int custoMana;
    private double multiplicador;

    public AtaqueFisico(String nome, int custoMana, double multiplicador){
        this.nome = nome;
        this.custoMana = custoMana;
        this.multiplicador = multiplicador;
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
        return Alvo.INIMIGO;
    }

    

    @Override 
    public String executar(Personagem conjurador, Personagem alvo){
        if(!podeUsar(conjurador)){
            return conjurador.getNome() + " não possui mana suficiente para usar " + nome + "!";
        }

        //consumo de mana
        conjurador.gastarMana(custoMana);

        
        int danoBruto = (int) ((conjurador.getForcaTotal() + conjurador.getBonusDeDano()) * multiplicador);
        int defesaAlvo = alvo.getDefesa();

        int danoFinal = danoBruto - defesaAlvo;

        if(danoFinal < 1){
            danoFinal = 1;
        }

        //Aplica o dano no alvo
        alvo.receberDano(danoFinal);

        return conjurador.getNome() + " Usou" + nome + "em " + alvo.getNome() + " causando " + danoFinal + " de dano físico";
    }
}
