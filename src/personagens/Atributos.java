    public record Atributos(int forca, int defesa, int magia) {
        
        public static final int LIMITE_POR_ATRIBUTO = 20;  //criamos a constante de limite atributo

        public Atributos{ //aqui e o construtor que valida tudo

            if (forca < 0 || forca > LIMITE_POR_ATRIBUTO) {
                throw new IllegalArgumentException("Força não pode ser menor do que zero ou maior que 20");
            }
            if (defesa < 0 || defesa > LIMITE_POR_ATRIBUTO) {
                throw new IllegalArgumentException("Defesa não pode ser menor do que zero ou maior que 20");
            }
            if (magia < 0 || magia > LIMITE_POR_ATRIBUTO) {
                throw new IllegalArgumentException("Magia não pode ser menor do que zero ou maior que 20");
            }
            
        }

        public int valorDe(Atributo atributo){

            if (atributo == null) {
              throw new IllegalArgumentException("Valor do atributo é nulo");
            }

            if (atributo == Atributo.DEFESA){
                return defesa;
            }
            if (atributo == Atributo.FORCA){
                return forca;
            }
            if (atributo == Atributo.MAGIA){
                return magia;
            }

            throw new IllegalArgumentException("Atributo invalido");
        }

        public Atributos somar(Atributo atributo, int valor){

            if (atributo == null) {
                throw new IllegalArgumentException("O atributo nao pode ser nulo");
            }

            int forca = this.forca;
            int def = this.defesa;
            int magia = this.magia;

            if (atributo == Atributo.FORCA){
                forca +=valor;
            }
            if (atributo == Atributo.DEFESA){
                def +=valor;
            }
            if (atributo == Atributo.MAGIA){
                magia +=valor;
            }

            return new Atributos(forca, defesa, magia);
        }
            
    }