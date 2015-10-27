package miage.mbds.cours_mbds;

import android.app.Activity;
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
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {


    String editTextNom;
    String editTextPrenom;
    RadioGroup radioGroup;
    RadioButton radioSexButton;
    String editTextTelephonePortable;
    String editTextEmail;
    String editTextMotDePasse;
    String editTextMotDePasseConfirme;
    Button buttonSenregistrer;
    ArrayList<String> alInputRegister;
    private AQuery aq;
    private String url = "http://92.243.14.22/person/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupSexe);
        buttonSenregistrer = (Button) findViewById(R.id.buttonSenregistrer);
        buttonSenregistrer.setOnClickListener(this);
        
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

                int idRadioChecked = radioGroup.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(idRadioChecked);
                String sexe = radioSexButton.getText().toString();
                alInputRegister = new ArrayList<String>();
                ArrayList<Integer> errorTabEditText = new ArrayList<Integer>();
                /*On met dans un tableau tous les ids d'EditText*/
                int tabId[] ={R.id.editTextNom,
                        R.id.editTextPrenom,
                        R.id.editTextTelephonePortable,
                        R.id.editTextEmail,
                        R.id.editTextMotDePasse,
                        R.id.editTextMotDePasseConfirme};

                //Check des EditText Vide
                for (int i =0;i<tabId.length;i++){
                    System.out.println("Test = " + ((EditText) findViewById(tabId[i])).getText().toString());
                    if(((EditText) findViewById(tabId[i])).getText().toString().isEmpty()){
                        errorTabEditText.add(i);
                    }
                }

                /*A supprimer aprés le dév !!!!*/
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                //Si on a pas d'erreur on lance l'activity
                if(errorTabEditText.isEmpty()){
                    //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
                else{
                    //Afficher les croix rouges a coté des elements causant l'erreur
                }
                break;
        }
    }

}
