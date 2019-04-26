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
        String methodName = "send";
        String methodSrc = "public void send() {" +
                "   System.out.println(\"UwU Watz Dis!?\");" +
                "   System.out.println(\"2 + 2 = \" + (2+2));" +
                "}";
        try {
            // Create class with javassist
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.makeClass("Test");
            CtMethod m = CtNewMethod.make(methodSrc, cc);
            cc.addMethod(m);
            // Create object from class then call method
            Object test = cc.toClass().newInstance();
            test.getClass().getMethod(methodName).invoke(test);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
