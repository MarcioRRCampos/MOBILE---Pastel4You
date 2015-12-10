package com.br.marcio.campos.pastel4you;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.marcio.campos.pastel4you.domain.Client;
import com.br.marcio.campos.pastel4you.helper.CheckFormularioClientHelper;
import com.br.marcio.campos.pastel4you.helper.ClienteFormularioHelper;
import com.br.marcio.campos.pastel4you.persistencia.ClienteDAO;

import java.util.HashMap;


public class AccountFragment extends Fragment {

    EditText edit_nome, edit_email, edit_passoword,edit_passoword_confir,edit_fone, edit_rua, edit_bairro, edit_numero,edit_cep;
    Button btn_alterar;
    ClienteDAO dao = null;
    boolean andamentoAlteracao = false;
    private ClienteFormularioHelper helper;
    Client usuario;
    HashMap<String, String> user;

    // User Session Manager Class
    UserSessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        //TODO: Alterar o nome da action Bar para Account

        edit_nome = (EditText) rootView.findViewById(R.id.ed_nome);
        disableEditText(edit_nome);
        edit_email = (EditText) rootView.findViewById(R.id.ed_email);
        disableEditText(edit_email);
        edit_passoword = (EditText) rootView.findViewById(R.id.edit_passoword);
        disableEditText(edit_passoword);
        edit_passoword_confir  = (EditText) rootView.findViewById(R.id.edit_password_confir);
        disableEditText(edit_passoword_confir);
        edit_fone = (EditText) rootView.findViewById(R.id.edit_fone);
        disableEditText(edit_fone);
        edit_rua = (EditText) rootView.findViewById(R.id.edit_rua);
        disableEditText(edit_rua);
        edit_bairro = (EditText) rootView.findViewById(R.id.edit_bairro);
        disableEditText(edit_bairro);
        edit_numero = (EditText) rootView.findViewById(R.id.edit_numero);
        disableEditText(edit_numero);
        edit_cep = (EditText) rootView.findViewById(R.id.edit_cep);
        disableEditText(edit_cep);

        btn_alterar = (Button) rootView.findViewById(R.id.btn_alterar);

        session = new UserSessionManager(getActivity().getApplicationContext());

        // get user data from session
        user = session.getUserDetails();

        // get name
        String name = user.get(UserSessionManager.KEY_NAME);

        // get email
        String email = user.get(UserSessionManager.KEY_EMAIL);

        dao = new ClienteDAO(getContext());

        usuario = dao.listarPorEmail(user.get(UserSessionManager.KEY_EMAIL));

        edit_nome.setText(usuario.getNome());
        edit_email.setText(usuario.getEmail());
        edit_passoword.setText(usuario.getPassword());
        edit_passoword_confir.setText(usuario.getPassword());
        edit_fone.setText(usuario.getTelefone());
        edit_rua.setText(usuario.getRua());
        edit_bairro.setText(usuario.getBairro());
        edit_numero.setText(String.valueOf(usuario.getNumero()));
        edit_cep.setText(usuario.getCep());

        btn_alterar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(andamentoAlteracao == false) {

                    Toast.makeText(getActivity().getApplicationContext(), "Realize as alterações necessárias e click em alterar",
                            Toast.LENGTH_SHORT).show();

                    habiltaAlterar(edit_nome);
                    habiltaAlterar(edit_email);
                    habiltaAlterar(edit_passoword);
                    habiltaAlterar(edit_passoword_confir);
                    habiltaAlterar(edit_fone);
                    habiltaAlterar(edit_rua);
                    habiltaAlterar(edit_bairro);
                    habiltaAlterar(edit_numero);
                    habiltaAlterar(edit_nome);
                    habiltaAlterar(edit_cep);
                    andamentoAlteracao = true;

                }else{

                    String message = CheckFormularioClientHelper.CheckRegistrationFields(edit_nome, edit_email, edit_passoword, edit_passoword_confir,
                            edit_fone, edit_rua, edit_bairro, edit_numero, edit_cep);

                    if(message.equals("OK")){

                        Client upUser = new Client();

                        upUser.setNome(edit_nome.getText().toString());
                        upUser.setEmail(edit_email.getText().toString());
                        upUser.setPassword(edit_passoword.getText().toString());
                        upUser.setTelefone(edit_fone.getText().toString());
                        upUser.setRua(edit_rua.getText().toString());
                        upUser.setBairro(edit_bairro.getText().toString());
                        upUser.setNumero(Integer.parseInt(edit_numero.getText().toString()));
                        upUser.setCep(edit_cep.getText().toString());

                        dao.Alterar(usuario);

                        disableEditText(edit_nome);
                        disableEditText(edit_email);
                        disableEditText(edit_passoword);
                        disableEditText(edit_passoword_confir);
                        disableEditText(edit_fone);
                        disableEditText(edit_rua);
                        disableEditText(edit_bairro);
                        disableEditText(edit_numero);
                        disableEditText(edit_nome);
                        disableEditText(edit_cep);

                        andamentoAlteracao = false;

                        message = "Alteração realizada com sucesso.";
                    }

                    Toast.makeText(getActivity().getApplicationContext(), message,
                            Toast.LENGTH_SHORT).show();
                }

            }


        });

        return  rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onStop() {
        super.onStop();

    }

    private void disableEditText(EditText editText) {
        editText.setEnabled(false);
        editText.setInputType(InputType.TYPE_NULL);
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setFocusable(false);

    }

    private void habiltaAlterar(EditText editText) {
        editText.setFocusableInTouchMode(true);
        editText.setFocusable(true);
        editText.setEnabled(true);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setFocusable(true);

    }

}
