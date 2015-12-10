package com.br.marcio.campos.pastel4you.helper;

import android.widget.EditText;

/**
 * Created by marci_000 on 10/12/2015.
 */
public class CheckFormularioClientHelper {

    // Todo: Validar CEP e email como validos
    public static String CheckRegistrationFields(EditText edit_nome, EditText edit_email, EditText edit_passoword,EditText edit_passoword_confir,
                                          EditText edit_fone, EditText edit_rua, EditText edit_bairro,EditText edit_numero,EditText edit_cep){

        String message = "";

        if(     edit_nome.getText().toString().equals("") &&
                edit_email.getText().toString().equals("") &&
                edit_passoword.getText().toString().equals("") &&
                edit_passoword_confir.getText().toString().equals("") &&
                edit_fone.getText().toString().equals("") &&
                edit_rua.getText().toString().equals("") &&
                edit_bairro.getText().toString().equals("") &&
                edit_numero.getText().toString().equals("") &&
                edit_cep.getText().toString().equals("") ) {

            return message = "Favor validar preechimento dos campos.";
        }
        if((!(edit_passoword.getText().toString().length() >= 6))|| ((edit_passoword.getText().toString().length() > 8))) {

            return message = "A senha deve conter minimo de seis e emaximo de oito caracteres.";
        }

        if(!(edit_passoword.getText().toString().equals(edit_passoword_confir.getText().toString()))){

            return message = "A senhas devem ser iguais.";
        }


        return message = "OK";

    }
}
