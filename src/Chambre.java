import java.util.Comparator;

abstract class Chambre {
    protected int prix;
    Hebergement hébergement;

    Chambre(Hebergement hébergement){
        this.hébergement = hébergement;
        this.prix = 0;
    }

    int getPrix(){
        return this.prix;
    }

    public String toString(){
        return " * Chambre \n  Hebergement : " + this.hébergement.nom + "\n" + "  Adresse : " + this.hébergement.adresse + "\n" + "  type : " + this.getType() + " \n" + "  prix : " + this.prix + "\n";
    }

    abstract public String getType();
}

class ChambreSimple extends Chambre {
    ChambreSimple(Hebergement hébergement, int prix){
        super(hébergement);
        this.prix = prix;
    }

    @Override
    public String getType(){
        return "Simple";
    }
}

class ChambreDouble extends Chambre {
    ChambreDouble(Hebergement hébergement, int prix){
        super(hébergement);
        this.prix = prix;
    }

    @Override
    public String getType(){
        return "Double";
    }
}

class ChambreSuite extends Chambre {
    ChambreSuite(Hebergement hébergement, int prix){
        super(hébergement);
        this.prix = prix;
    }

    @Override
    public String getType(){
        return "Suite";
    }
}

class ChambreComparator implements Comparator<Chambre> {
    @Override
    public int compare(Chambre a, Chambre b) {
        return a.prix < b.prix ? -1 : a.prix == b.prix ? 0 : 1;
    }
}
