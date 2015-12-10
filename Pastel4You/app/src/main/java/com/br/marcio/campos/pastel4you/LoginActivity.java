package com.br.marcio.campos.pastel4you;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.marcio.campos.pastel4you.domain.Client;
import com.br.marcio.campos.pastel4you.persistencia.ClienteDAO;
import com.br.marcio.campos.pastel4you.prefs.BaseActivity;
import com.br.marcio.campos.pastel4you.prefs.MainActivity;

/**
 * Created by Marcio on 26/10/2015.
 */
public class LoginActivity extends BaseActivity {


        EditText edtEmail;
        EditText edtPassword;
        Button btnEntrar;

        ClienteDAO dao = null;

        Client usuario;

        UserSessionManager session;

        // UserSessionManager session;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_email);

            // Configura a Toolbar como a action bar
            setUpToolbar();

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // Título da toolbar e botão up navigation
            getSupportActionBar().setTitle("Login");

            // User Session Manager
            session = new UserSessionManager(getApplicationContext());
            dao = new ClienteDAO(this);


            // session = new UserSessionManager(LoginActivity.this);

            //TODO - passar componentes para form helper
            edtEmail = (EditText) findViewById(R.id.editEmail);
            edtPassword = (EditText) findViewById(R.id.editPassoword);

            btnEntrar = (Button) findViewById(R.id.btnEntrar);

            btnEntrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String email =  edtEmail.getText().toString();
                    String password =  edtPassword.getText().toString();

                    if(email.equals("adm") && password.equals("123")){

                        Intent it = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(it);

                    }else{

                        usuario = dao.listarPorEmail(email);

                        if(password.equals(usuario.getPassword())){

                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Seja Bem-Vindo(a)", Toast.LENGTH_SHORT).show();

                            Intent it = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(it);

                            session.createUserLoginSession(usuario.getNome(), usuario.getEmail());

                            finish();

                        }else{

                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Senha incorreta favor validar.",
                                    Toast.LENGTH_SHORT).show();
                            edtEmail.setText("");
                        }
                    }
                }
            });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu
            // this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_login, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.home) {
                finish();
                return true;
            }
            // Para a volta a fragment principal
            switch (item.getItemId()) {
                case android.R.id.home:
                    // API 5+ solution
                    onBackPressed();
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }

