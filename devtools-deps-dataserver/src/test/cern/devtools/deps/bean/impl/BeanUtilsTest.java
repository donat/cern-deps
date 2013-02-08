package cern.devtools.deps.bean.impl;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import cern.devtools.deps.bean.impl.BeanUtils;

public class BeanUtilsTest {

	@Test(expected=Exception.class)
	public void instantion() throws Exception {
		Class<?> cls = Class.forName("cern.devtools.depanalysis.bean.impl.BeanUtils");
		Constructor<?> constr = cls.getDeclaredConstructor();
		constr.setAccessible(true);
		constr.newInstance();
	}
	
	@Test
	public void listToStringEmpty() {
		List<String> list = Collections.emptyList();
		String result = BeanUtils.listToString(list, ",");
		assertEquals("", result);
	}
	
	@Test
	public void listToStringSingleElement() {
		List<String> list = Arrays.asList("a");
		String result = BeanUtils.listToString(list, ",");
		assertEquals("a", result);
	}
	
	@Test
	public void listToStringMultipleElement() {
		List<String> list = Arrays.asList("a", "b", "c");
		String result = BeanUtils.listToString(list, ",");
		assertEquals("a,b,c", result);
	}
	
	@Test
	public void listToStringCompareResults() {
		List<String> list = Arrays.asList("a", "b", "c");
		String result1 = BeanUtils.listToString(list, ",");
		String result2 = BeanUtils.listToString(list, ',');
		assertEquals(result1, result2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void listToStringInvalidInput1() {
		BeanUtils.listToString(Arrays.asList("a", "b", "c"), null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void listToStringInvalidInput2() {
		BeanUtils.listToString(null, ",");
	}
	
	@Test
	public void stringToListEmptyInput() {
		List<String> list = BeanUtils.stringToList("", ' ');
		assertEquals(0, list.size());
	}
	
	@Test
	public void stringToListOneElement() {
		List<String> list = BeanUtils.stringToList("a", ' ');
		assertEquals(1, list.size());
		assertEquals("a", list.get(0));
	}
	
	@Test
	public void stringToListMultipleElements() {
		List<String> list = BeanUtils.stringToList("a b c", ' ');
		assertEquals(3, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("c", list.get(2));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void stringToListIllegalParam() {
		BeanUtils.stringToList(null, ' ');
	}
	
	@Test
	public void stringToListRoundUsage() {
		List<String> param = Arrays.asList("a", "b", "c");
		BeanUtils.stringToList(BeanUtils.listToString(param, ' '), ' ').equals(param);
	}
	
	@Test
	public void getMilis2007() {
		long result = BeanUtils.getMillis2007();
		System.out.println(result);
		assertTrue(result > 116764092971L);
	}
	
	@Test
	public void endsWithDollar() {
		assertTrue(BeanUtils.endsWithDollarNumber("anything$123"));
		assertTrue(BeanUtils.endsWithDollarNumber("anything$0"));
		assertFalse(BeanUtils.endsWithDollarNumber("anything$"));
		assertFalse(BeanUtils.endsWithDollarNumber("anything"));
		assertFalse(BeanUtils.endsWithDollarNumber("anything$foo"));
        assertFalse(BeanUtils.endsWithDollarNumber("anything$0$foo"));
    }
}
