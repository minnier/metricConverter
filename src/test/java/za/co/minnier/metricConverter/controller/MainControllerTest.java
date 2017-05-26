package za.co.minnier.metricConverter.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MainController.class, secure = false)
public class MainControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void GetConversions() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/convert/conversions").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{'category':'length','name':'METERS TO FEET','from':'meters','to':'feet'},"
				+ "{'category':'length','name':'CENTIMETERS TO INCHES','from':'centimeters','to':'inches'},"
				+ "{'category':'length','name':'KILOMETERS TO YARDS','from':'kilometers','to':'yards'},"
				+ "{'category':'mass','name':'KILOGRAMS TO POUNDS','from':'kilograms','to':'pounds'},"
				+ "{'category':'temperature','name':'CELSIUS TO FAHRENHEIT','from':'celsius','to':'fahrenheit'},"
				+ "{'category':'temperature','name':'FAHRENHEIT TO CELSIUS','from':'fahrenheit','to':'celsius'}]";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void getLengthMetersToFeet() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/convert/length/1?from=meters&to=feet").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "3.28084";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void getCentimetersToInches() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/convert/length/1?from=centimeters&to=inches").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "0.393701";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void getKilometersToYards() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/convert/length/1?from=kilometers&to=yards").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "1093.61";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void getKilogramsToPounds() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/convert/mass/1?from=kilograms&to=pounds").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "2.20462";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void getCelsiusToFahrenheit() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/convert/temperature/1?from=celsius&to=fahrenheit").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "33.8";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
