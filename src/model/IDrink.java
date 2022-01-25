package model;

public interface IDrink {
	String getName();
	Float getPrice();
	void setName(String name);
	void setPrice(Float price);
	boolean equals(Object o);
	String toString();
}
