package dofi.sge.student.service;

import dofi.sge.student.entity.model.QuimestreItemEntity;
import dofi.sge.student.entity.request.QuimestreItemRequest;
import dofi.sge.student.entity.response.QuimestreItemResponse;
import dofi.sge.student.repository.QuimestreItemRepository;
import dofi.sge.util.entity.OutputEntity;
import dofi.sge.util.enums.MessageEnum;
import dofi.sge.util.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class QuimestreItemService {

    @Autowired
    private QuimestreItemRepository itemRepository;

    public OutputEntity<List<QuimestreItemResponse>> getAllItemQuimestres() {
        OutputEntity<List<QuimestreItemResponse>> outPut = new OutputEntity<>();
        try {
            List<QuimestreItemEntity> quimestreItemEntities = itemRepository.findAll();
            if (quimestreItemEntities.isEmpty()) {
                throw new MyException(MessageEnum.IS_EMPTY.getCode(), MessageEnum.IS_EMPTY.getMensaje());
            }
            List<QuimestreItemResponse> quimestreResponses = quimestreItemEntities.stream().map(QuimestreItemResponse::new).collect(Collectors.toList());
            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), quimestreResponses);
        } catch (MyException e) {
            log.error("Error de acceso a datos: ", e);
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return outPut.error();
        }
    }

    public OutputEntity<String> createItemQuimestre(QuimestreItemRequest data) {
        OutputEntity<String> outPut = new OutputEntity<>();
        List<QuimestreItemEntity> itemQuimestre = itemRepository.findByNameQuimestre(data.getItemQuimestre());

        try {
            if (data.getItemQuimestre().trim().isEmpty()) {
                throw new MyException(MessageEnum.NO_Empty_fields.getCode(), MessageEnum.NO_Empty_fields.getMensaje());
            }
            if (!itemQuimestre.isEmpty()) {
                throw new MyException(MessageEnum.ITEM_UNIQUE.getCode(), MessageEnum.ITEM_UNIQUE.getMensaje());
            }
            QuimestreItemEntity quimestreItemEntity = new QuimestreItemEntity(data);
            itemRepository.save(quimestreItemEntity);
            return outPut.ok(MessageEnum.CREATE.getCode(), MessageEnum.CREATE.getMensaje(), null);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return outPut.error();
        }
    }
}
