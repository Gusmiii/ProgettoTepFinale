package Gym;

public class Main {
    public static void main(String[] args) {
        int numClienti = 10;
        int numStazioni = 3;

        Palestra palestra = new Palestra(numStazioni);
        palestra.avviaSimulazione(numClienti);
    }
}