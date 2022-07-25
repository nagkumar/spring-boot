package com.tejasoft.sboot.archunit.service.test;

import com.tejasoft.sboot.archunit.utils.test.TestArchUnitConsts;
import com.tejasoft.sboot.archunit.utils.test.TestCommonRules;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.stereotype.Service;

@AnalyzeClasses(packages = TestArchUnitConsts.DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
final class TestServiceRules
{
    // Classes
    @ArchTest
    static final ArchRule component_annotation_is_not_allowed = TestCommonRules.componentAnnotationIsNotAllowedRule(
	    TestArchUnitConsts.SERVICE_PACKAGE);

    @ArchTest
    static final ArchRule classes_should_be_annotated =
	    ArchRuleDefinition.classes()
			      .that().resideInAPackage(TestArchUnitConsts.SERVICE_PACKAGE).should()
			      .beAnnotatedWith(Service.class)
			      .because(String.format(
				      TestArchUnitConsts.ANNOTATED_EXPLANATION,
				      TestArchUnitConsts.SERVICE_SUFFIX,
				      "@Service"));

    // Fields
    @ArchTest
    static final ArchRule fields_should_not_be_public =
	    TestCommonRules.fieldsShouldNotBePublic(TestArchUnitConsts.SERVICE_PACKAGE);

    // Constructors
    @ArchTest
    static final ArchRule constructors_should_not_be_private =
	    TestCommonRules.publicConstructorsRule(TestArchUnitConsts.SERVICE_PACKAGE);

    // Methods
    @ArchTest
    static final ArchRule bean_methods_are_not_allowed =
	    TestCommonRules.beanMethodsAreNotAllowedRule(TestArchUnitConsts.SERVICE_PACKAGE);

    @ArchTest
    static final ArchRule private_methods_are_not_allowed =
	    TestCommonRules.privateMethodsAreNotAllowedRule(TestArchUnitConsts.SERVICE_PACKAGE);

    @ArchTest
    static final ArchRule static_methods_are_not_allowed =
	    TestCommonRules.staticMethodsAreNotAllowedRule(TestArchUnitConsts.SERVICE_PACKAGE);
}