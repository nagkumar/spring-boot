package com.tejasoft.sboot.archunit.utils.test;

public final class TestArchUnitConsts
{
    // Suffixes
    public static final String CONTROLLER_SUFFIX = "Controller";
    public static final String REPOSITORY_SUFFIX = "Repository";
    public static final String SERVICE_SUFFIX = "Service";

    public static final String[] TEST_PACKAGE = {"..test.."};

    // Packages
    public static final String CONTROLLER_PACKAGE = "..controller..";
    public static final String MODEL_PACKAGE = "..model..";
    public static final String DTO_PACKAGE = "..dto..";
    public static final String REPOSITORY_PACKAGE = "..repository..";
    public static final String SERVICE_PACKAGE = "..service..";


    // Package to scan
    public static final String DEFAULT_PACKAGE = "com.example.archunit";

    // Explanations
    public static final String ANNOTATED_EXPLANATION = "Classes in %s package should be annotated with %s";

    private TestArchUnitConsts()
    {
    }
}