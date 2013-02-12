/*
 * $Id: Pair.java,v 1.7 2008-12-17 09:38:41 vbaggiol Exp $
 * 
 * $Date: 2008-12-17 09:38:41 $ $Revision: 1.7 $ $Author: vbaggiol $
 * 
 * Copyright CERN, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui.handlers;

import java.io.Serializable;

/**
 * This generic class {@code Pair<E, T>} reflects a pair of two objects of type {@code <E>} and {@code <T>}. Instances
 * of this class are immutable.<br>
 * The class can be used, for instance, when in a map the key value needs to be a pair of two objects or when a method
 * needs to return two different objects. Because of this purpose the {@link #hashCode()} and {@link #equals(Object)}
 * methods have been implemented and the classes of the objects that are used in the pair also must implement these
 * methods. 
 * TODO: Greg: Consider if we need here newInstance(..) method or rather we should use directly constructor..
 * 
 * @author Raphael Mueller
 * @author Wojciech Sliwinski
 * @param <E> the first part of the pair
 * @param <T> the second part of the pair
 */
public class Pair<E, T> implements Serializable {

    private static final long serialVersionUID = -4802312005581189229L;
    
    /** Represents the first part of the pair. */
    private E first;
    /** Represents the second part of the pair. */
    private T second;

    //
    // -- CONSTRUCTORS -----------------------------------------------
    //

    /**
     * This constructor takes references to two objects and initializes first and second part of the pair with them.
     * 
     * @param first the object reference that should be used as first part of the pair
     * @param second the object reference that should be used as second part of the pair
     */
    protected Pair(E first, T second) {
        this.first = first;
        this.second = second;
    }

    //
    // -- PUBLIC METHODS -----------------------------------------------
    //

    /**
     * Convenience method to be able to instantiate this class easily:<br> {@code Pair<String, Integer> stringIntPair =
     * Pair.newInstance(first, second);}
     * 
     * @param first the first part of the pair
     * @param second the second part of the pair
     * @param <R> the type of the part member
     * @param <S> the type of the part member
     * @return a new instance of this class.
     */
    public static <R, S> Pair<R, S> newInstance(R first, S second) {
        return new Pair<R, S>(first, second);
    }

    /**
     * Returns the first part of the pair.
     * 
     * @return the first.
     */
    public E getFirst() {
        return this.first;
    }

    /**
     * Return the second part of the pair.
     * 
     * @return the second.
     */
    public T getSecond() {
        return this.second;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((this.first == null) ? 0 : this.first.hashCode());
        result = PRIME * result + ((this.second == null) ? 0 : this.second.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair<?, ?> other = (Pair<?, ?>) obj;
        if (this.first == null) {
            if (other.first != null) {
                return false;
            }
        } else if (!this.first.equals(other.first)) {
            return false;
        }
        if (this.second == null) {
            if (other.second != null) {
                return false;
            }
        } else if (!this.second.equals(other.second)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "<" + this.first + ", " + this.second + ">";
    }
    
    //
    // -- PROTECTED METHODS -----------------------------------------------
    //

    //
    // -- PRIVATE METHODS -----------------------------------------------
    //

    /**
     * Sets the first part of the pair.
     * 
     * @param first the first to set.
     */
    protected void setFirst(E first) {
        this.first = first;
    }

    /**
     * Sets the second part of the pair.
     * 
     * @param second the second to set.
     */
    protected void setSecond(T second) {
        this.second = second;
    }

    // /**
    // * The main method is for testing purpose and contains examples.
    // * @param args the command line arguments to pass.
    // */
    // public static void main(String args[]) {
    // // Create some value we use
    // String street = "Route de l'Europe";
    // Integer houseNumber = Integer.valueOf(936);
    // String someName = "Some Name";
    // // This will also work with a set or a list, but I will use a map for this
    // // example.
    // // Create an empty Map for a Pair<String, Integer>.
    // Map<Pair<String, Integer>, String> stringIntKeyMap = new HashMap<Pair<String, Integer>, String>();
    // // Create an empty pair (first and second are null) and initialize it (I now
    // // street/houseNumber is a silly example, but it will do)
    // Pair<String, Integer> stringIntPair = Pair.newInstance(street, houseNumber);
    // // add it to the map
    // stringIntKeyMap.put(stringIntPair, someName);
    // }
}
