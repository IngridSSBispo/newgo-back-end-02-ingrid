package application.dto;

public class CreateDTO {

    private String nome;
    private String descricao;
    private String ean13;
    private String preco;
    private String quantidade;
    private String estoque_min;
    private int estoue_min_convertido ;
    private double preco_conertido ;
    private int quantidade_convertida  ;

    public CreateDTO(String nome, String descricao, String ean13, String preco, String quantidade, String estoque_min, int estoue_min_convertido, double preco_conertido, int quantidade_convertida) {
        this.nome = nome;
        this.descricao = descricao;
        this.ean13 = ean13;
        this.preco = preco;
        this.quantidade = quantidade;
        this.estoque_min = estoque_min;
        this.estoue_min_convertido = estoue_min_convertido;
        this.preco_conertido = preco_conertido;
        this.quantidade_convertida = quantidade_convertida;
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

    public String getPreco() {
        return preco;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public String getEstoque_min() {
        return estoque_min;
    }

    public int getEstoue_min_convertido() {
        return estoue_min_convertido;
    }

    public double getPreco_conertido() {
        return preco_conertido;
    }

    public int getQuantidade_convertida() {
        return quantidade_convertida;
    }
}

