package dofi.sge.student.service;

import dofi.sge.student.entity.model.NotasEntity;
import dofi.sge.student.entity.model.ParcialEntity;
import dofi.sge.student.entity.request.NotasRequest;
import dofi.sge.student.entity.response.NotasResponse;
import dofi.sge.student.repository.NotaRepository;
import dofi.sge.student.repository.ParcialRepository;
import dofi.sge.util.entity.OutputEntity;
import dofi.sge.util.enums.MessageEnum;
import dofi.sge.util.exception.MyException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private ParcialRepository parcialRepository;

    @Autowired
    private QuimestreService quimestreService;


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
        } catch (Exception e) {
            log.error(e.getMessage());
            return outPut.error();
        }
    }

    public OutputEntity<String> createNota(NotasRequest data) {
        log.info("data, {}", data);
        OutputEntity<String> outPut = new OutputEntity<>();
        try {
            // obteniendo el parcialEntity correspondiente
            Optional<ParcialEntity> parcialOptional = parcialRepository.findById(data.getParcialId());

            if (parcialOptional.isEmpty()) {
                throw new MyException(404, "El parcial con el ID especificado no existe");
            }
            ParcialEntity parcial = parcialOptional.get();

            // Crea la nota
            NotasEntity notasEntity = new NotasEntity(data, parcial);
            notaRepository.save(notasEntity);

            // actualizar el promedio parcial
            // ParcialEntity parcial = notasEntity.getParcialId();
            log.info("parcial, {}", parcial);
            parcial.setPromedioParcial(parcial.calcularPromedioParcial());
            parcialRepository.save(parcial);

            // Actualicar el promedio quimestre
            quimestreService.actualizarPromedioQuimestral(parcial.getQuimestreId().getId());

            return outPut.ok(MessageEnum.CREATE.getCode(), MessageEnum.CREATE.getMensaje(), null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return outPut.error();
        }
    }

}
