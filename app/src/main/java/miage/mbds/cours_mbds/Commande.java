package miage.mbds.cours_mbds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 12/12/2015.
 */
public class Commande {

    protected static Commande commandeInstance = null;

    private List<EchangeServeur.Product> products = new ArrayList<EchangeServeur.Product>();

    private EchangeServeur.Person serveur;

    private EchangeServeur.Cooker cooker;

    public Commande() {

    }

    public List<EchangeServeur.Product> getProducts() {
        return products;
    }

    public EchangeServeur.Person getServeur() {
        return serveur;
    }

    public void setServeur(EchangeServeur.Person serveur) {
        this.serveur = serveur;
    }

    public EchangeServeur.Cooker getCooker() {
        return cooker;
    }

    public void setCooker(EchangeServeur.Cooker cooker) {
        this.cooker = cooker;
    }

    public static Commande getInstance() {
        if( commandeInstance == null ) {
            commandeInstance = new Commande();
        }

        return commandeInstance;
    }


    public List<Object> getProducts_tri() {

        List<Object> products_tri = new ArrayList<Object>();
        List<EchangeServeur.Product> products_entree = new ArrayList<EchangeServeur.Product>();
        List<EchangeServeur.Product> products_plat = new ArrayList<EchangeServeur.Product>();
        List<EchangeServeur.Product> products_dessert = new ArrayList<EchangeServeur.Product>();
        List<EchangeServeur.Product> products_apperitif = new ArrayList<EchangeServeur.Product>();

        for(int i = 0; i<products.size(); i++) {
            if(products.get(i).type.equals("Entrée")) {
                products_entree.add(products.get(i));
            }
            else if(products.get(i).type.equals("Plat ")) {
                products_plat.add(products.get(i));
            }
            else if(products.get(i).type.equals("Dessert")) {
                products_dessert.add(products.get(i));
            }
            else if(products.get(i).type.equals("Appéritif")) {
                products_apperitif.add(products.get(i));
            }
        }
        if(products_entree.size()>0) {
            products_tri.add("Entrée");
            products_tri.addAll(products_entree);
        }
        if(products_plat.size()>0) {
            products_tri.add("Plat");
            products_tri.addAll(products_plat);
        }
        if(products_dessert.size()>0) {
            products_tri.add("Dessert");
            products_tri.addAll(products_dessert);
        }
        if(products_apperitif.size()>0) {
            products_tri.add("Appéritif");
            products_tri.addAll(products_apperitif);
        }

        return products_tri;
    }
}
