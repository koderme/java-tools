package com.gharat.recon.streams;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
