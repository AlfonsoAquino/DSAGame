package com.example.claramonaco.testdislessia;

public class Statistica {

    private String genere;
    private String eta;
    private int numCorrette;
    private int numSbagliate;
    private int numSaltate;
    private int livelloRaggiunto;
    private int idPaziente;
    private String tempoImpiegato;
    private int errore1;
    private int errore2;
    private int errore3;
    private int errore4;

    public Statistica() {
    }

    public Statistica(String genere, String eta,int numCorrette, int numSbagliate, int numSaltate, int livelloRaggiunto, int idPaziente, String tempoImpiegato, int errore1, int errore2, int errore3, int errore4) {

        this.genere = genere;
        this.eta = eta;
        this.numCorrette = numCorrette;
        this.numSbagliate = numSbagliate;
        this.numSaltate = numSaltate;
        this.livelloRaggiunto = livelloRaggiunto;
        this.idPaziente = idPaziente;
        this.tempoImpiegato = tempoImpiegato;
        this.errore1 = errore1;
        this.errore2 = errore2;
        this.errore3 = errore3;
        this.errore4 = errore4;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public int getNumCorrette() {
        return numCorrette;
    }

    public void setNumCorrette(int numCorrette) {
        this.numCorrette = numCorrette;
    }

    public int getNumSbagliate() {
        return numSbagliate;
    }

    public void setNumSbagliate(int numSbagliate) {
        this.numSbagliate = numSbagliate;
    }

    public int getNumSaltate() {
        return numSaltate;
    }

    public void setNumSaltate(int numSaltate) {
        this.numSaltate = numSaltate;
    }

    public int getLivelloRaggiunto() {
        return livelloRaggiunto;
    }

    public void setLivelloRaggiunto(int livelloRaggiunto) {
        this.livelloRaggiunto = livelloRaggiunto;
    }

    public int getIdPaziente() {
        return idPaziente;
    }

    public void setIdPaziente(int idPaziente) {
        this.idPaziente = idPaziente;
    }

    public String getTempoImpiegato() {
        return tempoImpiegato;
    }

    public void setTempoImpiegato(String tempoImpiegato) {
        this.tempoImpiegato = tempoImpiegato;
    }

    public int getErrore1() {
        return errore1;
    }

    public void setErrore1(int errore1) {
        this.errore1 = errore1;
    }

    public int getErrore2() {
        return errore2;
    }

    public void setErrore2(int errore2) {
        this.errore2 = errore2;
    }

    public int getErrore3() {
        return errore3;
    }

    public void setErrore3(int errore3) {
        this.errore3 = errore3;
    }

    public int getErrore4() {
        return errore4;
    }

    public void setErrore4(int errore4) {
        this.errore4 = errore4;
    }

    @Override
    public String toString() {
        return "Statistica{" +
                "numCorrette=" + numCorrette +
                ", numSbagliate=" + numSbagliate +
                ", numSaltate=" + numSaltate +
                ", livelloRaggiunto=" + livelloRaggiunto +
                ", idPaziente=" + idPaziente +
                ", tempoImpiegato=" + tempoImpiegato +
                ", errore1=" + errore1 +
                ", errore2=" + errore2 +
                ", errore3=" + errore3 +
                ", errore4=" + errore4 +
                '}';
    }
}
