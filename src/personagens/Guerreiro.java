public class Guerreiro extends LutadorFisico {

    public Guerreiro(String nome, Inventario inventario) {
        super(nome, 100, 10, new Atributos(8, 5, 1), inventario);

        adicionarHabilidade(new AtaqueFisico("Golpe Poderoso", 5, 2.0));
        adicionarHabilidade(new Buff("Postura Defensiva", 4, 3, 3));

    }

    @Override
    public int receberDano(int dano) {

        int danoReduzido = (int) (dano * 0.8); // Reduz o dano em 20%

        return super.receberDano(danoReduzido); // Chama o método da superclasse para aplicar o dano reduzido
    }

}