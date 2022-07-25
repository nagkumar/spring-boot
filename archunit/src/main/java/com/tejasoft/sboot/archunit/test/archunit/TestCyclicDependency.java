package com.tejasoft.sboot.archunit.test.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

import static com.tejasoft.sboot.archunit.test.archunit.utils.ArchUnitConsts.DEFAULT_PACKAGE;

@AnalyzeClasses(packages = DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
final class TestCyclicDependency
{
    @ArchTest
    public static final ArchRule noCyclesDependencies =
	    SlicesRuleDefinition.slices().matching("..(*)..").should().beFreeOfCycles();
}