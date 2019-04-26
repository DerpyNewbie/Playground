package com.github.derpynewbie.classfromjson;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class Main {

//    static String stringJson = "{\n" +
//            "        'users': {\n" +
//            "            'methods': ('run','create', 'retrieve', 'update', 'destroy', 'list'),\n" +
//            "            'run': \"public void run() {" +
//            "System.out.println(\"Success!\");\"" +
//            "       }\n" +
//            "}";

    public static void main(String[] args) {
        //TODO: somehow get argument from json

        String className = "Test";
        String methodSrc = "public void send() {" +
                "   int a = 1;" +
                "   int b = 2;" +
                "   System.out.println(\"UwU Watz Dis!?\");" +
                "   System.out.println(a + \" + \" + b + \" = \" + (a+b));" +
                "}";
        try {
            // Create class with javassist
            ClassPool classPool = ClassPool.getDefault();
            CtClass ctClass = classPool.makeClass(className);
            CtMethod method = CtNewMethod.make(methodSrc, ctClass);

            ctClass.addMethod(method);
            // Call method using Reflection
            Object object = ctClass.toClass().newInstance();
            object.getClass().getMethod(method.getName()).invoke(object);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
