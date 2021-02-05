package com.techatpark.lambda;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class MyArchitectureTest {

    @Test
     void some_architecture_rule() {
        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(new ImportOption.DoNotIncludeTests())
                .withImportOption(new ImportOption.DoNotIncludeJars())
                .importPackages("com.techatpark.lambda");
        ArchRule rule = classes().should().onlyAccessClassesThat()
                .resideInAnyPackage("java.lang"
                        ,"com.techatpark.lambda");
        rule.check(importedClasses);
    }
}
