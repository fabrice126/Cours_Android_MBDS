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
        public List<EchangeServeur.Person> persons;

        public PersonItemAdapter(Context context, List<EchangeServeur.Person> persons) {
            this.context = context;
            this.persons = persons;
        }

        @Override
        public int getCount() {
            return persons.size();
        }

        @Override
        public Object getItem(int arg0) {
            return persons.get(arg0);
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
            if(v==null){
                v = View.inflate(context, R.layout.content_list_contact, null);
                viewHolder = new PersonViewHolder();
                viewHolder.nom_prenom= (TextView)v.findViewById(R.id.nom);
                viewHolder.connected= (TextView)v.findViewById(R.id.connected);
                v.setTag(viewHolder);
            }
            else{
                viewHolder = (PersonViewHolder) v.getTag();
            }
            EchangeServeur.Person person = persons.get(position);
            viewHolder.nom_prenom.setText(person.prenom+" "+person.nom);
            viewHolder.connected.setText(person.connected);
            return v;
        }

        class PersonViewHolder{
            TextView nom_prenom;
            TextView connected;
        }

    }
