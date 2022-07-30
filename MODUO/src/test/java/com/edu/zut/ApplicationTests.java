package com.edu.zut;

import com.edu.zut.entity.Dictionary;
import com.edu.zut.mapper.DictionaryMapper;
import com.edu.zut.mapper.LogTitleMapper;
import com.edu.zut.mapper.MessageMapper;
import com.edu.zut.mapper.UserBasicMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class ApplicationTests {

    @Resource
    private MessageMapper messageMapper;
    @Resource
    private UserBasicMapper userBasicMapper;
    @Resource
    private LogTitleMapper logTitleMapper;
    @Resource
    private DictionaryMapper dictionaryMapper;

    @Test
    void contextLoads() {
        SimpleDateFormat forma=new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
        Date date=new Date(2030,11,23,11,11,1);
        Date date2=new Date(2030,11,23,11,13,27);

        System.out.println((date2.getTime()-date.getTime())/1000/60);
    }
    @Test
    public  void test3(){
        System.out.println("411525239911010924".matches("^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$"));
    }

    @Test
    public void test4(){
//
//        String lastTid="topic_12";
//        int index =lastTid.indexOf("_");
//        String substring = all.get(0).getTid().substring(index + 1);
//        int next=Integer.parseInt(substring)+1;
    }

    @Test
    public void test00(){
        Dictionary ga = dictionaryMapper.findByDname("游戏");
        System.out.println(ga.toString());
    }
}
