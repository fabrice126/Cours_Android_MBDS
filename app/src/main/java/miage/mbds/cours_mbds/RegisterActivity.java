package miage.mbds.cours_mbds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.androidquery.AQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {


    EditText editTextNom;
    EditText editTextPrenom;
    RadioGroup radioGroup;
    RadioButton radioSexButton;
    EditText editTextTelephonePortable;
    EditText editTextEmail;
    EditText editTextMotDePasse;
    EditText editTextMotDePasseConfirme;
    Button buttonSenregistrer;
    ArrayList<String> alInputRegister;
    View focusViewMail = null;
    View focusViewPortable = null;
    View focusViewConfirmeMotDePasse = null;
    private AQuery aq;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextNom = (EditText)findViewById(R.id.editTextNom);
        editTextPrenom = (EditText)findViewById(R.id.editTextPrenom);
        editTextTelephonePortable = (EditText)findViewById(R.id.editTextTelephonePortable);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextMotDePasse = (EditText)findViewById(R.id.editTextMotDePasse);
        editTextMotDePasseConfirme = (EditText)findViewById(R.id.editTextMotDePasseConfirme);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupSexe);
        buttonSenregistrer = (Button) findViewById(R.id.buttonSenregistrer);
        buttonSenregistrer.setOnClickListener(this);
        aq = new AQuery(this);
    }


    /* TextWatcher Implementation Methods */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    // Implement the OnClickListener callback
    public void onClick(View v) {
        // do something when the button is clicked
        final int id = v.getId();
        switch (id) {
            case R.id.buttonSenregistrer:
                boolean cancelRegistration = false;
                int idRadioChecked = radioGroup.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(idRadioChecked);
                String sexe = radioSexButton.getText().toString();
                alInputRegister = new ArrayList<String>();
                /*On met dans un tableau tous les ids d'EditText*/
                //ArrayList<View> alViewError = new ArrayList<View>();

                int tabId[] ={R.id.editTextNom,
                        R.id.editTextPrenom,
                        R.id.editTextTelephonePortable,
                        R.id.editTextEmail,
                        R.id.editTextMotDePasse,
                        R.id.editTextMotDePasseConfirme};

                if(!isEmailValid(editTextEmail.getText().toString())){
                    editTextEmail.setError(getString(R.string.error_invalid_email));
                    cancelRegistration = true;
                }
                if(!isIdenticalPassword(editTextMotDePasse.getText().toString(),editTextMotDePasseConfirme.getText().toString())){
                    editTextMotDePasseConfirme.setError(getString(R.string.error_incorrect_password));
                    cancelRegistration = true;
                }
                //Check des EditText Vide
                for (int i =0;i<tabId.length;i++){
                    System.out.println("Test = " + ((EditText) findViewById(tabId[i])).getText().toString());
                    if(((EditText) findViewById(tabId[i])).getText().toString().isEmpty()){
                        ((EditText) findViewById(tabId[i])).setError("Veuillez remplir le champs");
                        cancelRegistration = true;
                    }
                }


                /*A supprimer aprés le dév !!!!*/

                //Si cancelRegistration = true on annule l'enregistrement et on lance les erreurs
                if(cancelRegistration){

                }
                else{
                    //post_register();
                    async_transformer();
                    //Afficher les croix rouges a coté des elements causant l'erreur
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
                break;
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());
        return m.matches();
    }
    private boolean isIdenticalPassword(String password,String passwordConfirm){
        if(password.equals(passwordConfirm)){
            return true;
        }
        return false;
    }

    public void async_transformer(){

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nom",editTextNom.getText().toString());
        params.put("prenom",editTextPrenom.getText().toString());
        params.put("telephone",editTextTelephonePortable.getText().toString());
        params.put("email",editTextEmail.getText().toString());
        params.put("createdby","Linares");
        params.put("password",editTextMotDePasse.getText().toString());
        params.put("sexe", radioSexButton.getText().toString());

        EchangeServeur e = new EchangeServeur();
        e.async_register(params, aq);

        /*aq.transformer(t).ajax(url, params, Profil.class, new AjaxCallback<Profil>() {
            public void callback(String url, Profil profil, AjaxStatus status) {
                Gson gson = new Gson();

                Log.d("Réponse", gson.toJson(profil));
                Log.d("Nom du mec", profil.nom);
            }
        });*/

    }


}