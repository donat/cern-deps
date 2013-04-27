/**
 */
package cern.devtools.depanalysis.repomodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>RDependency Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRDependencyType()
 * @model
 * @generated
 */
public enum RDependencyType implements Enumerator {
	/**
	 * The '<em><b>PROJECT REFERENCES PROJECT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROJECT_REFERENCES_PROJECT_VALUE
	 * @generated
	 * @ordered
	 */
	PROJECT_REFERENCES_PROJECT(2, "PROJECT_REFERENCES_PROJECT", "PROJECT_REFERENCES_PROJECT"),

	/**
	 * The '<em><b>CLASS USES CLASS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLASS_USES_CLASS_VALUE
	 * @generated
	 * @ordered
	 */
	CLASS_USES_CLASS(0, "CLASS_USES_CLASS", "CLASS_USES_CLASS"),

	/**
	 * The '<em><b>CLASS EXTENDS CLASS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLASS_EXTENDS_CLASS_VALUE
	 * @generated
	 * @ordered
	 */
	CLASS_EXTENDS_CLASS(1, "CLASS_EXTENDS_CLASS", "CLASS_EXTENDS_CLASS"),

	/**
	 * The '<em><b>FIELD TYPE CLASS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIELD_TYPE_CLASS_VALUE
	 * @generated
	 * @ordered
	 */
	FIELD_TYPE_CLASS(3, "FIELD_TYPE_CLASS", "FIELD_TYPE_CLASS"),

	/**
	 * The '<em><b>CLASS IMPLEMENTS CLASS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLASS_IMPLEMENTS_CLASS_VALUE
	 * @generated
	 * @ordered
	 */
	CLASS_IMPLEMENTS_CLASS(4, "CLASS_IMPLEMENTS_CLASS", "CLASS_IMPLEMENTS_CLASS"),

	/**
	 * The '<em><b>METHOD RETURNTYPE CLASS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #METHOD_RETURNTYPE_CLASS_VALUE
	 * @generated
	 * @ordered
	 */
	METHOD_RETURNTYPE_CLASS(5, "METHOD_RETURNTYPE_CLASS", "METHOD_RETURNTYPE_CLASS"),

	/**
	 * The '<em><b>METHOD PARAMTYPE CLASS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #METHOD_PARAMTYPE_CLASS_VALUE
	 * @generated
	 * @ordered
	 */
	METHOD_PARAMTYPE_CLASS(6, "METHOD_PARAMTYPE_CLASS", "METHOD_PARAMTYPE_CLASS"),

	/**
	 * The '<em><b>METHOD CALL METHOD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #METHOD_CALL_METHOD_VALUE
	 * @generated
	 * @ordered
	 */
	METHOD_CALL_METHOD(7, "METHOD_CALL_METHOD", "METHOD_CALL_METHOD"),

	/**
	 * The '<em><b>METHOD REFERENCE FIELD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #METHOD_REFERENCE_FIELD_VALUE
	 * @generated
	 * @ordered
	 */
	METHOD_REFERENCE_FIELD(8, "METHOD_REFERENCE_FIELD", "METHOD_REFERENCE_FIELD"),

	/**
	 * The '<em><b>METHOD OVERRIDE METHOD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #METHOD_OVERRIDE_METHOD_VALUE
	 * @generated
	 * @ordered
	 */
	METHOD_OVERRIDE_METHOD(9, "METHOD_OVERRIDE_METHOD", "METHOD_OVERRIDE_METHOD");

	/**
	 * The '<em><b>PROJECT REFERENCES PROJECT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PROJECT REFERENCES PROJECT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PROJECT_REFERENCES_PROJECT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PROJECT_REFERENCES_PROJECT_VALUE = 2;

	/**
	 * The '<em><b>CLASS USES CLASS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CLASS USES CLASS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CLASS_USES_CLASS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CLASS_USES_CLASS_VALUE = 0;

	/**
	 * The '<em><b>CLASS EXTENDS CLASS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CLASS EXTENDS CLASS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CLASS_EXTENDS_CLASS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CLASS_EXTENDS_CLASS_VALUE = 1;

	/**
	 * The '<em><b>FIELD TYPE CLASS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FIELD TYPE CLASS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FIELD_TYPE_CLASS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FIELD_TYPE_CLASS_VALUE = 3;

	/**
	 * The '<em><b>CLASS IMPLEMENTS CLASS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CLASS IMPLEMENTS CLASS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CLASS_IMPLEMENTS_CLASS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CLASS_IMPLEMENTS_CLASS_VALUE = 4;

	/**
	 * The '<em><b>METHOD RETURNTYPE CLASS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>METHOD RETURNTYPE CLASS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #METHOD_RETURNTYPE_CLASS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int METHOD_RETURNTYPE_CLASS_VALUE = 5;

	/**
	 * The '<em><b>METHOD PARAMTYPE CLASS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>METHOD PARAMTYPE CLASS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #METHOD_PARAMTYPE_CLASS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int METHOD_PARAMTYPE_CLASS_VALUE = 6;

	/**
	 * The '<em><b>METHOD CALL METHOD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>METHOD CALL METHOD</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #METHOD_CALL_METHOD
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int METHOD_CALL_METHOD_VALUE = 7;

	/**
	 * The '<em><b>METHOD REFERENCE FIELD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>METHOD REFERENCE FIELD</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #METHOD_REFERENCE_FIELD
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int METHOD_REFERENCE_FIELD_VALUE = 8;

	/**
	 * The '<em><b>METHOD OVERRIDE METHOD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>METHOD OVERRIDE METHOD</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #METHOD_OVERRIDE_METHOD
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int METHOD_OVERRIDE_METHOD_VALUE = 9;

	/**
	 * An array of all the '<em><b>RDependency Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final RDependencyType[] VALUES_ARRAY =
		new RDependencyType[] {
			PROJECT_REFERENCES_PROJECT,
			CLASS_USES_CLASS,
			CLASS_EXTENDS_CLASS,
			FIELD_TYPE_CLASS,
			CLASS_IMPLEMENTS_CLASS,
			METHOD_RETURNTYPE_CLASS,
			METHOD_PARAMTYPE_CLASS,
			METHOD_CALL_METHOD,
			METHOD_REFERENCE_FIELD,
			METHOD_OVERRIDE_METHOD,
		};

	/**
	 * A public read-only list of all the '<em><b>RDependency Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<RDependencyType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>RDependency Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RDependencyType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RDependencyType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>RDependency Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RDependencyType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RDependencyType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>RDependency Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RDependencyType get(int value) {
		switch (value) {
			case PROJECT_REFERENCES_PROJECT_VALUE: return PROJECT_REFERENCES_PROJECT;
			case CLASS_USES_CLASS_VALUE: return CLASS_USES_CLASS;
			case CLASS_EXTENDS_CLASS_VALUE: return CLASS_EXTENDS_CLASS;
			case FIELD_TYPE_CLASS_VALUE: return FIELD_TYPE_CLASS;
			case CLASS_IMPLEMENTS_CLASS_VALUE: return CLASS_IMPLEMENTS_CLASS;
			case METHOD_RETURNTYPE_CLASS_VALUE: return METHOD_RETURNTYPE_CLASS;
			case METHOD_PARAMTYPE_CLASS_VALUE: return METHOD_PARAMTYPE_CLASS;
			case METHOD_CALL_METHOD_VALUE: return METHOD_CALL_METHOD;
			case METHOD_REFERENCE_FIELD_VALUE: return METHOD_REFERENCE_FIELD;
			case METHOD_OVERRIDE_METHOD_VALUE: return METHOD_OVERRIDE_METHOD;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private RDependencyType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //RDependencyType
