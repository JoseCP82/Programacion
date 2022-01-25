package model;

public class Drink implements IDrink {

	private String name;
	private Float price;
	
	public Drink(String name, Float price) {
		this.name = name;
		this.price = price;
	}
	
	public Drink() {
		this("", 0.0f);
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Float getPrice() {
		return this.price;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result=false;
		if (obj != null) {
			if (this == obj) {
				result = true;
			} else {
				if(obj instanceof Drink){
					if (this.name!=null	&& this.name.equals(((Drink)obj).name)) {
						result = true;
					}
				}
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + " --> Price: " + price;
	}

}
