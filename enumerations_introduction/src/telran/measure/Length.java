package telran.measure;

public class Length implements Comparable<Length>{

	private float amount;
	private LengthUnit unit; //measure
	
	public Length(float val, LengthUnit unit) {
		amount = val;
		this.unit = unit;
	}
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(obj instanceof Length) {
			Length temp = ((Length)obj).convert(unit);
			return Float.compare(amount, temp.amount) == 0;
		}
		return false;
	}
	
	@Override
	public int compareTo(Length o) {
		return Float.compare(amount, o.convert(unit).amount);
	}
	
	public String toString() {
		return  Float.toString(amount) + unit.name();
	}
	
	public Length convert(LengthUnit lenUn) {
		
		return  new Length (amount * unit.getValue() / lenUn.getValue(), lenUn);
		
	}
	public LengthUnit getUnit() {
		return unit;
	}
	public float getAmount() {
		return amount;
	}
}
