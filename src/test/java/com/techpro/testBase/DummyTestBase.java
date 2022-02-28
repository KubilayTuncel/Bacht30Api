package com.techpro.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyTestBase {
    protected RequestSpecification spec03;

    @Before
    public void setup(){
        spec03 =new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com/api/v1")
                .build();
    }
}
