package com.gharat.recon.streams;

import com.gharat.recon.model.AllComparisonResult;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class OutputStream {

    List<String> lineList = new LinkedList<>();

    abstract int writeData(AllComparisonResult compareResult) throws IOException;
}
