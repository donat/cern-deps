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
package cern.devtools.deps.bean.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantFieldref;
import org.apache.bcel.classfile.ConstantInterfaceMethodref;
import org.apache.bcel.classfile.ConstantMethodref;
import org.apache.bcel.classfile.ConstantNameAndType;
import org.apache.bcel.classfile.ConstantUtf8;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.util.ByteSequence;

/**
 * Helper class which extract method call and field reference information from the bytecode.<p>
 * 
 * The constructor saves the BCEL representation of the class files, and by execution the {@link #traverse()} function
 * it reads all field and method reference information. It determines which method accesses which method and which
 * field. After the traversal the information is available through the {@link #calledMethods(Method)} and
 * {@link #referencedFields(Method)} function.<p>
 * 
 * <i>Note</i> The traverse implementation is based on the BCEL internal bytecode disassemble function which originally
 * used for printing the bytecode in a human-readable format. But, because the library does not provide proper API for
 * this function, it was necessary to copy-paste (and clean-ul )the source code to this class.<p>
 * 
 * @author Donat Csikos
 */
public class ByteCodeTraversal {
	/**
	 * Storage for referenced fields.
	 */
	private final Map<Method, List<String>> fieldRefs = new HashMap<Method, List<String>>();

	/**
	 * Storage for called methods.
	 */
	private final Map<Method, List<String>> methodRefs = new HashMap<Method, List<String>>();
	/**
	 * Java class to extract the references from.
	 */
	private final JavaClass javaClass;

	/**
	 * The constructor saves the class file. The caller should invoke the {@link #traverse()} function, if he explicitly
	 * calls the constructor.
	 * 
	 * @param jca The java class file representation to analyse.
	 */
	public ByteCodeTraversal(JavaClass jca) {
		this.javaClass = jca;
	}

	/**
	 * Returns the binary method signatures which is called within a method.
	 * 
	 * @param m The method.
	 * @return The references or <code>null</code>, if the passed method is not part of the class.
	 */
	public List<String> calledMethods(Method m) {
		return fieldRefs.get(m);
	}

	/**
	 * Returns the list of the referenced fields in binary format which is accessed in the passed method.
	 * 
	 * @param m The method.
	 * @return The references or <code>null</code>, if the passed method is not part of the class.
	 */
	public List<String> referencedFields(Method m) {
		return methodRefs.get(m);
	}

	/**
	 * Extracts called methods and referenced fields from the class' bytecode.
	 * 
	 * @throws IOException If the bytecode is not well-formed.
	 */
	public void traverse() throws IOException {
		Method[] methods = javaClass.getMethods();
		
		for (Method m : methods) {
			// Create container for external method.
			List<String> mRefs = new LinkedList<String>();
			List<String> fRefs = new LinkedList<String>();

			// Traverse the method.
			traverseMethod(m, mRefs, fRefs);

			// Store the referenced items.
			methodRefs.put(m, mRefs);
			fieldRefs.put(m, fRefs);
		}
	}

	private String constantToString(ConstantClass constClass) {
		ConstantUtf8 c1 = (ConstantUtf8) javaClass.getConstantPool().getConstant(constClass.getNameIndex());
		return c1.getBytes();
	}

	private String printSignature(int nameAndTypeIndex) {
		ConstantNameAndType nat = (ConstantNameAndType) javaClass.getConstantPool().getConstant(nameAndTypeIndex);
		String signature = nat.getName(javaClass.getConstantPool()) + ":"
				+ nat.getSignature(javaClass.getConstantPool());
		return signature;
	}

	/**
	 * <p>
	 * Disassembles a stream of byte codes and return the string representation. This code almost entirely copied from
	 * the BCEL library, more precisely from the
	 * {@link org.apache.bcel.classfile.Utility#codeToString(ByteSequence, org.apache.bcel.classfile.ConstantPool, boolean)}
	 * function.
	 * </p>
	 * <p>
	 * Originally it is used to print out the bytecode to the output, but it can be reused as source how the constant
	 * pool is used amongst the functions. In this way, we can determine, which functions are are using a specific
	 * methods/fields.
	 * </p>
	 * 
	 * @param m Method to traverse.
	 * @param mRefs Output variable: The referenced methods.
	 * @param fRefs Output variable: The referenced fields.
	 * @throws IOException If the bytecode of the method is not well-formed.
	 */
	private void traverseMethod(Method m, final List<String> mRefs, final List<String> fRefs) throws IOException {
		// abstract methods does not have bytecode.
		if (m.getCode() == null) {
			return;
		}

		ByteSequence bytes = new ByteSequence(m.getCode().getCode());

		while (bytes.available() > 0) {
			boolean wide = false;
			short opcode = (short) bytes.readUnsignedByte();
			int low, high, npairs;
			int index;
			int no_pad_bytes = 0;
			/*
			 * Special case: Skip (0-3) padding bytes, i.e., the following bytes are 4-byte-aligned
			 */
			if ((opcode == Constants.TABLESWITCH) || (opcode == Constants.LOOKUPSWITCH)) {
				int remainder = bytes.getIndex() % 4;
				no_pad_bytes = (remainder == 0) ? 0 : 4 - remainder;

				for (int i = 0; i < no_pad_bytes; i++) {
					byte b;

					if ((b = bytes.readByte()) != 0)
						throw new IOException("Warning: Padding byte != 0 in " + Constants.OPCODE_NAMES[opcode] + ":"
								+ b);
				}

				// Both cases have a field default_offset in common
				bytes.readInt();
			}

			switch (opcode) {
			/*
			 * Table switch has variable length arguments.
			 */
			case Constants.TABLESWITCH:
				low = bytes.readInt();
				high = bytes.readInt();
				bytes.getIndex();

				int max = high - low + 1;
				for (int i = 0; i < max; i++) {
					bytes.readInt();
				}
				break;

			/*
			 * Lookup switch has variable length arguments.
			 */
			case Constants.LOOKUPSWITCH: {
				npairs = bytes.readInt();
				for (int i = 0; i < npairs; i++) {
					bytes.readInt();
					bytes.readInt();
				}
			}
				break;

			/*
			 * Two address bytes + offset from start of byte stream form the jump target
			 */
			case Constants.GOTO:
			case Constants.IFEQ:
			case Constants.IFGE:
			case Constants.IFGT:
			case Constants.IFLE:
			case Constants.IFLT:
			case Constants.JSR:
			case Constants.IFNE:
			case Constants.IFNONNULL:
			case Constants.IFNULL:
			case Constants.IF_ACMPEQ:
			case Constants.IF_ACMPNE:
			case Constants.IF_ICMPEQ:
			case Constants.IF_ICMPGE:
			case Constants.IF_ICMPGT:
			case Constants.IF_ICMPLE:
			case Constants.IF_ICMPLT:
			case Constants.IF_ICMPNE:
				bytes.readShort();
				break;

			/*
			 * 32-bit wide jumps
			 */
			case Constants.GOTO_W:
			case Constants.JSR_W:
				bytes.readInt();
				break;

			/*
			 * Index byte references local variable (register)
			 */
			case Constants.ALOAD:
			case Constants.ASTORE:
			case Constants.DLOAD:
			case Constants.DSTORE:
			case Constants.FLOAD:
			case Constants.FSTORE:
			case Constants.ILOAD:
			case Constants.ISTORE:
			case Constants.LLOAD:
			case Constants.LSTORE:
			case Constants.RET:
				if (wide) {
					bytes.readUnsignedShort();
					wide = false; // Clear flag
				} else
					bytes.readUnsignedByte();
				break;

			/*
			 * Remember wide byte which is used to form a 16-bit address in the following instruction. Relies on that
			 * the method is called again with the following opcode.
			 */
			case Constants.WIDE:
				wide = true;
				break;

			/*
			 * Array of basic type.
			 */
			case Constants.NEWARRAY:
				bytes.readByte();
				break;

			/*
			 * Access object/class fields.
			 */
			case Constants.GETFIELD:
			case Constants.GETSTATIC:
			case Constants.PUTFIELD:
			case Constants.PUTSTATIC:

				// ----------------------------------------------------------------------------------------------------
				// Extracts referenced field
				// ----------------------------------------------------------------------------------------------------
				index = bytes.readUnsignedShort();
				ConstantFieldref fieldRef = (ConstantFieldref) javaClass.getConstantPool().getConstant(index);
				ConstantClass fieldCls = (ConstantClass) javaClass.getConstantPool().getConstant(
						fieldRef.getClassIndex());
				String fieldClsName = constantToString(fieldCls);
				String fieldSig = printSignature(fieldRef.getNameAndTypeIndex());

				fRefs.add(fieldClsName + "." + fieldSig);
				break;

			/*
			 * Operands are references to methods in constant pool
			 */
			case Constants.INVOKESPECIAL:
			case Constants.INVOKESTATIC:
			case Constants.INVOKEVIRTUAL:

				// ----------------------------------------------------------------------------------------------------
				// Extracts referenced methods
				// ----------------------------------------------------------------------------------------------------
				index = bytes.readUnsignedShort();
				ConstantMethodref methRef = (ConstantMethodref) javaClass.getConstantPool().getConstant(index);
				ConstantClass methCls = (ConstantClass) javaClass.getConstantPool()
						.getConstant(methRef.getClassIndex());
				String methClsName = constantToString(methCls);
				String methSig = printSignature(methRef.getNameAndTypeIndex());
				mRefs.add(methClsName + "." + methSig);
				break;

			case Constants.INVOKEINTERFACE:
				index = bytes.readUnsignedShort();
				bytes.readUnsignedByte(); // historical, redundant
				bytes.readUnsignedByte();

				// ----------------------------------------------------------------------------------------------------
				// Extracts referenced methods
				// ----------------------------------------------------------------------------------------------------
				ConstantInterfaceMethodref iMethRef = (ConstantInterfaceMethodref) javaClass.getConstantPool()
						.getConstant(index);
				methCls = (ConstantClass) javaClass.getConstantPool().getConstant(iMethRef.getClassIndex());
				methClsName = constantToString(methCls);
				methSig = printSignature(iMethRef.getNameAndTypeIndex());
				mRefs.add(methClsName + "." + methSig); // space

				break;

			/*
			 * Operands are references to classes in constant pool and Operands are references to items in constant pool
			 */
			case Constants.NEW:
			case Constants.CHECKCAST:
			case Constants.INSTANCEOF:
			case Constants.LDC_W:
			case Constants.LDC2_W:
				bytes.readUnsignedShort();
				break;

			case Constants.LDC:
				bytes.readUnsignedByte();
				break;

			/*
			 * Increment local variable.
			 */
			case Constants.IINC:
				if (wide) {
					bytes.readUnsignedShort();
					bytes.readShort();
					wide = false;
				} else {
					bytes.readUnsignedByte();
					bytes.readByte();
				}
				break;

			default:
				if (Constants.NO_OF_OPERANDS[opcode] > 0) {
					for (int i = 0; i < Constants.TYPE_OF_OPERANDS[opcode].length; i++) {
						switch (Constants.TYPE_OF_OPERANDS[opcode][i]) {
						case Constants.T_BYTE:
							bytes.readByte();
							break;
						case Constants.T_SHORT:
							bytes.readShort();
							break;
						case Constants.T_INT:
							bytes.readInt();
							break;

						default: // Never reached
							throw new IOException("Unreachable default case reached!");
						}
					}
				}
			}
		}
	}
}
