package wooteco.subway.line;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wooteco.subway.exception.DuplicateLineException;
import wooteco.subway.exception.NoSuchLineException;
import wooteco.subway.exception.NoSuchStationException;
import wooteco.subway.section.SectionDao;
import wooteco.subway.section.SectionService;
import wooteco.subway.station.Station;
import wooteco.subway.station.StationDao;
import wooteco.subway.station.StationService;

@Service
@Transactional
public class LineService {

    private final LineDao lineDao;

    private final SectionService sectionService;

    @Autowired
    public LineService(LineDao lineDao, SectionService sectionService) {
        this.lineDao = lineDao;
        this.sectionService = sectionService;
    }

    public Line createLine(Line line) {
        try {
            long lineId = lineDao.save(line);
            return new Line(lineId, line);
        } catch (DataAccessException e) {
            throw new DuplicateLineException();
        }
    }

    public List<Line> showLines() {
        return lineDao.findAll();
    }

    public Line showLine(long id) {
        try {
            Line line = lineDao.findById(id);
            return new Line(line, sectionService.makeOrderedStations(id));
        } catch (DataAccessException e) {
            throw new NoSuchLineException();
        }
    }

    public void updateLine(long id, Line line) {
        if (lineDao.update(id, line) != 1) {
            throw new NoSuchLineException();
        }
    }

    public void deleteLine(long id) {
        if (lineDao.delete(id) != 1) {
            throw new NoSuchLineException();
        }
    }
}
