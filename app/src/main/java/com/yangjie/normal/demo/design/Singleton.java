package com.yangjie.normal.demo.design;

/**
 * @author yangjie
 * @description:
 * @date :2020/3/12 12:12 PM
 */
public class Singleton {

    public void getInstance() {

    }

    /**
     * 懒汉式
     */
    static class Test01 {
        private static Test01 instance = null;

        private Test01() {

        }

        public static Test01 getInstance() {
            if (instance == null) {
                synchronized (Test01.class) {
                    if (instance == null) {
                        instance = new Test01();
                    }
                }
            }

            return instance;
        }

    }

    /**
     * 饿汉式
     */
    static class Test02 {

        private static Test02 instance = new Test02();

        private Test02() {

        }

        public static Test02 getInstance() {
            return instance;
        }

    }

    /**
     * 静态内部类方式
     */
    static class Test03 {
        private Test03() {

        }

        private static class Test03Holder {
            private static Test03 instance = new Test03();
        }

        public static Test03 getInstance() {
            return Test03Holder.instance;
        }
    }


    static class Test04 {
        private Test04() {

        }

        private static class Test04Holder {
            private static Test04 instance = new Test04();
        }

        public Test04 getInstance(){
            return Test04Holder.instance;
        }

    }


}
