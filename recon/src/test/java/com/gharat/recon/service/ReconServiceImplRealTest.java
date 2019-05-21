package com.gharat.recon.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReconServiceImplRealTest {

    @Autowired
    private ReconServiceImpl reconService;

    @Before
    public void init() {
    }

    @Test
    public void recon_test() throws IOException {

        String s1 = "this,is,a,good,place";
        String[] s2 = s1.split(",");

        String filePaths[] = {"src/test/resources/lhs.csv", "src/test/resources/rhs.csv"};
        reconService.recon(filePaths);
    }

}