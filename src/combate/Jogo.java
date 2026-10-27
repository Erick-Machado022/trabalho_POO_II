import java.util.List;

public class Jogo {
    private final Personagem jogador;
    private final List<Monstro> monstros;
    private final TabelaDeRecompensas tabelaDeRecompensas;

    private int indiceMonstro;
    private Batalha batalhaAtual;
    private EstadoDoJogo estado;

    public Jogo(Personagem jogador, List<Monstro> monstros, TabelaDeRecompensas tabelaDeRecompensas) {
        if (jogador == null || monstros == null || monstros.isEmpty() || tabelaDeRecompensas == null) {
            throw new IllegalArgumentException("Jogador, monstros e tabela de recompensas sao obrigatórios");
        }

        for (Monstro monstro : monstros) {
            if (monstro == null) {
                throw new IllegalArgumentException(
                        "A lista não pode conter monstros nulos.");
            }
        }

        this.jogador = jogador;
        this.monstros = monstros;
        this.tabelaDeRecompensas = tabelaDeRecompensas;
        this.indiceMonstro = 0;
        this.batalhaAtual = null;
        this.estado = EstadoDoJogo.EM_ANDAMENTO;
    }

    public EstadoDoJogo getEstado() {
        return estado;
    }

    public Batalha iniciarProximaBatalha() {
        if (estado != EstadoDoJogo.EM_ANDAMENTO) {
            throw new IllegalArgumentException("O jogo já terminou");
        }

        if (indiceMonstro >= monstros.size()) {
            throw new IllegalArgumentException("Não há mais monstros para enfrentar");
        }

        batalhaAtual = new Batalha(jogador, monstros.get(indiceMonstro));
        return batalhaAtual;

    }

        public Item concluirBatalha(Batalha batalha) {
            if (batalha == null || batalha != batalhaAtual) {
                throw new IllegalArgumentException("A batalha informada não é a batalha atual;");
            }

            if (!jogador.estaVivo()) {
                estado = EstadoDoJogo.PERDIDO;
                batalhaAtual = null;
                return null;    
            }

            if (!batalha.getMonstro().estaVivo()) {
                jogador.recuperarVida(jogador.getVidaMaxima() / 2);
                jogador.recuperarMana(jogador.getManaMaxima());
                jogador.ganharPontos(2);

                Item recompensa = tabelaDeRecompensas.sortear();
                jogador.getInventario().adicionarItem(recompensa);

                indiceMonstro++;

                if (indiceMonstro >= monstros.size()) {
                    estado = EstadoDoJogo.VENCIDO;
                }

                batalhaAtual = null;
                return recompensa;
            }

        throw new IllegalArgumentException("A batalha terminou sem uma vitória ou derrota válida");
    }

    

}
