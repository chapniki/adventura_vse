package logika;
/**
 *  Třída PrikazMluv implementuje pro hru příkaz mluv.
 *
 *@author     Nikita Chapovskii
 *@version    z kurzu 4IT101 pro školní rok 2021/2022
 */
public class PrikazMluv implements IPrikaz{
    private static final String NAZEV = "mluv";
    private HerniPlan herniPlan;
    /**
     *  Konstruktor třídy
     *
     *  @param herniPlan - odkazuje na plan, s postavy v jednotlivých prostorách
     */
    public PrikazMluv(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * metoda promluvi na danou postavu
     * @param parametry - jméno postavy
     * @return vrací se zpráva pro hráče
     */
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Na koho mam mluvit?";
        }
        if(parametry.length == 1 && herniPlan.getAktualniProstor().getPostava().getJmeno().equals(parametry[0])) {
            if(herniPlan.getAktualniProstor().getNazev().equals("recepce")){
                herniPlan.otevriOrdinace();
            }
            // pokud je druhe slovo takové, které lze vložit do kabelky
            return herniPlan.getAktualniProstor().getPostava().mluv();
        }

        else return "Vypadá že on/ona tady neni!";
    }
    /**
     *@return Vrací se nazev příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}

