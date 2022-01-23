package telran.util.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StreamIntroductionTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void streamArraySourceTest() {
		int ar[] = {10, -8, 17, 13, 10};
		int expected[] = {-8, 10};
		int actual[] = Arrays.stream(ar).filter(n -> n % 2 == 0).distinct().sorted().toArray();
		assertArrayEquals(expected, actual);
	}
	
	@Test
	void streamRandomSourceTest() {
		Random gen = new Random();
		assertEquals(10, gen.ints().limit(10).toArray().length);
		gen.ints(10, 10, 25).forEach(n -> assertTrue(n >= 10 && n < 25));
	}
	
	@Test
	void streamCollectionSourceTest() {
		List<Integer> list = Arrays.asList(10, -8, 30);
		Integer[] actual = list.stream().filter(n -> n < 30).toArray(Integer[]::new);
		Integer[] expected = {10, -8};
		assertArrayEquals(expected, actual);
	}
	
	@Test
	void streamStringSourceTest() {
		String str = "Hello";
		str.chars().forEach(n -> System.out.printf("%c;", n));
	}
	
	@Test
	void convertionFromIntToInteger() {
		//List<Integer> expected = Arrays.asList(1, 2, 3);
		//int ar[] = {1, 2, 3};
		//List<Integer> actual = Arrays.stream(ar).boxed().toList();
		//assertIterableEquals(expected, actual);
	}

	@Test
	void conversionFromIntegerToInt() {
		List<Integer> list = Arrays.asList(1, 2, 3);
		assertEquals(6, list.stream().mapToInt(n -> n).sum());
		assertArrayEquals(new int[] {1, 2, 3}, list.stream().mapToInt(n -> n).toArray());
	}
	
	private Integer[] getLotoNumbers(int nNumbers, int min, int max) {
		// TODO
		// using one stream to get array of unique random numbers in given range
		Random gen = new Random();
		int[] obj = gen.ints(nNumbers, min, max).limit(7).toArray();
		Integer[] ar = new Integer[7];
		int ind = 0;
		for (int i : obj) {
			ar[ind] = (Integer) i;
			ind++;
		}
		return ar;
	}
	
	@Test
	void lotoTest() {
		Integer[] lotoNumbers = getLotoNumbers(7, 1, 49);
		assertEquals(7, lotoNumbers.length);
		assertEquals(7, new HashSet<Integer>(Arrays.asList(lotoNumbers)).size());
		Arrays.stream(lotoNumbers).forEach(n -> assertTrue(n >= 1 && n <= 49));
	}
	
	/**
	 * 
	 * @param ar
	 * @return true if contains two numbers, the sum of which equals half of all array's numbers
	 * complexity O[N] 
	 */
	private boolean isHalfSum(int[] ar) {
		// TODO (Not related to streams)
		Arrays.sort(ar);
		int halfOfSum = Arrays.stream(ar).sum() / 2;
		int j = ar.length - 1;
		for (int i = 0; i < ar.length; i++) {
			if (ar[i] + ar[j] == halfOfSum) return true;
		}
		return false;
	}
	
	@Test
	void isHalfSumTest() {
		int ar[] = {1, 2, 10, -7};
		assertTrue(isHalfSum(ar));
		int ar1[] = {1, 2, 10, 7};
		assertFalse(isHalfSum(ar1));
	}
	
	
}
