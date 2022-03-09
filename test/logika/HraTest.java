package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková, Nikita Chapovskii
 * @version  pro školní rok 2021/2022
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("hala", hra1.getHerniPlan().getAktualniProstor().getNazev());

        // testujeme příkaz teleportuj, může se ukázat chyba, protože občas příkaz přenáší hráče do stejdného prostoru
        hra1.zpracujPrikaz("teleportuj");
        assertNotEquals("hala", hra1.getHerniPlan().getAktualniProstor().getNazev());
        tearDown();
        setUp();


        //testujeme průchody mezi prostory a zda prostory s lekaři se otevírají pouze když hrač o nich dozví
        assertEquals("Tam se odsud jít nedá!", hra1.zpracujPrikaz("jdi ordinace112"));
        hra1.zpracujPrikaz("jdi recepce");
        hra1.zpracujPrikaz("mluv recepcni");
        hra1.zpracujPrikaz("jdi hala");
        assertNotEquals("Tam se odsud jít nedá!", hra1.zpracujPrikaz("jdi ordinace112"));

        //otestujeme batoh a příkazy malujPas, seber a poloz
        hra1.zpracujPrikaz("jdi hala");
        hra1.zpracujPrikaz("jdi recepce");
        assertEquals("Sebral jsi bonbon", hra1.zpracujPrikaz("vezmi bonbon"));
        assertEquals("věci: ",hra1.getHerniPlan().getAktualniProstor().nazvyVeci());
        hra1.zpracujPrikaz("jdi hala");
        hra1.zpracujPrikaz("jdi pediatrie");
        hra1.zpracujPrikaz("vezmi papir");
        assertEquals("Taková věc tu není a nebo ji nemůžeš vzít!", hra1.zpracujPrikaz("vezmi pastelky")); //testujeme kapacitu batohu
        hra1.zpracujPrikaz("poloz bonbon");
        assertEquals("Sebral jsi pastelky", hra1.zpracujPrikaz("vezmi pastelky"));//testujeme že item nebyl odstranen z prostoru
        assertEquals("věci v kapse: papir pastelky ", hra1.zpracujPrikaz("kapsy"));
        hra1.zpracujPrikaz("poloz papir");
        hra1.zpracujPrikaz("vezmi bonbon");
        assertEquals("věci v kapse: pastelky bonbon ", hra1.zpracujPrikaz("kapsy"));//testujeme že vyložený item byl spravně uložen do prostoru
        hra1.zpracujPrikaz("poloz bonbon");
        assertEquals("Taková věc tu není a nebo ji nemůžeš vzít!", hra1.zpracujPrikaz("vezmi dite"));//testujeme neprenositelne veci
        hra1.zpracujPrikaz("vezmi papir");
        hra1.zpracujPrikaz("malujPas");
        assertEquals("věci v kapse: pas ", hra1.zpracujPrikaz("kapsy"));//testujeme že prikaz malujPas

        //ověrujeme prikaz mluv a zda lekari neřeknou uvítání po kontaktování bud když hrač hned žadá očkování nebo jen s lekarem mluví
        hra1.zpracujPrikaz("jdi hala");
        hra1.zpracujPrikaz("jdi ordinace112");

        assertEquals("lekar112: Dobrý den. Takže jste příšel za dávkou Moderny? V takovém případě mi dejte vědět kdy budete připravení a dejte mi svůj doklad.", hra1.zpracujPrikaz("mluv lekar112"));
        assertEquals("lekar112: Aha, to jste znovu vy. Potřebujete ještě něco?", hra1.zpracujPrikaz("mluv lekar112"));
        hra1.zpracujPrikaz("ockujSe");
        hra1.zpracujPrikaz("jdi hala");
        hra1.zpracujPrikaz("jdi ordinace113");
        hra1.zpracujPrikaz("ockujSe");
        assertEquals("lekar113: Aha, to jste znovu vy. Potřebujete ještě něco?", hra1.zpracujPrikaz("mluv lekar113"));

        //testujeme prikaz ockujSe a seznamVakcin
        hra1.zpracujPrikaz("ockujSe");
        assertEquals("Máš dávky následujících vakcín: Jenssen Moderna pfizer", hra1.zpracujPrikaz("seznamVakcin"));
        assertEquals("už tu vakcinu máš.", hra1.zpracujPrikaz("ockujSe"));
        hra1.zpracujPrikaz("jdi hala");
        assertEquals("O to bys měl poprosit nějakého lékáře. Tady ale vhodného kandidata nevidím.", hra1.zpracujPrikaz("ockujSe"));

        //testujeme úspěšný konec hry
        hra1.zpracujPrikaz("jdi ordinace114");
        hra1.zpracujPrikaz("ockujSe");
        assertTrue(hra1.konecHry());

        tearDown();
        setUp();

        //testujeme neúspěšný konec hry
        hra1.zpracujPrikaz("jdi recepce");
        hra1.zpracujPrikaz("mluv recepcni");
        hra1.zpracujPrikaz("jdi hala");
        hra1.zpracujPrikaz("jdi ordinace112");
        assertFalse(hra1.konecHry());
        hra1.zpracujPrikaz("ockujSe");
        assertTrue(hra1.konecHry());

        tearDown();
        setUp();

        // příkaz konec
        hra1.zpracujPrikaz("konec");
        assertTrue(hra1.konecHry());
    }
}
