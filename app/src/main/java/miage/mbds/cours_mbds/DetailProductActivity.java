package miage.mbds.cours_mbds;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;

public class DetailProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        Bundle bundle = getIntent().getExtras();
        final EchangeServeur.Product product = bundle.getParcelable("miage.mbds.cours_mbds.EchangeServeur.Product");

        Log.d("impossible", "ca marche pas"+product.name);

        TextView name= (TextView)this.findViewById(R.id.name);
        name.setText(product.name);
        TextView description= (TextView)this.findViewById(R.id.description);
        description.setText(product.description);
        ImageView image= (ImageView)this.findViewById(R.id.image);

        AQuery aq = new AQuery(this);
        aq.id(image).image(product.picture);

        TextView price= (TextView)this.findViewById(R.id.price);
        price.setText(String.valueOf(product.price));
        TextView calories= (TextView)this.findViewById(R.id.calories);
        calories.setText(String.valueOf(product.calories));
        TextView type= (TextView)this.findViewById(R.id.type);
        type.setText(product.type);
        TextView discount= (TextView)this.findViewById(R.id.discount);
        discount.setText(String.valueOf(product.discount));
        Button ajouter= (Button) this.findViewById(R.id.addProduct);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Commande.getInstance().getProducts().add(product);
                Toast.makeText(getApplicationContext(), "Produit ajouté", Toast.LENGTH_LONG).show();
            }
        });
    }
}
