package com.tejasoft.sboot.archunit.model.test.archunit;

import com.tejasoft.sboot.archunit.test.archunit.utils.structs.FieldsRules;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.thirdparty.com.google.common.collect.Maps;

import static com.tejasoft.sboot.archunit.test.archunit.utils.ArchUnitConsts.DEFAULT_PACKAGE;
import static com.tejasoft.sboot.archunit.test.archunit.utils.ArchUnitConsts.MODEL_PACKAGE;
import static com.tejasoft.sboot.archunit.test.archunit.utils.CustomConditions.HAVE_EQUALS_AND_HASH_CODE;
import static com.tejasoft.sboot.archunit.test.archunit.utils.structs.ClassesRules.springAnnotationsClassesAreNotAllowedRule;
import static com.tejasoft.sboot.archunit.test.archunit.utils.structs.FieldsRules.publicAndFinalFieldsAreNotAllowedRule;
import static com.tejasoft.sboot.archunit.test.archunit.utils.structs.MethodsRules.methodsShouldBePublicRule;
import static com.tejasoft.sboot.archunit.test.archunit.utils.structs.MethodsRules.staticMethodsAreNotAllowedRule;

@AnalyzeClasses(packages = DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
final class TestModels
{
    //Classes
    @ArchTest
    static final ArchRule classesShouldOverrideEqualsAndHashCode =
	    ArchRuleDefinition.classes().that()
			      .resideInAnyPackage(MODEL_PACKAGE)
			      .and().areNotMemberClasses()
			      .should(HAVE_EQUALS_AND_HASH_CODE)
			      .because("Model classes should override equals and hashCode methods");

    @ArchTest
    static final ArchRule springAnnotationsAreNotAllowed = springAnnotationsClassesAreNotAllowedRule(MODEL_PACKAGE);

    //Fields
    @ArchTest
    static final ArchRule fieldsShouldHaveGetter =
	    FieldsRules.fieldsShouldHaveGetterRule(Maps.newHashMap(), MODEL_PACKAGE);

    @ArchTest
    static final ArchRule publicAndFinalFieldsAreNotAllowed = publicAndFinalFieldsAreNotAllowedRule(MODEL_PACKAGE);

    //Methods
    @ArchTest
    static final ArchRule methodsShouldBePublic = methodsShouldBePublicRule(MODEL_PACKAGE);

    @ArchTest
    static final ArchRule staticMethodsAreNotAllowed = staticMethodsAreNotAllowedRule(MODEL_PACKAGE);
}
