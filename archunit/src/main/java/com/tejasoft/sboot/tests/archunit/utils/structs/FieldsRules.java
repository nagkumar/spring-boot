package com.tejasoft.sboot.tests.archunit.utils.structs;

import com.google.common.collect.Maps;
import com.tejasoft.sboot.tests.archunit.utils.CustomConditions;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import java.util.Arrays;
import java.util.Map;

public final class FieldsRules
{
    public static ArchRule fieldsShouldHaveGetterRule(final Map<String, String> aExclusions,
						      final String... aPackageNames)
    {
	return ArchRuleDefinition.fields()
				 .that()
				 .areDeclaredInClassesThat()
				 .resideInAnyPackage(
					 aPackageNames)
				 .and()
				 .areDeclaredInClassesThat()
				 .areNotMemberClasses()
				 .and()
				 .arePrivate()
				 .and()
				 .areNotFinal()
				 .and()
				 .areNotStatic()
				 .should(
					 CustomConditions.haveGetter(aExclusions))
				 .because(
					 "Private fields should have getters in %s" + Arrays.toString(aPackageNames));
    }

    public static ArchRule fieldsShouldNotBePublic(final String aPackageName)
    {
	return ArchRuleDefinition.fields().that().areDeclaredInClassesThat().resideInAPackage(
		aPackageName).should().notBePublic().because(
		String.format("Public fields are not allowed in %s", aPackageName));
    }

    public static ArchRule publicAndFinalFieldsAreNotAllowedRule(final String... aPackageNames)
    {
	return ArchRuleDefinition.fields().that().areDeclaredInClassesThat().resideInAnyPackage(
		aPackageNames).and().doNotHaveName(
		"serialVersionUID").should().notBeFinal().andShould().notBePublic().because(
		String.format("Fields with public and final modifiers are not allowed in %s",
			      Arrays.toString(aPackageNames)));
    }

    public static ArchRule fieldsShouldHaveGetterRule(final String... aPackageNames)
    {
	return fieldsShouldHaveGetterRule(Maps.newHashMap(), aPackageNames);
    }

    public static ArchRule finalFieldsRule(final String aPackageName, final String... aExcludedPackages)
    {
	return ArchRuleDefinition.fields().that().areDeclaredInClassesThat()
				 .resideInAPackage(aPackageName).and().areDeclaredInClassesThat()
				 .resideOutsideOfPackages(aExcludedPackages)
				 .and().arePrivate().and()
				 .doNotHaveModifier(JavaModifier.SYNTHETIC).should().beFinal()
				 .because(String.format(
					 "Private attributes should be instanced by constructor classes, or it should be static in %s",
					 aPackageName));
    }
}
