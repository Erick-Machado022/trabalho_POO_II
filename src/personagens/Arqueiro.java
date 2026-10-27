public class Arqueiro extends LutadorFisico {

    public Arqueiro(String nome, Inventario inventario) {
        super(nome, 80, 15, new Atributos(7, 3, 2), inventario);

        // Adiciona exatamente 3 poções de vida
        

        adicionarHabilidade(new AtaqueFisico("Tiro Certeiro", 5, 2.0));
        
    }

    @Override 
    public int getForcaAtaque() {
        return super.getForcaAtaque() + 3; // Bônus de +3 na força de ataque
    }
}