package wooteco.subway.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.ReflectionUtils;
import wooteco.subway.domain.Station;

public class StationDao {
    private static final String STATION_DUPLICATION = "이미 등록된 지하철 역입니다.";
    private static final String STATION_NOT_EXIST = "해당 지하철역은 존재하지 않습니다.";
    private static Long seq = 0L;
    private static List<Station> stations = new ArrayList<>();

    public static Station save(Station station) {
        validateDuplication(station);

        Station persistStation = createNewObject(station);
        stations.add(persistStation);
        return persistStation;
    }

    private static void validateDuplication(Station station) {
        if (stations.contains(station)) {
            throw new IllegalArgumentException(STATION_DUPLICATION);
        }
    }

    public static List<Station> findAll() {
        return stations;
    }

    public static void deleteById(Long id) {
        Station station = stations.stream()
                .filter(value -> value.isSameId(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(STATION_NOT_EXIST));

        stations.remove(station);
    }

    private static Station createNewObject(Station station) {
        Field field = ReflectionUtils.findField(Station.class, "id");
        field.setAccessible(true);
        ReflectionUtils.setField(field, station, ++seq);
        return station;
    }
}
