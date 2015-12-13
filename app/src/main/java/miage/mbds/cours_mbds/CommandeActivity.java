package miage.mbds.cours_mbds;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.androidquery.AQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommandeActivity extends AppCompatActivity {


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

        aq = new AQuery(this);
        e = new EchangeServeur();
        commande = Commande.getInstance();

        ListView lst = (ListView)this.findViewById(R.id.listView);

        adapter = new CommandeItemAdapter(this, commande.getProducts(), lst);
        lst.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    envoyer_commande();
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void envoyer_commande() throws JSONException {

        int price = 0;
        int discount = 0;

        JSONArray items = new JSONArray();
        Log.d("size", "" + commande.getProducts().size());
        for(int i = 0; i<commande.getProducts().size();i++) {
            JSONObject item = new JSONObject();
            item.put("id",commande.getProducts().get(i).id);
            items.put(item);
            price += commande.getProducts().get(i).price;
            discount += commande.getProducts().get(i).discount;
        }

        JSONObject server = new JSONObject();
        server.put("id", "12343567");

        JSONObject cooker = new JSONObject();
        cooker.put("id","12343567");

        JSONObject obj = new JSONObject();
        obj.put("price",price);
        obj.put("discount",discount);
        obj.put("server",server);
        obj.put("cooker",cooker);
        obj.put("items",items);

        JSONObject ob = new JSONObject();
        ob.put("0", obj);

        e.async_menu(ob, aq);

    }

}
