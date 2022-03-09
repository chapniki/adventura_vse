package logika;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *  Taky vytváří postavy a věci v kladuje je do prostorů
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Nikta Chapovskii
 *@version    z kurzu 4IT101 pro školní rok 2021/2022
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;
    private Postava postava;
    private Hra hra;
    private Batoh batoh;

    
     /**
     *  Konstruktor třídy.
     *  @param hra pro určení stavu konce hry
     *  @param batoh batoh, ve kterém by pro úspěšný konec hry měl být pas
     */
    public HerniPlan(Hra hra, Batoh batoh) {
        this.hra = hra;
        this.batoh = batoh;
        zalozProstoryHry();
    }
    /**
     *  Vytváříme instance třídy Vakcina které potom budou přidělené k lekařům.
     */
    Vakcina moderna = new Vakcina("Moderna");
    Vakcina jenssen = new Vakcina("Jenssen");
    Vakcina astraZeneca = new Vakcina("astraZeneca");

    /**
     *  Vytváříme postavy a prostory kam se přidělují statické pastavy
     */
    Postava recepcni = new Postava("recepcni", false, "Dobrý den! Co potřebujete?" + System.lineSeparator() +
            "Já: chtěl bych se očkovat." + System.lineSeparator() +
            "Recepční: Jasný. Jakou vakcínu budete potřebovat?" + System.lineSeparator() +
            "Já: Všechny." + System.lineSeparator() +
            "Recepční: Jak všechny? Všechny 3 dávky?" + System.lineSeparator() +
            "Já: Ne, všechny vakciny. Pfizer už mám, proto budu potřebovat Astra Zeneca, Modernu a Jenssen." + System.lineSeparator() +
            "*okamžitě zbledla, ale po několika vteřinách odpověděla*:" + System.lineSeparator() +
            "Recepční: D-dobrý. Váš doklad p-prosím, aha, zaregisrovala jsem Vás. Tak běžte d-do ordinací číslo 112, 113 a 114." + System.lineSeparator() +
            "Já: Dík. Nashle." + System.lineSeparator() +
            "*Odcházíš z recepce a slyšíš jak postupně volá policistům, FBI a Mosada*", "Aha! T-to js-ste znovu vy? Už jste byl u lekařů? Ne? Tak oni na vás čekají.", null);
    Postava lekar112 = new Postava("lekar112", false, "Dobrý den. Takže jste příšel za dávkou Moderny? V takovém případě mi dejte vědět kdy budete připravení a dejte mi svůj doklad.", "Aha, to jste znovu vy. Potřebujete ještě něco?", moderna);
    Postava lekar113 = new Postava("lekar113", false, "Dobrý den. Takže jste příšel za dávkou Jenssen? V takovém případě mi dejte vědět kdy budete připravení a dejte mi svůj doklad.", "Aha, to jste znovu vy. Potřebujete ještě něco?", jenssen);
    Postava lekar114 = new Postava("lekar114", false, "Dobrý den. Takže jste příšel za dávkou Astra Zeneca? V takovém případě mi dejte vědět kdy budete připravení a dejte mi svůj doklad.", "Aha, to jste znovu vy. Potřebujete ještě něco?", astraZeneca);
    // vytvářejí se jednotlivé prostory

    Prostor hala = new Prostor("hala","hala", null);
    Prostor recepce = new Prostor("recepce", "recepce. Za stolem sedí recepční", recepcni);
    Prostor ordinace112 = new Prostor("ordinace112","ordinace číslo 112. Na dvěřích bylo napsáno, že tady pracuje nějaký pan Vozábal ale ja bych ho spíš pojmenoval prostě jako lekar112", lekar112);
    Prostor ordinace113 = new Prostor("ordinace113","ordinace číslo 113. Na dvěřích bylo napsáno, že tady pracuje nějaká paní Světlíková ale ja bych ji spíš pojmenoval prostě jako lekar113", lekar113);
    Prostor ordinace114 = new Prostor("ordinace114","ordinace číslo 114. Na dvěřích bylo napsáno, že tady pracuje nějaký pan Brabec ale ja bych ho spíš pojmenoval prostě jako lekar114", lekar114);
    Prostor pediatrie = new Prostor("pediatrie","pediatrie", null);

    //vytváříme seznam prostrů, lterý budeme používat pro telepotrací
    List<Prostor> seznamProstoru = new ArrayList<>(Arrays.asList(hala, recepce, pediatrie, ordinace112, ordinace113, ordinace114));
    /**
     *  Metoda vybírá polozku z listu s random indexem
     */
    public Prostor vybratProstor(){
        Prostor prostorProTeleportaci;
        Random randCis = new Random();
        prostorProTeleportaci = seznamProstoru.get(randCis.nextInt(seznamProstoru.size()));

        return prostorProTeleportaci;
    }

    /**
     *  Propojuje prostory pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     *  Vytvaří věci a vkladá je do prostorů
     */
    private void zalozProstoryHry() {

        // přiřazují se průchody mezi prostory (sousedící prostory)
        hala.setVychod(recepce);
        hala.setVychod(pediatrie);
        recepce.setVychod(hala);
        pediatrie.setVychod(hala);
        ordinace112.setVychod(hala);
        ordinace113.setVychod(hala);
        ordinace114.setVychod(hala);

        aktualniProstor = hala;  // hra začíná v hale

        //Vytvařejí se jednotlivé věci a vkladají se do prostorů
        Vec pastelky = new Vec("pastelky", true);
        Vec papir = new Vec("papir", true);
        Vec dite = new Vec("dite", false);
        Vec bonbon = new Vec("bonbon", true);
        pediatrie.vlozVec(pastelky);
        pediatrie.vlozVec(papir);
        pediatrie.vlozVec(dite);
        recepce.vlozVec(bonbon);
    }
    /**
     *  Propojuje prostory o kterých hrač dozví pouze po mluvení s recepční pomocí východů.
     */
    public void otevriOrdinace() {
        hala.setVychod(ordinace112);
        hala.setVychod(ordinace113);
        hala.setVychod(ordinace114);
    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *@return     aktuální prostor
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }

    /**
     *Metoda ukončuje hru
     *@return  zprává o konci hry a výsledky
     */
    public String hraKonec(){
        if(batoh.obsahujeVec("pas")){
            hra.setKonecHry(true);
            return "Udělal jste to! Je čas budovat nový řád!";
        }
        else {
            hra.setKonecHry(true);
            return "Lékař se podíval na tvůj pas a uviděl tam to samé jméno, o kterém mu řekla před chvíli recepční"+ System.lineSeparator() +
                    "a proto zavolal z haly jíž připravené policisté kteří čekali právě na tebe." + System.lineSeparator() +
                    "Bohužel se nestaneš covid-thanosem ale gulag-trpaslíkem.";
        }

    }
}
