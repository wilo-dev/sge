package dofi.sge.student.service;

import dofi.sge.student.entity.model.ParcialEntity;
import dofi.sge.student.entity.request.ParcialRequest;
import dofi.sge.student.entity.response.ParcialResponse;
import dofi.sge.student.repository.ParcialRepository;
import dofi.sge.util.entity.OutputEntity;
import dofi.sge.util.enums.MessageEnum;
import dofi.sge.util.enums.MessageParcial;
import dofi.sge.util.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ParcialesService {

    @Autowired
    private ParcialRepository parcialRepository;

    public OutputEntity<List<ParcialResponse>> getAllParciales() {
        OutputEntity<List<ParcialResponse>> outPut = new OutputEntity<>();
        try {
            List<ParcialEntity> parcialEntities = parcialRepository.findAll();
            if (parcialEntities.isEmpty()) {
                throw new MyException(MessageEnum.IS_EMPTY.getCode(), MessageEnum.IS_EMPTY.getMensaje());
            }
            List<ParcialResponse> parcialResponses = parcialEntities.stream().map(ParcialResponse::new).collect(Collectors.toList());
            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), parcialResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }

    public OutputEntity<String> createParciales(ParcialRequest data) {
        OutputEntity<String> outPut = new OutputEntity<>();
        List<ParcialEntity> parcial = parcialRepository.findByParcial(data.getNameParcial());
        try {
            if (data.getNameParcial().trim().isEmpty()) {
                throw new MyException(MessageEnum.NO_Empty_fields.getCode(), MessageEnum.NO_Empty_fields.getMensaje());
            }
            if (!parcial.isEmpty()) {
                throw new MyException(MessageParcial.PARCIAL_UNIQUE.getCode(), MessageParcial.PARCIAL_UNIQUE.getMensaje());
            }
            ParcialEntity parcialEntity = new ParcialEntity(data);
            parcialRepository.save(parcialEntity);
            return outPut.ok(MessageEnum.CREATE.getCode(), MessageEnum.CREATE.getMensaje(), null);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }

}
