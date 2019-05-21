package com.gharat.recon.streams;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FileInputStream extends InputStream {

    private String filePath;
    private BufferedReader br;

    public FileInputStream(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void load() throws FileNotFoundException {
        this.br = new BufferedReader(new FileReader(filePath));
    }

    @Override
    public String readLine() throws IOException {
        return this.br.readLine();
    }
}
