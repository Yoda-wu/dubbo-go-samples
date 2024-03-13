package org.apache.dubbo.samples;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.samples.proto.GreetRequest;
import org.apache.dubbo.samples.proto.GreetResponse;
import org.apache.dubbo.samples.proto.GreetService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // Define a private variable (Required in Spring)
    private static GreetService greetService;
    public static void main(String[] args) throws InterruptedException, IOException {
//        System.setProperty("dubbo.application.service-discovery.migration", "APPLICATION_FIRST");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"/spring/dubbo.consumer.xml"});
//      context.start();
        greetService = (GreetService)context.getBean("GreetService");
        GreetRequest req = GreetRequest.getDefaultInstance();
        System.out.println("dubbo ref started");
        GreetResponse greet = greetService.greet(req);
        System.out.println(greet.getGreeting());
        new CountDownLatch(1).await();
    }

}