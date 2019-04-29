package com.github.derpynewbie.classfromjson.tool;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class ObjectExecutor {

    private static final Class<?>[] NULL_PARAM = new Class<?>[]{};

    private final HashMap<String, Class<?>[]> methodMap = new HashMap<>();
    private Object object;

    public void callMethod(String methodName) {
        callMethod(methodName, new Object[]{});
    }

    public void callMethod(String methodName, Object[] params) {
        callMethod(methodName, params, getParamTypes(params));
    }

    public void callMethod(String methodName, Object[] params, Class<?>[] paramTypes) {
        try {
            object.getClass().getMethod(methodName, paramTypes).invoke(object, params);
        } catch (ReflectiveOperationException ex) {
            ex.printStackTrace();
        }
    }

    public static Class<?>[] getParamTypes(Object[] params) {
        ArrayList<Class<?>> paramTypes = new ArrayList<>();

        for (int i = 0; i < params.length; i++) {
            paramTypes.add(params[i].getClass());
        }
        return paramTypes.toArray(new Class<?>[]{});
    }

    public ObjectExecutor(Object object) {
        this.object = object;
        for (Method m :
                object.getClass().getMethods()) {
            methodMap.put(m.getName(), m.getParameterTypes());
        }
    }
}
