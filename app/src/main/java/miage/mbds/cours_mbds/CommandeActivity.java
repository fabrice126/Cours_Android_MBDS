package miage.mbds.cours_mbds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommandeActivity extends AppCompatActivity implements ResultCallBack {


    private AQuery aq;
    private EchangeServeur e;
    private CommandeItemAdapter adapter;
    private Commande commande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Commande en cours");
        aq = new AQuery(this);
        e = EchangeServeur.getInstance();
        commande = Commande.getInstance();

        Button button_commande = (Button) findViewById(R.id.passCommande);
        button_commande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    envoyer_commande();
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });


        if(commande.getProducts().size() == 0) {
            button_commande.setVisibility(View.GONE);
        } else {
            ListView lst = (ListView)this.findViewById(R.id.listView);

            adapter = new CommandeItemAdapter(this, commande.getProducts_tri(), lst);
            lst.setAdapter(adapter);

            TextView title = (TextView)findViewById(R.id.aucun_produit);
            title.setVisibility(View.GONE);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void envoyer_commande() throws JSONException {

        int price = 0;
        int discount = 0;

        JSONArray items = new JSONArray();
        for(int i = 0; i<commande.getProducts().size();i++) {
            JSONObject item = new JSONObject();
            item.put("id",commande.getProducts().get(i).id);
            items.put(item);
            price += commande.getProducts().get(i).price;
            discount += commande.getProducts().get(i).discount;
        }

        JSONObject server = new JSONObject();
        server.put("id", commande.getServeur().id);

        JSONObject cooker = new JSONObject();
        cooker.put("id",commande.getCooker().id);

        JSONObject obj = new JSONObject();
        obj.put("price",price);
        obj.put("discount",discount);
        obj.put("server",server);
        obj.put("cooker",cooker);
        obj.put("items",items);

        e.async_menu(obj, aq, this);

    }

    @Override
    public void ResultCallBack() {

        Toast.makeText(this, "Commande envoyée", Toast.LENGTH_LONG).show();
        Commande.getInstance().getProducts().clear();

        startActivity(new Intent(this, ProductActivity.class));

    }

    @Override
    public void ResultCallBackDelete() {

    }
}
