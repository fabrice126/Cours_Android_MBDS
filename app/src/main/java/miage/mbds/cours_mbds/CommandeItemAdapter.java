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
import android.widget.Toast;

import java.util.List;

/**
 * Created by user on 08/12/2015.
 */
public class CommandeItemAdapter extends BaseAdapter {

    private Context context;
    public List<Object> products;
    private ListView lst;
    private static final int TYPE_PRODUCT = 0;
    private static final int TYPE_DIVIDER = 1;
    private String entree = "Voir toutes les entrées";
    private String plat = "Voir tous les plats";
    private String dessert = "Voir tous les desserts";
    private String apperitif = "Voir tous les appéritifs";

    public CommandeItemAdapter(Context context, List<Object> products, ListView lst) {
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof EchangeServeur.Product) {
            return TYPE_PRODUCT;
        }

        return TYPE_DIVIDER;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        View v = convertView;

        Log.d("TAILLE",""+products.size());
        int type = getItemViewType(position);
        CommandeViewHolder viewHolder = null;
        if(v==null){
            switch (type) {
                case TYPE_PRODUCT:
                    v = View.inflate(context, R.layout.content_list_commande, null);
                    viewHolder = new CommandeViewHolder();
                    viewHolder.name= (TextView)v.findViewById(R.id.name);
                    viewHolder.price= (TextView)v.findViewById(R.id.price);
                    viewHolder.supprimer= (Button)v.findViewById(R.id.deleteProduct);
                    viewHolder.detail= (Button)v.findViewById(R.id.detail);
                    v.setTag(viewHolder);
                    break;
                case TYPE_DIVIDER:
                    v = View.inflate(context, R.layout.content_list_commande_header, null);
                    break;
            }
        }
        else{
            viewHolder = (CommandeViewHolder) v.getTag();
        }
        switch (type) {
            case TYPE_PRODUCT:
                final EchangeServeur.Product product = (EchangeServeur.Product)products.get(position);
                viewHolder.name.setText(product.name);
                viewHolder.price.setText(product.price+"€");
                viewHolder.supprimer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Commande.getInstance().getProducts().remove(product);
                        CommandeItemAdapter adapter = new CommandeItemAdapter(context, Commande.getInstance().getProducts_tri(), lst);
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
                break;
            case TYPE_DIVIDER:
                TextView title = (TextView)v.findViewById(R.id.headerTitle);
                TextView lien = (TextView)v.findViewById(R.id.lien);
                String titleString = (String)getItem(position);
                title.setText(titleString);
                if(titleString.equals("Entrée")) {
                    lien.setText(entree);
                    lien.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(v.getContext(), ProductActivity.class);
                            intent.putExtra("categorie", 0);
                            context.startActivity(intent);
                        }
                    });
                }
                if(titleString.equals("Plat")) {
                    lien.setText(plat);
                    lien.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(v.getContext(), ProductActivity.class);
                            intent.putExtra("categorie", 1);
                            context.startActivity(intent);
                        }
                    });

                }
                if(titleString.equals("Dessert")) {
                    lien.setText(dessert);
                    lien.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(v.getContext(), ProductActivity.class);
                            intent.putExtra("categorie", 2);
                            context.startActivity(intent);
                        }
                    });

                }
                if(titleString.equals("Appéritif")) {
                    lien.setText(apperitif);
                    lien.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(v.getContext(), ProductActivity.class);
                            intent.putExtra("categorie", 3);
                            context.startActivity(intent);
                        }
                    });

                }
                break;
        }
        return v;
    }

    class CommandeViewHolder{
        TextView name;
        TextView price;
        Button supprimer;
        Button detail;
    }
}
