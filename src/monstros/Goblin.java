import java.util.Random;

public class Goblin extends Monstro{

    public Goblin(String nome, Inventario inventario, Random random){
        super(nome, 40,10, new Atributos(6, 2, 1), inventario, new EstrategiaPadrao(random));
    }

    @Override 
    public Habilidade getAtaqueEspecial(){
        return new AtaqueFisico("Facada dupla", 0, 2.0);
    }
    
}