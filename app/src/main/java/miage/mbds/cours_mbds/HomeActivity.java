package miage.mbds.cours_mbds;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements ResultCallBack,View.OnClickListener{

    private AQuery aq;
    private EchangeServeur e;
    private PersonItemAdapter adapter;
    private Button btnAddServeur;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle params = getIntent().getExtras();
        String nomExtra = params.getString("nom");
        String prenomExtra = params.getString("prenom");
        btnAddServeur = (Button) findViewById(R.id.addServeur);
        btnAddServeur.setOnClickListener(this);

        Toast.makeText(this, "Bienvenue "+prenomExtra+" "+nomExtra, Toast.LENGTH_LONG).show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        aq = new AQuery(this);
        e = new EchangeServeur();
        updateList();
        startActivity(new Intent(this, ProductActivity.class));
    }

    @Override
    public void ResultCallBack() {

        ListView lst = (ListView)this.findViewById(R.id.listView);

        List<EchangeServeur.Person> person = e.getPersons();
        adapter = new PersonItemAdapter(this, person, this);
        lst.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void ResultCallBackDelete() {
        updateList();
        Toast.makeText(aq.getContext(), "Suppression effectuée", Toast.LENGTH_LONG).show();
    }

    public void updateList() {
        e.async_list(aq, this);
    }
    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.addServeur:
                    Intent intent = new Intent(this, RegisterActivity.class);
                    startActivity(intent);
                break;
        }

    }


}
