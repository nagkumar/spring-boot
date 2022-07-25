package com.tejasoft.sboot.api.tests.archunit.utils;

public final class ArchUnitConsts
{
    // Suffixes
    public static final String CONTROLLER_SUFFIX = "Controller";
    public static final String REPOSITORY_SUFFIX = "Repository";
    public static final String SERVICE_SUFFIX = "Service";

    public static final String[] TEST_PACKAGE = {"..tests.."};

    // Packages
    public static final String CONTROLLER_PACKAGE = "..controllers..";
    public static final String MODEL_PACKAGE = "..models..";
    public static final String REPOSITORY_PACKAGE = "..repositories..";
    public static final String SERVICE_PACKAGE = "..services..";
    public static final String DTO_PACKAGE = "..dtos..";

    // Package to scan
    public static final String DEFAULT_PACKAGE = "com.tejasoft.sboot.api";

    // Explanations
    public static final String ANNOTATED_EXPLANATION = "Classes in %s package should be annotated with %s";

    private ArchUnitConsts()
    {
    }
}
