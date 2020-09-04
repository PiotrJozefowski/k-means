package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Grupa> lista_grup = new ArrayList<>();

        Scanner odczyt = new Scanner(System.in);
        int k = Integer.parseInt(odczyt.nextLine());
        int indx = 0;

        for (int i = 0; i < k; i++) {
            lista_grup.add(new Grupa(i));
        }

        CSV_Processor csv1 = new CSV_Processor("src/iris.csv");
        csv1.stworz_punkty();

        for (int i = 0; i < csv1.lsita_punktow.size(); i++) {
            if(indx == k){ indx = 0;}
            lista_grup.get(indx).lista_punktow.add(csv1.lsita_punktow.get(i));                  // rozdzielenie punktow do grup
            csv1.lsita_punktow.get(i).index_grupy = indx;                           // do sprawdzania czy zmienia grupe
            indx++;
        }

        ////////////////////////////////////////////////////////  POCZATKOWY PODZIAL

        boolean[] zmiana = new boolean[1];
        zmiana[0] = false;

        do {
            zmiana[0] = false;
            lista_grup.forEach((grupa -> {
                grupa.oblicz_centroid();
                grupa.lista_punktow.removeAll(grupa.lista_punktow);
            }));

            csv1.lsita_punktow.forEach((punkt -> {
                double[] min = new double[2];
                for (int i = 0; i < lista_grup.size(); i++) {
                    if (i == 0) {                                                                                       // nadanie indexow poczatkowych
                        min[0] = i;                                                                                     //sprawdzanie odleglosci punktow od centroidow
                        min[1] = punkt.oblicz_odleglosc_od_centroidu(lista_grup.get(i).centroid);                       // odleglosc od pierwszego centroidu przypisywana bez sprawdzania
                    } else {
                        if (min[1] > punkt.oblicz_odleglosc_od_centroidu(lista_grup.get(i).centroid)) {
                            min[0] = i;
                            min[1] = punkt.oblicz_odleglosc_od_centroidu(lista_grup.get(i).centroid);
                        }
                    }
                }

                if(punkt.index_grupy != min[0]){
                    zmiana[0] = true;
                    punkt.index_grupy = (int)(min[0]);
                }
                lista_grup.get((int) (min[0])).lista_punktow.add(punkt);
            }));


          lista_grup.forEach((grupa -> {
              grupa.oblicz_centroid();
              grupa.oblicz_E();
              System.out.println(grupa.id + " " + grupa.E);                                                                     ////// obliczanie E kazdej iteracji
          }));
            System.out.println();

        }while(zmiana[0] == true);


        lista_grup.forEach((grupa -> {
            System.out.println(grupa.id);
            grupa.lista_punktow.forEach((punkt -> {
                for (int i = 0; i < punkt.wspolrzedne.length; i++) {
                    System.out.print(punkt.wspolrzedne[i] + " ");
                }
                System.out.println();
            }));
            System.out.println();
        }));





    }

}
