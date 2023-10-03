package application.dto;

import infrastructure.enums.Status;

import java.util.UUID;

public class StatusDTO {

    private Status status;
    private UUID hash = null;

    public StatusDTO(UUID hash, Status status) {
        this.hash = hash;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public UUID getHash() {
        return hash;
    }
}
