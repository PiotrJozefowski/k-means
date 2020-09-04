package com.company;

public class Punkt {

    double[] wspolrzedne;
    int wymiar;
    int index_grupy;

    public Punkt(double[] wspolrzedne) {
        this.wspolrzedne = wspolrzedne;
        wymiar = wspolrzedne.length;
    }

    double oblicz_odleglosc_od_centroidu(double[] centroid){

        double suma = 0;
        for (int i = 0; i < wspolrzedne.length; i++) {
            suma = suma + Math.pow(wspolrzedne[i] - centroid[i],2);
        }

        double odleglosc = Math.sqrt(suma);

        return odleglosc;
    }

}
