import java.util.Random;

public class EstrategiaPadrao implements EstrategiaDoMonstro {

    private final Random random;

    public EstrategiaPadrao(Random random) {
        if (random == null) {
            throw new IllegalArgumentException("Random nao pode ser nulo");
        }

        this.random = random;
    }

    @Override
    public AcaoDoMonstro decidir(Monstro monstro, Personagem jogador, int turno) {

        if (monstro == null || jogador == null || turno <1) {
            throw new IllegalArgumentException("Monstro, jogador e turno devem ser validos");
        }
        
        if (monstro.getVida() <= monstro.getVidaMaxima() * 0.30 
            && monstro.getInventario().temPocaoDeVida() 
            && random.nextInt(100) <25) 
        {

            return AcaoDoMonstro.USAR_POCAO;
            
        }
        
        if (turno > 1 && random.nextInt(100) < 40) {
            return AcaoDoMonstro.ATAQUE_ESPECIAL;
        }

        



        return AcaoDoMonstro.ATAQUE_BASICO;
    }
}