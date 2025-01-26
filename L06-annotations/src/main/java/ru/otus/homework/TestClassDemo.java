package ru.otus.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

@SuppressWarnings("java:S106")
public class TestClassDemo {
    public static final Logger logger = LoggerFactory.getLogger(TestClass.class);

    public static void TestClassStart(String classTestName) throws ClassNotFoundException {
        Class<?> testClass = Class.forName(classTestName);
        ArrayList<Method> methodsTest = getMetodsForAnnotation(testClass,"Test");
        ArrayList<Method> methodsBefore = getMetodsForAnnotation(testClass,"Before");
        ArrayList<Method> methodsAfter = getMetodsForAnnotation(testClass,"After");

        if (methodsTest.isEmpty()) return;

        for (Method method: methodsTest){
            Object object = null;
            try {
                Constructor<?> constructor = testClass.getConstructor();
                object = constructor.newInstance();

                runBefore(methodsBefore, object);
                runTest(method, object);

            } catch (java.lang.Throwable e){
                logger.debug("Test failed");
            }
            finally {
                runAfter(methodsAfter, object);
            }
        }
    }

    private static void runBefore(ArrayList<Method> methodsBefore, Object object)  {
        for (Method before: methodsBefore){
            try {
                var resultBefore = before.invoke(object);
            } catch (Throwable e) {
                logger.debug(before.toString() + " failed");
            }
        }
    }

    private static void runTest(Method method, Object object) throws IllegalAccessException, InvocationTargetException {
        var resultTest = method.invoke(object);
    }

    private static void runAfter(ArrayList<Method> methodsAfter, Object object) {
        for (Method after: methodsAfter){
            try {
                var resultAfter = after.invoke(object);
            } catch (Throwable e) {
                logger.debug(after.toString() + " failed");
            }
        }
    }

    private static ArrayList<Method> getMetodsForAnnotation(Class<?> testClass, String annotationText) {
        ArrayList<Method> result = new ArrayList<>();
        for (Method method: testClass.getDeclaredMethods()){
            Annotation [] annotations = method.getAnnotations();
            for (Annotation annotation : annotations){
                if (annotation.toString().contains(annotationText))
                    result.add(method);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            TestClassDemo.TestClassStart("ru.otus.homework.TestClass");
        }
        catch (ClassNotFoundException ex){
            logger.debug(ex.toString());
        }
    }
}
