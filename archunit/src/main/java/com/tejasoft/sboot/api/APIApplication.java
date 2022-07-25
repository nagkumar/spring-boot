package com.tejasoft.sboot.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.tejasoft.sboot.archunit"})
public class APIApplication
{
    public static void main(final String[] aArgs)
    {
	SpringApplication.run(APIApplication.class, aArgs);
    }
}
