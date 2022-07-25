package com.example.archunit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.archunit"})
public final class ExampleArchunitApplication
{
    public static void main(final String[] aArgs)
    {
	SpringApplication.run(ExampleArchunitApplication.class, aArgs);
    }
}
