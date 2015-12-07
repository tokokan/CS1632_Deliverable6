import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import org.junit.Test;
import org.apache.commons.lang3.*; //used for randomizing strings

/*** IMPORTANT!!!! ******

This project tries to test the built-in concat() function inside Java String library

External libraries: Junit, org.hamcrest, and org.apache.commons !!!
****/

public class StringConcatTest {
	
	//needed global variables/constants for the test
	int NUM_OF_ARRAYS = 10000; //number of arrays tested
	int MAX_SIZE = 10000; //maximum  size of an array
	Random rnd = new Random(); //random generator
	
	//consider this: String C = String A + String B
	String initArraysLeft[] = new String[NUM_OF_ARRAYS]; //This is String A
	String initArraysRight[] = new String[NUM_OF_ARRAYS]; //This is String B
	String concatArrays[] = new String[NUM_OF_ARRAYS]; //This is String C
	
	//these 2 arrays are for Property 5
	String initArraysLeftBackup[] = new String[NUM_OF_ARRAYS]; //This is String A_Backup
	String initArraysRightBackup[] = new String[NUM_OF_ARRAYS]; //This is String B_Backup
	
	//set up the arrays before testing can be done
	@Before
	public void setUp() throws Exception
	{
		//randomize the arrays with random Strings
		for (int i = 0; i < NUM_OF_ARRAYS; i++)
		{
			//initialize, aka randomizing the String arrays
			initArraysLeft[i] = RandomStringUtils.random(rnd.nextInt(MAX_SIZE));
			initArraysRight[i] = RandomStringUtils.random(rnd.nextInt(MAX_SIZE));
			//preparation to test proprety 5 
			initArraysLeftBackup[i] = new String(initArraysLeft[i]);
			initArraysRightBackup[i] = new String(initArraysRight[i]);
			//we concat the arrays!
			concatArrays[i] = initArraysLeft[i].concat(initArraysRight[i]);
		}
	}
	
	
	//Property 1 : concatenated string's sizes are the sum of the other 2 strings
	@Test
	public void testSize() {
		boolean result = true;
		for (int i = 0 ; i < NUM_OF_ARRAYS; i++)
		{
			//check the size of all the arrays
			if (concatArrays[i].length() != (initArraysLeft[i].length() + initArraysRight[i].length() ) )
			{
				result = false;
				break;
			}
		}
		assertTrue(result);
	}

	
	//property 2 : adding empty string return itself!
	@Test 
	public void testAddingEmptyString()
	{
		boolean result = true;
		//here we try adding initArraysLeft members with an empty string and see if it returns the same object
		for (int i = 0; i < NUM_OF_ARRAYS; i++)
		{
			String temp = initArraysLeft[i].concat("");
			if (temp != initArraysLeft[i])
			{
				result = false;
				break;
			}
		}
		assertTrue(result);
	}

	
	//property 3 : final string has all characters of the left string at the first half
	//it means we test if all content of initArraysLeft is inside concatArrays at the correct order and placement
	@Test
	public void testContainLeft()
	{
		boolean result = true;
		for (int i = 0; i< NUM_OF_ARRAYS; i++)
		{
			for (int j = 0; j< initArraysLeft[i].length(); j++)
			{
				if ( initArraysLeft[i].charAt(j) != concatArrays[i].charAt(j))
				{
					result = false;
					break;
				}
			}
			if (result == false) break;
		}
		assertTrue(result);
	}
	
	
	//property 4: final string has all characters of the right string at the second half
	//it means we test if all content of initArraysRight is inside concatArrays at the correct order and placement
	@Test
	public void testContainRight()
	{
		boolean result = true;
		for (int i = 0; i< NUM_OF_ARRAYS; i++)
		{
			int leftSize = initArraysLeft[i].length();
			for (int j = 0; j< initArraysRight[i].length(); j++)
			{
				if ( initArraysRight[i].charAt(j) != concatArrays[i].charAt(leftSize + j))
				{
					result = false;
					break;
				}
			}
			if (result == false) break;
		}
		assertTrue(result);
	}
	
	
	//property 5: concatenation should not change the 2 original strings
	@Test
	public void testNotChangeOperands()
	{
		boolean result = true;
		for (int i = 0; i< NUM_OF_ARRAYS ; i++)
		{
			if ( (initArraysLeft[i].compareTo(initArraysLeftBackup[i]) != 0) || (initArraysRight[i].compareTo(initArraysRightBackup[i]) != 0) )
			{
				result = false;
				break;
			}
		}
		assertTrue(result);
	}
}