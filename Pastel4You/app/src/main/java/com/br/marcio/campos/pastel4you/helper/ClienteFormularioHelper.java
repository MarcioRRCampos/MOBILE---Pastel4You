package com.br.marcio.campos.pastel4you.helper;

import android.widget.EditText;

import com.br.marcio.campos.pastel4you.R;
import com.br.marcio.campos.pastel4you.RegisterActivity;
import com.br.marcio.campos.pastel4you.domain.Client;

/**
 * Created by marci_000 on 07/12/2015.
 */
public class ClienteFormularioHelper {

    EditText edtNome, edtEmail, edtPassword,edtPasswordConfirm, edtBairro, edtRua, edtNumero, edtCEP, edtFone;
    private Client client = null;

    public ClienteFormularioHelper(RegisterActivity activity) {
        edtNome = (EditText) activity.findViewById(R.id.editNome);
        edtEmail = (EditText) activity.findViewById(R.id.editEmail);
        edtFone = (EditText) activity.findViewById(R.id.editFone);
        edtPassword = (EditText) activity.findViewById(R.id.editPassoword);
        edtPasswordConfirm = (EditText) activity.findViewById(R.id.editPassowordConfirm);
        edtBairro = (EditText) activity.findViewById(R.id.editBairro);
        edtRua = (EditText) activity.findViewById(R.id.editRua);
        edtNumero = (EditText) activity.findViewById(R.id.editNumero);
        edtCEP = (EditText) activity.findViewById(R.id.editCEP);
        client = new Client();
    }

    public Client getClient (){
        client.setNome(edtNome.getText().toString());
        client.setEmail(edtEmail.getText().toString());
        client.setTelefone(edtFone.getText().toString());
        client.setPassword(edtPassword.getText().toString());
        client.setPasswordConfirm(edtPasswordConfirm.getText().toString());
        client.setBairro(edtBairro.getText().toString());
        client.setRua(edtRua.getText().toString());
        client.setNumero(Integer.parseInt(edtNumero.getText().toString()));
        client.setCep(edtCEP.getText().toString());
        return client;
    }

    public void setClient(Client client){
        edtNome.setText(client.getNome());
        edtEmail.setText(client.getEmail());
        edtFone.setText(client.getTelefone());
        edtPassword.setText(client.getPassword());
        edtPasswordConfirm.setText(client.getPasswordConfirm());
        edtBairro.setText(client.getBairro());
        edtRua.setText(client.getRua());
        edtNumero.setText(client.getNumero());
        edtCEP.setText(client.getCep());
        this.client = client;
    }

    public String CheckField(){

        return CheckFormularioClientHelper.CheckRegistrationFields(edtNome, edtEmail, edtPassword, edtPasswordConfirm, edtFone, edtRua, edtBairro, edtNumero, edtCEP);
    }




}