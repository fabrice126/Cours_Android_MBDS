package miage.mbds.cours_mbds;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.util.List;

/**
 * Created by user on 08/12/2015.
 */
public class ProductItemAdapter extends BaseAdapter {

    private Context context;
    public List<EchangeServeur.Product> products;
    private ResultCallBack listener;

    public ProductItemAdapter(Context context, List<EchangeServeur.Product> products, ResultCallBack listener) {
        this.context = context;
        this.products = products;
        this.listener = listener;
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
            viewHolder.description= (TextView)v.findViewById(R.id.description);
            viewHolder.image= (ImageView)v.findViewById(R.id.image);
            viewHolder.price= (TextView)v.findViewById(R.id.price);
            viewHolder.calories= (TextView)v.findViewById(R.id.calories);
            viewHolder.type= (TextView)v.findViewById(R.id.type);
            viewHolder.discount= (TextView)v.findViewById(R.id.discount);
            v.setTag(viewHolder);
        }
        else{
            viewHolder = (ProductViewHolder) v.getTag();
        }
        EchangeServeur.Product product = products.get(position);
        viewHolder.name.setText(product.name);
        viewHolder.description.setText(product.description);
        viewHolder.price.setText(""+product.price);
        viewHolder.calories.setText(""+product.calories);
        viewHolder.type.setText(product.type);
        viewHolder.discount.setText(""+product.discount);
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        final String id = product.id;
        return v;
    }

    class ProductViewHolder{
        TextView name;
        TextView description;
        ImageView image;
        TextView price;
        TextView calories;
        TextView type;
        TextView discount;
        TextView ajouter;
    }
}
