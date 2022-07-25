package com.tejasoft.sboot.archunit.utils.test.arch;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

import static com.tejasoft.sboot.archunit.utils.test.TestArchUnitConsts.DEFAULT_PACKAGE;

@AnalyzeClasses(packages = DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
final class TestCyclicDependencyRules
{
    @ArchTest
    public static final ArchRule noCyclesDependencies =
	    SlicesRuleDefinition.slices().matching("..(*)..").should().beFreeOfCycles();
}