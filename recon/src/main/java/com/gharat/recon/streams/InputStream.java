package com.gharat.recon.streams;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class InputStream {

    abstract public void load() throws FileNotFoundException;

    abstract public String readLine() throws IOException;

}
