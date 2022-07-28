package com.tejasoft.sboot.tests.archunit.utils;

public interface ArchUnitConsts
{
    // Suffixes
    String CONTROLLER_SUFFIX = "Controller";
    String REPOSITORY_SUFFIX = "Repository";
    String SERVICE_SUFFIX = "Service";

    String[] TEST_PACKAGE = {"..tests.."};

    // Packages
    String CONTROLLER_PACKAGE = "..controllers..";
    String MODEL_PACKAGE = "..models..";
    String REPOSITORY_PACKAGE = "..repositories..";
    String SERVICE_PACKAGE = "..services..";
    String DTO_PACKAGE = "..dtos..";

    // Package to scan
    String DEFAULT_PACKAGE = "com.tejasoft.sboot.api";

    // Explanations
    String ANNOTATED_EXPLANATION = "Classes in %s package should be annotated with %s";
}
