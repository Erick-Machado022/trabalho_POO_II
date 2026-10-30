public interface  Equipavel {
    
    public Slot getSlot();


    default int bonusDeDano(){
            return 0;
    }

    default int bonusDeDefesa(){
        return 0;
    }


}



