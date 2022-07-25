package com.tejasoft.sboot.archunit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.tejasoft.sboot.archunit"})
public class ArchUnitSBootApplication
{
    public static void main(final String[] aArgs)
    {
	SpringApplication.run(ArchUnitSBootApplication.class, aArgs);
    }
}
