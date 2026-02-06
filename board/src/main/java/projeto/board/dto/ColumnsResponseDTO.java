package projeto.board.dto;

import projeto.board.model.Status;

import java.util.List;

public class ColumnsResponseDTO {
    private Long id;
    private String name;
    private Integer position;
    private Status status;
    private List<CardResponseDTO> cardResponseDTO;

    public ColumnsResponseDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<CardResponseDTO> getCardResponseDTO() {
        return cardResponseDTO;
    }

    public void setCardResponseDTO(List<CardResponseDTO> cardResponseDTO) {
        this.cardResponseDTO = cardResponseDTO;
    }
}
