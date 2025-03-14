package Gym;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Palestra {
    private final List<Stazione> stazioni;
    private final Semaphore accesso;
    private int clientiTotali;

    public Palestra(int numStazioni) {
        stazioni = new ArrayList<>();
        accesso = new Semaphore(1);
        clientiTotali = 0;

        stazioni.add(new Stazione("Tapis Roulant", 1000));
        stazioni.add(new Stazione("Cyclette", 1500));
        stazioni.add(new Stazione("Pesi", 2000));
    }

    public void avviaSimulazione(int numClienti) {
        clientiTotali = numClienti;

        System.out.println("Inizio simulazione palestra con " + numClienti + " clienti e " + stazioni.size() + " stazioni.");

        for (Stazione stazione : stazioni) {
            new Thread(stazione).start();
        }

        for (int i = 1; i <= numClienti; i++) {
            Cliente cliente = new Cliente("Cliente-" + i, stazioni, accesso);
            new Thread(cliente).start();
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep((numClienti * 3000) + 5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mostraStatistiche();
    }

    private void mostraStatistiche() {
        System.out.println("\nStatistiche di fine giornata:");
        for (Stazione stazione : stazioni) {
            System.out.println(stazione.getStatistiche());
        }
    }
}