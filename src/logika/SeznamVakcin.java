package logika;

import java.util.HashMap;
import java.util.Map;

/**
 *  Trida SeznamVakcin představuje seznam s vakcínami které má hrač
 *
 *@author     Nikita Chapovskii
 *@version    z kurzu 4IT101 pro školní rok 2021/2022
 */

public class SeznamVakcin
{
    public Map<String, Vakcina> vakciny ;   // seznam vakcín
    /**
     *  Konstruktor třídy
     */
    public SeznamVakcin () {
        vakciny = new HashMap<String, Vakcina>();
    }
    /**
     * Vloží věc do seznamu vakcín
     *
     *@param  vakcina  instance vakcíny, která bude vližená do seznamu
     */
    public void vlozVakcinu (Vakcina vakcina) {
        vakciny.put(vakcina.getNazev(),vakcina);
    }
    /**
     * Vrací řetězec názvů vakcín, které má hrač

     *@return  Vracejí se názvy vakcín
     */
    public String nazvyVakcin () {
        String nazvy = "Máš dávky následujících vakcín: ";
        for (String nazvyVakciny: vakciny.keySet()){
            nazvy += nazvyVakciny + " ";
        }
        return nazvy;
    }
    /**
     * Vrací se počet dávek pro kontrolu jestli hrač hyhral
     */
    public int pocetInjekci(){
        return vakciny.size();
    }


}