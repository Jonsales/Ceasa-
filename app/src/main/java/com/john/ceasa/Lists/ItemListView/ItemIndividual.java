package com.john.ceasa.Lists.ItemListView;

/**
 * Created by paulo on 08/01/16.
 */
public class ItemIndividual {
    private String data;
    private String hora;
    private String nome;
    private String email;
    private String produto;
    private String qtd;
    private String valorUnitario;
    private String total;



    public ItemIndividual(String data, String hora, String nome, String email, String produto,String qtd, String valorUnitario, String total) {
        this.setData(data);
        this.setHora(hora);
        this.setNome(nome);
        this.setEmail(email);
        this.setProduto(produto);
        this.setQtd(qtd);
        this.setValorUnitario(valorUnitario);
        this.setTotal(total);

    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }
}
