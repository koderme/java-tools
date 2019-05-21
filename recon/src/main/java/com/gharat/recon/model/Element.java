package com.gharat.recon.model;

/**
 * Holds value of type T
 */
public class Element<T> {
    private T value;

    public Element(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Element{" +
                "value=" + value +
                '}';
    }
}
