import java.util.Random;

public class Dragao extends Monstro {
    public Dragao(String nome, Inventario inventario, Random random) {
        super(nome, 150, 10, new Atributos(12, 6, 9), inventario, new EstrategiaPadrao(random));
    }

    @Override
    public Habilidade getAtaqueEspecial(){
        return new Magia("Sopro de fogo", 0, 15);
    }

}