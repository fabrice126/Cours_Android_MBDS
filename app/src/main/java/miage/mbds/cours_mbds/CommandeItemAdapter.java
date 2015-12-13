package miage.mbds.cours_mbds;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by user on 08/12/2015.
 */
public class CommandeItemAdapter extends BaseAdapter {

    private Context context;
    public List<EchangeServeur.Product> products;
    private ListView lst;

    public CommandeItemAdapter(Context context, List<EchangeServeur.Product> products, ListView lst) {
        this.context = context;
        this.products = products;
        this.lst = lst;
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

        CommandeViewHolder viewHolder = null;
        if(v==null){
            v = View.inflate(context, R.layout.content_list_commande, null);
            viewHolder = new CommandeViewHolder();
            viewHolder.name= (TextView)v.findViewById(R.id.name);
            viewHolder.price= (TextView)v.findViewById(R.id.price);
            viewHolder.supprimer= (Button)v.findViewById(R.id.deleteProduct);
            viewHolder.detail= (Button)v.findViewById(R.id.detail);
            v.setTag(viewHolder);
        }
        else{
            viewHolder = (CommandeViewHolder) v.getTag();
        }
        final EchangeServeur.Product product = products.get(position);
        viewHolder.name.setText(product.name);
        viewHolder.price.setText(""+product.price);
        viewHolder.supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Commande.getInstance().getProducts().remove(product);
                CommandeItemAdapter adapter = new CommandeItemAdapter(context, Commande.getInstance().getProducts(), lst);
                lst.setAdapter(adapter);
                Toast.makeText(context, "Produit supprimé", Toast.LENGTH_LONG).show();
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
        return v;
    }

    class CommandeViewHolder{
        TextView name;
        TextView price;
        Button supprimer;
        Button detail;
    }
}
