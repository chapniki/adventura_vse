package logika;

/**
 *  Třída PrikazObsahBatohu implementuje pro hru příkaz obsahBatohu.
 *@author     Nikita Chapovskii
 *@version    z kurzu 4IT101 pro školní rok 2021/2022
 */

public class PrikazKapsy implements IPrikaz
{
private static final String NAZEV = "kapsy";
    private Batoh batoh;
  /**
    *  Konstruktor třídy
    *  
    * @param batoh - inventář, ve kterém se budou hledat věci
    */      
    public PrikazKapsy(Batoh batoh) {
        this.batoh = batoh;
    }
    /**
     *  Provádí příkaz "obsahBatohu". Vypíše názvy věcí v batohu
     *  
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String proved(String... parametry) {
            return batoh.vypisVeci();
    }
    /**
     *  Metoda vrací nazev příkazu
     */
    public String getNazev() {
        return NAZEV;
    }

}


