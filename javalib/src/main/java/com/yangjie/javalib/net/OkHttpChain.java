package com.yangjie.javalib.net;

import java.util.List;

/**
 * @author yangjie
 * @description:
 * @date :2020/3/14 9:54 PM
 */
public class OkHttpChain {

    class Response {
        public void addFailure(Response nextResponse) {

        }

    }

    class Request {

    }

    public interface Validatetor<T> {
        // 将链传入校验方法
        Response validate(Chain<T> chain);

        // 链持有元数据
        interface Chain<T> {
            T getData();

            Response proceed(T data);
        }
    }

    class ValidateChain implements Validatetor.Chain<Request> {
        private final Request request;
        private List<String> interceptors;
        private final int index;

        /**
         * 构造器
         *
         * @param request
         * @param interceptors
         * @param index
         */
        public ValidateChain(Request request, List<String> interceptors, int index) {
            this.request = request;
            this.interceptors = interceptors;
            this.index = index;
        }

        @Override
        public Request getData() {
            return this.request;
        }

        @Override
        public Response proceed(Request request) {
            // 判断是否执行完所有拦截器
            if (index >= interceptors.size()) {
                return null;
            }
            // index加1，指向下个拦截器
            ValidateChain next = new ValidateChain(request, interceptors, index + 1);
            String interceptorName = interceptors.get(index);
            // 因为拦截器是Spring-Bean，所以采用IOC方式去获取具体Bean
//            Validatetor validatetor = SpringIocUtil.getBean(interceptorName, Validatetor.class);

//            Response response = interceptors.get(index).validate(next);
            Response response = null;
            return response;
        }
    }

    class NameValidator implements Validatetor<Request> {
        @Override
        public Response validate(Chain<Request> chain) {
            Request request = chain.getData();

            Response response = new Response();
            // 校验姓名

            // 执行下个校验器
            Response nextResponse = chain.proceed(request);
            response.addFailure(nextResponse);
            return response;
        }
    }

    public static void main(String[] args) {


    }

}
