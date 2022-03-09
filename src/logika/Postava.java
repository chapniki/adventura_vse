package logika;



/**
 * Třída postava určuje osobnosti všech postav a co oni ríkají když se s nimi hrač kontaktuje poprve a podruhe
 *
 *@author     Nikita Chapovskii
 *@version    z kurzu 4IT101 pro školní rok 2021/2022
 */
public class Postava {
    private String jmeno;
    private Boolean uzSNiMluvil;
    private String rec1;
    private String rec2;
    private Vakcina davka;


    /**
     *  Konstruktor třídy.
     *  @param jmeno jmeno postavy
     *  @param uzSNiMluvil určje jectli hrač jíž s postavou kontaktoval
     *  @param rec1 a rec2 je co řekne postava při mluvení
     *  @param davka používá se pro lekaři, ukazuje jakou vakcínu hrač muže od určitého lekaře získat
     */
    public Postava(String jmeno, Boolean uzSNiMluvil, String rec1, String rec2, Vakcina davka) {
        this.jmeno = jmeno;

        this.uzSNiMluvil = uzSNiMluvil;
        this.rec1 = rec1;
        this.rec2 = rec2;
        this.davka = davka;
    }
    /**
     *  Metoda vrací jméno postavy
     */
    public String getJmeno() {
        return jmeno;
    }
    /**
     *  Metoda nastavuje že hrač s touto postavou jíž kontaktoval
     */
    public void setUzSNiMluvil(Boolean uzSNiMluvil) {
        this.uzSNiMluvil = uzSNiMluvil;
    }
    /**
     *  Metoda vrací vakcínu kterou má poostava
     */
    public Vakcina getDavka() {
        return davka;
    }
    /**
     *  Metoda nastavuje vakcíny které má postava
     */
    public void setDavka(Vakcina davka) {
        this.davka = davka;
    }

    /**
     *Metoda vrací vhodný dialog a nastavuje že hrač s postavou jíž mluvil
     */
    public String mluv(){

        if(!uzSNiMluvil) {
            setUzSNiMluvil(true);
            return jmeno + ": " + rec1;
        }
        else {return jmeno + ": " + rec2;
        }
    }
}





