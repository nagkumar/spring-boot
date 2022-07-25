package com.tejasoft.sboot.archunit.service.test;

import com.tejasoft.sboot.archunit.test.utils.ArchUnitConsts;
import com.tejasoft.sboot.archunit.test.utils.structs.ClassesArchUnitRules;
import com.tejasoft.sboot.archunit.test.utils.structs.ConstructorsArchUnitRules;
import com.tejasoft.sboot.archunit.test.utils.structs.FieldsArchUnitRules;
import com.tejasoft.sboot.archunit.test.utils.structs.MethodsArchUnitRules;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.stereotype.Service;

@AnalyzeClasses(packages = ArchUnitConsts.DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
final class TestServiceRules
{
    // Classes
    @ArchTest
    static final ArchRule component_annotation_is_not_allowed = ClassesArchUnitRules.componentAnnotationIsNotAllowedRule(
	    ArchUnitConsts.SERVICE_PACKAGE);

    @ArchTest
    static final ArchRule classes_should_be_annotated =
	    ArchRuleDefinition.classes()
			      .that().resideInAPackage(ArchUnitConsts.SERVICE_PACKAGE).should()
			      .beAnnotatedWith(Service.class)
			      .because(String.format(
				      ArchUnitConsts.ANNOTATED_EXPLANATION,
				      ArchUnitConsts.SERVICE_SUFFIX,
				      "@Service"));

    // Fields
    @ArchTest
    static final ArchRule fields_should_not_be_public =
	    FieldsArchUnitRules.fieldsShouldNotBePublic(ArchUnitConsts.SERVICE_PACKAGE);

    // Constructors
    @ArchTest
    static final ArchRule constructors_should_not_be_private =
	    ConstructorsArchUnitRules.publicConstructorsRule(ArchUnitConsts.SERVICE_PACKAGE);

    // Methods
    @ArchTest
    static final ArchRule bean_methods_are_not_allowed =
	    MethodsArchUnitRules.beanMethodsAreNotAllowedRule(ArchUnitConsts.SERVICE_PACKAGE);

    @ArchTest
    static final ArchRule private_methods_are_not_allowed =
	    MethodsArchUnitRules.privateMethodsAreNotAllowedRule(ArchUnitConsts.SERVICE_PACKAGE);

    @ArchTest
    static final ArchRule static_methods_are_not_allowed =
	    MethodsArchUnitRules.staticMethodsAreNotAllowedRule(ArchUnitConsts.SERVICE_PACKAGE);
}