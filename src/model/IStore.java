package model;

public interface IStore {
	boolean addDrink(IDrink drink);
	boolean searchDrink(String name);
	IDrink getDrink(String name);
	boolean updateDrink(String name, IDrink drink);
	boolean isFull();
	Float howMuch();
	Integer howMany(DrinkType type);
}
