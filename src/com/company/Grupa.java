package com.company;

import java.util.ArrayList;

public class Grupa {

    int id;

    public Grupa(int id) {
        this.id = id;
    }

    ArrayList<Punkt> lista_punktow = new ArrayList<>();

    double[] centroid;
    double E;


    void oblicz_centroid(){

        if(!lista_punktow.isEmpty()) {

            double[] centroid_obliczany = new double[lista_punktow.get(0).wymiar];

            for (int i = 0; i < centroid_obliczany.length; i++) {
                double suma = 0;
                for (int j = 0; j < lista_punktow.size(); j++) {
                    suma = suma +lista_punktow.get(j).wspolrzedne[i];
                }
                centroid_obliczany[i] = suma/lista_punktow.size();
            }

        centroid = centroid_obliczany;

        }else{
            System.out.println("LISTA PUSTA");
        }




    }

    double oblicz_E(){

        double suma = 0;

        for (int i = 0; i < lista_punktow.size(); i++) {
            suma = suma + Math.pow(lista_punktow.get(i).oblicz_odleglosc_od_centroidu(centroid),2);
        }

        E = suma;
        return E;
    }

    void wyswietl_punkty(){
        System.out.println(id);
        lista_punktow.forEach((punkt -> {
            for (int i = 0; i < punkt.wymiar; i++) {
                System.out.print(punkt.wspolrzedne[i] + " ");
            }
            System.out.println();
        }));
    }





}
