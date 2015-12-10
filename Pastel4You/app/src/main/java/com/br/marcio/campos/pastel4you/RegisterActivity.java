package com.br.marcio.campos.pastel4you;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.br.marcio.campos.pastel4you.domain.Client;
import com.br.marcio.campos.pastel4you.helper.ClienteFormularioHelper;
import com.br.marcio.campos.pastel4you.location.gpstracker.AppLocationService;
import com.br.marcio.campos.pastel4you.location.gpstracker.GPSTracker;
import com.br.marcio.campos.pastel4you.location.gpstracker.GpsLocation;
import com.br.marcio.campos.pastel4you.persistencia.ClienteDAO;
import com.br.marcio.campos.pastel4you.prefs.BaseActivity;
import com.br.marcio.campos.pastel4you.prefs.MainActivity;

/**
 * Created by Marcio on 27/10/2015.
 */
public class RegisterActivity extends BaseActivity {

    EditText edtNome;
    EditText edtEmail;
    EditText edtPassword;
    Button btnRegistrar;
    EditText edtBairro;
    EditText edtRua;
    EditText edtNumero;
    EditText edtCEP;

    UserSessionManager session;

    ClienteDAO dao = null;
    private ClienteFormularioHelper helper;

    // Singleton
    private static RegisterActivity instance = null;


    // Para o GPS!
    AppLocationService appLocationService;
    TextView textEnd = null;
    String rua = "";
    private static final String[] INITIAL_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS
    };
    private static final String[] LOCATION_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION
    };


    public static RegisterActivity getInstance(){
        return instance;
    }

    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // User Session Manager
        session = new UserSessionManager(getApplicationContext());


        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        dao = new ClienteDAO(this);

        /*List<Client> clients = dao.listar();

        for (Client c: clients) {
            Log.i("Cliente", c.getNome());
        }*/

        helper = new ClienteFormularioHelper(this);

        // Configura a Toolbar como a action bar
        setUpToolbar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Título da toolbar e botão up navigation
        getSupportActionBar().setTitle("Registre-se");

        // Todo: Location comentada
        // Cria um app pra localização
        /*
        appLocationService = new AppLocationService(this);

        Location gpsLocation = appLocationService
                .getLocation(LocationManager.GPS_PROVIDER);

        if (gpsLocation != null) {
            double latitude = gpsLocation.getLatitude();
            double longitude = gpsLocation.getLongitude();
            String result = "Latitude: " + gpsLocation.getLatitude() +
                    " Longitude: " + gpsLocation.getLongitude();
        } else {
            showSettingsAlert();
        }

        // Adquire a referência ao Location Manager
        LocationManager locationManager = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);

        // Define o listener que responde às atualizações de localização

        */
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Client client = helper.getClient();

                    String msg = helper.CheckField();

                    if(msg.equals("OK")){
                        // Salvando

                        dao.cadastrar(client);

                        Intent it = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(it);

                        session.createUserLoginSession(client.getNome(), client.getEmail());

                        finish();

                    }else{
                        Toast.makeText(getApplicationContext(),
                                msg, Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    e.getStackTrace();
                }
            }
        });
    }

    private GpsLocation getMyLocation() {
        // GPSTracker class
        GPSTracker gps = new GPSTracker(RegisterActivity.this);
        GpsLocation location = null;

        // Verificando se o GPS está habilitado
        if(gps.canGetLocation()){
            location = new GpsLocation();
            location.setLatitude(gps.getLatitude());
            location.setLongitude(gps.getLongitude());

        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        return location;
    }

    // Metodo de Alert! para localização não realizada..
    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("GPS desativado! Vamos ativar?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    private class GeocoderHandler extends android.os.Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }

                Toast.makeText(getApplicationContext(),
                        "Sua Localização é :- \n"+ locationAddress, Toast.LENGTH_LONG).show();

                /*String[] endBreak = locationAddress.split(Pattern.quote("\n"));
                rua = (endBreak[0].substring(2, endBreak[0].lastIndexOf(',')));
                String bairro = (endBreak[0].substring(endBreak[0].lastIndexOf('-')+1));
                endBreak[0].lastIndexOf(',');
                endBreak[0].length();
                edtRua.setText(rua);
                edtBairro.setText(bairro);*/

        }
    }

    public RegisterActivity(){}

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the afction bar if it is present.
            getMenuInflater().inflate(R.menu.menu_register, menu);
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
