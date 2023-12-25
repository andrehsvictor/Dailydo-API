package andrehsvictor.api.dailydo.util;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

public class ObjectMapper {
    private static ModelMapper mapper = new ModelMapper();

    public static <O, D> D map(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> mapList(List<O> origin, Class<D> destination) {
        List<D> destinationList = new ArrayList<>();
        origin.forEach((o) -> destinationList.add(map(o, destination)));
        return destinationList;
    }
}
