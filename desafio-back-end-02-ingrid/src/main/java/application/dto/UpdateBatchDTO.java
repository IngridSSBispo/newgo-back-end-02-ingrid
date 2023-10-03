package application.dto;

import infrastructure.enums.Operacao;

import java.util.UUID;

public class UpdateBatchDTO {

    private UUID hash;

    private float valor;

    private Operacao operation;

    public UpdateBatchDTO(UUID hash, float valor, Operacao operation) {
        this.hash = hash;
        this.valor = valor;
        this.operation = operation;
    }

    public UUID getHash() {
        return hash;
    }

    public float getValor() {
        return valor;
    }

    public Operacao getOperation() {
        return operation;
    }
}
