package dofi.sge.student.service;

import dofi.sge.student.entity.model.CoursesEntity;
import dofi.sge.student.entity.model.NotasEntity;
import dofi.sge.student.entity.request.CourseRequest;
import dofi.sge.student.entity.request.NotasRequest;
import dofi.sge.student.entity.response.CourseResponse;
import dofi.sge.student.entity.response.NotasResponse;
import dofi.sge.student.repository.CourseRepository;
import dofi.sge.student.repository.NotaRepository;
import dofi.sge.util.entity.OutputEntity;
import dofi.sge.util.enums.MessageCourse;
import dofi.sge.util.enums.MessageEnum;
import dofi.sge.util.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public OutputEntity<List<NotasResponse>> getNotas() {
        OutputEntity<List<NotasResponse>> outPut = new OutputEntity<>();
        try {
            List<NotasEntity> notaEntity = notaRepository.findAll();
            if (notaEntity.isEmpty()) {
                throw new MyException(MessageEnum.IS_EMPTY.getCode(), MessageEnum.IS_EMPTY.getMensaje());
            }
            List<NotasResponse> notaResponses = notaEntity.stream().map(NotasResponse::new).collect(Collectors.toList());
            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), notaResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }

    public OutputEntity<String> createNota(NotasRequest data) {
        OutputEntity<String> outPut = new OutputEntity<>();
        try {
            NotasEntity notasEntity = new NotasEntity(data);
            notaRepository.save(notasEntity);
            return outPut.ok(MessageEnum.CREATE.getCode(), MessageEnum.CREATE.getMensaje(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }

}
