package com.github.derpynewbie.classfromjson;

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

        CtClassManager ctClassManager = new CtClassManager("Test");
        String methodSrc = "public void send() {" +
                "   int a = 1;" +
                "   int b = 2;" +
                "   System.out.println(\"UwU Watz Dis!?\");" +
                "   System.out.println(a + \" + \" + b + \" = \" + (a+b));" +
                "}";
        try {
            ctClassManager.createMethod(methodSrc);
            //TODO: how do you know there is send method?
            ctClassManager.doMethod("send");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
