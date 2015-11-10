package miage.mbds.cours_mbds;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

<<<<<<< HEAD:app/src/main/java/miage/mbds/cours_mbds/cours_mbds/HomeActivity.java
import com.androidquery.AQuery;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements ResultCallBack{

    private AQuery aq;
    private EchangeServeur e;
=======
public class HomeActivity extends AppCompatActivity {
>>>>>>> parent of 1c8d514... commit de merge:app/src/main/java/miage/mbds/cours_mbds/HomeActivity.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle params = getIntent().getExtras();
        String nomExtra = params.getString("nom");
        String prenomExtra = params.getString("prenom");

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
        e.async_list(aq);
    }

    @Override
    public void ResultCallBack() {

        ListView lst = (ListView)this.findViewById(R.id.listView);

        List<EchangeServeur.Person> person = e.getPerson();
        PersonItemAdapter adapter = new PersonItemAdapter(this, person);
        lst.setAdapter(adapter);
    }
}
