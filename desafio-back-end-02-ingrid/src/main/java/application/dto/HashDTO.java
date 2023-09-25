package application.dto;

import java.util.UUID;

public class HashDTO {
    private UUID hash;

    public HashDTO(UUID hash) {

        this.hash = hash;
    }

    public UUID getHash() {
        return hash;
    }

}