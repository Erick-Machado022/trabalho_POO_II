public interface  Equipavel {
    
    public Slot getSlot();


    default int bonusDeDano(){
            return 0;
    }

    default int bonusDeDefesa(){
        return 0;
    }


}


/*numa interface, métodos abstratos não precisam de corpo. Para um método com implementação padrão, usamos a palavra-chave default. */
