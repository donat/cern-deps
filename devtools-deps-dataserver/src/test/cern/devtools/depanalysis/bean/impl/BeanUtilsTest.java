/***********************************************************************************************************************
 * © Copyright 2013 CERN. This software is distributed under the terms of the GNU General Public Licence version 3 (GPL
 * Version 3), copied verbatim in the file "COPYING". In applying this licence, CERN does not waive the privileges and
 * immunities granted to it by virtue of its status as an Intergovernmental Organization or submit itself to any
 * jurisdiction.
 * 
 * If you modify this Program, or any covered work, by linking or combining it with the Eclipse Integrated Development
 * Environment Plugin libraries (or a modified version of these libraries), containing parts covered by the terms of EPL
 * (Eclipse Public licence), the licensors of this Program grant you additional permission to convey the resulting work.
 * Corresponding Source for a non-source form of such a combination shall include the source code for the parts of
 * Eclipse Integrated Development Environment Plugin libraries used as well as that of the covered work.
 **********************************************************************************************************************/
package cern.devtools.depanalysis.bean.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

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
