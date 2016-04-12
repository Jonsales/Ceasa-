package com.john.ceasa.Lists.ItemListView;

/**
 * Created by paulo on 08/01/16.
 */
public class ItemCliente {

    private String nome;
    private String email;



    public ItemCliente( String nome, String email){
        this.setNome(nome);
        this.setEmail(email);

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

}
