//package com.gq.courfersbm;
//
//import com.gq.jkl.manage.JklUserService;
//import com.gq.util.BaiduUtil;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@WebAppConfiguration //必须加上该注解
//public class CourferSbmApplicationTests {
//    @Autowired
//    JklUserService userService;
//
//    @Autowired
//    BridageUUserAddressMapper mapper;
//
//    @Before
//    public void init() {
//        System.out.println("开始测试-----------------");
//    }
//
//    @After
//    public void after() {
//        System.out.println("测试结束-----------------");
//    }
//
//    @Test
//    public void testTransactional(){
//       List<BridageUUserAddress> addresses = mapper.selectAll();
//       for(BridageUUserAddress address : addresses){
//           String lng = address.getLatitude().toString();
//           String lon =  address.getLongitude().toString();
//           String location[] = BaiduUtil.getAddress(address.getLongitude().toString(), address.getLatitude().toString());
//
//       }
//    }
//
//    @Test
//    public void contextLoads() {
//        BigDecimal a = BigDecimal.ZERO;
//        BigDecimal b = new BigDecimal(12.11);
//        System.out.println("result0 = "+a.add(b));
//
//        System.out.println("result1 = "+b.intValue());
//        BigDecimal prices=b.multiply(new BigDecimal(100));
//        System.out.println("result2 = "+prices.intValue());
//        //设置小数位，变量1是小数位数，变量2是取舍方法(四舍五入)
//        BigDecimal prices1=prices.setScale(2, BigDecimal.ROUND_HALF_UP);
//    }
//
//    @Test
//    public void testStrLen() {
////        String str = "{\"qryBindCardExecutorsData\":{\"availableAmount\":\"1187.93\",\"cardExtInfo\":[{\"bindType\":\"CARD\",\"cardBalance\":\"0.00\",\"cardExpiredDate\":\"9912\",\"cardLever\":\"P\",\"cardName\":\"京客隆主卡\",\"cardNo\":\"2336443301600000049\",\"hotReason\":\"\",\"isVirtualCard\":\"Y\",\"status\":\"A\"},{\"bindType\":\"CARD\",\"cardBalance\":\"100.00\",\"cardExpiredDate\":\"9912\",\"cardLever\":\"S\",\"cardName\":\"京客隆虚拟卡100\",\"cardNo\":\"2336443320100006322\",\"hotReason\":\"\",\"isVirtualCard\":\"Y\",\"status\":\"A\"},{\"bindType\":\"CARD\",\"cardBalance\":\"100.00\",\"cardExpiredDate\":\"9912\",\"cardLever\":\"S\",\"cardName\":\"京客隆虚拟卡100\",\"cardNo\":\"2336443320100006331\",\"hotReason\":\"\",\"isVirtualCard\":\"Y\",\"status\":\"A\"},{\"bindType\":\"CARD\",\"cardBalance\":\"100.00\",\"cardExpiredDate\":\"9912\",\"cardLever\":\"S\",\"cardName\":\"京客隆虚拟卡100\",\"cardNo\":\"2336443320100006348\",\"hotReason\":\"\",\"isVirtualCard\":\"Y\",\"status\":\"A\"},{\"bindType\":\"CARD\",\"cardBalance\":\"100.00\",\"cardExpiredDate\":\"9912\",\"cardLever\":\"S\",\"cardName\":\"京客隆虚拟卡100\",\"cardNo\":\"2336443320100006082\",\"hotReason\":\"\",\"isVirtualCard\":\"Y\",\"status\":\"A\"},{\"bindType\":\"CARD\",\"cardBalance\":\"100.00\",\"cardExpiredDate\":\"9912\",\"cardLever\":\"S\",\"cardName\":\"京客隆虚拟卡100\",\"cardNo\":\"2336443320100006091\",\"hotReason\":\"\",\"isVirtualCard\":\"Y\",\"status\":\"A\"},{\"bindType\":\"CARD\",\"cardBalance\":\"87.93\",\"cardExpiredDate\":\"9912\",\"cardLever\":\"S\",\"cardName\":\"京客隆虚拟卡100\",\"cardNo\":\"2336443320100006066\",\"hotReason\":\"\",\"isVirtualCard\":\"Y\",\"status\":\"A\"},{\"bindType\":\"CARD\",\"cardBalance\":\"100.00\",\"cardExpiredDate\":\"9912\",\"cardLever\":\"S\",\"cardName\":\"京客隆虚拟卡100\",\"cardNo\":\"2336443320100006132\",\"hotReason\":\"\",\"isVirtualCard\":\"Y\",\"status\":\"A\"},{\"bindType\":\"CARD\",\"cardBalance\":\"100.00\",\"cardExpiredDate\":\"9912\",\"cardLever\":\"S\",\"cardName\":\"京客隆虚拟卡100\",\"cardNo\":\"2336443320100006141\",\"hotReason\":\"\",\"isVirtualCard\":\"Y\",\"status\":\"A\"},{\"bindType\":\"CARD\",\"cardBalance\":\"100.00\",\"cardExpiredDate\":\"9912\",\"cardLever\":\"S\",\"cardName\":\"京客隆虚拟卡100\",\"cardNo\":\"2336443320100006157\",\"hotReason\":\"\",\"isVirtualCard\":\"Y\",\"status\":\"A\"},{\"bindType\":\"CARD\",\"cardBalance\":\"100.00\",\"cardExpiredDate\":\"9912\",\"cardLever\":\"S\",\"cardName\":\"京客隆虚拟卡100\",\"cardNo\":\"2336443320100006165\",\"hotReason\":\"\",\"isVirtualCard\":\"Y\",\"status\":\"A\"},{\"bindType\":\"CARD\",\"cardBalance\":\"100.00\",\"cardExpiredDate\":\"9912\",\"cardLever\":\"S\",\"cardName\":\"京客隆虚拟卡100\",\"cardNo\":\"2336443320100006521\",\"hotReason\":\"\",\"isVirtualCard\":\"Y\",\"status\":\"A\"},{\"bindType\":\"CARD\",\"cardBalance\":\"100.00\",\"cardExpiredDate\":\"9912\",\"cardLever\":\"S\",\"cardName\":\"京客隆虚拟卡100\",\"cardNo\":\"2336443320100020182\",\"hotReason\":\"\",\"isVirtualCard\":\"Y\",\"status\":\"A\"}],\"code\":\"00\",\"msg\":\"查询成功\",\"totalAmount\":\"1187.93\"},\"success\":false}";
////
////        System.out.println(str.length());
//        BigDecimal discValue = new BigDecimal("1");
//        BigDecimal discValue1 = discValue.multiply(new BigDecimal(2)).divide(new BigDecimal(3),2,BigDecimal.ROUND_DOWN);
//        BigDecimal discValue2 = discValue.multiply(new BigDecimal(1)).divide(new BigDecimal(3),2,BigDecimal.ROUND_UP);
//        System.out.println(discValue1);
//        System.out.println(discValue2);
//    }
//
//
//    @Test
//    public void testCollectionsUtil() {
////        List<Integer> list = new ArrayList<Integer>();
////        System.out.println("List"+ JSON.toJSONString(list)+","+CollectionUtils.isNotEmpty(list));
//
//        BigDecimal decimal = new BigDecimal(0.28);
//        BigDecimal decimal1 = new BigDecimal(3);
//        System.out.println(decimal.divide(decimal1));
//    }
//
//
//}
