package shevtsov.denis.calculator_api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import shevtsov.denis.calculator_api.Entity.Record;
import shevtsov.denis.calculator_api.Entity.UserInfo;
import shevtsov.denis.calculator_api.Repository.RecordRepository;
import shevtsov.denis.calculator_api.Service.RecordService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RecordServiceTest {

    @Mock
    RecordRepository recordRepository;

    RecordService recordService;

    List<Record> recordList;


    @Before
    public void initData() {
        recordService = new RecordService();
        recordService.setRecordRepository(recordRepository);

        UserInfo userInfo = new UserInfo(1, "user1", "user1");
        recordList = new ArrayList<>();
        recordList.add(new Record(1, "1+2", "3", new Date(), userInfo));
        recordList.add(new Record(1, "1/0", "Dividing by zero", new Date(), userInfo));
    }

    @Test
    public void saveRecordTest() {
        recordService.saveRecord("1+2", "3");
        Mockito.verify(recordRepository).save(Mockito.any());
    }

    @Test
    public void findByExpressionTest_oneRecordExpected() {
        List<Record> expectedList = new ArrayList<>(recordList);
        expectedList.remove(1);
        Mockito.when(recordRepository.findRecordsByExpression("1+2")).thenReturn(expectedList);

        List<Record> actualList = recordService.findByExpression("1+2");

        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void findByDateTest_recordListExpected() {
        Mockito.when(
                recordRepository.findRecordsByTimestampBetween(Mockito.any(), Mockito.any()))
                .thenReturn(recordList);

        List<Record> actualList = recordService.findByDate(new Date(), new Date());

        Assert.assertEquals(recordList, actualList);
    }

    @Test
    public void findByUserTest_recordListExpected() {
        Mockito.when(
                recordRepository.findRecordsByUserInfoUsername("user1"))
                .thenReturn(recordList);

        List<Record> actualList = recordService.findByUsername("user1");

        Assert.assertEquals(recordList, actualList);
    }

}
