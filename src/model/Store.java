package model;

public class Store implements IStore {
	
	Drink[][] drinks;
	
	public Store(int rows, int cols) {
		this.drinks = new Drink[rows][cols];
	}
	
	public Store() {
		this(5, 5);
	}

	private boolean isColumnFull(int col) {
		boolean result = false;
		
		return result;
	}
	
	@Override
	public boolean addDrink(IDrink drink) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean searchDrink(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IDrink getDrink(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateDrink(String name, IDrink drink) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Float howMuch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer howMany(DrinkType type) {
		// TODO Auto-generated method stub
		return null;
	}
}
