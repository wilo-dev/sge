package dofi.sge.student.service;

import dofi.sge.student.entity.model.QuimestreEntity;
import dofi.sge.student.entity.model.StudentEntity;
import dofi.sge.student.entity.request.QuimestreRequest;
import dofi.sge.student.entity.response.QuimestreResponse;
import dofi.sge.student.repository.QuimestreRepository;
import dofi.sge.student.repository.StudentRepository;
import dofi.sge.util.entity.OutputEntity;
import dofi.sge.util.enums.MessageEnum;
import dofi.sge.util.enums.MessageQuimestre;
import dofi.sge.util.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class QuimestreService {

    @Autowired
    private QuimestreRepository quimestreRepository;

    @Autowired
    private StudentRepository studentRepository;

    public OutputEntity<List<QuimestreResponse>> getAllQuimestres() {
        OutputEntity<List<QuimestreResponse>> outPut = new OutputEntity<>();
        try {
            List<QuimestreEntity> courseEntities = quimestreRepository.findAll();
            if (courseEntities.isEmpty()) {
                throw new MyException(MessageEnum.IS_EMPTY.getCode(), MessageEnum.IS_EMPTY.getMensaje());
            }
            List<QuimestreResponse> quimestreResponses = courseEntities.stream().map(QuimestreResponse::new).collect(Collectors.toList());
            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), quimestreResponses);
        } catch (MyException e) {
            log.error("Error de acceso a datos: ", e);
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return outPut.error();
        }
    }

    public OutputEntity<String> createQuimestre(QuimestreRequest data) {
        OutputEntity<String> outPut = new OutputEntity<>();
        try {
            Optional<StudentEntity> existStudentById = studentRepository.findById(data.getStudentId());
            if (existStudentById.isEmpty()) {
                throw new MyException(404, "El estudiante con el ID especificado no existe");
            }

            if (data.getQuimestre().trim().isEmpty()) {
                throw new MyException(MessageEnum.NO_Empty_fields.getCode(), MessageEnum.NO_Empty_fields.getMensaje());
            }
//            List<QuimestreEntity> quimestre = quimestreRepository.findByQuimestre(data.getQuimestre());
//            if (!quimestre.isEmpty()) {
//                throw new MyException(MessageQuimestre.QUIMESTRE_UNIQUE.getCode(), MessageQuimestre.QUIMESTRE_UNIQUE.getMensaje());
//            }

            StudentEntity studentId = existStudentById.get();
            QuimestreEntity quimestreEntity = new QuimestreEntity(data, studentId);
            saveOrUpdateQuimestre(quimestreEntity);
            return outPut.ok(MessageEnum.CREATE.getCode(), MessageEnum.CREATE.getMensaje(), null);
        } catch (MyException e) {
            log.error("Error de acceso a datos: ", e);
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return outPut.error();
        }
    }

    public QuimestreEntity saveOrUpdateQuimestre(QuimestreEntity quimestre) {
        quimestre.setPromedioQuimestral(quimestre.calcularPromedioQuimestral());
        return quimestreRepository.save(quimestre);
    }

    public void actualizarPromedioQuimestral(Long quimestreId) {
        Optional<QuimestreEntity> quimestreOp = quimestreRepository.findById(quimestreId);
        if (quimestreOp.isPresent()) {
            QuimestreEntity quimestre = quimestreOp.get();
            quimestre.setPromedioQuimestral(quimestre.calcularPromedioQuimestral());
            quimestreRepository.save(quimestre);
        }
    }
}
