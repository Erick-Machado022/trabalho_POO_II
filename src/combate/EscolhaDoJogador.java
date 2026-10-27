public record EscolhaDoJogador(
    AcaoDoJogador acao,
    Habilidade habilidade
) {
    public EscolhaDoJogador {
        if (acao == null) {
            throw new IllegalArgumentException("A ação não pode ser nula");
        }

        if (acao == AcaoDoJogador.USAR_HABILIDADE && habilidade == null) {
            throw new IllegalArgumentException("Informe a habilidade");
        }
        if (acao != AcaoDoJogador.USAR_HABILIDADE && habilidade != null) {
            throw new IllegalArgumentException(
                "Esta ação não deve possuir habilidade"
            );
        }
    }
}