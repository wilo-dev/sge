package dofi.sge.student.service;

import dofi.sge.student.entity.model.ParaleloEntity;
import dofi.sge.student.entity.request.ParaleloRequest;
import dofi.sge.student.entity.response.ParaleloResponse;
import dofi.sge.student.repository.ParaleloRepository;
import dofi.sge.util.entity.OutputEntity;
import dofi.sge.util.enums.MessageEnum;
import dofi.sge.util.enums.MessageParalelo;
import dofi.sge.util.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParaleloService {

    @Autowired
    private ParaleloRepository paraleloRepository;

    public OutputEntity<List<ParaleloResponse>> getAllParalelo() {
        OutputEntity<List<ParaleloResponse>> outPut = new OutputEntity<>();
        try {
            List<ParaleloEntity> paraleloEntities = paraleloRepository.findAll();
            if (paraleloEntities.isEmpty()) {
                throw new MyException(MessageEnum.IS_EMPTY.getCode(), MessageEnum.IS_EMPTY.getMensaje());
            }
            List<ParaleloResponse> paraleloResponses = paraleloEntities.stream().map(ParaleloResponse::new).collect(Collectors.toList());
            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), paraleloResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }

    public OutputEntity<String> createParalelo(ParaleloRequest data) {
        OutputEntity<String> outPut = new OutputEntity<>();
        List<ParaleloEntity> paralelo = paraleloRepository.findByParalelo(data.getParalelo());
        try {
            if (data.getParalelo().trim().isEmpty()) {
                throw new MyException(MessageEnum.NO_Empty_fields.getCode(), MessageEnum.NO_Empty_fields.getMensaje());
            }
            if (!paralelo.isEmpty()) {
                throw new MyException(MessageParalelo.PARALELO_UNIQUE.getCode(), MessageParalelo.PARALELO_UNIQUE.getMensaje());
            }
            ParaleloEntity paraleloEntity = new ParaleloEntity(data);
            paraleloRepository.save(paraleloEntity);
            return outPut.ok(MessageEnum.CREATE.getCode(), MessageEnum.CREATE.getMensaje(), null);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }


}
