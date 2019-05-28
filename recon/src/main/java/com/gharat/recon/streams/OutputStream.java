package com.gharat.recon.streams;

import com.gharat.recon.model.AllParsedRowComparisonResult;

import java.io.IOException;

public abstract class OutputStream {

    public void preHook() {
    }

    ;

    public abstract void write(AllParsedRowComparisonResult compareResult);

    public void postHook() throws IOException {
    }

    ;
}
