package com.john.ceasa.Utils;

import android.widget.EditText;

import com.john.ceasa.Lists.AdapterListView.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;

/**
 * Created by paulo on 20/01/16.
 */
public class Valida {

    public boolean email(EditText mail){
        if (getString(mail).matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+") ||
                getString(mail).matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.[a-z]+") &&
                        getString(mail).length() > 0) {

            return true;
        }
        else
            return false;

    }
    private String getString(EditText e1){
        return e1.getText().toString().trim().equals("") ? " " : e1.getText().toString();
    }

    public boolean formEmpty() {
        return (isEditTextEmpty(AdapterPFisica.nome) || isEditTextEmpty(AdapterPFisica.cpf) ||
                isEditTextEmpty(AdapterPFisica.tel) || isEditTextEmpty(AdapterPFisica.tel) ||
                isEditTextEmpty(AdapterPFisica.mail) || isEditTextEmpty(AdapterPFisica.cep) ||
                isEditTextEmpty(AdapterPFisica.end) || isEditTextEmpty(AdapterPFisica.bairro) ||
                isEditTextEmpty(AdapterPFisica.numero) || isEditTextEmpty(AdapterPFisica.compl));
    }
    public boolean formEmptyJuridico() {
        return (isEditTextEmpty(AdapterPJuridica.nome) || isEditTextEmpty(AdapterPJuridica.cnpj) ||
                isEditTextEmpty(AdapterPJuridica.tel) || isEditTextEmpty(AdapterPJuridica.tel) ||
                isEditTextEmpty(AdapterPJuridica.mail) || isEditTextEmpty(AdapterPJuridica.cep) ||
                isEditTextEmpty(AdapterPJuridica.end) || isEditTextEmpty(AdapterPJuridica.bairro) ||
                isEditTextEmpty(AdapterPJuridica.numero) || isEditTextEmpty(AdapterPJuridica.compl));
    }
    private boolean isEditTextEmpty(EditText e1){
        String email1Text;
        email1Text = e1.getText().toString();
        return email1Text.isEmpty();
    }

    public static boolean isCNPJ(String CNPJ) {
    // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
            || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
            || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
            || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
            || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
            || (CNPJ.length() != 14))
            return(false);
        char dig13, dig14;
        int sm, i, r, num, peso;
         // "try" - protege o código para eventuais erros de conversao de tipo (int)
         try {
         // Calculo do 1o. Digito Verificador
             sm = 0;
             peso = 2;
             for (i=11; i>=0; i--) {
                 // converte o i-ésimo caractere do CNPJ em um número:
                 // por exemplo, transforma o caractere '0' no inteiro 0 // (48 eh a posição de '0' na tabela ASCII)
                 num = (int)(CNPJ.charAt(i) - 48);
                 sm = sm + (num * peso);
                 peso = peso + 1;
                 if (peso == 10)
                     peso = 2;
             }
             r = sm % 11;
             if ((r == 0) || (r == 1))
                 dig13 = '0';
             else
                 dig13 = (char)((11-r) + 48);
                  // Calculo do 2o. Digito Verificador
             sm = 0;
             peso = 2;
             for (i=12; i>=0; i--) {
                 num = (int)(CNPJ.charAt(i)- 48);
                 sm = sm + (num * peso);
                 peso = peso + 1;
                 if (peso == 10)
                     peso = 2;
             }
             r = sm % 11;
             if ((r == 0) || (r == 1))
                 dig14 = '0';
             else
                 dig14 = (char)((11-r) + 48);
                 // Verifica se os dígitos calculados conferem com os dígitos informados.
             if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
                 return(true);
             else
                 return(false);
         } catch (InputMismatchException erro) {
             return(false);
         }
    }



    public static boolean isCPF(String CPF) {

        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") ||
                CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") ||
                CPF.equals("99999999999") || (CPF.length() != 11))
            return(false);
        char dig10, dig11;
        int sm, i, r, num, peso;
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0; peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm += + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char)(r + 48);
            // converte no respectivo caractere numerico // Calculo do 2o. Digito Verificador
            sm = 0; peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else
                return(false);
        } catch (InputMismatchException erro) {
            return(false);

        }

    }
    public static boolean isValidDate(String date) {
        Calendar cal = GregorianCalendar.getInstance();
        String[] newDate = date.split("/");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date testDate = null;
        try{
            testDate = sdf.parse(date);
        } catch (ParseException e){
            return false;
        }
        if (!sdf.format(testDate).equals(date)){
            return false;
        }
        if (Integer.parseInt(newDate[2]) < 1900 || Integer.parseInt(newDate[2]) > cal.get(Calendar.YEAR))
            return false;

        return true;

    } // end isValidDate

    public static boolean isValidTel(String tel){
        if (tel.length() >= 6)
            return true;
        else
            return false;
    }


    public boolean validEditTel(){
        if(!telIsValid(AdapterPFisica.tel)) {
            return false;
        }
        if (arrayEmpty(AdapterPFisica.listaTelefones)) {
            for (EditText e : AdapterPFisica.listaTelefones) {
                if (!telIsValid(e)) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean validEditTelJuridico(){
        if(!telIsValid(AdapterPJuridica.tel)) {
            return false;
        }
        if (arrayEmpty(AdapterPJuridica.listaTelefones)) {
            for (EditText e : AdapterPJuridica.listaTelefones) {
                if (!telIsValid(e)) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean arrayEmpty(ArrayList<EditText> lista){
        if (lista.size() > 0)
            return true;
        return false;
    }
    public boolean mailIsValid(EditText mail){
        if(!email(mail))
            return false;
        else
            return  true;
    }


    public boolean validEditMail(){
        if(!mailIsValid(AdapterPFisica.mail)) {
            return false;
        }
        if (arrayEmpty(AdapterPFisica.listaEmails)) {
            for (EditText e : AdapterPFisica.listaEmails) {
                if (!mailIsValid(e)) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean validEditMailJuridico(){
        if(!mailIsValid(AdapterPJuridica.mail)) {
            return false;
        }
        if (arrayEmpty(AdapterPJuridica.listaEmails)) {
            for (EditText e : AdapterPJuridica.listaEmails) {
                if (!mailIsValid(e)) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean telIsValid(EditText tel){
        if(!isValidTel(String.valueOf(getString(tel))))
            return false;
        else
            return  true;
    }
}
