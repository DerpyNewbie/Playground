package com.github.derpynewbie.classfromjson.tool;

import javassist.*;

import javax.naming.CannotProceedException;
import java.util.HashMap;

public class CtClassManager {

    private static ClassManager classManager = ClassManager.getInstance();
    private HashMap<String, CtMethod> methodMap = new HashMap<>();
    private CtClass ctClass;
    private boolean isFrozen;


    public ClassManager toClass() throws CannotCompileException, CannotProceedException {
        isFrozen();
        isFrozen = true;
        classManager.addDynamicClass(ctClass.toClass());
        return classManager;
    }

    /**
     * Creates private ctClass method from string.
     *
     * @param methodSrc the source of method.
     * @throws IllegalArgumentException when method name is duplicated.
     */
    public void createMethod(String methodSrc) throws IllegalArgumentException, CannotCompileException, CannotProceedException {
        isFrozen();
        CtMethod method = CtNewMethod.make(methodSrc, this.ctClass);
        this.ctClass.addMethod(method);
    }

    /**
     * Creates private ctClass field from string.
     *
     * @param fieldSrc the source of field.
     */
    public void createField(String fieldSrc) throws CannotCompileException, CannotProceedException {
        isFrozen();
        CtField.make(fieldSrc, this.ctClass);
    }

    /**
     * Sets private ctClass interfaces.
     *
     * @param interfaces the list of CtClass interfaces.
     */
    public void setInterfaces(CtClass[] interfaces) throws CannotProceedException {
        isFrozen();
        this.ctClass.setInterfaces(interfaces);
    }

    /**
     * Sets private ctClass superclass.
     *
     * @param superclass the superclass of private ctClass.
     * @throws CannotCompileException when {@param superclass} was illegal.
     */
    public void setSuperclass(CtClass superclass) throws CannotCompileException, CannotProceedException {
        isFrozen();
        this.ctClass.setSuperclass(superclass);
    }

    /**
     * Gets private ctClass name.
     *
     * @return private ctClass name.
     */
    public String getClassName() {
        return this.ctClass.getName();
    }

    private void isFrozen() throws CannotProceedException {
        if (isFrozen) {
            throw new CannotProceedException("ctClass already compiled");
        }
    }


    /**
     * Const.
     *
     * @param className the name of dynamic class.
     */
    public CtClassManager(String className) {
        this.ctClass = ClassPool.getDefault().makeClass(className);
    }

}
