package com.spring.test.springtest.importtest;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author brink
 * 2021/9/10 17:32
 * 此类没有被装载进IOC容器里，但颜色有
 */
public class ColorImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {Blue.class.getName(), Green.class.getName(),SelectorConfiguration.class.getName()};
    }

}
