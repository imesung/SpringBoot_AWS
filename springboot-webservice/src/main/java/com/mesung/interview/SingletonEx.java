package com.mesung.interview;

public class SingletonEx {
    private SingletonEx() {

    }

    public static SingletonEx getInstance() {
        return LazyHolder.singletonEx;
    }

    private static class LazyHolder{
        private static final SingletonEx singletonEx = new SingletonEx();
    }
}
