package dofi.sge.student.service;

import dofi.sge.student.entity.model.StudentEntity;
import dofi.sge.student.entity.request.StudentRequest;
import dofi.sge.student.entity.response.StudentResponse;
import dofi.sge.student.repository.StudentRepository;
import dofi.sge.util.entity.OutputEntity;
import dofi.sge.util.enums.MessageEnum;
import dofi.sge.util.exception.MyException;
import dofi.sge.util.helper.MethodHelper;
import dofi.sge.util.helper.ValidationsHelpers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public OutputEntity<List<StudentResponse>> getAllStudents() {
        OutputEntity<List<StudentResponse>> outPut = new OutputEntity<>();
        try {
            List<StudentEntity> studentEntities = studentRepository.findAll();
            if (studentEntities.isEmpty()) {
                throw new MyException(MessageEnum.IS_EMPTY.getCode(), MessageEnum.IS_EMPTY.getMensaje());
            }
            List<StudentResponse> studentResponses = studentEntities.stream().map(StudentResponse::new).collect(Collectors.toList());
            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), studentResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }

    public OutputEntity<List<StudentResponse>> getStatusStudent(boolean status) {
        OutputEntity<List<StudentResponse>> outPut = new OutputEntity<>();
        try {
            List<StudentEntity> studentEntities = studentRepository.findByStatus(status);
            if (studentEntities.isEmpty()) {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
            List<StudentResponse> studentResponses = studentEntities.stream().map(StudentResponse::new).collect(Collectors.toList());
            log.info(studentResponses.toString());
            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), studentResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }


    public OutputEntity<List<StudentResponse>> getAllEnableStudent() {
        OutputEntity<List<StudentResponse>> outPut = new OutputEntity<>();
        try {
            List<StudentEntity> studentEntities = studentRepository.findByStatusTrue();
            if (studentEntities.isEmpty()) {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
            List<StudentResponse> studentResponses = studentEntities.stream().map(StudentResponse::new).collect(Collectors.toList());
            log.info(studentResponses.toString());
            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), studentResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }

    public OutputEntity<List<StudentResponse>> getAllDisabledStudent() {
        OutputEntity<List<StudentResponse>> outPut = new OutputEntity<>();
        try {
            List<StudentEntity> studentEntities = studentRepository.findByStatusFalse();
            if (studentEntities.isEmpty()) {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
            List<StudentResponse> studentResponses = studentEntities.stream().map(StudentResponse::new).collect(Collectors.toList());
            log.info(studentResponses.toString());
            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), studentResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }

    public OutputEntity<StudentResponse> getStudentById(Long id) {
        OutputEntity<StudentResponse> outPut = new OutputEntity<>();
        try {
            Optional<StudentEntity> studentEntities = studentRepository.findByIdAndStatus(id, true);
            if (studentEntities.isPresent()) {
                StudentResponse studentResponse = new StudentResponse(studentEntities.get());
                return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), studentResponse);
            } else {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }

    public OutputEntity<String> createStudent(StudentRequest data) {
        OutputEntity<String> outPut = new OutputEntity<>();
        try {
//            TODO: validar q los campos no esten vacios
//            if (data.toString().trim().isEmpty())
//                throw new MyException(MessageEnum.NO_Empty_fields.getCode(), MessageEnum.NO_Empty_fields.getMensaje());

            // TODO: validar correo
            if (!ValidationsHelpers.isEmail(data.getEmail()))
                throw new MyException(MessageEnum.CORREO_NO_VALIDO.getCode(), MessageEnum.CORREO_NO_VALIDO.getMensaje());

            // TODO: correo unico
            List<StudentEntity> email = studentRepository.findByEmail(data.getEmail());
            if (!email.isEmpty()) {
                throw new MyException(MessageEnum.CORREO_UNICO.getCode(), MessageEnum.CORREO_UNICO.getMensaje());
            }
            StudentEntity studentEntity = new StudentEntity(data);
            // TODO: save
            studentRepository.save(studentEntity);
            return outPut.ok(MessageEnum.CREATE.getCode(), MessageEnum.CREATE.getMensaje(), null);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }

    public OutputEntity<String> deleteStudent(Long id) {
        OutputEntity<String> outPut = new OutputEntity<>();
        try {
            Optional<StudentEntity> existStudent = studentRepository.findByIdAndStatus(id, true);
            if (existStudent.isPresent()) {
                StudentEntity studentStatus = existStudent.get();
                studentStatus.setStatus(false);
                studentRepository.save(studentStatus);
                return outPut.ok(MessageEnum.DELETE.getCode(), MessageEnum.DELETE.getMensaje(), null);
            } else {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            return outPut.error();
        }
    }

    public OutputEntity<String> updateStudent(StudentRequest data, Long id) {
        OutputEntity<String> outPut = new OutputEntity<>();
        try {
            Optional<StudentEntity> existStudent = studentRepository.findByIdAndStatus(id, true);
            if (existStudent.isPresent()) {

                // TODO: update data
                StudentEntity student = existStudent.get();
                student.updateData(data);
                studentRepository.save(student);
                return outPut.ok(MessageEnum.UPDATE.getCode(), MessageEnum.UPDATE.getMensaje(), null);
            } else {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            return outPut.error();
        }
    }

    public OutputEntity<String> updateStudentEnabled(StudentRequest data, Long id) {
        OutputEntity<String> outPut = new OutputEntity<>();
        try {
            Optional<StudentEntity> existStudent = studentRepository.findByIdAndStatus(id, false);
            if (existStudent.isPresent()) {
                // TODO: update data
                StudentEntity student = existStudent.get();
                student.updateDataStatus(data.getStatus());
                studentRepository.save(student);
                return outPut.ok(MessageEnum.UPDATE.getCode(), MessageEnum.UPDATE.getMensaje(), null);
            } else {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            return outPut.error();
        }
    }

//    public OutputEntity<List<StudentResponse>> getCourseStudent(String course) {
//        return
//    }
}
/* *
 *
 * getStudentById => OutputEntity<StudentResponse> = solo retorno una peticion de tipo StudentResponse
 * getAllEnableStudent => OutputEntity<List<StudentResponse>> retorna listado de estudiantes habilitados
 * getAllStudents => OutputEntity<List<StudentResponse>> retorna listado de todos los estudiantes
 * createStudent => OutputEntity<string> = retornar un mensaje de creado o no creado
 * deleteStudent => OutputEntity<string> = retornar un mensaje de eliminado o no eliminado
 * updateStudent => OutputEntity<string> = retornar un mensaje de actualizado o no actualizado
 *
 * */

