package produtos;
import java.time.LocalDateTime;
import java.util.UUID;

public class Produto {
    private int id;
    private UUID hash;
    private String nome;
    private String descricao;
    private String ean13;
    private double preco;
    private int quantidade;
    private int estoque_min;
    private LocalDateTime dtcreate;
    private LocalDateTime dtupdate;
    private Boolean ativo;

    public Produto( String nome, String descricao, String ean13, double preco,
                   int quantidade, int estoque_min) {

        if(preco < 0 || quantidade < 0 || estoque_min < 0){
            throw new IllegalArgumentException("Valores não podem ser negativos");
        }

        this.id = id;
        this.hash = UUID.randomUUID();
        this.nome = nome;
        this.descricao = descricao;
        this.ean13 = ean13;
        this.preco = preco;
        this.quantidade = quantidade;
        this.estoque_min = estoque_min;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getHash() {
        return hash;
    }

    public void setHash(UUID hash) {
        this.hash = hash;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEan13() {
        return ean13;
    }

    public void setEan13(String ean13) {
        this.ean13 = ean13;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getEstoque_min() {
        return estoque_min;
    }

    public void setEstoque_min(int estoque_min) {
        this.estoque_min = estoque_min;
    }

    public LocalDateTime getDtcreate() {
        return dtcreate;
    }

    public void setDtcreate(LocalDateTime dtcreate) {
        this.dtcreate = dtcreate;
    }

    public LocalDateTime getDtupdate() {
        return dtupdate;
    }

    public void setDtupdate(LocalDateTime dtupdate) {
        this.dtupdate = dtupdate;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }


}




