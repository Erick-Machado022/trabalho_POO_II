
import java.util.Random;

public class MainRoteirizado {

    public static void main(String[] args) {

        Random random = new Random();

        System.out.println("==================================");
        System.out.println("       MAIN ROTEIRIZADO");
        System.out.println("==================================");

        testarCuraComGuerreiro();
        testarLimiteDeAtributo();
        testarAtaqueEspecialNoPrimeiroTurno();

        executarBatalhaCompleta(random);

        System.out.println("\nFim do roteiro.");
    }

    private static void testarCuraComGuerreiro() {

        System.out.println("\n=== TESTE: CURA COM GUERREIRO ===");

        Arqueiro arqueiro = new Arqueiro(
            "Legolas", new Inventario(10)
        );

        Cura cura = new Cura("Cura", 6, 20);

        try {
            cura.executar(arqueiro, arqueiro);

            System.out.println(
                "ERRO: a Cura deveria ter sido rejeitada."
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                "Cura rejeitada corretamente: " + e.getMessage()
            );
        }
    }

    private static void testarLimiteDeAtributo() {

        System.out.println("\n=== TESTE: LIMITE DE ATRIBUTO ===");

        Guerreiro guerreiro = new Guerreiro(
            "Conan", new Inventario(10)
        );

        guerreiro.ganharPontos(20);

        try {
            // Força inicial 8 + 13 = 21, acima do limite 20.
            guerreiro.distribuirPontos(Atributo.FORCA, 13);

            System.out.println(
                "ERRO: a distribuição deveria ter sido rejeitada."
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                "Distribuição rejeitada corretamente: "
                    + e.getMessage()
            );
        }
    }

    private static void testarAtaqueEspecialNoPrimeiroTurno() {

        System.out.println(
            "\n=== TESTE: ATAQUE ESPECIAL NO TURNO 1 ==="
        );

        Arqueiro jogador = new Arqueiro(
            "Legolas", new Inventario(10)
        );

        Goblin goblin = new Goblin("Teste Goblin", new Inventario(10) , new Random());

        Batalha batalha = new Batalha(jogador, goblin);

        try {
            batalha.executarTurno(
                new EscolhaDoJogador(
                    AcaoDoJogador.ATACAR, null
                ),
                AcaoDoMonstro.ATAQUE_ESPECIAL
            );

            System.out.println(
                "ERRO: o ataque especial no turno 1 deveria ser rejeitado."
            );

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(
                "Ataque especial rejeitado corretamente: "
                    + e.getMessage()
            );
        }
    }

    private static void executarBatalhaCompleta(Random random) {

        System.out.println("\n=== BATALHA COMPLETA ===");

        Arqueiro jogador = new Arqueiro(
            "Legolas", new Inventario(20)
        );

        Goblin goblin = new Goblin(
            "Goblin", new Inventario(10), random);

        Batalha batalha = new Batalha(jogador, goblin);

        Habilidade golpePoderoso = jogador.getHabilidades()
            .stream()
            .filter(h -> h.getNome().equals("Golpe Poderoso"))
            .findFirst()
            .orElse(null);

        while (!batalha.isEncerrada()) {

            EscolhaDoJogador escolha;

            // A semente fixa torna a sequência reproduzível.
            boolean usarHabilidade = random.nextBoolean();

            if (usarHabilidade
                    && golpePoderoso != null
                    && jogador.getManaAtual()
                        >= golpePoderoso.getCustoMana()) {

                escolha = new EscolhaDoJogador(
                    AcaoDoJogador.USAR_HABILIDADE,
                    golpePoderoso
                );

            } else {
                escolha = new EscolhaDoJogador(
                    AcaoDoJogador.ATACAR, null
                );
            }

            AcaoDoMonstro acaoMonstro =
                goblin.decidirAcao(
                    jogador, batalha.getTurnoAtual()
                );

            ResultadoDoTurno resultado =
                batalha.executarTurno(escolha, acaoMonstro);

            System.out.println(
                "\nTurno " + resultado.numeroTurno()
            );

            resultado.eventos().forEach(System.out::println);

            System.out.println(
                "Vida do jogador: " + jogador.getVida()
                    + "/" + jogador.getVidaMaxima()
            );

            System.out.println(
                "Vida do Goblin: " + goblin.getVida()
                    + "/" + goblin.getVidaMaxima()
            );
        }

        System.out.println("\nResultado: "
            + (jogador.estaVivo() && !goblin.estaVivo()
                ? "VITÓRIA"
                : "DERROTA"));
    }
}
