package Classes;

public class Cardapio {

    private String nome;
    private Double preco;
    private String descricao;
    private int calorias;
    private int quantidade;

    public Cardapio() {
    }

    public Cardapio(String nome, Double preco, String descricao, int calorias, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.calorias = calorias;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Cardapio{" + "nome=" + nome + ", preco=" + preco + ", descricao=" + descricao + ", calorias=" + calorias + ", quantidade=" + quantidade + '}';
    }

   
       

}
