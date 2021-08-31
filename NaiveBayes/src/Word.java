public class Word {
	String name;
	int count_cooking;
	int count_restaurant;
	int count_plant;
	double freq_cooking;
	double freq_restaurant;
	double freq_plant;
	
	public Word(String name, int count_cooking, int count_plant, int count_restaurant,
			double freq_cooking, double freq_plant, double freq_restaurant) {
		this.name = name;
		this.count_cooking = count_cooking;
		this.count_restaurant = count_restaurant;
		this.count_plant = count_plant;
		this.freq_cooking = freq_cooking+1;
		this.freq_restaurant = freq_restaurant+1;
		this.freq_plant = freq_plant+1;
	}
	
	@Override
    public String toString() {
        return "Word: " + name + " Frequencies: " + freq_cooking + ", " + freq_plant + ", " + freq_restaurant;
    }
}
