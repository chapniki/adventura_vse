package logika;
/**
 *  Třída PrikazMalujPas implementuje pro hru příkaz malujPas.
 *
 *@author     Nikita Chapovskii
 *@version    z kurzu 4IT101 pro školní rok 2021/2022
 */
public class PrikazMalujPas implements IPrikaz
{
    private static final String NAZEV = "malujPas";
    private Batoh batoh;
    Vec pas = new Vec("pas", false);
    /**
     *  Konstruktor třídy
     *
     *  @param batoh je inventář ze kterého budou odstraňovat potřebné pro malování pasu věci a kam se bude umíštět pas
     */
    public PrikazMalujPas(Batoh batoh) {
        this.batoh = batoh;
    }


    /**
     *  Provádí příkaz "malujPas". V inventaři se hledají potřebné věci a vkladá se pas
     *
     *@return zpráva, kterou vypíše hra hráči
     */
    public String proved(String... parametry) {
        if (batoh.obsahujeVec("papir")&&batoh.obsahujeVec("pastelky")){
            batoh.vyberVec("papir");
            batoh.vyberVec("pastelky");
            batoh.vlozVec(pas);

            return "namaloval sis pas, gratuluju! Teď konečně máš ačkoli jednu krásnou fotku a jmenuješ se El Muchacho.";
        }
        else {return "dobrý nápad! Ale ten masterpiece nemůžeš namalovat jen holýma rukama.";
        }

    }
    /**
     *@return Vrací se nazev příkazu
     */
    public String getNazev() {
        return NAZEV;
    }

}
