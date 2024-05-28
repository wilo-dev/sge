package dofi.sge.student.service;

import dofi.sge.student.entity.model.ParcialEntity;
import dofi.sge.student.entity.model.ParcialItemEntity;
import dofi.sge.student.entity.model.QuimestreEntity;
import dofi.sge.student.entity.model.QuimestreItemEntity;
import dofi.sge.student.entity.request.ParcialItemRequest;
import dofi.sge.student.entity.request.ParcialRequest;
import dofi.sge.student.entity.response.ParcialItemResponse;
import dofi.sge.student.entity.response.ParcialResponse;
import dofi.sge.student.repository.ParcialItemRepository;
import dofi.sge.student.repository.ParcialRepository;
import dofi.sge.student.repository.QuimestreRepository;
import dofi.sge.util.entity.OutputEntity;
import dofi.sge.util.enums.MessageEnum;
import dofi.sge.util.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ParcialeItemService {

    @Autowired
    private ParcialItemRepository itemRepository;


    public OutputEntity<List<ParcialItemResponse>> getAllItemParciales() {
        OutputEntity<List<ParcialItemResponse>> outPut = new OutputEntity<>();
        try {
            List<ParcialItemEntity> parcialItemEntities = itemRepository.findAll();
            if (parcialItemEntities.isEmpty()) {
                throw new MyException(MessageEnum.IS_EMPTY.getCode(), MessageEnum.IS_EMPTY.getMensaje());
            }
            List<ParcialItemResponse> parcialResponses = parcialItemEntities.stream().map(ParcialItemResponse::new).collect(Collectors.toList());
            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), parcialResponses);
        } catch (MyException e) {
            log.error(e.getMessage());
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getLocalizedMessage());
            return outPut.error();
        }
    }

    public OutputEntity<String> createItemParciales(ParcialItemRequest data) {
        OutputEntity<String> outPut = new OutputEntity<>();
        List<ParcialItemEntity> itemQuimestre = itemRepository.findByNameParcial(data.getItemParcial());
        try {
            if (data.getItemParcial().trim().isEmpty()) {
                throw new MyException(MessageEnum.NO_Empty_fields.getCode(), MessageEnum.NO_Empty_fields.getMensaje());
            }
            if (!itemQuimestre.isEmpty()) {
                throw new MyException(MessageEnum.ITEM_UNIQUE.getCode(), MessageEnum.ITEM_UNIQUE.getMensaje());
            }
            ParcialItemEntity parcialItemEntity = new ParcialItemEntity(data);
            itemRepository.save(parcialItemEntity);
            return outPut.ok(MessageEnum.CREATE.getCode(), MessageEnum.CREATE.getMensaje(), null);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return outPut.error();
        }
    }


}
