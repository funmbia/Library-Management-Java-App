
public class HardcoverBooks  extends PhysicalItem{

	private String title;
	private boolean isRented = false;

	public boolean isRented() {
		return isRented;
	}

	public void setRented(boolean rented) {
		isRented = rented;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public boolean isHardcover() {
		return true;
	}
}
