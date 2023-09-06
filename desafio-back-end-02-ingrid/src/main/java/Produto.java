import java.util.Date;
import java.util.UUID;

public class Produto {
    private Long id;
    private UUID hash;
    private String nome;
    private String descricao;
    private String ean13;
    private double preco;
    private int quantidade;
    private int estoque_min;
    private Date dtcreate;
    private Date dtupdate;
    private Boolean ativo;

    public Produto(Long id, UUID hash, String nome, String descricao, String ean13, double preco, int quantidade, int estoque_min, Date dtcreate, Date dtupdate, Boolean ativo) {
        this.id = id;
        this.hash = hash;
        this.nome = nome;
        this.descricao = descricao;
        this.ean13 = ean13;
        this.preco = preco;
        this.quantidade = quantidade;
        this.estoque_min = estoque_min;
        this.dtcreate = dtcreate;
        this.dtupdate = dtupdate;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getDtcreate() {
        return dtcreate;
    }

    public void setDtcreate(Date dtcreate) {
        this.dtcreate = dtcreate;
    }

    public Date getDtupdate() {
        return dtupdate;
    }

    public void setDtupdate(Date dtupdate) {
        this.dtupdate = dtupdate;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}




