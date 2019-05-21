package com.gharat.recon.service;

import com.gharat.recon.mapper.ParsedRowMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class ReconServiceImplTest {

    @Mock
    public ParsedRowMapper parsedRowMapper;
    @Mock
    public ParsedRowComparator parsedRowComparator;
    @InjectMocks
    ReconServiceImpl reconService;

    @Before
    public void init() {
        // Below statement is not required if @RunWith annotation is specified
        //MockitoAnnotations.initMocks(this);
    }

    @Test
    public void recon_test() throws IOException {

        String s1 = "this,is,a,good,place";
        String[] s2 = s1.split(",");

        String filePaths[] = {"src/test/resources/lhs.csv", "src/test/resources/rhs.csv"};
        reconService.recon(filePaths);
    }

}