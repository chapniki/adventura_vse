package logika;

/**
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *@author     Nikita Chapovskii
 *@version    z kurzu 4IT101 pro školní rok 2021/2022
 */
public class PrikazVezmi implements IPrikaz
{
private static final String NAZEV = "vezmi";
    private HerniPlan plan;
    private Batoh batoh;
  /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude hledat aktuální prostor
    *  @param batoh inventař kam se budou umístět nelezené v prostoru věci
    */      
    public PrikazVezmi(HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }
    /**
     *  Provádí příkaz "seber". V aktuální místnosti hledá věc, která je předána jako parametr
     *
     *@param parametry - nazev věci která se bude hledat v prostoru
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // jestliže bude chybět druhé slovo, pak se program zeptá:
            return "Co mám sebrat? Musíš zadat jméno věci";
        }
        String jmenoVeci = parametry[0];

        if (!plan.getAktualniProstor().vecJeMozneVzit(jmenoVeci) || batoh.pocetVeciVBatohu()==2) {
            //jestliže věc není přenositelná a nebo kapsy hrače jsou plné
            return "Taková věc tu není a nebo ji nemůžeš vzít!";
        }
        else {
	     // vkladá věc do batohu
	        batoh.vlozVec(plan.getAktualniProstor().vyberVec(jmenoVeci));
            return "Sebral jsi " + jmenoVeci;
        }
    }
    /**
     *  Metoda vrací nazev příkazu
     */
    public String getNazev() {
        return NAZEV;
    }
}

