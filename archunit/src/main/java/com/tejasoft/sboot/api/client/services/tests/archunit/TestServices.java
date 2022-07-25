package com.tejasoft.sboot.api.client.services.tests.archunit;

import com.tejasoft.sboot.tests.archunit.utils.ArchUnitConsts;
import com.tejasoft.sboot.tests.archunit.utils.structs.ClassesRules;
import com.tejasoft.sboot.tests.archunit.utils.structs.ConstructorsRules;
import com.tejasoft.sboot.tests.archunit.utils.structs.FieldsRules;
import com.tejasoft.sboot.tests.archunit.utils.structs.MethodsRules;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.stereotype.Service;

@AnalyzeClasses(packages = ArchUnitConsts.DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
final class TestServices
{
    // Classes
    @ArchTest
    static final ArchRule component_annotation_is_not_allowed =
	    ClassesRules.componentAnnotationIsNotAllowedRule(ArchUnitConsts.SERVICE_PACKAGE);

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
	    FieldsRules.fieldsShouldNotBePublic(ArchUnitConsts.SERVICE_PACKAGE);

    // Constructors
    @ArchTest
    static final ArchRule constructors_should_not_be_private =
	    ConstructorsRules.publicConstructorsRule(ArchUnitConsts.SERVICE_PACKAGE);

    // Methods
    @ArchTest
    static final ArchRule bean_methods_are_not_allowed =
	    MethodsRules.beanMethodsAreNotAllowedRule(ArchUnitConsts.SERVICE_PACKAGE);

    @ArchTest
    static final ArchRule private_methods_are_not_allowed =
	    MethodsRules.privateMethodsAreNotAllowedRule(ArchUnitConsts.SERVICE_PACKAGE);

    @ArchTest
    static final ArchRule static_methods_are_not_allowed =
	    MethodsRules.staticMethodsAreNotAllowedRule(ArchUnitConsts.SERVICE_PACKAGE);
}