package Gym;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
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

    public void run() {
        try {
            int esercizi = random.nextInt(3) + 1; // numero di esercizi da 1 a 3
            System.out.println(nome + " entra in palestra per fare " + esercizi + " esercizi.");
            Set<Stazione> stazioniUsate = new HashSet<>();

            for (int i = 0; i < esercizi; i++) {
                accesso.acquire();
                Stazione stazioneScelta;
                do {
                    stazioneScelta = stazioni.get(random.nextInt(stazioni.size()));
                } while (stazioniUsate.contains(stazioneScelta));
                stazioniUsate.add(stazioneScelta);
                System.out.println(nome + " ha scelto la stazione " + stazioneScelta.getNome() + " (Coda: " + stazioneScelta.getLunghezzaCoda() + " persone)");
                accesso.release();

                boolean inAttesa = stazioneScelta.getLunghezzaCoda() > 0;
                if (inAttesa) {
                    System.out.println(nome + " è in attesa che si liberi " + stazioneScelta.getNome());
                }

                // Simula il tempo di attesa
                Thread.sleep(1000);

                stazioneScelta.aggiungiCliente();
                if (inAttesa) {
                    System.out.println(nome + " ha finito la coda, inizia ad allenarsi su " + stazioneScelta.getNome());
                }

                // Simula il tempo di allenamento
                Thread.sleep(stazioneScelta.getTempoPerEsercizio());

                stazioneScelta.rimuoviCliente();

                // Simula il tempo di riposo tra gli esercizi
                Thread.sleep(300);
            }

            System.out.println(nome + " ha finito gli esercizi e lascia la palestra.");
        } catch (InterruptedException e) {
            System.out.println(nome + " errore: " + e.getMessage());
        }
    }
}