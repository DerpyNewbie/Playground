package com.github.derpynewbie.jsontoclass;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

import java.util.Map;
import java.util.Objects;

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

            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.makeClass("Test");
            CtMethod m = CtNewMethod.make(methodSrc, cc);
            cc.addMethod(m);

            Object test = cc.toClass().newInstance();
            test.getClass().getMethod(methodName).invoke(test);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }


}
