import java.util.ArrayList;
import java.util.List;

public class Batalha {

    private Personagem jogador;
    private Monstro monstro;
    private int turno;
    private boolean encerrada;

    public Batalha(Personagem jogador, Monstro monstro) {

        if (jogador == null || monstro == null) {
            throw new IllegalArgumentException("Jogador e monstro não podem ser nulos");
        }

        this.jogador = jogador;
        this.monstro = monstro;
        this.turno = 1;
        this.encerrada = false;

    }

    // GETTERS

    public Personagem getJogador() {
        return jogador;
    }

    public Monstro getMonstro() {
        return monstro;
    }

    public int getTurnoAtual() {
        return turno;
    }

    public boolean isEncerrada() {
        return encerrada;
    }
    /*
     * getJogador() e getMonstro() permitem consultar quem está lutando.
     * 
     * 
     * 
     * getTurno() permite que a estratégia saiba em qual turno está.
     * 
     * 
     * 
     * isEncerrada() permite verificar se a batalha terminou.
     * 
     */

    // METODOS

    private void validarAcaoMonstro(AcaoDoMonstro acao) {
        if (acao == null) {
            throw new IllegalArgumentException("A ação do monstro nao pode ser nula");
        }

        if (acao == AcaoDoMonstro.ATAQUE_ESPECIAL && turno == 1) {
            throw new IllegalArgumentException("O monstro não pode usar ataque especial no primeiro turno");
        }

        if (acao == AcaoDoMonstro.USAR_POCAO
                && !monstro.getInventario().temPocaoDeVida()) {
            throw new IllegalArgumentException(
                    "O monstro não possui poção de vida");

        }

    }

    private void validarBatalhaAtiva() {
        if (encerrada) {
            throw new IllegalStateException("A batalha já foi encerrada");
        }
    }

    private void validarEscolhaJogador(EscolhaDoJogador escolha) {
        if (escolha == null) {
            throw new IllegalArgumentException("A escolha do jogador não pode ser nula");
        }

        if (escolha.acao() == AcaoDoJogador.ATACAR) {
            return;
        }

        if (escolha.acao() == AcaoDoJogador.USAR_HABILIDADE) {
            Habilidade habilidade = escolha.habilidade();

            if (!jogador.getHabilidades().contains(habilidade)) {
                throw new IllegalArgumentException("O personagem não conhece essa habilidade");
            }

            if (!habilidade.podeUsar(jogador)) {
                throw new IllegalArgumentException("Mana insuficiente para usar a Habilidade");
            }

            return;
        }

        if (escolha.acao() == AcaoDoJogador.USAR_POCAO_VIDA) {
            if (!jogador.getInventario().temPocaoDeVida()) {
                throw new IllegalArgumentException("O jogador não possui poção de vida");
            }

            return;
        }

        if (escolha.acao() == AcaoDoJogador.USAR_POCAO_MANA) {
            if (!jogador.getInventario().temPocaoDeMana()) {
                throw new IllegalArgumentException("O jogador nao possui poção de mana");
            }
        }

        return;

    }

    private void executarAcaoJogador(EscolhaDoJogador escolha, List<String> eventos) {
        if (escolha.acao() == AcaoDoJogador.ATACAR) {
            int vidaAntes = monstro.getVida();

            jogador.atacar(monstro);

            int dano = vidaAntes - monstro.getVida();

            eventos.add(jogador.getNome() + " ataca e causa " + dano + " de dano");
            return;
        }

        if (escolha.acao() == AcaoDoJogador.USAR_HABILIDADE) {
            Habilidade habilidade = escolha.habilidade();

            if (!jogador.getHabilidades().contains(habilidade)) {
                throw new IllegalArgumentException(
                        "O personagem não possui essa habilidade.");
            }

            int vidaAntes = monstro.getVida();

            String evento = habilidade.executar(jogador, monstro);

            int vidaPerdida = vidaAntes - monstro.getVida();

            if (habilidade.getAlvo() == Alvo.INIMIGO) {
                eventos.add(
                        jogador.getNome() + " usou " + habilidade.getNome()
                                + " e causou " + vidaPerdida + " de dano.");
            } else {
                eventos.add(evento);
            }
        }

        if (escolha.acao() == AcaoDoJogador.USAR_POCAO_VIDA) {
            jogador.getInventario().usarPocaoDeVida(jogador);

            eventos.add(jogador.getNome() + " usa uma poção de vida");
        }

        if (escolha.acao() == AcaoDoJogador.USAR_POCAO_MANA) {
            jogador.getInventario().usarPocaoDeMana(jogador);

            eventos.add(jogador.getNome() + " usa uma poção de mana");
        }
    }

    private void executarAcaoMonstro(AcaoDoMonstro acao, List<String> eventos) {

        if (acao == AcaoDoMonstro.ATAQUE_BASICO) {
            int vidaAntes = jogador.getVida();

            monstro.atacar(jogador);

            int dano = vidaAntes - jogador.getVida();

            eventos.add(monstro.getNome() + " ataca e causa " + dano + " de dano");

            return;
        }

        if (acao == AcaoDoMonstro.ATAQUE_ESPECIAL) {
            Habilidade habilidade = monstro.getAtaqueEspecial();

            String resultado = habilidade.executar(monstro, jogador);

            eventos.add(resultado);
            return;
        }

        if (acao == AcaoDoMonstro.USAR_POCAO) {
            monstro.getInventario().usarPocaoDeVida(monstro);

            eventos.add(monstro.getNome() + " usa uma poção de vida");
            return;
        }

        throw new IllegalArgumentException("Ação do monstro inválida");
    }

    public ResultadoDoTurno executarTurno(EscolhaDoJogador escolha, AcaoDoMonstro acaoDoMonstro) {

        validarBatalhaAtiva();
        validarEscolhaJogador(escolha);
        validarAcaoMonstro(acaoDoMonstro);

        List<String> eventos = new ArrayList<>();
        int turnoAtual = turno;

        executarAcaoJogador(escolha, eventos);

        if (!monstro.estaVivo()) {
            encerrada = true;
            return new ResultadoDoTurno(turnoAtual, eventos, EstadoDaBatalha.VITORIA);
        }

        executarAcaoMonstro(acaoDoMonstro, eventos);

        if (!jogador.estaVivo()) {
            encerrada = true;
            return new ResultadoDoTurno(turnoAtual, eventos, EstadoDaBatalha.DERROTA);
        }

        jogador.encerrarTurno();
        monstro.encerrarTurno();

        turno++;

        return new ResultadoDoTurno(turnoAtual, eventos, EstadoDaBatalha.EM_ANDAMENTO);
    }

}
