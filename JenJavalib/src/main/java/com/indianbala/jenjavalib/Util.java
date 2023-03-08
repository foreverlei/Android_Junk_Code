package com.indianbala.jenjavalib;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;
import java.util.UUID;

import javax.lang.model.element.Modifier;


public class Util {
    public static String packageName = "com.example.helloworld";
    public  static  String ClassName = "";
    public static int methodNameLength = 10;

    public static String[] randomValues = new String[]{
            "Double.valueOf(%d).equals(\"%d\")",
            "Double.valueOf(%d).intValue()", //1
            "Double.valueOf(%d).floatValue()",
            "Double.valueOf(%d).doubleValue()",
            "Double.valueOf(%d).toString()",
            "Double.valueOf(%d).hashCode()",
            "new Double(%d).intValue()", //6
            "new Double(%d).floatValue()",
            "new Double(%d).doubleValue()",
            "new Double(%d).toString()",
            "new Double(%d).hashCode()",
            "Float.valueOf(%d).equals(\"%d\")",
            "Float.valueOf(%d).intValue()", //1
            "Float.valueOf(%d).floatValue()",
            "Float.valueOf(%d).doubleValue()",
            "Float.valueOf(%d).toString()",
            "Float.valueOf(%d).hashCode()",
            "new Float(%d).intValue()", //6
            "new Float(%d).floatValue()",
            "new Float(%d).doubleValue()",
            "new Float(%d).toString()",
            "new Float(%d).hashCode()",
            "Integer.valueOf(%d).equals(\"%d\")",
            "Integer.valueOf(%d).intValue()", //1
            "Integer.valueOf(%d).floatValue()",
            "Integer.valueOf(%d).doubleValue()",
            "Integer.valueOf(%d).toString()",
            "Integer.valueOf(%d).hashCode()",
//            "new Integer(%d).intValue()", //6
//            "new Integer(%d).floatValue()",
//            "new Integer(%d).doubleValue()",
//            "new Integer(%d).toString()",
//            "new Integer(%d).hashCode()",
            "String.valueOf(%d).equals(\"%d\")",
//            "\"%s\".getBytes().toString()",
    };

    public static MethodSpec genConstruct(TypeSpec.Builder classBuilder){

        String fileName = RandomStringUtils.randomAlphabetic(methodNameLength);
        classBuilder.addField(String.class,fileName,Modifier.PRIVATE);
        MethodSpec flux = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(String.class, fileName)
                .addStatement("this.$N = $N", fileName, fileName)
                .build();
        return flux;
    }

//    public static MethodSpec computeRange(String name, int from, int to, String op) {
//        return MethodSpec.methodBuilder(name)
//                .returns(int.class)
//                .addStatement("int result = 1")
//                .beginControlFlow("for (int i = " + from + "; i < " + to + "; i++)")
//                .addStatement("result = result " + op + " i")
//                .endControlFlow()
//                .addStatement("return result")
//                .build();
//    }

    public static MethodSpec computeRange2(String name, int from, int to, String op) {
        return MethodSpec.methodBuilder(name)
                .returns(int.class)
                .addStatement("int result = 0")
                .beginControlFlow("for (int i = $L; i < $L; i++)", from, to)
                .addStatement("result = result $L i", op)
                .endControlFlow()
                .addStatement("return result")
                .build();
    }

    public static MethodSpec genSysOutPrint(){
        MethodSpec main = MethodSpec.methodBuilder(RandomStringUtils.randomAlphabetic(methodNameLength))
                .addStatement("long now = $T.currentTimeMillis()", System.class)
                .beginControlFlow("if ($T.currentTimeMillis() < now)", System.class)
                .addStatement("$T.out.println($S)", System.class, RandomStringUtils.randomAlphanumeric(40,100) + UUID.randomUUID().toString())
                .nextControlFlow("else if ($T.currentTimeMillis() == now)", System.class)
                .addStatement("$T.out.println($S)", System.class, RandomStringUtils.randomAlphanumeric(40,100))
                .nextControlFlow("else")
                .addStatement("$T.out.println($S)", System.class, RandomStringUtils.randomAlphanumeric(40,100))
                .endControlFlow()
                .build();
        return main;
    }

    public static MethodSpec genTryCatch(){
        MethodSpec main = MethodSpec.methodBuilder(RandomStringUtils.randomAlphabetic(methodNameLength))
                .beginControlFlow("try")
                .addStatement("throw new Exception($S)", RandomStringUtils.randomAlphabetic(methodNameLength))
                .nextControlFlow("catch ($T e)", Exception.class)
                .addStatement("throw new $T(e)", RuntimeException.class)
                .endControlFlow()
                .build();
        return main;
    }

    public static MethodSpec genRandomStr1(){

        MethodSpec.Builder main = MethodSpec.methodBuilder(RandomStringUtils.randomAlphabetic(methodNameLength));
        main.addModifiers(Modifier.PUBLIC);

        java.util.Random random = new java.util.Random();
        int count = random.nextInt(10);
        for(int i = 0 ; i <count;i++){
            int index = random.nextInt(randomValues.length);
            if (i % 3 == 0){
                main.addStatement(String.format("\"%s\".getBytes().toString()",RandomStringUtils.randomAlphanumeric(methodNameLength*2)));
            }
            main.addStatement(randomValues[index].replaceAll("%d",String.valueOf(random.nextInt(9999)) ));
        }

        genRandomBlockIf(main);

        return main.build();
    }

    public static MethodSpec genRandomPublicStr2(){

        MethodSpec.Builder main = MethodSpec.methodBuilder(RandomStringUtils.randomAlphabetic(methodNameLength));
        main.addModifiers(Modifier.PUBLIC);

        java.util.Random random = new java.util.Random();
        int count = random.nextInt(10);
        for(int i = 0 ; i <count;i++){
            int index = random.nextInt(randomValues.length);
            if (i % 3 == 0){
                main.addStatement(String.format("\"%s\".getBytes().toString()",RandomStringUtils.randomAlphanumeric(methodNameLength*2)));
            }
            main.addStatement(randomValues[index].replaceAll("%d",String.valueOf(random.nextInt(9999)) ));
        }

        return main.build();
    }

    public static MethodSpec.Builder genRandomBlockLine(MethodSpec.Builder main){

        java.util.Random random = new java.util.Random();
        int count = random.nextInt(10);
        for(int i = -1 ; i <count;i++){
            int index = random.nextInt(randomValues.length);
            if (i % 3 == 0){
                main.addStatement(String.format("\"%s\".getBytes().toString()",RandomStringUtils.randomAlphanumeric(methodNameLength*2)));
            }
            main.addStatement(randomValues[index].replaceAll("%d",String.valueOf(random.nextInt(9999)) ));
        }

        return main;
    }

    public static MethodSpec.Builder genRandomBlockIf(MethodSpec.Builder main){
        java.util.Random random = new java.util.Random();
        int count = random.nextInt(100);
        if (count < 50){

            main.beginControlFlow("if ($L > $L) ",String.format("Double.valueOf(%d).intValue()",random.nextInt(9999)),
                    String.format("Float.valueOf(%d).intValue()",random.nextInt(9999)));
            genRandomBlockLine(main);

            if (count < 25){
                //try catch
                genRandomBlockTryCatch(main);
            }

            main.endControlFlow();
        }else if (count < 100){
            main.beginControlFlow("if ($L >= $L) ",String.format("Float.valueOf(%d).intValue()",random.nextInt(9999)),
                    String.format("Double.valueOf(%d).intValue()",random.nextInt(9999)));
            genRandomBlockLine(main);
            main.nextControlFlow("else if ($L <= $L)",String.format("Float.valueOf(%d).intValue()",random.nextInt(9999)),
                    String.format("Double.valueOf(%d).intValue()",random.nextInt(9999)));
            if (count < 75){
                //try catch
                genRandomBlockTryCatch(main);
            }
            genRandomBlockLine(main);
            main.endControlFlow();
        }


        return main;
    }

    public static MethodSpec.Builder genRandomBlockTryCatch(MethodSpec.Builder main){
        main.beginControlFlow("try");
        genRandomBlockLine(main);
        main.nextControlFlow("catch ($T e)", Exception.class);
        main.addStatement("throw new $T(e)", RuntimeException.class);
        main.endControlFlow();
        return main;
    }

}
