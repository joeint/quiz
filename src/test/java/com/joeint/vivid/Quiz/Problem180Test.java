package com.joeint.vivid.Quiz;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.collect.ImmutableList;

public class Problem180Test {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testGenerateAnswer() {
		// Arrange
		//
		Problem180 app = new Problem180();
		String expectedQuotient = "0372";
		int expectedRemainder = 4;
		List<String> expectedProduct = ImmutableList.of("0", "33", "77", "22");
		List<String> subtrahendArray = ImmutableList.of("40", "79", "26");

		// Act
		//
		Result result = app.generateAnswer(11, 4096);

		// Assert
		//
		assertThat(result, is(notNullValue()));
		assertThat(result.getQuotient(), is(equalTo(expectedQuotient)));
		assertThat(result.getRemainder(), is(equalTo(expectedRemainder)));
		assertThat(result.getProductArray(), is(equalTo(expectedProduct)));
		assertThat(result.getSubtrahendArray(), is(equalTo(subtrahendArray)));
	}

	@Test
	public void testGenerateAnswerNoRemainder() {
		// Arrange
		//
		Problem180 app = new Problem180();
		String expectedQuotient = "1024";
		int expectedRemainder = 0;
		List<String> expectedProduct = ImmutableList.of("4", "0", "8", "16");
		List<String> subtrahendArray = ImmutableList.of("00", "09", "16");

		// Act
		//
		Result result = app.generateAnswer(4, 4096);

		// Assert
		//
		assertThat(result, is(notNullValue()));
		assertThat(result.getQuotient(), is(equalTo(expectedQuotient)));
		assertThat(result.getRemainder(), is(equalTo(expectedRemainder)));
		assertThat(result.getProductArray(), is(equalTo(expectedProduct)));
		assertThat(result.getSubtrahendArray(), is(equalTo(subtrahendArray)));
	}

	@Test
	public void testGenerateAnswerCheckDecimal() {
		// Arrange
		//
		Problem180 app = new Problem180();
		String expectedQuotient = "1024";
		int expectedRemainder = 0;
		List<String> expectedProduct = ImmutableList.of("4", "0", "8", "16");
		List<String> subtrahendArray = ImmutableList.of("00", "09", "16");

		// Act
		//
		Result result = app.generateAnswer(4, 4096);

		// Assert
		//
		assertThat(result, is(notNullValue()));
		assertThat(result.getQuotient(), is(equalTo(expectedQuotient)));
		assertThat(result.getRemainder(), is(equalTo(expectedRemainder)));
		assertThat(result.getProductArray(), is(equalTo(expectedProduct)));
		assertThat(result.getSubtrahendArray(), is(equalTo(subtrahendArray)));
	}

	@Test
	public void testGenerateAnswerDivisorTooLarge() {
		// Arrange
		//
		Problem180 app = new Problem180();

		// Assert/Act
		//
		exception.expect(IllegalArgumentException.class);
		app.generateAnswer(10, 1);
	}
	
	@Test
	public void testGenerateAnswerZero() {
		// Arrange
		//
		Problem180 app = new Problem180();

		// Assert/Act
		//
		exception.expect(IllegalArgumentException.class);
		app.generateAnswer(0, 0);
	}
	
	@Test
	public void testGenerateAnswerDivisorZero() {
		// Arrange
		//
		Problem180 app = new Problem180();

		// Assert/Act
		//
		exception.expect(IllegalArgumentException.class);
		app.generateAnswer(0, 1);
	}
	
	@Test
	public void testGenerateAnswerDividendZero() {
		// Arrange
		//
		Problem180 app = new Problem180();

		// Assert/Act
		//
		exception.expect(IllegalArgumentException.class);
		app.generateAnswer(1, 0);
	}

	@Test
	public void testGenerateAnswerDivisorEqualDividend() {
		// Arrange
		//
		Problem180 app = new Problem180();
		String expectedQuotient = "1";
		int expectedRemainder = 0;
		List<String> expectedProduct = ImmutableList.of("1");
		List<String> subtrahendArray = ImmutableList.of();

		// Act
		//
		Result result = app.generateAnswer(1, 1);

		// Assert
		//
		assertThat(result, is(notNullValue()));
		assertThat(result.getQuotient(), is(equalTo(expectedQuotient)));
		assertThat(result.getRemainder(), is(equalTo(expectedRemainder)));
		assertThat(result.getProductArray(), is(equalTo(expectedProduct)));
		assertThat(result.getSubtrahendArray(), is(equalTo(subtrahendArray)));
	}
	
	@Test
	public void testDigits() {
		// Arrange
		//
		Problem180 app = new Problem180();
		int expectedVal = 1234;
		List<Integer> expectedArray = ImmutableList.of(1, 2, 3, 4);

		// Act
		//
		List<Integer> actualVal = app.splitDigits(expectedVal);

		// Assert
		//
		assertThat(actualVal, is(notNullValue()));
		assertThat(actualVal, is(equalTo(expectedArray)));		
	}
	
	@Test
	public void testDigitsSingle() {
		// Arrange
		//
		Problem180 app = new Problem180();
		int expectedVal = 1;
		List<Integer> expectedArray = ImmutableList.of(1);

		// Act
		//
		List<Integer> actualVal = app.splitDigits(expectedVal);

		// Assert
		//
		assertThat(actualVal, is(notNullValue()));
		assertThat(actualVal, is(equalTo(expectedArray)));		
	}
	
	@Test
	public void testDigitsNegative() {
		// Arrange
		//
		Problem180 app = new Problem180();
		int expectedVal = -1;		

		// Assert/Act
		//
		exception.expect(IllegalArgumentException.class);		
		app.splitDigits(expectedVal);
	}
	
	@Test
	public void testGenerateDetailOutput() {
		// Arrange
		//
		Problem180 app = new Problem180();
		Result resultDouble = new Result();
		resultDouble.setQuotient("0372");
		resultDouble.setRemainder(4);
		resultDouble.setProductArray(ImmutableList.of("0", "33", "77", "22"));
		resultDouble.setSubtrahendArray(ImmutableList.of("40", "79", "26"));
		
		String expected = "   0372 R4\n" + 
				"  +----\n" + 
				"11|4096\n" + 
				"   0\n" + 
				"   -\n" + 
				"   40\n" + 
				"   33\n" + 
				"   --\n" + 
				"    79\n" + 
				"    77\n" + 
				"    --\n" + 
				"     26\n" + 
				"     22\n" + 
				"     --\n" + 
				"      4\n";
		

		// Act
		//
		String result = app.generateDetailOutput(11, 4096, resultDouble);		

		// Assert
		//
		assertThat(result, is(notNullValue()));
		assertThat(result, is(equalTo(expected)));
	}

	@Test
	public void testGenerateDetailOutputNoRemainder() {
		// Arrange
		//
		Problem180 app = new Problem180();
		Result resultDouble = new Result();

		resultDouble.setQuotient("1024");
		resultDouble.setRemainder(0);
		resultDouble.setProductArray(ImmutableList.of("4", "0", "8", "16"));
		resultDouble.setSubtrahendArray(ImmutableList.of("00", "09", "16"));
		
		String expected = "  1024\n" + 
				" +----\n" + 
				"4|4096\n" + 
				"  4\n" + 
				"  -\n" + 
				"  00\n" + 
				"   0\n" + 
				"  --\n" + 
				"   09\n" + 
				"    8\n" + 
				"   --\n" + 
				"    16\n" + 
				"    16\n" + 
				"    --\n" + 
				"     0\n";
		

		// Act
		//
		String result = app.generateDetailOutput(4, 4096, resultDouble);	

		// Assert
		//
		assertThat(result, is(notNullValue()));
		assertThat(result, is(equalTo(expected)));
	}	
}
