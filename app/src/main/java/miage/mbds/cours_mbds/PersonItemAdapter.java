package miage.mbds.cours_mbds;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 30/10/2015.
 */
public class PersonItemAdapter extends BaseAdapter {

        private Context context;
        public List<EchangeServeur.Person> person;

        public PersonItemAdapter(Context context, List<EchangeServeur.Person> person) {
            this.context = context;
            this.person = person;
        }

        @Override
        public int getCount() {
            return person.size();
        }

        @Override
        public Object getItem(int arg0) {
            return person.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup arg2) {
            View v = convertView;

            PersonViewHolder viewHolder = null;
//            if(v==null){
//                v = View.inflate(context, R.layout.list_person, null);
//                viewHolder = new CommentListViewHolder();
//                viewHolder.nom_prenom= (TextView)v.findViewById(R.id.txt_nom_prenom);
//                viewHolder.date_creation= (TextView)v.findViewById(R.id.txt_date_inscription);
//                v.setTag(viewHolder);
//            }
//            else{
//                viewHolder = (CommentListViewHolder) v.getTag();
//            }
//            EchangeServeur.Person person = person.get(position);
//            viewHolder.nom_prenom.setText(comment.getComment_body_value());
//            viewHolder.date_creation.setText(comment.getName());
            return v;
        }

        class PersonViewHolder{
            TextView nom_prenom;
            TextView date_creation;
        }

    }
