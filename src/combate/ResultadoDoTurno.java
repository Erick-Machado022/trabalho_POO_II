import java.util.List;

public record ResultadoDoTurno(
    int numeroTurno,
    List<String> eventos,
    EstadoDaBatalha estado
) {
    public ResultadoDoTurno {
        if (numeroTurno < 1) {
            throw new IllegalArgumentException("Turno inválido");
        }

        if (eventos == null || estado == null) {
            throw new IllegalArgumentException(
                "Eventos e estado são obrigatórios"
            );
        }

        eventos = List.copyOf(eventos);
    }
}