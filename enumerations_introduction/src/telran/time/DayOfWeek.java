package telran.time;

public enum DayOfWeek implements DaysOperations{
	SUN, MON, TUE, WED, THU, FRI, Shabbat;

	@Override
	public Enum<?> plusDays(int days) {
		DayOfWeek res = null;
		if(days < 0)
			res = (DayOfWeek) minusDays(-days);
		else {
			int seqNumber = (ordinal() + days) % MAX_DAYS;
			res = values()[seqNumber];
		}
		
		return res;
	}

	@Override
	public Enum<?> minusDays(int days) {
		DayOfWeek res = null;
		if(days < 0)
			res = (DayOfWeek) plusDays(-days);
		else {
			int seqNumber = (ordinal() - days) % MAX_DAYS;
			if(seqNumber < 0 )
				seqNumber +=MAX_DAYS;
			res = values()[seqNumber];
		}
		return res;
	}
	
	public static int between(DayOfWeek day1, DayOfWeek day2) {
		int res = day2.ordinal() - day1.ordinal();
		if (res < 0) 
			res +=MAX_DAYS;
		return res;
	}
}
