abstract class Chambre {
    protected int prix;

    Chambre(){
        this.prix = 0;
    }

    int getPrix(){
        return this.prix;
    }

    public String toString(){
        return " * Chambre : " + this.getType() + " \n" + "   prix : " + this.prix + "\n";
    }

    abstract public String getType();
}

class ChambreSimple extends Chambre {
    ChambreSimple(int prix){
        super();
        this.prix = prix;
    }

    @Override
    public String getType(){
        return "Simple";
    }
}

class ChambreDouble extends Chambre {
    ChambreDouble(int prix){
        super();
        this.prix = prix;
    }

    @Override
    public String getType(){
        return "Double";
    }
}

class ChambreSuite extends Chambre {
    ChambreSuite(int prix){
        super();
        this.prix = prix;
    }

    @Override
    public String getType(){
        return "Suite";
    }
}
