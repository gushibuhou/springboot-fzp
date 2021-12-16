package com.jyp.springboot;

import javafx.scene.media.MediaPlayer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static cn.hutool.core.lang.Singleton.get;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @title: ApplicationTests类
 * @Author jiangyp
 * @Date: 2021/7/28 17:41
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
public class ApplicationTests {
    @Test
    public void contextLoads(){

    }

}  