package com.tejasoft.sboot.tests.archunit.utils.structs;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

public final class MethodsRules
{
    public static ArchRule beanMethodsAreNotAllowedRule(final String aPackageName)
    {
	return ArchRuleDefinition.methods()
				 .that()
				 .areDeclaredInClassesThat()
				 .resideInAPackage(aPackageName)
				 .should()
				 .notBeAnnotatedWith(Bean.class)
				 .because(String.format("Bean methods are not allowed in %s",
							aPackageName));
    }

    public static ArchRule privateMethodsAreNotAllowedRule(final String aPackageName)
    {
	return ArchRuleDefinition.methods()
				 .that()
				 .areDeclaredInClassesThat()
				 .resideInAPackage(aPackageName)
				 .should()
				 .notBePrivate()
				 .because(String.format("Private methods are not allowed in %s",
							aPackageName));
    }

    public static ArchRule staticMethodsAreNotAllowedRule(final String aPackageName)
    {
	return ArchRuleDefinition.methods()
				 .that()
				 .areDeclaredInClassesThat()
				 .resideInAPackage(aPackageName)
				 .should()
				 .notBeStatic()
				 .because(String.format("Static methods are not allowed in %s",
							aPackageName));
    }

    public static ArchRule methodsShouldBePublicRule(final String... aPackageNames)
    {
	return ArchRuleDefinition.methods()
				 .that()
				 .areDeclaredInClassesThat()
				 .resideInAnyPackage(aPackageNames)
				 .should()
				 .bePublic()
				 .because("Public methods are only allowed in " + Arrays.toString(aPackageNames));
    }

    public static ArchRule staticMethodsAreOnlyAllowedRule(final String aPackageName)
    {
	return ArchRuleDefinition.methods()
				 .that()
				 .areDeclaredInClassesThat()
				 .resideInAPackage(aPackageName)
				 .should()
				 .beStatic()
				 .because(String.format("Static methods are only allowed in %s",
							aPackageName));
    }
}
