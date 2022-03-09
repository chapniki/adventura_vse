package logika;
/**
 *  Třída PrikazSeznamVakcin implementuje pro hru příkaz seznamVakcin.
 *@author     Nikita Chapovskii
 *@version    z kurzu 4IT101 pro školní rok 2021/2022
 */
public class PrikazSeznamVakcin implements IPrikaz{

    private static final String NAZEV = "seznamVakcin";
    private SeznamVakcin seznamVakcin;
    /**
     *  Konstruktor třídy
     *  @param seznamVakcin - seznam dávek které má hrač
     */
    public PrikazSeznamVakcin( SeznamVakcin seznamVakcin) {
        this.seznamVakcin = seznamVakcin;
    }
    /**
     *  Provádí příkaz "seznamVakcin" a vypisuje vakcíny které hrač jíž má
     *
     *@return zpráva, kterou vypíše hra hráči
     */
    public String proved(String... parametry) {
        return seznamVakcin.nazvyVakcin()+"pfizer";
    }
    /**
     *  Metoda vrací nazev příkazu
     */
    public String getNazev() {
        return NAZEV;
    }

}


