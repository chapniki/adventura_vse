package logika;
import java.util.Map;
import java.util.HashMap;
/**
 *  Trida Batoh, reprezentuje kapsy hlavní postavy, kam se mohou umíštět věci, nalezené v jakémkoli prostoru
 *@author     Nikita Chapovskii
 *@version    z kurzu 4IT101 pro školní rok 2021/2022
 */

public class Batoh
{
private Map<String, Vec> seznamVeci ; //mapa seznamVeci reprezentuje všechny věci, které byly umíštěné do kapsy
    /**
     *  Konstruktor třídy
     */
public Batoh () {
seznamVeci = new HashMap<String, Vec>();
}
    /**
     * Vložení věci do kapes
    *@param  vec  instance věci, která se bude vkládat do kapsy
    */
   public void vlozVec (Vec vec) {
     seznamVeci.put(vec.getJmeno(),vec);
    }
    /**
    * Tato metoda vrací nazvy všech věcí, které jsou v kapsách
    *@return Vrací se řetězec s názvy všech vecí
     */
    /**
     * Metoda stelně jako metoda VyberVec hledá věc s daným jménem ale nevymazuje ji z "batohu"
     *@param  nazev   Jméno věci ktera ba měla být nalezena v kapsách
     *@return Vraci false jestli nic nalezeno nebylo a true jestli bylo
     */
    public boolean obsahujeVec (String nazev) {
        return seznamVeci.containsKey(nazev);
    }
    /**
     * Metoda stelně jako metoda VyberVec hledá věc s daným jménem ale nevymazuje ji z batohu
     *@return Vraci počet všech věcí v kapsách
     */
    public int pocetVeciVBatohu(){return seznamVeci.size();}
    public String vypisVeci () {
        StringBuilder nazvy = new StringBuilder("věci v kapse: ");
        for (String jmenoVeci : seznamVeci.keySet()){
            	nazvy.append(jmenoVeci).append(" ");
        }
        return nazvy.toString();
    }
     /**
     * Metoda hledá věc s daným jmenem a pokud ta věc je v kapsách, tak ji vrátí a vymaže ze seznamu
     *@param  jmeno   Jméno věci ktera ba měla být nalezena v kapsách
     *@return Vraci se instance nalezené v kapse veci nebo hodnota null jestli nic nalezeno nebylo
     */
    public Vec vyberVec (String jmeno) {
        Vec nalezenaVec = null;
        if (seznamVeci.containsKey(jmeno)) {
            nalezenaVec = seznamVeci.get(jmeno);
            seznamVeci.remove(jmeno);
        }   
        return nalezenaVec;
    }


}



