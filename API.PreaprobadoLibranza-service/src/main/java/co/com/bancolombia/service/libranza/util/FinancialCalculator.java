package co.com.bancolombia.service.libranza.util;

/**
 * 
 *<b>IBM. Colombia.</b>
 *
 *<p>Description: </p>
 *
 * @author <A HREF="mailto:duban.cano@ibm.com">Duban Cano</A>
 *
 * @version Aug 21, 2018
 */

public class FinancialCalculator {
	
	private static final float ONE_MILLON = 1000000F;

	/**
	 * 
	 *<b>IBM. Colombia.</b>
	 *
	 *<p>Description: fee calculator</p>
	 *
	 * @author <A HREF="mailto:duvacano@co.ibm.com">Duban Cano</A>
	 *
	 * @param amount
	 * @param i
	 * @param term
	 * @return fee
	 */
	public static double calculate(double amount, double i, int term) {
		return  (amount * (Math.pow(1 + i, term) * i )) / 
				          (Math.pow(1 + i, term) - 1 ); 
	}

	/**
	 * 
	 * @param amount
	 * @param factor
	 * @return
	 */
	public static double calculateSecureCost(double amount, double factor) {
		return amount * factor;
	}

	/**
	 * 
	 * @param amount
	 * @param interestRate
	 * @param term
	 * @param factor
	 * @return
	 */
	public static int calculate(double amount, double interestRate, int term, double factor) {

		return (int) Math.round(doublefeePlusSecureCost);
	}

	/**
	 * 
	 * @param amount
	 * @param interestRateSeguroDesempleo
	 * @param term
	 * @param factorSeguroDesempleo
	 * @return
	 */
	public static int calculate(Double amount, double interestRateSeguroDesempleo,
			int term, double factorSeguroProductoPlanSeguroDesempleo, Double factorSeguroDesempleo) {

		return (int) Math.round(operacion);
	}
}
