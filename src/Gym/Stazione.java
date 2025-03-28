package Gym;

public class Stazione implements Runnable {
    private final String nome;
    private final int tempoPerEsercizio;
    private int coda;
    private final StatisticheStazione statistiche;

    public Stazione(String nome, int tempoPerEsercizio) {
        this.nome = nome;
        this.tempoPerEsercizio = tempoPerEsercizio;
        this.coda = 0;
        this.statistiche = new StatisticheStazione(nome);
    }

    public String getNome() {
        return nome;
    }
    public int getTempoPerEsercizio() {
        return tempoPerEsercizio;
    }

    public synchronized int getLunghezzaCoda() {
        return coda;
    }

    public synchronized void aggiungiCliente() {
        coda++;
        statistiche.aggiungiCliente();
    }

    public synchronized void rimuoviCliente() {
        coda--;
    }

    public StatisticheStazione getStatistiche() {
        return statistiche;
    }

    public void run() {
        // Simulazione continua della stazione
    }
}