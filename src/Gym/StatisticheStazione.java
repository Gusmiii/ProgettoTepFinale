package Gym;

public class StatisticheStazione {
    private final String nomeStazione;
    private int clientiServiti;

    public StatisticheStazione(String nomeStazione) {
        this.nomeStazione = nomeStazione;
        this.clientiServiti = 0;
    }

    public synchronized void aggiungiCliente() {
        clientiServiti++;
    }

    public int getClientiServiti() {
        return clientiServiti;
    }

    @Override
    public String toString() {
        return nomeStazione + ": " + clientiServiti + " clienti serviti";
    }
}