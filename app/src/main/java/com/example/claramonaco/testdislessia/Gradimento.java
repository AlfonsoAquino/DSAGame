package com.example.claramonaco.testdislessia;

public class Gradimento {
    private String idStatistica;
    private String domanda1;
    private String domanda2;
    private String domanda3;
    private String domanda4;
    private String domanda5;
    private String domanda6;
    private String domanda7;
    private String domanda8;


    public Gradimento() {
    }

    public Gradimento(String idStatistica, String domanda1, String domanda2, String domanda3, String domanda4, String domanda5, String domanda6, String domanda7, String domanda8) {
        this.idStatistica = idStatistica;
        this.domanda1 = domanda1;
        this.domanda2 = domanda2;
        this.domanda3 = domanda3;
        this.domanda4 = domanda4;
        this.domanda5 = domanda5;
        this.domanda6 = domanda6;
        this.domanda7 = domanda7;
        this.domanda8 = domanda8;
    }

    public String getIdStatistica() {
        return idStatistica;
    }

    public void setIdStatistica(String idStatistica) {
        this.idStatistica = idStatistica;
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

    @Override
    public String toString() {
        return "Gradimento{" +
                "idStatistica='" + idStatistica + '\'' +
                ", domanda1='" + domanda1 + '\'' +
                ", domanda2='" + domanda2 + '\'' +
                ", domanda3='" + domanda3 + '\'' +
                ", domanda4='" + domanda4 + '\'' +
                ", domanda5='" + domanda5 + '\'' +
                ", domanda6='" + domanda6 + '\'' +
                ", domanda7='" + domanda7 + '\'' +
                ", domanda8='" + domanda8 + '\'' +
                '}';
    }
}
