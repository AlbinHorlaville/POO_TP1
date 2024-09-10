abstract class Chambre {
    protected int prix;

    Chambre(){
        this.prix = 0;
    }

    int getPrix(){
        return this.prix;
    }

    public String toString(){
        return "   prix : " + this.prix + "\n";
    }
}

class ChambreSimple extends Chambre {
    ChambreSimple(int prix){
        super();
        this.prix = prix;
    }

    public String toString(){
        String resultat = super.toString();
        return " * Chambre : Simple \n" + resultat;
    }
}

class ChambreDouble extends Chambre {
    ChambreDouble(int prix){
        super();
        this.prix = prix;
    }

    public String toString(){
        String resultat = super.toString();
        return " * Chambre : Double \n" + resultat;
    }
}

class ChambreSuite extends Chambre {
    ChambreSuite(int prix){
        super();
        this.prix = prix;
    }

    public String toString(){
        String resultat = super.toString();
        return " * Chambre : Suite \n" + resultat;
    }
}
