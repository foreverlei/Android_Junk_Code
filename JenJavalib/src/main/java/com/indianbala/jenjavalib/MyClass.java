package com.indianbala.jenjavalib;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.lang.model.element.Modifier;
import org.apache.commons.lang3.RandomStringUtils;


public class MyClass {


    public static void main(String[] args){
        System.out.println("-----------");

//        MethodSpec main = MethodSpec.methodBuilder("main")
//                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
//                .returns(void.class)
//                .addParameter(String[].class, "args")
//                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
//                .build();

        String className = RandomStringUtils.randomAlphabetic(Util.methodNameLength);
        List<MethodSpec> methodSpecs = new ArrayList<MethodSpec>();

        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className);
        classBuilder.addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        methodSpecs.add(Util.genConstruct(classBuilder));

        Double.valueOf(7555).floatValue();
        Integer.valueOf(7938).toString();
        Integer.valueOf(4718).doubleValue();
        //
        methodSpecs.add(Util.genRandomStr1());


        classBuilder.addMethods(methodSpecs);
        TypeSpec helloWorld = classBuilder.build();

        JavaFile javaFile = JavaFile.builder(Util.packageName, helloWorld)
                .build();

        try {
            File javaDir = new File("./Gen/", "src");
            if (!javaDir.exists()) javaDir.mkdirs();
//            javaFile.writeTo(javaDir);
            javaFile.writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}