package miage.mbds.cours_mbds;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 27/10/2015.
 */
public class EchangeServeur{

    private Result resultat;
    private List<Person> persons = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Menu> menu = new ArrayList<>();

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
        public boolean connected;
        public String createdAt;
        public String updatedAt;
        public String id;
    }

    public static class Menu {
        public int price;
        public int discount;
        public Server server;
        public Cooker cooker;
        public ArrayList<Product> items;
        public String createdAt;
        public String updatedAt;
        public String id;
    }

    public static class Server {
        public String id;
    }

    public static class Cooker {
        public String id; //"566c6eb783916d6520e32581"
        public String apikey;// "AIzaSyBNy7W34fFGy8oWrtD7q-O7tXwTC0LW6o4"
        public String email;// "email@cuisinier.fr"
        public String name;// "Nom du cuisinier"
        public String gcmkey;// "dKgIaHlarSg:APA91bE1IQemhZ9mnrBJpCZrGIOxKZdkrkl1M_qReF6mLmcgTuq4opV0TamDSNug33Rlbi96Wdo3z3JGhC-wHuraLkTtTmDaAJdXGyoS2xD5fixIu8lLjwcs1Vy92x6HzIcN__-1atmx"
        public boolean active;// true

    }

    public static class Product implements Parcelable {

        public String name;
        public String description;
        public int price;
        public int calories;
        public String type;
        public String picture;
        public int discount;
        public String createdAt;
        public String updatedAt;
        public String id;

        protected Product(Parcel in) {
            name = in.readString();
            description = in.readString();
            price = in.readInt();
            calories = in.readInt();
            type = in.readString();
            picture = in.readString();
            discount = in.readInt();
            createdAt = in.readString();
            updatedAt = in.readString();
            id = in.readString();
        }

        public static final Creator<Product> CREATOR = new Creator<Product>() {
            @Override
            public Product createFromParcel(Parcel in) {
                return new Product(in);
            }

            @Override
            public Product[] newArray(int size) {
                return new Product[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(description);
            dest.writeInt(price);
            dest.writeInt(calories);
            dest.writeString(type);
            dest.writeString(picture);
            dest.writeInt(discount);
            dest.writeString(createdAt);
            dest.writeString(updatedAt);
            dest.writeString(id);
        }
    }

    private static class GsonTransformer implements Transformer {

        public <T> T transform(String url, Class<T> type, String encoding, byte[] data, AjaxStatus status) {
            Gson g = new Gson();
            return g.fromJson(new String(data), type);
        }
    }

    public void async_login(Map<String, Object> params, AQuery aq, final ResultCallBack listener){

        GsonTransformer t = new GsonTransformer();
        String url = "http://92.243.14.22:1337/person/login";

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
        String url = "http://92.243.14.22:1337/person/";

        aq.transformer(t).ajax(url, params, Person.class, new AjaxCallback<Person>() {
            public void callback(String url, Person person, AjaxStatus status) {
                Gson gson = new Gson();
                Log.d("Réponse", gson.toJson(person));
            }
        });

    }

    public void async_list(AQuery aq, final ResultCallBack listener){

        GsonTransformer t = new GsonTransformer();
        String url = "http://92.243.14.22:1337/person/";

        aq.ajax(url, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray json, AjaxStatus status) {
                Gson gson = new Gson();
                persons = gson.fromJson(json.toString(), new TypeToken<ArrayList<Person>>() {
                }.getType());
                listener.ResultCallBack();
            }
        });
    }

    public void async_delete(final AQuery aq, String id, final ResultCallBack listener){

        String url = "http://92.243.14.22:1337/person/"+id;

        aq.delete(url, String.class, new AjaxCallback<String>() {
            @Override
            public void callback(String url, String json, AjaxStatus status) {
                listener.ResultCallBackDelete();
            }
        });
    }


    public void async_list_product(AQuery aq, final ResultCallBack listener){

        GsonTransformer t = new GsonTransformer();
        String url = "http://92.243.14.22:1337/product/";

        aq.ajax(url, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray json, AjaxStatus status) {
                Gson gson = new Gson();
                products = gson.fromJson(json.toString(), new TypeToken<ArrayList<Product>>() {
                }.getType());
                Log.d("PRODUIT 1", products.get(0).id);
                Log.d("PRODUIT 1", String.valueOf(products.get(0).discount));
                Log.d("PRODUIT 1", String.valueOf(products.get(0).price));
                listener.ResultCallBack();
            }
        });
    }

    public void async_menu(JSONObject json, AQuery aq){

        String url = "http://92.243.14.22:1337/menu/";
        Log.d("params", json.toString());

        aq.post(url, json, JSONObject.class, new AjaxCallback<JSONObject>() {
            public void callback(String url, JSONObject json, AjaxStatus status) {
                Log.d("Réponse", "" + json);
            }
        });

    }


    public void async_list_menu(AQuery aq){

        String url = "http://92.243.14.22:1337/menu/";

        aq.ajax(url, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray json, AjaxStatus status) {
                Log.d("Réponse", "" + json);
                Gson gson = new Gson();
                menu = gson.fromJson(json.toString(), new TypeToken<ArrayList<Menu>>() {
                }.getType());
                Log.d("Menu 1", menu.get(menu.size()-1).id);
                Log.d("Menu 1", menu.get(menu.size()-1).price+"");
                Log.d("Product 1", menu.get(menu.size()-1).items.toString());
            }
        });

    }

    public Result getResultat() {
        return resultat;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public List<Product> getProducts() {
        return products;
    }

}
