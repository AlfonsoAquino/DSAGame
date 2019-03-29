package com.example.alfonsoaquino.test;

public class Statistica {

    private String codicePlesso, tipoTest, regione, data, genere, eta, numCorrette, numSbagliate, numSaltate, livelloRaggiunto,fileName;
    private String tempoImpiegato, errore1, errore2, errore3, errore4, domanda1, domanda2, domanda3, domanda4, domanda5, domanda6, domanda7, domanda8, codiceClasse, codiceRegistro;

    public Statistica() {
    }

    public Statistica(String codicePlesso, String tipoTest, String regione, String data, String genere, String eta, String numCorrette, String numSbagliate,
                      String numSaltate, String livelloRaggiunto, String tempoImpiegato, String errore1, String errore2, String errore3, String errore4, String domanda1, String domanda2, String domanda3, String domanda4, String domanda5, String domanda6, String domanda7,
                      String domanda8, String codiceClasse, String codiceRegistro, String fileName) {
        this.codicePlesso = codicePlesso;
        this.tipoTest = tipoTest;
        this.regione = regione;
        this.data = data;
        this.genere = genere;
        this.eta = eta;
        this.numCorrette = numCorrette;
        this.numSbagliate = numSbagliate;
        this.numSaltate = numSaltate;
        this.livelloRaggiunto = livelloRaggiunto;
        this.tempoImpiegato = tempoImpiegato;
        this.errore1 = errore1;
        this.errore2 = errore2;
        this.errore3 = errore3;
        this.errore4 = errore4;
        this.domanda1 = domanda1;
        this.domanda2 = domanda2;
        this.domanda3 = domanda3;
        this.domanda4 = domanda4;
        this.domanda5 = domanda5;
        this.domanda6 = domanda6;
        this.domanda7 = domanda7;
        this.domanda8 = domanda8;
        this.codiceClasse = codiceClasse;
        this.codiceRegistro = codiceRegistro;
        this.fileName = fileName;
    }

    public String getCodicePlesso() {
        return codicePlesso;
    }

    public void setCodicePlesso(String codicePlesso) {
        this.codicePlesso = codicePlesso;
    }

    public String getTipoTest() {
        return tipoTest;
    }

    public void setTipoTest(String tipoTest) {
        this.tipoTest = tipoTest;
    }


    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public String getNumCorrette() {
        return numCorrette;
    }

    public void setNumCorrette(String numCorrette) {
        this.numCorrette = numCorrette;
    }

    public String getNumSbagliate() {
        return numSbagliate;
    }

    public void setNumSbagliate(String numSbagliate) {
        this.numSbagliate = numSbagliate;
    }

    public String getNumSaltate() {
        return numSaltate;
    }

    public void setNumSaltate(String numSaltate) {
        this.numSaltate = numSaltate;
    }

    public String getLivelloRaggiunto() {
        return livelloRaggiunto;
    }

    public void setLivelloRaggiunto(String livelloRaggiunto) {
        this.livelloRaggiunto = livelloRaggiunto;
    }


    public String getTempoImpiegato() {
        return tempoImpiegato;
    }

    public void setTempoImpiegato(String tempoImpiegato) {
        this.tempoImpiegato = tempoImpiegato;
    }

    public String getErrore1() {
        return errore1;
    }

    public void setErrore1(String errore1) {
        this.errore1 = errore1;
    }

    public String getErrore2() {
        return errore2;
    }

    public void setErrore2(String errore2) {
        this.errore2 = errore2;
    }

    public String getErrore3() {
        return errore3;
    }

    public void setErrore3(String errore3) {
        this.errore3 = errore3;
    }

    public String getErrore4() {
        return errore4;
    }

    public void setErrore4(String errore4) {
        this.errore4 = errore4;
    }

    public String getDomanda1() {
        return domanda1;
    }

    public void setDomanda1(String domanda1) {
        this.domanda1 = domanda1;
    }

    public String getDomanda2() {
        return domanda2;
    }

    public void setDomanda2(String domanda2) {
        this.domanda2 = domanda2;
    }

    public String getDomanda3() {
        return domanda3;
    }

    public void setDomanda3(String domanda3) {
        this.domanda3 = domanda3;
    }

    public String getDomanda4() {
        return domanda4;
    }

    public void setDomanda4(String domanda4) {
        this.domanda4 = domanda4;
    }

    public String getDomanda5() {
        return domanda5;
    }

    public void setDomanda5(String domanda5) {
        this.domanda5 = domanda5;
    }

    public String getDomanda6() {
        return domanda6;
    }

    public void setDomanda6(String domanda6) {
        this.domanda6 = domanda6;
    }

    public String getDomanda7() {
        return domanda7;
    }

    public void setDomanda7(String domanda7) {
        this.domanda7 = domanda7;
    }

    public String getDomanda8() {
        return domanda8;
    }

    public void setDomanda8(String domanda8) {
        this.domanda8 = domanda8;
    }

    public String getCodiceClasse() {
        return codiceClasse;
    }

    public void setCodiceClasse(String codiceClasse) {
        this.codiceClasse = codiceClasse;
    }

    public String getCodiceRegistro() {
        return codiceRegistro;
    }

    public void setCodiceRegistro(String codiceRegistro) {
        this.codiceRegistro = codiceRegistro;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Statistica{" +
                "codicePlesso='" + codicePlesso + '\'' +
                ", tipoTest='" + tipoTest + '\'' +
                ", regione='" + regione + '\'' +
                ", data='" + data + '\'' +
                ", genere='" + genere + '\'' +
                ", eta='" + eta + '\'' +
                ", numCorrette='" + numCorrette + '\'' +
                ", numSbagliate='" + numSbagliate + '\'' +
                ", numSaltate='" + numSaltate + '\'' +
                ", livelloRaggiunto='" + livelloRaggiunto + '\'' +
                ", fileName='" + fileName + '\'' +
                ", tempoImpiegato='" + tempoImpiegato + '\'' +
                ", errore1='" + errore1 + '\'' +
                ", errore2='" + errore2 + '\'' +
                ", errore3='" + errore3 + '\'' +
                ", errore4='" + errore4 + '\'' +
                ", domanda1='" + domanda1 + '\'' +
                ", domanda2='" + domanda2 + '\'' +
                ", domanda3='" + domanda3 + '\'' +
                ", domanda4='" + domanda4 + '\'' +
                ", domanda5='" + domanda5 + '\'' +
                ", domanda6='" + domanda6 + '\'' +
                ", domanda7='" + domanda7 + '\'' +
                ", domanda8='" + domanda8 + '\'' +
                ", codiceClasse='" + codiceClasse + '\'' +
                ", codiceRegistro='" + codiceRegistro + '\'' +
                '}';
    }
}
