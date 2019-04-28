package com.github.derpynewbie.classfromjson;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

import java.util.ArrayList;

public class CtClassManager {

    private ArrayList<String> methodList = new ArrayList<>();
    private CtClass ctClass;
    private Object object;

    // TODO; make method arguments able to work

    /**
     * Calls private ctClass method from string.
     *
     * @param name the name of method.
     * @throws IllegalArgumentException     when method does not exist.
     * @throws ReflectiveOperationException when calling method is failed.
     */
    public void doMethod(String name) throws IllegalArgumentException, ReflectiveOperationException {
        if (!this.methodList.contains(name)) {
            throw new IllegalArgumentException("Method does not exist");
        }
        try {
            // Call method using reflection
            this.object.getClass().getMethod(name).invoke(this.object);

        } catch (NoSuchMethodException ex1) {
            throw new IllegalArgumentException("Method not found");
        }
    }

    public void doMethod(String name, Object[] params, Class[] paramTypes) throws IllegalArgumentException, ReflectiveOperationException {
        if (!this.methodList.contains(name)) {
            throw new IllegalArgumentException("Method does not exist");
        }
        if (!(params.length == paramTypes.length)) {
            throw new IllegalArgumentException("param and paramTypes length is not same");
        }
        try {
            // Call method using reflection
            this.object.getClass().getMethod(name, paramTypes).invoke(this.object, params);

        } catch (NoSuchMethodException ex1) {
            throw new IllegalArgumentException("Method not found");
        }
    }

    /**
     * Creates private ctClass method from string.
     *
     * @param methodSrc the source of method.
     * @throws IllegalArgumentException when method name is duplicated.
     */
    public void createMethod(String methodSrc) throws IllegalArgumentException {
        try {
            // Create method with javassist
            CtMethod method = CtNewMethod.make(methodSrc, this.ctClass);

            // Check duplicate
            if (methodList.contains(method.getName())) {
                throw new IllegalArgumentException("Method name already exist");
            }

            this.ctClass.addMethod(method);
            this.methodList.add(method.getName());
            this.object = ctClass.toClass().newInstance();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public CtClassManager(String className) {
        this.ctClass = ClassPool.getDefault().makeClass(className);
    }

}
