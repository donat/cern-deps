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

import java.util.LinkedList;
import java.util.List;

public class Measurement {

	public static final int EMF = 1;
	public static final int ORACLE = 2;

	private static int selected = 1;

	private static Measurement emfInstance = new Measurement("EMF");
	private static Measurement oracleInstance = new Measurement("Oracle");

	private static List<Measurement> history = new LinkedList<Measurement>();

	public static Measurement getInstance() {
		switch (selected) {
		case EMF:
			return emfInstance;
		case ORACLE:
			return oracleInstance;
		default:
			throw new RuntimeException();
		}
	}

	public static void save() {
		history.add(getInstance());
		
		getInstance().prettyPrint();
		
		emfInstance = new Measurement("EMF");
		oracleInstance = new Measurement("Oracle");
	}

	public static void select(int s) {
		selected = s;
	}

	long productNum;
	long classNum;
	long methodNum;
	long depsNum;
	long fileSize;

	long fieldnum;
	final List<Long> structHeapSizeMiB = new LinkedList<Long>();
	final List<Long> depsHeapSizeMiB = new LinkedList<Long>();
	long startTimeNanos;
	long endTimeNanos;
	String type = "undefined";

	public Measurement(String type) {
		this.type = type;
	}

	public long getProductNum() {
		return productNum;
	}

	public void setProductNum(long productNum) {
		this.productNum = productNum;
	}

	public long getClassNum() {
		return classNum;
	}

	public void setClassNum(long classNum) {
		this.classNum = classNum;
	}

	public long getMethodNum() {
		return methodNum;
	}

	public void setMethodNum(long methodNum) {
		this.methodNum = methodNum;
	}

	public long getFieldnum() {
		return fieldnum;
	}

	public void setFieldnum(long fieldnum) {
		this.fieldnum = fieldnum;
	}

	public long getStartTimeNanos() {
		return startTimeNanos;
	}

	public void registerStartTime() {
		this.startTimeNanos = System.nanoTime();
	}

	public long getEndTimeNanos() {
		return endTimeNanos;
	}

	public void registerEndTime() {
		this.endTimeNanos = System.nanoTime();
	}

	public void registerStructHeapSizeOnDiscovery() {
		Runtime runtime = Runtime.getRuntime();
		structHeapSizeMiB.add((runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024));
	}

	public void registerDepsHeapSizeOnDiscovery() {
		Runtime runtime = Runtime.getRuntime();
		depsHeapSizeMiB.add((runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024));
	}

	public String getType() {
		return type;
	}

	public void prettyPrint() {
		if (!history.contains(this)) {
			history.add(this);
		}

		System.out.print("Type,");
		System.out.print("Number of products,");
		System.out.print("Number of classes,");
		System.out.print("Number of methods,");
		System.out.print("Number of fields,");
		System.out.print("Number of deps,");
		System.out.print("Total discovery time (sec),");
		System.out.print("Model size (bytes)");
		System.out.println();

		for (Measurement m : history) {
			System.out.print(m.type);
			System.out.print(",");
			System.out.print(m.productNum);
			System.out.print(",");
			System.out.print(m.classNum);
			System.out.print(",");
			System.out.print(m.methodNum);
			System.out.print(",");
			System.out.print(m.fieldnum);
			System.out.print(",");
			System.out.print(m.depsNum);
			System.out.print(",");
			System.out.print((m.endTimeNanos - m.startTimeNanos) / (1000 * 1000 * 1000));
			System.out.print(",");
			System.out.print(m.fileSize);
			System.out.println();
		}
	}

	public void addClassesNum(int size) {
		classNum += size;

	}

	public void addMethodsNum(int size) {
		methodNum += size;

	}

	public void addFieldsNum(int size) {
		fieldnum += size;
	}

	public long getDepsNum() {
		return depsNum;
	}

	public void setDepsNum(long depsNum) {
		this.depsNum = depsNum;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

}
