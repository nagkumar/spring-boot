package com.tejasoft.sboot.tests.archunit.utils;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class CustomConditions
{
    private static final String HAVE_GETTER_DESCRIPTION = "have getter";
    private static final String HAVE_GETTER_AND_SETTER_DESCRIPTION = "have getter and setter";
    private static final String GETTER_OR_SETTER_NOT_PRESENT_ERROR_MESSAGE = "Field %s of %s does not have %s";
    private static final String GETTER_PREFIX = "get";
    private static final String IS_PREFIX = "is";
    private static final String SETTER_PREFIX = "set";
    private static final String BOOLEAN_TYPE = "boolean";

    private static final String EQUALS_AND_HASH_CODE_DESCRIPTION = "have equals and hashCode";
    public static final ArchCondition<JavaClass> HAVE_NOT_STATIC_METHODS = buildStaticMethodsAreNotAllowedCondition();
    private static final String EQUALS_OR_HASH_CODE_NOT_PRESENT_ERROR_MESSAGE = "%s not found in %s";
    private static final String EQUALS_METHOD = "equals";
    private static final String HASH_CODE_METHOD = "hashCode";
    public static final ArchCondition<JavaClass> HAVE_EQUALS_AND_HASH_CODE = buildClassHaveEqualsAndHashCodeCondition();

    private CustomConditions()
    {
    }

    public static ArchCondition<JavaField> haveGetter(Map<String, String> exclusions)
    {
	return buildFieldHaveGetterAndSetterCondition(false, exclusions);
    }

    public static ArchCondition<JavaField> haveGetterAndSetter(Map<String, String> exclusions)
    {
	return buildFieldHaveGetterAndSetterCondition(true, exclusions);
    }

    private static ArchCondition<JavaField> buildFieldHaveGetterAndSetterCondition(final boolean aForceSetters,
										   final Map<String, String> aExclusions)
    {
	return new ArchCondition<JavaField>(
		aForceSetters ? HAVE_GETTER_AND_SETTER_DESCRIPTION : HAVE_GETTER_DESCRIPTION)
	{
	    @Override
	    public void check(final JavaField aJavaField, final ConditionEvents aConditionEvents)
	    {
		Set<String> publicMethods = aJavaField.getOwner().getMethods().stream()
						      .filter(m -> m.getModifiers().contains(JavaModifier.PUBLIC))
						      .map(JavaMember::getName)
						      .collect(Collectors.toSet());

		String name = aJavaField.getName();

		if (aExclusions.containsKey(name))
		{
		    String className = aExclusions.get(name);
		    if (aJavaField.getOwner().getName().equals(className))
		    {
			return;
		    }
		}

		String lGetter = calculateGetterPrefix(aJavaField.reflect().getType().getName()) + capitalize(name);

		if (!publicMethods.contains(lGetter))
		{
		    String message = String
			    .format(GETTER_OR_SETTER_NOT_PRESENT_ERROR_MESSAGE, aJavaField.getName(),
				    aJavaField.getOwner().getName(),
				    GETTER_PREFIX);
		    aConditionEvents.add(SimpleConditionEvent.violated(aJavaField, message));
		}

		if (aForceSetters)
		{
		    String setter = SETTER_PREFIX + capitalize(name);

		    if (!publicMethods.contains(setter))
		    {
			String message =
				String.format(GETTER_OR_SETTER_NOT_PRESENT_ERROR_MESSAGE, aJavaField.getName(),
					      aJavaField.getOwner().getName(), SETTER_PREFIX);
			aConditionEvents.add(SimpleConditionEvent.violated(aJavaField, message));
		    }
		}
	    }
	};
    }

    private static ArchCondition<JavaClass> buildClassHaveEqualsAndHashCodeCondition()
    {
	return new ArchCondition<JavaClass>(EQUALS_AND_HASH_CODE_DESCRIPTION)
	{
	    @Override
	    public void check(JavaClass javaClass, ConditionEvents events)
	    {
		Optional<JavaMethod> equalsMethod = findPublicMethodFromClass(javaClass, EQUALS_METHOD);
		Optional<JavaMethod> hashCodeMethod = findPublicMethodFromClass(javaClass, HASH_CODE_METHOD);

		if (!equalsMethod.isPresent())
		{
		    events.add(SimpleConditionEvent.violated(javaClass,
							     String.format(
								     EQUALS_OR_HASH_CODE_NOT_PRESENT_ERROR_MESSAGE,
								     EQUALS_METHOD,
								     javaClass.getName())));
		}

		if (!hashCodeMethod.isPresent())
		{
		    events.add(SimpleConditionEvent.violated(javaClass,
							     String.format(
								     EQUALS_OR_HASH_CODE_NOT_PRESENT_ERROR_MESSAGE,
								     HASH_CODE_METHOD,
								     javaClass.getName())));
		}
	    }
	};
    }

    private static ArchCondition<JavaClass> buildStaticMethodsAreNotAllowedCondition()
    {
	return new ArchCondition<JavaClass>(EQUALS_AND_HASH_CODE_DESCRIPTION)
	{

	    @Override
	    public void check(JavaClass javaClass, ConditionEvents events)
	    {
		javaClass.getMethods().stream()
			 .filter(m -> m.getModifiers().contains(JavaModifier.STATIC))
			 .forEach(m -> SimpleConditionEvent.violated(javaClass, String
				 .format("Static method %s in %s is not allowed", m.getName(), javaClass.getName())));
	    }
	};
    }

    private static String capitalize(final String aValue)
    {
	return aValue.substring(0, 1).toUpperCase() + aValue.substring(1);
    }

    private static String calculateGetterPrefix(final String aType)
    {
	return !aType.equals(BOOLEAN_TYPE) ? GETTER_PREFIX : IS_PREFIX;
    }

    private static Optional<JavaMethod> findPublicMethodFromClass(final JavaClass aJavaClass, final String aMethodName)
    {
	return aJavaClass.getMethods().stream()
			 .filter(m -> m.getModifiers().contains(JavaModifier.PUBLIC) && aMethodName.equals(m.getName()))
			 .findFirst();
    }
}
