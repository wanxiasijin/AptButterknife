//package com.example.processor;
//
//import com.example.annotation.BindView;
//import com.google.auto.service.AutoService;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.LinkedHashSet;
//import java.util.Map;
//import java.util.Set;
//
//import javax.annotation.processing.AbstractProcessor;
//import javax.annotation.processing.ProcessingEnvironment;
//import javax.annotation.processing.Processor;
//import javax.annotation.processing.RoundEnvironment;
//import javax.lang.model.element.Element;
//import javax.lang.model.element.TypeElement;
//import javax.lang.model.element.VariableElement;
//import javax.lang.model.util.Elements;
//
///**
// * author : tanyonglin
// * e-mail : 1760032445@qq.com
// * date   : 2020/7/11
// */
//@AutoService(Processor.class)
//public class AnnotationCompiler extends AbstractProcessor {
//
//    //用于节点的工具
//    private Elements elementUtils;
//    //存放需要生成的类
//    private Map<String, ClassBuilder> classes = new HashMap<>();
//
//    @Override
//    public synchronized void init(ProcessingEnvironment processingEnvironment) {
//        super.init(processingEnvironment);
//        elementUtils = processingEnvironment.getElementUtils();
//    }
//
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        Set<String> supportTypes = new LinkedHashSet<>();
//        supportTypes.add(BindView.class.getCanonicalName());
//        return supportTypes;
//    }
//
//    @Override
//    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
//
//        //处理BindView的注解
//        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);
//        for (Element element : elements) {
//            //这里由于BindView注解只能标注在Field上，所以直接转为VariableElement
//            VariableElement variableElement = (VariableElement) element;
//            //首先得到类节点
//            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
//            //得到类名
//            String className = typeElement.getSimpleName().toString() + "_BindView";
//            //得到包名
//            String packageName = elementUtils.getPackageOf(typeElement).getQualifiedName().toString();
//            //得到唯一标识符  类全路径
//            String fullName = className + packageName;
//            //创建需要生成的类对象
//            ClassBuilder builder = classes.get(fullName);
//            if (builder == null) {
//                builder = new ClassBuilder();
//                classes.put(fullName, builder);
//                builder.className = className;
//                builder.packageName = packageName;
//                builder.mTypeElement = typeElement;
//            }
//
//            //得到字段值
//            BindView bindAnnotation = variableElement.getAnnotation(BindView.class);
//            int id = bindAnnotation.value();
//            builder.idAndFdieldNames.put(id, variableElement);
//        }
//
//        for (String key : classes.keySet()) {
//            ClassBuilder builder = classes.get(key);
//            try {
//                //　生成文件
//                builder.buildJavaFile().writeTo(processingEnv.getFiler());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return true;
//    }
//}