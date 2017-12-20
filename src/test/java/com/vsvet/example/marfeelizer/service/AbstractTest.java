package com.vsvet.example.marfeelizer.service;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public abstract  class AbstractTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
}
