package za.co.minnier.metricConverter.model;

import lombok.Data;

@Data
public class ConversionTypes {

	private String category; // ex. distance
	private String name; // ex. METER TO FEET
	private String from; // ex. meter
	private String to; // ex. feet
	
	/**
	 * @param category
	 * @param name
	 * @param from
	 * @param to
	 */
	public ConversionTypes(String category, String name, String from, String to) {
		super();
		this.category = category;
		this.name = name;
		this.from = from;
		this.to = to;
	}

}
