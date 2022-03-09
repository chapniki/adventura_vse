package logika;
/** Třída Vec
 *
 * *@author Nikita Chapovskii
 *@version    z kurzu 4IT101 pro školní rok 2021/2022
 */
public class Vec
{ private String jmeno;
    private boolean pickable;

	/**
	 *  Konstruktor třídy
	 * @param jmeno nazev věci
	 * @param pickable určuje jestli je věc přenositelná
	 */
    public Vec (String jmeno, boolean pickable) {
		this.jmeno = jmeno;
		this.pickable = pickable;
	}
	/**
	 *  Metoda vrací nazev věci
	 */
    public String getJmeno () {
		return jmeno;
	}
	/**
	 *  Metoda vrací true/false jestli je věc přenositelná
	 */
	public boolean isPickable() {
		return pickable;
	}

}

