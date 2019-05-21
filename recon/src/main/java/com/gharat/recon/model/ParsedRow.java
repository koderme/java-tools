package com.gharat.recon.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.gharat.recon.common.ReconConstants.INVALID_KEY;

/**
 * Row represent a collection of elements .
 * Consider it similar to row in table.
 */
public class ParsedRow {

    private List<Object> elementList = new ArrayList<>(50);
    private int keyIndex;

    public ParsedRow(int keyIndex) {
        this.keyIndex = keyIndex;
    }

    public String getKey() {
        Object keyObject = elementList.get(keyIndex);
        if (keyObject instanceof String) {
            return (String) keyObject;
        } else {
            return INVALID_KEY;
        }
    }

    public void setKeyIndex(int keyIndex) {
        this.keyIndex = keyIndex;
    }

    public void add(Object value) {
        elementList.add(value);
    }

    public Object getValueAt(int i) {
        return elementList.get(i);
    }

    public Iterator<Object> iterator() {
        return elementList.iterator();
    }

    public <T> T getValueAt(int i, T any) {
        return (T) getValueAt(i);
    }

    @Override
    public String toString() {
        return "ParsedRow{" +
                "elementList=" + elementList +
                ", keyIndex=" + keyIndex +
                '}';
    }
}
