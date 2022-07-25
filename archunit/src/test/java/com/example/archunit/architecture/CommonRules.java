package com.example.archunit.architecture;

import com.google.common.collect.Maps;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

import static com.example.archunit.architecture.CustomConditions.haveGetter;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.constructors;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

public final class CommonRules
{
    //Classes
    static ArchRule interfacesAreOnlyAllowedRule(final String aPackageName, final String... aExcludedPackages)
    {
	return classes().that()
			.resideInAPackage(aPackageName)
			.and().resideOutsideOfPackages(aExcludedPackages)
			.should().beInterfaces()
			.because(String.format("Resources should be interfaces in %s", aPackageName));
    }

    static ArchRule componentAnnotationIsNotAllowedRule(final String packageName)
    {
	return classes().that()
			.resideInAPackage(packageName)
			.should().notBeAnnotatedWith(Component.class)
			.because(String.format("Component annotation is not allowed in %s", packageName));
    }

    static ArchRule springAnnotationsClassesAreNotAllowedRule(final String... aPackageNames)
    {
	return classes().that()
			.resideInAnyPackage(aPackageNames)
			.should().notBeAnnotatedWith(Service.class)
			.andShould().notBeAnnotatedWith(Component.class)
			.andShould().notBeAnnotatedWith(Configuration.class)
			.andShould().notBeAnnotatedWith(ConfigurationProperties.class)
			.andShould().notBeAnnotatedWith(Bean.class)
			.andShould().notBeAnnotatedWith(Controller.class)
			.andShould().notBeAnnotatedWith(RestController.class)
			.because(String.format("Classes in %s should not be annotated with Spring annotations",
					       Arrays.toString(aPackageNames)));
    }

    //Fields
    static ArchRule fieldsShouldHaveGetterRule(final Map<String, String> exclusions, final String... aPackageNames)
    {
	return fields().that()
		       .areDeclaredInClassesThat().resideInAnyPackage(aPackageNames)
		       .and().areDeclaredInClassesThat().areNotMemberClasses()
		       .and().arePrivate().and().areNotFinal().and().areNotStatic()
		       .should(haveGetter(exclusions))
		       .because("Private fields should have getters in %s" + Arrays.toString(aPackageNames));
    }

    static ArchRule fieldsShouldNotBePublic(final String aPackageName)
    {
	return fields().that()
		       .areDeclaredInClassesThat().resideInAPackage(aPackageName)
		       .should().notBePublic()
		       .because(String.format("Public fields are not allowed in %s", aPackageName));
    }

    static ArchRule publicAndFinalFieldsAreNotAllowedRule(final String... aPackageNames)
    {
	return fields().that()
		       .areDeclaredInClassesThat().resideInAnyPackage(aPackageNames)
		       .and().doNotHaveName("serialVersionUID")
		       .should().notBeFinal()
		       .andShould().notBePublic()
		       .because(String.format("Fields with public and final modifiers are not allowed in %s",
					      Arrays.toString(aPackageNames)));
    }

    static ArchRule fieldsShouldHaveGetterRule(final String... aPackageNames)
    {
	return fieldsShouldHaveGetterRule(Maps.newHashMap(), aPackageNames);
    }

    static ArchRule finalFieldsRule(final String aPackageName, final String... aExcludedPackages)
    {
	return fields().that()
		       .areDeclaredInClassesThat().resideInAPackage(aPackageName)
		       .and().areDeclaredInClassesThat().resideOutsideOfPackages(aExcludedPackages)
		       .and().arePrivate()
		       .and().doNotHaveModifier(JavaModifier.SYNTHETIC)
		       .should().beFinal()
		       .because(String.format(
			       "Private attributes should be instanced by constructor classes, or it should be static in %s",
			       aPackageName));
    }

    //Constructors
    static ArchRule publicConstructorsRule(final String aPackageName)
    {
	return constructors().that()
			     .areDeclaredInClassesThat().resideInAPackage(aPackageName).and()
			     .areDeclaredInClassesThat().areNotAnonymousClasses()
			     .should().bePublic()
			     .because(String.format("Public constructors are only allowed in %s", aPackageName));
    }

    //Methods
    static ArchRule beanMethodsAreNotAllowedRule(final String aPackageName)
    {
	return methods().that()
			.areDeclaredInClassesThat().resideInAPackage(aPackageName)
			.should().notBeAnnotatedWith(Bean.class)
			.because(String.format("Bean methods are not allowed in %s", aPackageName));
    }

    static ArchRule privateMethodsAreNotAllowedRule(final String aPackageName)
    {
	return methods().that()
			.areDeclaredInClassesThat().resideInAPackage(aPackageName)
			.should().notBePrivate()
			.because(String.format("Private methods are not allowed in %s", aPackageName));
    }

    static ArchRule staticMethodsAreNotAllowedRule(final String aPackageName)
    {
	return methods()
		.that().areDeclaredInClassesThat().resideInAPackage(aPackageName)
		.should().notBeStatic()
		.because(String.format("Static methods are not allowed in %s", aPackageName));
    }

    static ArchRule methodsShouldBePublicRule(final String... aPackageNames)
    {
	return methods().that()
			.areDeclaredInClassesThat().resideInAnyPackage(aPackageNames)
			.should().bePublic()
			.because("Public methods are only allowed in " + Arrays.toString(aPackageNames));
    }

    static ArchRule staticMethodsAreOnlyAllowedRule(final String aPackageName)
    {
	return methods().that()
			.areDeclaredInClassesThat().resideInAPackage(aPackageName)
			.should().beStatic()
			.because(String.format("Static methods are only allowed in %s", aPackageName));
    }
}