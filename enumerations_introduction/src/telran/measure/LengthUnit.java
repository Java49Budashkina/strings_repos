package telran.measure;

public enum LengthUnit {
	MM(0.001f), SM(0.01f), INCH(0.0254f), FOOT(0.348f), ARSHIN(0.7112f), M(1), KM(1000),VERSTA(1066.8f), NM(1812);
	private float value;
	private LengthUnit(float val) {
		value = val;
	}

	 public Length between(Length len1, Length len2) {
		Length l1 = len1.convert(this); 
		System.out.print(l1.toString()+ "\n");
		Length l2 = len2.convert(this); 
		System.out.print(l2.toString()+ "\n");
		
			
		float res = l1.getAmount() - l2.getAmount();
		if (res < 0.0001)
			res = Math.round(res);
		return new Length(res, this);
	}
	 public float getValue() {return value;}
} 
