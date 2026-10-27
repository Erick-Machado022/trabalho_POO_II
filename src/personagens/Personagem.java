import java.util.ArrayList;
import java.util.List;

public abstract class Personagem implements Combatente {

    // ==========================================
    // ATRIBUTOS
    // ==========================================

    private String nome;

    private int vidaMaxima;
    private int vidaAtual;

    private int manaMaxima;
    private int manaAtual;

    private Atributos atributos;
    private int pontoDeAtributo;
    private List<Habilidade> habilidades = new ArrayList<>();

    private Inventario inventario;

    // Equipamento que ocupa o slot da mão.
    // Como é do tipo Equipavel, pode representar
    // diferentes classes que implementam essa interface.
    private Equipavel equipamentoMao;

    private Equipavel equipamentoCorpo;
    private Equipavel equipamentoCabeca;

    // Guarda o bônus de defesa e a duração do buff.
    private int bonusDefesaTemporario;
    private int turnosDeBuff;

    // ==========================================
    // CONSTRUTOR
    // ==========================================

    public Personagem(String nome, int vidaMaxima, int manaMaxima,
            Atributos atributos, Inventario inventario) {

        this.nome = nome;

        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;

        this.manaMaxima = manaMaxima;
        this.manaAtual = manaMaxima;

        this.atributos = atributos;
        this.inventario = inventario;

        this.pontoDeAtributo = 0;
    }

    // ==========================================
    // GETTERS
    // ==========================================

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getVida() {
        return vidaAtual;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getPontoDeAtributo() {
        return pontoDeAtributo;
    }

    public Atributos getAtributos() {
        return atributos;
    }

    // Retorna a defesa base somada ao bônus temporário.
    @Override
    public int getDefesa() {
        int defesaBase = atributos.defesa();
        int defesaArmadura = 0;

        if (equipamentoCorpo != null) {
            defesaArmadura += equipamentoCorpo.bonusDeDefesa();
        }

        if (equipamentoCabeca != null) {
            defesaArmadura += equipamentoCabeca.bonusDeDefesa();
        }

        return defesaBase + defesaArmadura + bonusDefesaTemporario;
    }

    private Equipavel getEquipamentoPorSlot(Slot slot) {
        if (slot == Slot.MAO) {
            return equipamentoMao;
        } else if (slot == Slot.CORPO) {
            return equipamentoCorpo;
        } else if (slot == Slot.CABECA) {
            return equipamentoCabeca;
        }

        else {
            return null;
        }
    }

    public Inventario getInventario() {
        return inventario;
    }

    public int getMana() {
        return manaAtual;
    }

    public int getManaMaxima() {
        return manaMaxima;
    }

    public int getManaAtual() {
        return manaAtual;
    }

    // Retorna a força base do personagem.
    public int getForcaAtaque() {
        return atributos.forca();
    }

    // Retorna a força total.
    // Futuramente pode considerar outros bônus.
    public int getForcaTotal() {
        return getForcaAtaque();
    }

    // Retorna a magia base do personagem.
    public int getMagiaTotal() {
        return atributos.magia();
    }

    // Retorna o bônus de dano do equipamento da mão.
    public int getBonusDeDano() {
        if (equipamentoMao == null) {
            return 0;
        }

        // Polimorfismo: o resultado depende do equipamento.
        return equipamentoMao.bonusDeDano();
    }

    // ==========================================
    // ATRIBUTOS
    // ==========================================

    public void ganharPontos(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade de pontos deve ser positiva.");
        }

        pontoDeAtributo += quantidade;
    }

    public void distribuirPontos(Atributo atributo, int quantidade) {

        if (atributo == null) {
            throw new IllegalArgumentException(
                    "O atributo não pode ser nulo.");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser positiva.");
        }

        if (quantidade > pontoDeAtributo) {
            throw new IllegalArgumentException(
                    "Pontos de atributo insuficientes.");
        }

        int forca = atributos.forca();
        int defesa = atributos.defesa();
        int magia = atributos.magia();

        if (atributo == Atributo.FORCA) {
            forca += quantidade;
        } else if (atributo == Atributo.DEFESA) {
            defesa += quantidade;
        } else if (atributo == Atributo.MAGIA) {
            magia += quantidade;
        }

        if (forca > 20 || defesa > 20 || magia > 20) {
            throw new IllegalArgumentException(
                    "Nenhum atributo pode ultrapassar 20.");
        }

        atributos = new Atributos(forca, defesa, magia);

        pontoDeAtributo -= quantidade;
    }

    // ==========================================
    // MANA
    // ==========================================

    public void gastarMana(int quantidade) {

        if (quantidade < 0) {
            throw new IllegalArgumentException(
                    "A quantidade de mana não pode ser negativa.");
        }

        if (quantidade > manaAtual) {
            throw new IllegalArgumentException(
                    "Mana insuficiente.");
        }

        manaAtual -= quantidade;
    }

    public void recuperarMana(int quantidade) {

        if (quantidade < 0) {
            throw new IllegalArgumentException(
                    "A quantidade de mana a ser recuperada não pode ser negativa.");
        }

        // Impede que a mana ultrapasse o máximo.
        if (manaAtual + quantidade > manaMaxima) {
            manaAtual = manaMaxima;
        } else {
            manaAtual += quantidade;
        }
    }

    // ==========================================
    // EQUIPAMENTOS
    // ==========================================

    public void equipar(Equipavel equipamento) { // vamos explicar o que acontece aqui, temos aqui um método que recebe
                                                 // um equipamento, e o slot do equipamento é verificado, se já houver
                                                 // um equipamento no slot, ele é adicionado ao inventário, caso não
                                                 // haja espaço no inventário, o equipamento não é equipado. Se houver
                                                 // espaço, o equipamento é equipado no slot correspondente.

        if (equipamento == null) {
            throw new IllegalArgumentException("Equipamentos não pode ser nulo.");
        }

        Slot slot = equipamento.getSlot();

        Equipavel equipamentoAntigo = getEquipamentoPorSlot(slot);

        boolean adicionado = true;

        if (equipamentoAntigo != null) {
            adicionado = inventario.adicionarItem((Item) equipamentoAntigo);
        }

        if (!adicionado) {
            return;
        }

        if (equipamento.getSlot() == Slot.MAO) {
            this.equipamentoMao = equipamento;
        } else if (equipamento.getSlot() == Slot.CABECA) {
            this.equipamentoCabeca = equipamento;
        } else if (equipamento.getSlot() == Slot.CORPO) {
            this.equipamentoCorpo = equipamento;
        }
    }

    // ==========================================
    // COMBATE
    // ==========================================

    public void adicionarHabilidade(Habilidade habilidade) {
        if (habilidade == null) {
            throw new IllegalArgumentException(
                    "A habilidade não pode ser nula");
        }

        habilidades.add(habilidade);
    }

    public List<Habilidade> getHabilidades() {
        return List.copyOf(habilidades);
    }

    @Override
    public boolean estaVivo() {
        return vidaAtual > 0;
    }

    @Override
    public int receberDano(int dano) {

        if (dano < 0) {
            throw new IllegalArgumentException(
                    "O dano não pode ser negativo.");
        }

        int vidaAntes = vidaAtual;

        // A vida nunca pode ficar abaixo de zero.
        if (vidaAtual - dano <= 0) {
            vidaAtual = 0;
        } else {
            vidaAtual -= dano;
        }

        return vidaAntes - vidaAtual;
    }

    @Override
    public int atacar(Combatente alvo) {

        if (alvo == null) {
            throw new IllegalArgumentException("O alvo não pode ser nulo.");
        }

        int dano = getForcaAtaque() + getBonusDeDano() - alvo.getDefesa();

        // Todo ataque causa pelo menos 1 de dano.
        if (dano < 1) {
            dano = 1;
        }

        return alvo.receberDano(dano);
    }

    // ==========================================
    // RECUPERAÇÃO DE VIDA
    // ==========================================

    public void recuperarVida(int quantidade) {

        if (quantidade < 0) {
            throw new IllegalArgumentException(
                    "A quantidade de vida a ser recuperada não pode ser negativa.");
        }

        // A vida recuperada não pode ultrapassar a vida máxima.
        if (vidaAtual + quantidade > vidaMaxima) {
            vidaAtual = vidaMaxima;
        } else {
            vidaAtual += quantidade;
        }
    }

    // ==========================================
    // BUFF DE DEFESA
    // ==========================================

    public void aplicarBuff(int bonusDefesa, int duracao) {

        if (bonusDefesa < 0 || duracao <= 0) {
            throw new IllegalArgumentException(
                    "O bônus não pode ser negativo e a duração deve ser positiva.");
        }

        // Guarda o bônus e a duração do efeito.
        // Reaplicar substitui o buff anterior.
        this.bonusDefesaTemporario = bonusDefesa;
        this.turnosDeBuff = duracao;
    }

    public void encerrarTurno() {

        // Só reduz a duração se houver um buff ativo.
        if (turnosDeBuff > 0) {
            turnosDeBuff--;

            // Quando a duração termina, remove o bônus.
            if (turnosDeBuff == 0) {
                bonusDefesaTemporario = 0;
            }
        }
    }
}
