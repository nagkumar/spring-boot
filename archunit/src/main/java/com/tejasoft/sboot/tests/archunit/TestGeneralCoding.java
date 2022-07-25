package com.tejasoft.sboot.tests.archunit;

import ch.qos.logback.classic.Logger;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.GeneralCodingRules;
import org.springframework.context.annotation.Bean;

import static com.tejasoft.sboot.tests.archunit.utils.ArchUnitConsts.DEFAULT_PACKAGE;

@AnalyzeClasses(packages = DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
final class TestGeneralCoding
{
    //Classes
    @ArchTest
    static final ArchRule noClassesShouldUseJodatime =
	    GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME.because("Use java.time objects instead");

    @ArchTest
    static final ArchRule noAccessToStandardStreams = GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

    @ArchTest
    static final ArchRule noGenericExceptions =
	    GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS
		    .because("Throw AlmundoException or any child of this instead");

    @ArchTest
    static final ArchRule noJavaUtilLogging =
	    GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING.because("Use org.slf4j.Logger instead");

    //Fields
    @ArchTest
    static final ArchRule loggersShouldBePrivateStaticAndFinal =
	    ArchRuleDefinition.fields().that().haveRawType(Logger.class)
			      .should().bePrivate()
			      .andShould().beStatic()
			      .andShould().beFinal()
			      .andShould().haveName("LOGGER")
			      .because(
				      "Logger variables should be private, static and final, and it should be named as LOGGER");

    @ArchTest
    static final ArchRule finalStaticVariablesInUppercase =
	    ArchRuleDefinition.fields().that().areStatic().and().areFinal()
			      .and().doNotHaveName("serialVersionUID")
			      .and().doNotHaveModifier(JavaModifier.SYNTHETIC)
			      .should().haveNameMatching(".*^[A-Z].*")
			      .because("Variables with static and final modifiers should be named in uppercase");

    //Methods
    @ArchTest
    static final ArchRule beanMethodsShouldBePublic =
	    ArchRuleDefinition.methods().that().areAnnotatedWith(Bean.class).should().bePublic()
			      .because("@Bean annotation does not work in non public methods");
}
