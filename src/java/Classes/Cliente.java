package Classes;

import java.util.Date;

public class Cliente {

    private String nome;
    private String celular;
    private String endereço;
    private Date dataPedido;

    public Cliente() {
    }

    public Cliente(String nome, String celular, String endereço, Date dataPedido) {
        this.nome = nome;
        this.celular = celular;
        this.endereço = endereço;
        this.dataPedido = dataPedido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nome=" + nome + ", celular=" + celular + ", endere\u00e7o=" + endereço + ", dataPedido=" + dataPedido + '}';
    }

    
}
