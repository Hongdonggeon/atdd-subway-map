package wooteco.subway.dto;

import wooteco.subway.domain.line.Line;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LineResponse {
    private Long id;
    private String name;
    private String color;
    private List<StationResponse> stations;

    public LineResponse() {
    }

    public LineResponse(Long id, String name, String color, List<StationResponse> stations) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.stations = stations;
    }

    public LineResponse(Long id, String name, String color) {
        this(id, name, color, new ArrayList<>());
    }

    public static LineResponse of(Line line, List<StationResponse> stations) {
        return new LineResponse(line.getId(), line.getName(), line.getColor(), stations);
    }

    public static LineResponse from(Line line) {
        return new LineResponse(line.getId(), line.getName(), line.getColor(), Collections.emptyList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<StationResponse> getStations() {
        return stations;
    }
}
