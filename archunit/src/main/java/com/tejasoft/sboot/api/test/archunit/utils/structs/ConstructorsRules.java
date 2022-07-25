package com.tejasoft.sboot.api.test.archunit.utils.structs;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

public final class ConstructorsRules
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
