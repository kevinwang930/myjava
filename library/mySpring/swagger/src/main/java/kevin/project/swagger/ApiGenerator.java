package hw.activity.generator;


import io.swagger.codegen.ClientOptInput;
import io.swagger.codegen.DefaultGenerator;
import io.swagger.codegen.Generator;
import io.swagger.codegen.config.CodegenConfigurator;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class ApiGenerator {
    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        generateCode();
    }


    public static void generateCode() throws URISyntaxException, MalformedURLException {




        CodegenConfigurator configurator = new CodegenConfigurator()
//                .setSkipOverwrite(true)
                .setVerbose(true)
                .setLang("spring")
                .setLibrary("spring-boot")
                .setOutputDir(".")
                .setInputSpec("./src/main/resources/api.yaml")
                .setApiPackage("hw.activity.swagger.api")
                .setModelPackage("hw.activity.swagger.model")
                .addAdditionalProperty("dateLibrary", "java8")
                .addAdditionalProperty("interfaceOnly", "false")
                .addAdditionalProperty("title", "My API");
        System.setProperty("supportingFiles","false");
        System.setProperty("apiDocs","false");
        System.setProperty("generateApis","true");

        // Initialize the generator
        ClientOptInput clientOptInput = configurator.toClientOptInput();
        DefaultGenerator generator = new DefaultGenerator();
        generator.opts(clientOptInput).generate();
    }
}
