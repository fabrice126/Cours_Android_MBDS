package miage.mbds.cours_mbds;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;

import java.util.List;

/**
 * Created by user on 08/12/2015.
 */
public class ProductItemAdapter extends BaseAdapter {

    private Context context;
    public List<EchangeServeur.Product> products;

    public ProductItemAdapter(Context context, List<EchangeServeur.Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int arg0) {
        return products.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        View v = convertView;

        ProductViewHolder viewHolder = null;
        if(v==null){
            v = View.inflate(context, R.layout.fragment_list_product, null);
            viewHolder = new ProductViewHolder();
            viewHolder.name= (TextView)v.findViewById(R.id.name);
            viewHolder.image= (ImageView)v.findViewById(R.id.image);
            viewHolder.price= (TextView)v.findViewById(R.id.price);
            viewHolder.ajouter= (Button) v.findViewById(R.id.addProduct);
            viewHolder.detail= (Button) v.findViewById(R.id.detail);
            v.setTag(viewHolder);
        }
        else{
            viewHolder = (ProductViewHolder) v.getTag();
        }
        final EchangeServeur.Product product = products.get(position);
        viewHolder.name.setText(product.name);
        viewHolder.price.setText(String.valueOf(product.price));
        viewHolder.ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Commande.getInstance().getProducts().add(product);
                Toast.makeText(context, "Produit ajouté", Toast.LENGTH_LONG).show();
            }
        });
        viewHolder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailProductActivity.class);
                intent.putExtra("miage.mbds.cours_mbds.EchangeServeur.Product", product);
                context.startActivity(intent);
            }
        });

        AQuery aq = new AQuery(v);
        aq.id(viewHolder.image).image(product.picture);

        return v;
    }

    class ProductViewHolder{
        TextView name;
        ImageView image;
        TextView price;
        Button ajouter;
        Button detail;
    }
}
