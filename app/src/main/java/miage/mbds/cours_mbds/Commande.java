package miage.mbds.cours_mbds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 12/12/2015.
 */
public class Commande {

    protected static Commande commandeInstance = null;

    private List<EchangeServeur.Product> products = new ArrayList<EchangeServeur.Product>();

    public Commande() {

    }

    public List<EchangeServeur.Product> getProducts() {
        return products;
    }


    public static Commande getInstance() {
        if( commandeInstance == null ) {
            commandeInstance = new Commande();
        }

        return commandeInstance;
    }
}
