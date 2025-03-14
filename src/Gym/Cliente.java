package Gym;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cliente implements Runnable {
    private final String nome;
    private final List<Stazione> stazioni;
    private final Semaphore accesso;
    private final Random random;

    public Cliente(String nome, List<Stazione> stazioni, Semaphore accesso) {
        this.nome = nome;
        this.stazioni = stazioni;
        this.accesso = accesso;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            int esercizi = random.nextInt(3) + 1; // numero di esercizi da 1 a 3
            System.out.println(nome + " entra in palestra per fare " + esercizi + " esercizi.");

            for (int i = 0; i < esercizi; i++) {
                accesso.acquire();
                Stazione stazioneScelta = stazioni.get(0);
                for (Stazione stazione : stazioni) {
                    if (stazione.getLunghezzaCoda() < stazioneScelta.getLunghezzaCoda()) {
                        stazioneScelta = stazione;
                    }
                }
                System.out.println(nome + " ha scelto la stazione " + stazioneScelta.getNome() + " (Coda: " + stazioneScelta.getLunghezzaCoda() + " persone)");
                accesso.release();

                stazioneScelta.aggiungiCliente();
                Thread.sleep(stazioneScelta.getTempoPerEsercizio());
                stazioneScelta.rimuoviCliente();
            }

            System.out.println(nome + " ha finito gli esercizi e lascia la palestra.");
        } catch (InterruptedException e) {
            System.out.println(nome + " errore: " + e.getMessage());
        }
    }
}