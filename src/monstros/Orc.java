import java.util.Random;

public class Orc extends Monstro {
    public Orc(String nome, Inventario inventario, Random random) {
        super(nome, 70, 10, new Atributos(9, 4, 1), inventario, new EstrategiaPadrao(random));
    }

    @Override
    public Habilidade getAtaqueEspecial() {
        return new AtaqueFisico("Golpe Esmagador", 0, 2.0);
    }
}
