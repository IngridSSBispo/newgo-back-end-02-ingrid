package application.dto;

import java.util.UUID;

public class ChangeStatusDTO {
    private boolean lativo;
    private UUID hash;

    public ChangeStatusDTO(boolean lativo, UUID hash) {
        this.lativo = lativo;
        this.hash = hash;
    }
    public UUID getHash() {
        return hash;
    }

    public boolean getLativo(){
        return lativo;
    }


}
