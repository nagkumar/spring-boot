package com.tejasoft.sboot.tests.archunit.utils.structs;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

public final class ClassesRules
{
    public static ArchRule interfacesAreOnlyAllowedRule(final String aPackageName, String... aExcludedPackages)
    {
	//aExcludedPackages = (aExcludedPackages == null) ? TestArchUnitConsts.TEST_PACKAGE : new String[0];

	return ArchRuleDefinition.classes()
				 .that()
				 .resideInAPackage(aPackageName)
				 .and()
				 .resideOutsideOfPackages(aExcludedPackages)
				 .should()
				 .beInterfaces()
				 .because(String.format("Resources should be interfaces in %s",
							aPackageName));
    }

    public static ArchRule componentAnnotationIsNotAllowedRule(final String aPackageName)
    {
	return ArchRuleDefinition.classes()
				 .that()
				 .resideInAPackage(aPackageName)
				 .should()
				 .notBeAnnotatedWith(Component.class)
				 .because(String.format("Component annotation is not allowed in %s",
							aPackageName));
    }

    public static ArchRule springAnnotationsClassesAreNotAllowedRule(final String... aPackageNames)
    {
	return ArchRuleDefinition.classes()
				 .that()
				 .resideInAnyPackage(aPackageNames)
				 .should()
				 .notBeAnnotatedWith(Service.class)
				 .andShould()
				 .notBeAnnotatedWith(Component.class)
				 .andShould()
				 .notBeAnnotatedWith(Configuration.class)
				 .andShould()
				 .notBeAnnotatedWith(ConfigurationProperties.class)
				 .andShould()
				 .notBeAnnotatedWith(Bean.class)
				 .andShould()
				 .notBeAnnotatedWith(Controller.class)
				 .andShould()
				 .notBeAnnotatedWith(RestController.class)
				 .because(String.format("Classes in %s should not be annotated with Spring annotations",
							Arrays.toString(aPackageNames)));
    }
}
