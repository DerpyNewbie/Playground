package com.github.derpynewbie.classfromjson;

import com.github.derpynewbie.classfromjson.tool.CtClassManager;
import com.github.derpynewbie.classfromjson.tool.ObjectExecutor;

public class Main {

    public static void main(String[] args) {
        //TODO: somehow get argument from json
        String className = "Test";
        String methodSrc = "public void send() {" +
                "   int a = 1;" +
                "   int b = 2;" +
                "   System.out.println(\"UwU Watz Dis!?\");" +
                "   System.out.println(a + \" + \" + b + \" = \" + (a+b));" +
                "}";
        String paramMethodSrc = "public void add(int a, int b) {" +
                "   int ans = a + b;" +
                "   System.out.println(ans);" +
                "}";
        // Create javassist CtClass from CtClassManager
        CtClassManager ctClassManager = new CtClassManager(className);
        try {
            // Create methods
            ctClassManager.createMethod(methodSrc);
            ctClassManager.createMethod(paramMethodSrc);
            // Create class -> Create executor
            ObjectExecutor objectExecutor = ctClassManager.toClass().createObjectExecutor(className);
            // Call methods from executor
            objectExecutor.callMethod("add", new Object[]{1, 2}, new Class<?>[]{int.class, int.class});
            objectExecutor.callMethod("add", new Object[]{3, 4}, new Class<?>[]{int.class, int.class});
            objectExecutor.callMethod("send");
            // UwU
            System.out.println("OwO");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
