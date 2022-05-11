package com.chenwei.csust;

import com.chenwei.csust.originaldata.OriginalDataInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = CreditRiskControlApplication.class)
@RunWith(SpringRunner.class)
public class CreditRiskControlApplicationTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void dataTest()
    {
        OriginalDataInfo originalDataInfo = new OriginalDataInfo();
        originalDataInfo.readData();
    }
}
