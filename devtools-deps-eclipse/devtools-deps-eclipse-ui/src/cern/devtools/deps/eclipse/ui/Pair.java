/*
 * File Pair.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 30 May 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui;

/**
 * Utility class to hold 2-ary data.
 *
 * @param <U> The type of the first element of the pair.
 * @param <V> The type of the second element of the pair.
 */
public class Pair<U, V> {
    private U first;
    private V second;

    private Pair(U first, V second) {
        super();
        this.first = first;
        this.second = second;
    }

    public static <A, B> Pair<A, B> newInstance(A a, B b) {
    	return new Pair<A, B>(a, b);
    }

    public String toString()
    {
           return "(" + first + ", " + second + ")";
    }

    public U getFirst() {
        return first;
    }

    public void setFirst(U first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		Pair other = (Pair) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}
}