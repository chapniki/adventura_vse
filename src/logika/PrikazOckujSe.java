package logika;
/**
 *  Třída PrikazOckujSe implementuje pro hru příkaz ockujSe.
 *@author     Nikita Chapovskii
 *@version    z kurzu 4IT101 pro školní rok 2021/2022
 */
public class PrikazOckujSe implements IPrikaz
{
    private static final String NAZEV = "ockujSe";
    private HerniPlan plan;
    private SeznamVakcin seznamVakcin;
    private Batoh batoh;



    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude hledat aktuální místnost
     *  @param seznamVakcin - vakciny které má hrač, kam se bude dodávat nové vakcíny
     *  @param batoh - inventář ve kterém by se měl nacházet pas pro úspěšný průběh příkazu
     */
    public PrikazOckujSe(HerniPlan plan, SeznamVakcin seznamVakcin, Batoh batoh) {
        this.plan = plan;
        this.seznamVakcin = seznamVakcin;
        this.batoh = batoh;
    }
    /**
     *  Provádí příkaz "ockujSe". Ověřuje zda se hrač nachází v postoru kde je lekař a zda hrač má pas a pokud ano pak vkladá do seznamu vakcin novou vakcínu
     *
     *@return zpráva, kterou vypíše hra hráči
     */

    public String proved(String... parametry) {
        if (plan.getAktualniProstor().getNazev().equals("recepce") | plan.getAktualniProstor().getNazev().equals("pediatrie") | plan.getAktualniProstor().getNazev().equals("hala")) {
            // pokud se hrač nachází v prostoru kde žádný lekař není
            return "O to bys měl poprosit nějakého lékáře. Tady ale vhodného kandidata nevidím.";
        }
        if (plan.getAktualniProstor().getPostava().getDavka() == null) {
            // pokud se hrač u tohoto lekaře jíž očoval
            return "už tu vakcinu máš.";
        } else {
            Vakcina naVlozeni = plan.getAktualniProstor().getPostava().getDavka();
            seznamVakcin.vlozVakcinu(naVlozeni);
            plan.getAktualniProstor().getPostava().setDavka(null);
            if(seznamVakcin.pocetInjekci()==3 || !batoh.obsahujeVec("pas")){
                plan.hraKonec();
                return plan.hraKonec();
            }
            else
                plan.getAktualniProstor().getPostava().setUzSNiMluvil(true);
                return "očkoval ses vakcinou " + naVlozeni.getNazev() + "!";
        }
    }
    
    /**
     *  Metoda vrací nazev příkazu
     */
    public String getNazev() {
        return NAZEV;
    }
}

