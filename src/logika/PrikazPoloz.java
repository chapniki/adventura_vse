package logika;
/**
 *  Třída PrikazPoloz implementuje pro hru příkaz poloz.
 *@author     Nikita Chapovskii
 *@version    z kurzu 4IT101 pro školní rok 2021/2022
 */
public class PrikazPoloz implements IPrikaz
{
private static final String NAZEV = "poloz";
    private HerniPlan plan;
    private Batoh batoh;
  /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude hledat aktuální místnost a do ni se bude vkladat věc z inventáře hrače
   *   @param batoh inventař ve kterem se bude hledat věc pro vyloženi
    */      
    public PrikazPoloz(HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }
    /**
     *  Provádí příkaz "poloz". V inventaři hledá věc a pokud ji najde, vyjme ji z bathu a vloží do aktuální místnosti
     *
     *@param parametry - nazev hledáné věci
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // jestliže bude chybět druhé slovo, pak se program zeptá:
            return "Co mám položit? Musíš zadat jméno věci";
        }
        String jmenoVeci = parametry[0];

        // odstraní se věc z inventaře a vratí se do proměnné vec
        Vec vec = batoh.vyberVec(jmenoVeci);
        if (vec == null) {
            return "Taková věc v batohu není ";
        }
        else {
	     // věc se vkladá do prostoru
	        plan.getAktualniProstor().vlozVec(vec);
            return "Položil jsi " + jmenoVeci;
        }
    }
    /**
     *  Metoda vrací nazev příkazu
     */
    public String getNazev() {
        return NAZEV;
    }

}
