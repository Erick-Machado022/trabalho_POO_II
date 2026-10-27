public abstract class Monstro extends Personagem {
    private EstrategiaDoMonstro estrategia;

    public Monstro(String nome, int VidaMaxima, int manaMaxima, Atributos atributos, Inventario inventario, EstrategiaDoMonstro estrategia){
        super(nome, VidaMaxima, manaMaxima, atributos, inventario);

        if (estrategia ==null) {
            throw new IllegalArgumentException("A estrategia não pode ser nula");
        }

        this.estrategia = estrategia;

        inventario.adicionarItem(new PocaoVida("Poção de vida"));
    }

    public abstract Habilidade getAtaqueEspecial();

    public AcaoDoMonstro decidirAcao(Personagem jogador, int turno){
        return estrategia.decidir(this, jogador, turno); //o que é esse this? ele representa o monstro que está tomando a decisão, e é passado como parâmetro para a estratégia do monstro, para que ela possa tomar decisões baseadas nas características do monstro.
    }

    

}
