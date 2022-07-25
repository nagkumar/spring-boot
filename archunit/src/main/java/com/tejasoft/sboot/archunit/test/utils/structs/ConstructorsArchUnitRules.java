package com.tejasoft.sboot.archunit.test.utils.structs;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

public final class ConstructorsArchUnitRules
{
    //Constructors
    public static ArchRule publicConstructorsRule(final String aPackageName)
    {
	return ArchRuleDefinition.constructors().that().areDeclaredInClassesThat()
				 .resideInAPackage(aPackageName).and().areDeclaredInClassesThat()
				 .areNotAnonymousClasses().should().bePublic()
				 .because(String.format("Public constructors are only allowed in %s", aPackageName));
    }
}
