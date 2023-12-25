package andrehsvictor.api.dailydo.util;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class EntityUpdater {
    
    public static <O, D> D updatePartialy(O origin, D destination) {
        BeanUtils.copyProperties(origin, destination, getNullProperties(origin));
        return destination;
    }

    private static <O> String[] getNullProperties(O object) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(object);
        return Stream.of(beanWrapper.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter((property) -> beanWrapper.getPropertyValue(property) == null)
                .toArray(String[]::new);
    }
}
