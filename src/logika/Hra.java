package logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Nikita Chapovskii
 *@version    z kurzu 4IT101 pro školní rok 2021/2022
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private Batoh batoh;
    private SeznamVakcin seznamVakcin;


    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan), seznam platných příkazů, inventář hráče a vakcíny.
     */
    public Hra() {
        seznamVakcin = new SeznamVakcin();
        batoh = new Batoh();
        herniPlan = new HerniPlan(this, batoh);
        platnePrikazy = new SeznamPrikazu();

        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazVezmi(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazKapsy(batoh));
        platnePrikazy.vlozPrikaz(new PrikazPoloz(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazSeznamVakcin(seznamVakcin));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOckujSe(herniPlan, seznamVakcin, batoh));
        platnePrikazy.vlozPrikaz(new PrikazMalujPas(batoh));
        platnePrikazy.vlozPrikaz(new PrikazTeleportuj(herniPlan));

    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Legendy říkají," + System.lineSeparator() +

               "že ten vyvolaný, kdo sj dá všechny vakcíny proti COVID-19 světa (Pfizer," + System.lineSeparator() +
               "Moderna, Jenssen a AstraZeneca), bude moct slyšet vzmach křídel motýlka ve " + System.lineSeparator() +
                  "vzdálenosti jednoho kilometru, cítit tok času, budovat a ničit celá města holýma rukama" + System.lineSeparator() +
                "a řídit evoluci." + System.lineSeparator() +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Dík, že jste zahrali. ahoj.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
        }
        else {
            textKVypsani="Nevím co tím myslíš, tento příkaz neznám? ";
        }
        return textKVypsani;
    }
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
}

