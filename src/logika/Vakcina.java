package logika;
 /** Třída Vakcina
 *
  * *@author Nikita Chapovskii
  *@version    z kurzu 4IT101 pro školní rok 2021/2022
  */
public class Vakcina {
        private String nazev;

     /**
      *  Konstruktor třídy
      * @param nazev nazev vakcíny
      */
    public Vakcina (String nazev) {
        this.nazev = nazev;
    }
     /**
      *  Metoda vrací nazev vakcíny
      */
        public String getNazev () {
        return nazev;
    }
    }



