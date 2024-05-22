package dofi.sge.student.service;

import dofi.sge.student.entity.model.CoursesEntity;
import dofi.sge.student.entity.model.StudentEntity;
import dofi.sge.student.entity.request.CourseRequest;
import dofi.sge.student.entity.request.StudentRequest;
import dofi.sge.student.entity.response.CourseResponse;
import dofi.sge.student.repository.CourseRepository;
import dofi.sge.util.entity.OutputEntity;
import dofi.sge.util.enums.MessageCourse;
import dofi.sge.util.enums.MessageEnum;
import dofi.sge.util.exception.MyException;
import dofi.sge.util.helper.ValidationsHelpers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public OutputEntity<List<CourseResponse>> getAllCourse() {
        OutputEntity<List<CourseResponse>> outPut = new OutputEntity<>();
        try {
            List<CoursesEntity> courseEntities = courseRepository.findAll();
            if (courseEntities.isEmpty()) {
                throw new MyException(MessageEnum.IS_EMPTY.getCode(), MessageEnum.IS_EMPTY.getMensaje());
            }
            List<CourseResponse> courseResponses = courseEntities.stream().map(CourseResponse::new).collect(Collectors.toList());
            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), courseResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }

    public OutputEntity<String> createCourse(CourseRequest data) {
        OutputEntity<String> outPut = new OutputEntity<>();
        List<CoursesEntity> course = courseRepository.findByCourse(data.getCourse());
        log.info(data.toString());
        try {
            if (data.getCourse().trim().isEmpty()) {
                throw new MyException(MessageEnum.NO_Empty_fields.getCode(), MessageEnum.NO_Empty_fields.getMensaje());
            }
            if (!course.isEmpty()) {
                throw new MyException(MessageCourse.COURSE_UNIQUE.getCode(), MessageCourse.COURSE_UNIQUE.getMensaje());
            }
            CoursesEntity coursesEntity = new CoursesEntity(data);
            courseRepository.save(coursesEntity);
            return outPut.ok(MessageEnum.CREATE.getCode(), MessageEnum.CREATE.getMensaje(), null);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }

}
