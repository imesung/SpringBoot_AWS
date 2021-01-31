package com.mesung.interview;

public class SingletonEx {
    private SingletonEx() throws Exception {

    }

    public static SingletonEx getInstance() {
        return LazyHolder.singletonEx;
    }

    private static class LazyHolder{

        public static SingletonEx singletonEx;

        static {
            try {
                singletonEx = new SingletonEx();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
