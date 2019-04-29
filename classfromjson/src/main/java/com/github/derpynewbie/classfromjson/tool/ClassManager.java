package com.github.derpynewbie.classfromjson.tool;

import java.util.HashMap;

public class ClassManager {

    private static ClassManager classManager;
    private static HashMap<String, Class> classMap = new HashMap<>();

    /**
     * Creates new ObjectExecutor from dynamic class's name.
     *
     * @param className the name of dynamic class.
     * @return the ObjectExecutor of dynamic class instance.
     * @throws IllegalAccessException when failed to create new instance.
     * @throws InstantiationException when failed to instantiation new instance.
     * @throws ClassNotFoundException when failed to find class from map of class.
     */
    public ObjectExecutor createObjectExecutor(String className) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (classMap.containsKey(className)) {
            Class clazz = classMap.get(className);
            return new ObjectExecutor(clazz.newInstance());
        } else {
            throw new ClassNotFoundException("Class " + className + " not found");
        }
    }

    /**
     * Gets dynamic class from dynamic class's name.
     *
     * @param className the name of dynamic class.
     * @return the dynamic class.
     * @throws ClassNotFoundException when dynamic class not found.
     */
    public Class getDynamicClass(String className) throws ClassNotFoundException {
        if (classMap.containsKey(className)) {
            return classMap.get(className);
        } else {
            throw new ClassNotFoundException("Class " + className + " not found");
        }
    }

    /**
     * Gets instance of ClassManager.
     *
     * @return this instance.
     */
    static ClassManager getInstance() {
        if (classManager == null) {
            new ClassManager();
        }
        return classManager;
    }

    void addDynamicClass(Class clazz) {
        classMap.put(clazz.getName(), clazz);
    }

    private ClassManager() {
        classManager = this;
    }

}
