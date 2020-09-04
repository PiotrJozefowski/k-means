package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSV_Processor {

    File plik_csv;
    Scanner odczyt;
    ArrayList<Punkt> lsita_punktow = new ArrayList<>();

    public CSV_Processor(String sciezka) {
        this.plik_csv = new File(sciezka);

        try {
            odczyt = new Scanner(plik_csv);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    void stworz_punkty(){
        String[] wspolrzedne_konwert = null;

        while(odczyt.hasNext()){
            wspolrzedne_konwert = odczyt.nextLine().split(";");
            double[] wspolrzedne = new double[wspolrzedne_konwert.length];
            for (int i = 0; i < wspolrzedne_konwert.length; i++){
                wspolrzedne[i] = Double.parseDouble(wspolrzedne_konwert[i]);
            }

            lsita_punktow.add(new Punkt(wspolrzedne));

        }

    }




}
