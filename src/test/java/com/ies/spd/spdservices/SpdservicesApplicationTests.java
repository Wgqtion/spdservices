package com.ies.spd.spdservices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpdservicesApplicationTests {

    @Test
    public void contextLoads() {
        byte i = (byte) 0xbd;
        int j = (i << 33);
        System.out.println(0xbd^0x80);
        if((i & 0xff) == 128){
            System.out.println(j>>>33);
        }
        System.out.println((i & 0xff) == 0xff ? "qweqweqeq" : 0);
    }

}
