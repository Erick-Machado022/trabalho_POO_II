public class EstrategiaBrutal implements EstrategiaDoMonstro {
    @Override 
    public AcaoDoMonstro decidir(Monstro monstro, Personagem jogador, int turno){
        if (turno<1) {
            return AcaoDoMonstro.ATAQUE_BASICO;
        }

        return AcaoDoMonstro.ATAQUE_BASICO;
    }
}
