package application.dto;

public class CreateDTO {

    private String nome;
    private String descricao;
    private String ean13;
    private int estoque_min;
    private double preco;
    private int quantidade;

    public CreateDTO(String nome, String descricao, String ean13, double preco, int quantidade, int estoque_min) {
        this.nome = nome;
        this.descricao = descricao;
        this.ean13 = ean13;
        this.preco = preco;
        this.quantidade = quantidade;
        this.estoque_min = estoque_min;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEan13() {
        return ean13;
    }

    public int getEstoque_min() {
        return estoque_min;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }
}

