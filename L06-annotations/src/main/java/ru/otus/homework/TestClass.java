package ru.otus.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"java:S106", "java:S1104", "java:S1144"})
public class TestClass {

    public static final Logger logger = LoggerFactory.getLogger(TestClass.class);

    public TestClass()
    {
        logger.debug("TestClass constructor");
    }

    @Before
    public void procedureBefore1()
    {
        logger.debug("procedureBefore1 is working now");
    }

    @Before
    public void procedureBefore2()
    {
        logger.debug("procedureBefore2 is working now");
    }

    @Test
    public void procedureTest1()
    {
        logger.debug("procedureTest1 is working now");
    }

    @Test
    public void procedureTest2()
    {
        logger.debug("procedureTest2 is working now");
        throw new ArithmeticException();
    }

    @Test
    public void procedureTest3()
    {
        logger.debug("procedureTest3 is working now");
    }

    @After
    public void procedureAfter1()
    {
        logger.debug("procedureAfter1 is working now");
    }
    @After
    public void procedureAfter2()
    {
        logger.debug("procedureAfter2 is working now");
    }
}
