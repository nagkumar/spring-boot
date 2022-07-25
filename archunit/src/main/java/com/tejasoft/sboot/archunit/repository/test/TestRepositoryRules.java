package com.tejasoft.sboot.archunit.repository.test;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.stereotype.Repository;

import static com.tejasoft.sboot.archunit.test.utils.ArchUnitConsts.ANNOTATED_EXPLANATION;
import static com.tejasoft.sboot.archunit.test.utils.ArchUnitConsts.DEFAULT_PACKAGE;
import static com.tejasoft.sboot.archunit.test.utils.ArchUnitConsts.REPOSITORY_PACKAGE;
import static com.tejasoft.sboot.archunit.test.utils.ArchUnitConsts.REPOSITORY_SUFFIX;
import static com.tejasoft.sboot.archunit.test.utils.structs.ClassesArchUnitRules.interfacesAreOnlyAllowedRule;

@AnalyzeClasses(packages = DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
final class TestRepositoryRules
{
    // Classes
    @ArchTest
    static final ArchRule classes_should_be_annotated =
	    ArchRuleDefinition.classes()
			      .that().resideInAPackage(REPOSITORY_PACKAGE).should()
			      .beAnnotatedWith(Repository.class)
			      .because(String.format(ANNOTATED_EXPLANATION,
						     REPOSITORY_SUFFIX,
						     "@Repository"));
    @ArchTest
    static final ArchRule classesShouldBeInterfaces = interfacesAreOnlyAllowedRule(REPOSITORY_PACKAGE);
}
