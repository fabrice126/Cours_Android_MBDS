package miage.mbds.cours_mbds;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.androidquery.AQuery;

import java.util.ArrayList;
import java.util.List;

public class CommandeCurrentActivity extends AppCompatActivity implements ResultCallBack {

    private AQuery aq;
    private EchangeServeur e;
    private CommandeCurrentItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande_current);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Liste des commandes");
        aq = new AQuery(this);
        e = EchangeServeur.getInstance();

        e.async_list_menu(aq, this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void ResultCallBack() {
        ListView lst = (ListView)this.findViewById(R.id.listViewMenu);

        List<EchangeServeur.Menu> menu = e.getMenu();
        List<Object> produit_menu = new ArrayList<Object>();
        for(int i = 0; i<menu.size();i++) {
            produit_menu.add(menu.get(i).price);
            produit_menu.addAll(menu.get(i).items);
        }
        adapter = new CommandeCurrentItemAdapter(this, produit_menu, lst, e.getProducts());
        lst.setAdapter(adapter);
    }

    @Override
    public void ResultCallBackDelete() {

    }
}
