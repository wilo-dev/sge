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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ParcialesService {

    @Autowired
    private ParcialRepository parcialRepository;

    @Autowired
    private QuimestreService quimestreService;


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
            log.error(e.getMessage());
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getLocalizedMessage());
            return outPut.error();
        }
    }

    public OutputEntity<String> createParciales(ParcialRequest data) {
        log.info("datos parcial: , {}", data);
        OutputEntity<String> outPut = new OutputEntity<>();
        try {
            if (data.getNameParcial().trim().isEmpty()) {
                throw new MyException(MessageEnum.NO_Empty_fields.getCode(), MessageEnum.NO_Empty_fields.getMensaje());
            }
            List<ParcialEntity> parcial = parcialRepository.findByParcial(data.getNameParcial());
            if (!parcial.isEmpty()) {
                throw new MyException(MessageParcial.PARCIAL_UNIQUE.getCode(), MessageParcial.PARCIAL_UNIQUE.getMensaje());
            }
            ParcialEntity parcialEntity = new ParcialEntity(data);
            saveOrUpdateParcial(parcialEntity);
//            parcialRepository.save(parcialEntity);
            return outPut.ok(MessageEnum.CREATE.getCode(), MessageEnum.CREATE.getMensaje(), null);
        } catch (MyException e) {
            log.error(e.getMessage());
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getLocalizedMessage());
            return outPut.error();
        }
    }

    public ParcialEntity saveOrUpdateParcial(ParcialEntity parcial) {
        log.info("parcialEntity, {}", parcial);
        parcial.setPromedioParcial(parcial.calcularPromedioParcial());
        ParcialEntity saveParcial = parcialRepository.save(parcial);
        quimestreService.actualizarPromedioQuimestral(parcial.getQuimestreId().getId());
        return saveParcial;

//        ParcialEntity saveParcial = parcialRepository.save(parcial);
//        saveParcial.setPromedioParcial(saveParcial.calcularPromedioParcial());
//        parcialRepository.save(saveParcial);
//        quimestreService.actualizarPromedioQuimestral(parcial.getQuimestreId().getId());
//        return saveParcial;

    }

}
