package utils;

import java.util.Calendar;

public class DateUtils {

	public static String getHHmm(){
		int heures = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int minutes = Calendar.getInstance().get(Calendar.MINUTE);
		String heures_avec_zeros = ("00" + heures).substring((""+heures).length());
		String minutes_avec_zeros = ("00" + minutes).substring((""+minutes).length());
		return heures_avec_zeros + ":" + minutes_avec_zeros;
	}
	
}
