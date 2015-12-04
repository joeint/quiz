package com.joeint.vivid.Quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * Computes the long division and output the details of the calc
 * just like grade school
 * 
 * http://rubyquiz.strd6.com/quizzes/180-long-division
 * @author joeint
 *
 */
public class Problem180 {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {		
		if (args.length != 2) {
			System.out.println("Usage is Problem180 {{n1}} {{n2}}");
			return;
		}
		
		int divisor = Integer.parseInt(args[0]);
		int dividend = Integer.parseInt(args[1]);
		
		Problem180 app = new Problem180();
		app.compute(divisor, dividend);
	}
	
	/**
	 * Computes and displays the long division
	 * Showing the work as my teachers have brainwashed me
	 * 
	 * @param divisor
	 * @param dividend
	 */
	public void compute(int divisor, int dividend) {		
		Result result = generateAnswer(divisor, dividend);
		String output = generateDetailOutput(divisor, dividend, result);
		
		System.out.print(output);		
	}
	 
	/**
	 * Generate the long division answer and populate the result object 
	 * with the details
	 * 
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	Result generateAnswer(int divisor, int dividend) {
		Preconditions.checkArgument(divisor <= dividend);
		Preconditions.checkArgument(divisor > 0);
		Preconditions.checkArgument(dividend > 0);


		List<Integer> dividendArray = splitDigits(dividend);
		List<String> productArray = new ArrayList<>();
		List<String> subtrahendArray = new ArrayList<>();
		StringBuilder answer = new StringBuilder();
		Result result = new Result();
		
		int i = 0;
		int quotient = 0;
		int remainder = 0;
		int working = dividendArray.get(i);
		int size = dividendArray.size();
		
		// loop over the dividend size
		// until all digits have been computed
		//
		while (i < size) {
			i++;		
			quotient = working / divisor;			
			productArray.add(String.valueOf(quotient * divisor));
			remainder = working % divisor;
			answer.append(quotient);
			
			// if you are not at the end, recompute the next number to divide into
			//
			if (i != size) {
				Integer[] array = { remainder, dividendArray.get(i) };
				String remainderVal = StringUtils.join(array);
				working =  Integer.valueOf(remainderVal);		
				subtrahendArray.add(remainderVal);
			}
			else if (remainder > 0){
				result.setRemainder(remainder);				
			}
		}
		
		result.setQuotient(answer.toString());
		result.setProductArray(productArray);
		result.setSubtrahendArray(subtrahendArray);

		return result;
	}
	/**
	 * Generates the detail work for the problem given the Result object
	 * 
	 * @param divisor
	 * @param dividend
	 * @param result
	 * @return
	 */
	String generateDetailOutput(int divisor, int dividend, Result result) {
		StringBuilder output = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		int detailStart = Integer.toString(divisor).length() + 1;
		String blankSpace = StringUtils.repeat(" ", detailStart); 
		
		String answer = result.getQuotient() + ( result.getRemainder() > 0 ? " R" + result.getRemainder(): StringUtils.EMPTY );
		List<String> productArray = result.getProductArray();
		List<String> subtrahendArray = result.getSubtrahendArray();
		
		output.append(blankSpace + answer).append(newLine);
		int dividendLength = String.valueOf(dividend).length();
		output.append(StringUtils.repeat(" ", detailStart - 1)).append("+").append(StringUtils.repeat("-", dividendLength)).append(newLine);
		output.append(divisor).append("|").append(dividend).append(newLine);
		
		String tempNumerator = productArray.get(0);

		for (int j=0; j<dividendLength; j++) {
			// General algorithm is the detail can only be as wide as index loop dividend value
			// Pad the result to shift the output to the corresponding location
			//
			output.append(Strings.padStart(productArray.get(j), detailStart + j + 1, ' ')).append(newLine);			
			output.append(Strings.padStart(StringUtils.repeat("-", tempNumerator.length()), detailStart + j + 1,' ')).append(newLine);
			
			if (j < subtrahendArray.size()) {
				// Shift by 2 to handle the next index loop dividend value
				//
				output.append(Strings.padStart(subtrahendArray.get(j), detailStart + j + 2, ' ')).append(newLine);
				tempNumerator = subtrahendArray.get(j);
			}
			else {
				// Shift by 1 to handle the remainder
				//
				output.append(Strings.padStart(String.valueOf(result.getRemainder()), detailStart + j + 1, ' ')).append(newLine);				
			}
		}
		return output.toString();
	}
	
	/**
	 * Splits the passed in value to individual parts
	 * 
	 * @param val
	 * @return
	 */
	public List<Integer> splitDigits(int val) {
		Preconditions.checkArgument(val > 0);

		List<Integer> retVal = new ArrayList<>();
		while (val > 0) {
			retVal.add(val % 10);
			val /= 10;
		}
		
		Collections.reverse(retVal);
		
		return retVal;
	}

}
