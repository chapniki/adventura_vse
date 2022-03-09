package uiText;


import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import logika.IHra;
import java.io.IOException;
/**
 *  Class TextoveRozhrani
 * 
 *  Toto je uživatelského rozhraní aplikace Adventura
 *  Tato třída vytváří instanci třídy Hra, která představuje logiku aplikace.
 *  Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *  Pokud chcete hrát tuto hru, vytvořte instanci této třídy
 *  a poté na ní vyvolejte metodu "hraj". 
 *  
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Nikita Chapovskii
 *@version    pro školní rok 2021/2022
 */

public class TextoveRozhrani {
    private IHra hra;
    String file;
    String output = "";        // vztvaříme proměnnou output kam se budou zapisovat vstupy od uživatele a výstupy hry


    /**
     *  Vytváří hru.
     */
    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }

    /**
     *  Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     *  příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     *  hodnotu true). Nakonec vypíše text epilogu.
     */
    public void hraj() {
        if (zeptatNaSecteni()){
            vytvoritSoubor();
        }
        output += hra.vratUvitani()+System.lineSeparator();
        System.out.println(hra.vratUvitani());

        // základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!hra.konecHry()) {
            String radek = prectiString();
            String tmp2 = hra.zpracujPrikaz(radek);
            output += tmp2+System.lineSeparator();
            System.out.println(tmp2);
        }
        output += hra.vratEpilog()+System.lineSeparator();
        napsatSoubor();
        System.out.println(hra.vratEpilog());
    }

    /**
     *  Metoda přečte příkaz z příkazového řádku
     *
     *@return    Vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String tmp = scanner.nextLine();
        output += "> " + tmp + System.lineSeparator();
        return tmp;
    }
    /**
     *  Metoda se ptá užívatele zda bude chtit ukladat hru do textového souboru
     *
     *@return    Vrací true jestli užívatel zvolil ano a naopak
     */
    private Boolean zeptatNaSecteni () {
        while (true) {
            System.out.println("Ukladat všechny vstupy a vypsání hry do textového souboru?");
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String tmp = scanner.nextLine();
            if (tmp.equals("ano")) {
                return true;
            }
            if (tmp.equals("ne")) {
                return false;
            } else {
                System.out.println("prikaz nebyl rozpoznan, napiste ano nebo ne");
                continue;
            }
        }
    }
    /**
     *  Metoda se ptá užívatele na cestu do souboru a jeho jméno
     */
    private void vytvoritSoubor(){
        while (true) {
            System.out.println("Zvolte cestu kam bude umíštěn soubor ve formatu DISK:\\SLOZKA");
            System.out.print("> ");
            Scanner path = new Scanner(System.in);
            String cesta = path.nextLine();

            System.out.println("Zvolte název textového souboru");
            System.out.print("> ");
            Scanner name = new Scanner(System.in);
            String nazev = name.nextLine();

            file = cesta + "\\" + nazev + ".txt";
            File soubor = new File(file); //vytváří se nový soubor
            try {
                soubor.createNewFile();
            } catch (IOException e) {
                System.out.println("Nepodarilo se vytvorit soubor");//pokud užívatel zvolil špatnou cestu
                continue;
            }
            break;
        }
    }
    /**
     *  Metoda vypisuje vstup od uživatele a výstup hry do souboru
     */
    private void napsatSoubor(){
        try {
            if (file != null) {
                FileWriter writer = new FileWriter(file);
                writer.write(output);
                writer.close();
            }
        } catch (IOException e) {}
    }
}
