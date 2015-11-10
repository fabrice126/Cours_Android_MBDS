package miage.mbds.cours_mbds;

import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 27/10/2015.
 */
public class EchangeServeur{

    private ResultCallBack listener;
    private Result resultat;
    private List<Person> person = new ArrayList<>();

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

    public void async_list(AQuery aq){

        GsonTransformer t = new GsonTransformer();
        String url = "http://92.243.14.22/person/";

        aq.ajax(url, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray json, AjaxStatus status) {
                Gson gson = new Gson();
                person = getListObjectFromJson(json.toString(), new TypeToken<ArrayList<Person>>() {}.getType());
                Log.d("Réponse", ""+person.get(0).prenom);
            }
        });

    }


    public Result getResultat() {
        return resultat;
    }

    public List<Person> getPerson() {
        return person;
    }

    public static <T> ArrayList<T> getListObjectFromJson(String theJsonObject, Type typeOfTheList) {
        try {
            GsonBuilder gsonb = new GsonBuilder();
            Gson gson = gsonb
                    .excludeFieldsWithoutExposeAnnotation()
                    .setDateFormat(DateFormat.LONG)
                    .create();
            JSONArray jsonObject = new JSONArray(theJsonObject);
            ArrayList<T> res = gson.fromJson(jsonObject.toString(), typeOfTheList);
            Log.v("Return", res.toString() + " " + res.toString());
            return res;
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }

}
