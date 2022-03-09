package logika;

public class PrikazTeleportuj implements IPrikaz{
    private static final String NAZEV = "teleportuj";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se vymístnost pro teleportaci
     */
    public PrikazTeleportuj(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "teleportuj". Přenáší hráče do náhodně vybrané místnosti ve hře.
     *@return zpráva, kterou vypíše hra hráči, název místnosti a její popis
     */
    public String proved(String... parametry) {
        plan.setAktualniProstor(plan.vybratProstor());
        return "Tvoje dávka vakcíny Pfizer ti dovolila se teleportovat do prostoru " + plan.getAktualniProstor().getNazev() + System.lineSeparator()
                + plan.getAktualniProstor().dlouhyPopis();
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }

}
