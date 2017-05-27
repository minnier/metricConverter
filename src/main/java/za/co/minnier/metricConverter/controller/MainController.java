package za.co.minnier.metricConverter.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.minnier.metricConverter.model.ConversionTypes;

@RestController
public class MainController {

	public static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	DecimalFormat precision5 = new DecimalFormat("#.#####");

	// conversion formulas
	private static final double METER_TO_FEET_FACTOR = 3.28084;
	private static final double CENTIMETER_TO_INCH_FACTOR = 0.393701;
	private static final double KILOMETER_TO_YARD_FACTOR = 1093.61;
	private static final double KILOGRAMS_TO_POUNDS_FACTOR = 2.20462;
	private static final double CELSIUS_TO_FAHRENHEIT_FACTOR = 1.8;

	// from & to parameters
	private static final String METER = "meters";
	private static final String FEET = "feet";
	private static final String CENTIMETER = "centimeters";
	private static final String INCH = "inches";
	private static final String KILOMETER = "kilometers";
	private static final String YARD = "yards";
	private static final String KILOGRAMS = "kilograms";
	private static final String POUNDS = "pounds";
	private static final String CELSIUS = "celsius";
	private static final String FAHRENHEIT = "fahrenheit";
	
	/*
	 * Returns a list of conversions that can be done
	 */
	@RequestMapping("/api/convert/conversions")
	public ResponseEntity<List<ConversionTypes>> getConversions() {
		
		List<ConversionTypes> result = new ArrayList<ConversionTypes>();
		
		result.add(new ConversionTypes("length", "METERS TO FEET", "meters", "feet"));
		result.add(new ConversionTypes("length", "FEET TO METERS", "feet", "meters"));
		result.add(new ConversionTypes("length", "CENTIMETERS TO INCHES", "centimeters", "inches"));
		result.add(new ConversionTypes("length", "INCHES TO CENTIMETERS", "inches", "centimeters"));
		result.add(new ConversionTypes("length", "KILOMETERS TO YARDS", "kilometers", "yards"));
		result.add(new ConversionTypes("length", "YARDS TO KILOMETERS", "yards", "kilometers"));
		
		result.add(new ConversionTypes("mass", "KILOGRAMS TO POUNDS", "kilograms", "pounds"));
		result.add(new ConversionTypes("mass", "POUNDS TO KILOGRAMS", "pounds", "kilograms"));

		result.add(new ConversionTypes("temperature", "CELSIUS TO FAHRENHEIT", "celsius", "fahrenheit"));
		result.add(new ConversionTypes("temperature", "FAHRENHEIT TO CELSIUS", "fahrenheit", "celsius"));

		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	/*
	 * api for handling lenght conversions
	 */
	@RequestMapping("/api/convert/length/{value}")
	public ResponseEntity<Double> convertLength(@PathVariable Double value, @PathParam("from") String from, @PathParam("to") String to) {
		try {
			Double result;
			
			if( METER.equals(from) && FEET.equals(to) ) {
				result = Double.valueOf(precision5.format(value * METER_TO_FEET_FACTOR));
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			if( CENTIMETER.equals(from) && INCH.equals(to) ) {
				result = Double.valueOf(precision5.format(value * CENTIMETER_TO_INCH_FACTOR));
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			if( KILOMETER.equals(from) && YARD.equals(to) ) {
				result = Double.valueOf(precision5.format(value * KILOMETER_TO_YARD_FACTOR));
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			
			if( FEET.equals(from) && METER.equals(to) ) {
				result = Double.valueOf(precision5.format(value / METER_TO_FEET_FACTOR));
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			if( INCH.equals(from) && CENTIMETER.equals(to) ) {
				result = Double.valueOf(precision5.format(value / CENTIMETER_TO_INCH_FACTOR));
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			if( YARD.equals(from) && KILOMETER.equals(to) ) {
				result = Double.valueOf(precision5.format(value / KILOMETER_TO_YARD_FACTOR));
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * api for handling mass conversions
	 */
	@RequestMapping("/api/convert/mass/{value}")
	public ResponseEntity<Double> convertMass(@PathVariable Double value, @PathParam("from") String from, @PathParam("to") String to) {
		try {
			Double result;
			if( KILOGRAMS.equals(from) && POUNDS.equals(to) ) {
				result = Double.valueOf(precision5.format(value * KILOGRAMS_TO_POUNDS_FACTOR));
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			
			if( POUNDS.equals(from) && KILOGRAMS.equals(to) ) {
				result = Double.valueOf(precision5.format(value / KILOGRAMS_TO_POUNDS_FACTOR));
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * api for handling temperature conversions
	 */
	@RequestMapping("/api/convert/temperature/{value}")
	public ResponseEntity<Double> convertTemperature(@PathVariable Double value, @PathParam("from") String from, @PathParam("to") String to) {
		try {
			Double result;
			
			if( CELSIUS.equals(from) && FAHRENHEIT.equals(to) ) {
				result = Double.valueOf(precision5.format(value * CELSIUS_TO_FAHRENHEIT_FACTOR + 32));
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			
			if( FAHRENHEIT.equals(from) && CELSIUS.equals(to) ) {
				result = Double.valueOf(precision5.format((value - 32) / CELSIUS_TO_FAHRENHEIT_FACTOR));
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
