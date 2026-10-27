import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int OPCAO_ATACAR = 1;
    private static final int OPCAO_HABILIDADE = 2;
    private static final int OPCAO_POCAO_VIDA = 3;
    private static final int OPCAO_POCAO_MANA = 4;

    private final Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        new Main().jogar();
    }

    private void aposVitoria(Personagem jogador, Item recompensa) {
        System.out.println("\nVitoria! Voce recebeu 2 pontos de atributo.");

        if (recompensa != null) {
            System.out.println("Recompensa: " + recompensa.descricao());

            boolean adicionada = jogador.getInventario().getItens()
                    .contains(recompensa);

            if (adicionada) {
                oferecerEquipamento(jogador, recompensa);
            } else {
                System.out.println("Inventario cheio: recompensa nao armazenada.");
            }
        }

        distribuirPontos(jogador);
    }

    private void oferecerEquipamento(Personagem jogador, Item item) {
        Equipavel equipamento = item.comoEquipavel();

        if (equipamento == null) {
            System.out.println("Item guardado no inventario.");
            return;
        }

        System.out.println("\nDeseja equipar " + item.getNome() + "?");
        System.out.println("1) Sim");
        System.out.println("2) Nao");

        if (lerInteiro(1, 2) == 1) {
            jogador.getInventario().removerItem(item);
            jogador.equipar(equipamento);
            System.out.println("Equipamento equipado!");
        } else {
            System.out.println("Item mantido no inventario.");
        }
    }

    private void jogar() {
        System.out.println("=== ARENA ===");
        System.out.print("Nome do personagem: ");

        String nome = entrada.nextLine().trim();
        if (nome.isEmpty()) {
            nome = "Heroi";
        }

        Personagem jogador = escolherClasse(nome);
        Random random = new Random();

        List<Monstro> monstros = List.of(
                new Goblin("Goblin", new Inventario(10), random),
                new Orc("Orc", new Inventario(10), random),
                new Dragao("Dragao", new Inventario(10), random));

        TabelaDeRecompensas recompensas = new TabelaDeRecompensas(
                List.of(
                        new Arma("Adaga", 2),
                        new Arma("Espada Longa", 4),
                        new Armadura("Elmo de Ferro", 2, Slot.CABECA),
                        new Armadura("Peitoral de Couro", 3, Slot.CORPO),
                        new PocaoVida("Pocao de vida"),
                        new PocaoVida("Pocao de vida"),
                        new PocaoMana("Pocao de mana"),
                        new PocaoMana("Pocao de mana")),
                random);

        Jogo jogo = new Jogo(jogador, monstros, recompensas);

        while (jogo.getEstado() == EstadoDoJogo.EM_ANDAMENTO) {
            Batalha batalha = jogo.iniciarProximaBatalha();

            System.out.println(
                    "\n>>> Um " + batalha.getMonstro().getNome() + " apareceu!");

            lutar(batalha, jogador);

            boolean venceu = jogador.estaVivo()
                    && !batalha.getMonstro().estaVivo();

            Item recompensa = jogo.concluirBatalha(batalha);

            if (venceu) {
                aposVitoria(jogador, recompensa);
            }
        }

        System.out.println();

        if (jogo.getEstado() == EstadoDoJogo.VENCIDO) {
            System.out.println("*** VITORIA! ***");
        } else {
            System.out.println("*** DERROTA ***");
        }

        System.out.println(status(jogador));
        System.out.println(jogador.getInventario().listarItens());
    }

    private Personagem escolherClasse(String nome) {
        System.out.println("Escolha sua classe:");
        System.out.println("1) Guerreiro");
        System.out.println("2) Arqueiro");
        System.out.println("3) Mago");

        int opcao = lerInteiro(1, 3);
        Inventario inventario = new Inventario(20);

        if (opcao == 1) {
            return new Guerreiro(nome, inventario);
        } else if (opcao == 2) {
            return new Arqueiro(nome, inventario);
        } else {
            return new Mago(nome, inventario);
        }
    }

    private void lutar(Batalha batalha, Personagem jogador) {
        Monstro monstro = batalha.getMonstro();

        while (!batalha.isEncerrada()) {
            System.out.println("\n--- Turno " + batalha.getTurnoAtual() + " ---");
            System.out.println(status(jogador));
            System.out.println(
                    monstro.getNome() + ": vida "
                            + monstro.getVida() + "/" + monstro.getVidaMaxima());

            EscolhaDoJogador escolha = lerEscolha(jogador);
            AcaoDoMonstro acao = monstro.decidirAcao(
                    jogador,
                    batalha.getTurnoAtual());

            try {
                ResultadoDoTurno resultado = batalha.executarTurno(escolha, acao);

                for (String evento : resultado.eventos()) {
                    System.out.println("  " + evento);
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Acao invalida: " + e.getMessage());
            }
        }
    }

    private EscolhaDoJogador lerEscolha(Personagem jogador) {
        System.out.println("\nEscolha sua acao:");
        System.out.println("1) Atacar");
        System.out.println("2) Usar habilidade");
        System.out.println("3) Pocao de vida (" + contarPocoes(jogador, true) + ")");
        System.out.println("4) Pocao de mana (" + contarPocoes(jogador, false) + ")");

        int opcao = lerInteiro(OPCAO_ATACAR, OPCAO_POCAO_MANA);

        if (opcao == OPCAO_HABILIDADE) {
            List<Habilidade> habilidades = jogador.getHabilidades();

            if (habilidades.isEmpty()) {
                System.out.println("Voce nao possui habilidades.");
                return new EscolhaDoJogador(AcaoDoJogador.ATACAR, null);
            }

            for (int i = 0; i < habilidades.size(); i++) {
                Habilidade habilidade = habilidades.get(i);
                System.out.println(
                        (i + 1) + ") " + habilidade.getNome()
                                + " (custo: " + habilidade.getCustoMana() + " de mana)");
            }
            int indice = lerInteiro(1, habilidades.size()) - 1;
            return new EscolhaDoJogador(
                    AcaoDoJogador.USAR_HABILIDADE,
                    habilidades.get(indice));
        }

        if (opcao == OPCAO_POCAO_VIDA) {
            return new EscolhaDoJogador(AcaoDoJogador.USAR_POCAO_VIDA, null);
        }

        if (opcao == OPCAO_POCAO_MANA) {
            return new EscolhaDoJogador(AcaoDoJogador.USAR_POCAO_MANA, null);
        }

        return new EscolhaDoJogador(AcaoDoJogador.ATACAR, null);
    }

    private int contarPocoes(Personagem jogador, boolean vida) {
        int quantidade = 0;

        for (Item item : jogador.getInventario().getItens()) {
            if (vida && item.recuperaVida()) {
                quantidade++;
            } else if (!vida && item.recuperaMana()) {
                quantidade++;
            }
        }

        return quantidade;
    }

    private void aposVitoria(Personagem jogador) {
        System.out.println("\nVitoria! Voce recebeu 2 pontos de atributo.");
        System.out.println("A recompensa da batalha foi adicionada pelo Jogo.");
        distribuirPontos(jogador);
    }

    private void distribuirPontos(Personagem jogador) {
        while (jogador.getPontoDeAtributo() > 0) {
            System.out.println("\nPontos disponiveis: " + jogador.getPontoDeAtributo());
            System.out.println("Atributos: " + jogador.getAtributos());
            System.out.println("1) Forca");
            System.out.println("2) Defesa");
            System.out.println("3) Magia");
            System.out.println("0) Guardar pontos");

            int opcao = lerInteiro(0, 3);

            if (opcao == 0) {
                return;
            }

            Atributo atributo;
            if (opcao == 1) {
                atributo = Atributo.FORCA;
            } else if (opcao == 2) {
                atributo = Atributo.DEFESA;
            } else {
                atributo = Atributo.MAGIA;
            }

            System.out.print("Quantos pontos deseja distribuir? ");
            int pontos = lerInteiro(1, jogador.getPontoDeAtributo());

            try {
                jogador.distribuirPontos(atributo, pontos);
            } catch (IllegalArgumentException e) {
                System.out.println("Distribuicao rejeitada: " + e.getMessage());
            }
        }
    }

    private String status(Personagem personagem) {
        return personagem.getNome()
                + ": vida " + personagem.getVida() + "/" + personagem.getVidaMaxima()
                + " | mana " + personagem.getManaAtual() + "/" + personagem.getManaMaxima()
                + " | defesa " + personagem.getDefesa();
    }

    private int lerInteiro(int minimo, int maximo) {
        while (true) {
            String linha = entrada.nextLine().trim();

            try {
                int valor = Integer.parseInt(linha);
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // Entrada nao numerica.
            }

            System.out.print(
                    "Digite um numero entre " + minimo + " e " + maximo + ": ");
        }
    }
}
