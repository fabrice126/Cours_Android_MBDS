package miage.mbds.cours_mbds;

import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 27/10/2015.
 */
public class EchangeServeur{

    private ResultCallBack listener;
    private Result resultat;
    private List<Person> persons = new ArrayList<>();

    public static class Result {

        @SerializedName("user")
        public Person person;
        @SerializedName("success")
        public boolean success;
        @SerializedName("message")
        public boolean message;
    }

    public static class Person {

        public String prenom;
        public String nom;
        public String sexe;
        public String telephone;
        public String email;
        public String createdby;
        public String password;
        public String connected;
        public String createdAt;
        public String updatedAt;
        public String id;
    }

    private static class GsonTransformer implements Transformer {

        public <T> T transform(String url, Class<T> type, String encoding, byte[] data, AjaxStatus status) {
            Gson g = new Gson();
            return g.fromJson(new String(data), type);
        }
    }

    public void async_login(Map<String, Object> params, AQuery aq, final ResultCallBack listener){

        this.listener = listener;
        GsonTransformer t = new GsonTransformer();
        String url = "http://92.243.14.22/person/login";

        Log.d("!!!!!!", params.toString());
        aq.transformer(t).progress(this).ajax(url, params, Result.class, new AjaxCallback<Result>() {
            public void callback(String url, Result result, AjaxStatus status) {
                Gson gson = new Gson();
                Log.d("Réponse", gson.toJson(result));
                resultat = result;
                listener.ResultCallBack();
            }
        });

    }

    public void async_register(Map<String, Object> params, AQuery aq){

        GsonTransformer t = new GsonTransformer();
        String url = "http://92.243.14.22/person/";

        Log.d("!!!!!!", params.toString());
        aq.transformer(t).ajax(url, params, Person.class, new AjaxCallback<Person>() {
            public void callback(String url, Person person, AjaxStatus status) {
                Gson gson = new Gson();
                Log.d("Réponse", gson.toJson(person));
                Log.d("Nom du mec", person.nom);
            }
        });

    }

    public void async_list(AQuery aq, final ResultCallBack listener){

        this.listener = listener;
        GsonTransformer t = new GsonTransformer();
        String url = "http://92.243.14.22/person/";

        aq.ajax(url, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray json, AjaxStatus status) {
                Gson gson = new Gson();
                persons = gson.fromJson(json.toString(), new TypeToken<ArrayList<Person>>() {
                }.getType());
                Log.d("Taille", "" + persons.size());
                for(int i = 0; i<persons.size();i++) {
                    Log.d("Réponse", "" + persons.get(i).prenom);
                }
                listener.ResultCallBack();
            }
        });
    }

    public Result getResultat() {
        return resultat;
    }

    public List<Person> getPersons() {
        return persons;
    }

}
