package Gym;

import java.util.concurrent.atomic.AtomicInteger;

public class Stazione implements Runnable {
    private final String nome;
    private final int tempoPerEsercizio;
    private final AtomicInteger coda;
    private final StatisticheStazione statistiche;

    public Stazione(String nome, int tempoPerEsercizio) {
        this.nome = nome;
        this.tempoPerEsercizio = tempoPerEsercizio;
        this.coda = new AtomicInteger(0);
        this.statistiche = new StatisticheStazione(nome);
    }

    public String getNome() {
        return nome;
    }

    public int getTempoPerEsercizio() {
        return tempoPerEsercizio;
    }

    public int getLunghezzaCoda() {
        return coda.get();
    }

    public void aggiungiCliente() {
        coda.incrementAndGet();
        statistiche.aggiungiCliente();
    }

    public void rimuoviCliente() {
        coda.decrementAndGet();
    }

    public StatisticheStazione getStatistiche() {
        return statistiche;
    }

    @Override
    public void run() {
        // Simulazione continua della stazione
    }
}