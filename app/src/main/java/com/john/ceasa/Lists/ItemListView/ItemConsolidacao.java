package com.john.ceasa.Lists.ItemListView;

/**
 * Created by paulo on 08/01/16.
 */
public class ItemConsolidacao {

    private String produto;
    private String qtd;
    private String valorUnitario;
    private String total;



    public ItemConsolidacao(String produto, String qtd, String valorUnitario, String total) {
        this.setProduto(produto);
        this.setQtd(qtd);
        this.setValorUnitario(valorUnitario);
        this.setTotal(total);

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
