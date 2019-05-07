public class Perro implements Comparable<Perro>{

    public int id;
    public String raza;

    public Perro(int id, String raza){
        this.id = id;
        this.raza = raza;
    }
    @Override
    public int compareTo(Perro o){
        return raza.compareTo(o.raza);
    }

    public String toString(){
        return raza;
    }
    
}