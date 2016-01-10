package miage.mbds.cours_mbds;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 08/12/2015.
 */
public class CommandeCurrentItemAdapter extends BaseAdapter {

    private Context context;
    public List<Object> menu;
    public List<EchangeServeur.Product> products;
    private ListView lst;
    private static final int TYPE_PRODUCT = 0;
    private static final int TYPE_COMMANDE = 1;

    public CommandeCurrentItemAdapter(Context context, List<Object> menu, ListView lst, List<EchangeServeur.Product> products) {
        this.context = context;
        this.menu = menu;
        this.lst = lst;
        this.products = products;
    }

    @Override
    public int getCount() {
        return menu.size();
    }

    @Override
    public Object getItem(int arg0) {
        return menu.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof EchangeServeur.Product) {
            return TYPE_PRODUCT;
        }

        return TYPE_COMMANDE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        View v = convertView;

        int type = getItemViewType(position);
        CommandeCurrentViewHolder viewHolder = null;
        if(v==null){
            switch (type) {
                case TYPE_PRODUCT:
                    v = View.inflate(context, R.layout.content_list_commande_current, null);
                    viewHolder = new CommandeCurrentViewHolder();
                    viewHolder.name= (TextView)v.findViewById(R.id.product);
                    viewHolder.detail= (Button)v.findViewById(R.id.detail);
                    v.setTag(viewHolder);
                    break;
                case TYPE_COMMANDE:
                    v = View.inflate(context, R.layout.content_list_commande_header, null);
                    break;
            }
        }
        else{
            viewHolder = (CommandeCurrentViewHolder) v.getTag();
        }
        switch (type) {
            case TYPE_PRODUCT:
                EchangeServeur.Product product_item = (EchangeServeur.Product)menu.get(position);
                Log.d("product",""+products.size());
                for(int i = 0; i < products.size();i++) {
                    if((product_item.id).equals(products.get(i).id)) {
                        product_item = products.get(i);
                        Log.d("item", product_item.id);
                        break;
                    }
                }
                final EchangeServeur.Product product = product_item;
                viewHolder.name.setText(product.name);
                viewHolder.detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), DetailProductActivity.class);
                        intent.putExtra("miage.mbds.cours_mbds.EchangeServeur.Product", product);
                        context.startActivity(intent);
                    }
                });
                break;
            case TYPE_COMMANDE:
                TextView title = (TextView)v.findViewById(R.id.headerTitle);
                TextView lien = (TextView)v.findViewById(R.id.lien);
                String price = (String)getItem(position);
                title.setText("Commande");
                lien.setText(price+"€");
                break;
        }
        return v;
    }

    class CommandeCurrentViewHolder{
        TextView name;
        Button detail;
    }
}
