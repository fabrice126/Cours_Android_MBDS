package miage.mbds.cours_mbds;

import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by user on 27/10/2015.
 */
public class EchangeServeur {

    private static class Profil {

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

    public void async_transformer(String url, Map<String, Object> params, AQuery aq){

        GsonTransformer t = new GsonTransformer();

        Log.d("!!!!!!", params.toString());
        aq.transformer(t).ajax(url, params, Profil.class, new AjaxCallback<Profil>() {
            public void callback(String url, Profil profil, AjaxStatus status) {
                Gson gson = new Gson();
                Log.d("Réponse", gson.toJson(profil));
                Log.d("Nom du mec", profil.nom);
            }
        });

    }
}
