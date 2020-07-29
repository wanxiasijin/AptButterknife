//package com.example.processor;
//
//import com.squareup.javapoet.ClassName;
//import com.squareup.javapoet.JavaFile;
//import com.squareup.javapoet.MethodSpec;
//import com.squareup.javapoet.TypeSpec;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.lang.model.element.ExecutableElement;
//import javax.lang.model.element.TypeElement;
//import javax.lang.model.element.VariableElement;
//
//import static javax.lang.model.element.Modifier.PUBLIC;
//
//
///**
// * author : tanyonglin
// * e-mail : 1760032445@qq.com
// * date   : 2020/7/11
// */
//public class ClassBuilder {
//    //包名
//    public String packageName ;
//    //类名
//    public String className ;
//    //类节点
//    public TypeElement mTypeElement;
//    //用于存放BindView标注的Field字段 1 - 1
//    public Map<Integer, VariableElement> idAndFdieldNames = new HashMap<>();
//    //用于存放OnClick标注的Method字段 1 - n
//    public Map<ExecutableElement, int[]> clickMethodNameAndIds = new HashMap<>();
//
//    public JavaFile buildJavaFile() {
//
//        ClassName activity = ClassName.bestGuess(mTypeElement.getQualifiedName().toString());
//        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("bind")
//                .addModifiers(PUBLIC)
//                .returns(void.class)
//                .addParameter(activity, "activity");
//
//        for (int id : idAndFdieldNames.keySet()) {
//            VariableElement element = idAndFdieldNames.get(id);
//            String fieldName = element.getSimpleName().toString();
//            String fieldType = element.asType().toString();
//            methodBuilder.addCode("activity." + fieldName + " = " + "(" + fieldType + ")(((android.app.Activity)activity).findViewById( " + id + "));");
//        }
//
//        TypeSpec helloWorld = TypeSpec.classBuilder(className)
//                .addModifiers(PUBLIC)
//                .addMethod(methodBuilder.build())
//                .build();
//
//        return JavaFile.builder(packageName, helloWorld)
//                .build();
//    }
//}