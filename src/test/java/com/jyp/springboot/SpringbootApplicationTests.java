package com.jyp.springboot;

import com.jyp.springboot.dao.AccountDao;
import com.jyp.springboot.entity.Account;
import com.mongodb.util.JSON;
import net.minidev.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.math.BigDecimal;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.security.Key;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootApplicationTests {



	@LocalServerPort
	private int port;

	@Autowired
	private AccountDao accountDao;

	private URL base;

	@Autowired
	private TestRestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
	}

	@Test
	public void getHello() throws Exception {
		ResponseEntity<String> response = template.getForEntity(base.toString(),
				String.class);
		assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
	}

	@Test
	public void contextLoads() {
	}
	@Test
	public void testLocate() {
		Optional<Account> accountDOOptional = accountDao.findById(1);
		if (accountDOOptional.isPresent()) {
			Account account = accountDOOptional.get();
			System.out.println("name = " + account.getName());
			System.out.println("money = " + account.getMoney());
		}
	}

	@Test
	public void testFindAll() {
		List<Account> accountDOList = accountDao.findAll(new Sort(Sort.Direction.DESC,"name"));
		for (Account account : accountDOList) {
			System.out.println("name = " + account.getName());
			System.out.println("money = " + account.getMoney());
		}
	}

	@Test
	public void testFindByName() {
		Account account = accountDao.findByName("aaa");
		if (account != null) {
			System.out.println("name = " + account.getName());
			System.out.println("money = " + account.getMoney());
		}
	}
	@Test
	public void testFindByTwoName() {
		List<Account> accountDOList = accountDao.findByTwoName("aaa","bbb");
		for (Account account : accountDOList) {
			System.out.println("name = " + account.getName());
			System.out.println("money = " + account.getMoney());
		}
	}
	@Test
	public void testFindSql() {
		List<Account> accountDOList = accountDao.findSQL("aaa","bbb");
		for (Account account : accountDOList) {
			System.out.println("name = " + account.getName());
			System.out.println("money = " + account.getMoney());
		}
	}
	@Test
	public void testSave() {
		Account account = accountDao.findByName("aaa");
		if (account != null) {
			accountDao.save(account);
		}
	}




	//当循环中只需要获取Map 的主键key时，迭代keySet() 是正确的；
	// 但是，当需要主键key 和取值value 时，迭代entrySet() 才是更高效的做法，其比先迭代keySet() 后再去通过get 取值性能更佳。
	@Test
	public void testKeySet() {
		//反面例子
		long startTime = System.nanoTime();
		HashMap<String, String> map = new HashMap<>();
		for (String key : map.keySet()){
			String value = map.get(key);
		}
		long endTime = System.nanoTime();  //获取结束时间
		System.out.println("反面例子程序运行时间：" + (endTime - startTime) + "ns"); //输出程序运行时间
		//正面例子
		long startTime1 = System.nanoTime();
		HashMap<String, String> map1 = new HashMap<>();
		for (Map.Entry<String,String> entry : map1.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
		}
		long endTime1 = System.nanoTime();  //获取结束时间
		System.out.println("正面例子程序运行时间：" + (endTime1 - startTime1) + "ns"); //输出程序运行时间
	}

	//使用Collection.size() 来检测是否为空在逻辑上没有问题，但是使用Collection.isEmpty() 使得代码更易读，并且可以获得更好的性能；
	// 除此之外，任何Collection.isEmpty() 实现的时间复杂度都是O(1) ，不需要多次循环遍历，但是某些通过Collection.size() 方法实现的时间复杂度可能是O(n)
	@Test
	public void testCollection() {
		//反面例子
		long startTime = System.nanoTime();
		LinkedList<Object> collection = new LinkedList<>();
		if (collection.size() == 0){
			System.out.println("collection is empty.");
		}
		long endTime = System.nanoTime();  //获取结束时间
		System.out.println("反面例子程序运行时间：" + (endTime - startTime) + "ns"); //输出程序运行时间
		//正面例子1
		long startTime1 = System.nanoTime();
		LinkedList<Object> collection1 = new LinkedList<>();
		if (collection1.isEmpty()){
			System.out.println("collection1 is empty.");
		}
		long endTime1 = System.nanoTime();  //获取结束时间
		System.out.println("正面例子程序运行时间1：" + (endTime1 - startTime1) + "ns"); //输出程序运行时间
		//正面例子2
		long startTime2 = System.nanoTime();
		LinkedList<Object> collection2 = new LinkedList<>();
		if (CollectionUtils.isEmpty(collection)){
			System.out.println("collection2 is empty.");
		}
		long endTime2 = System.nanoTime();  //获取结束时间
		System.out.println("正面例子程序运行时间2：" + (endTime2 - startTime2) + "ns"); //输出程序运行时间

	}

	//尽量在初始化时指定集合的大小，能有效减少集合的扩容次数，因为集合每次扩容的时间复杂度很可能时O(n)，耗费时间和性能。
	@Test
	public void testListInit() {
		//反面例子
		long startTime = System.nanoTime();
		int[] arr = new int[]{1,2,3,4};
		List<Integer> list = new ArrayList<>();
		for (int i : arr){
			list.add(i);
		}
		long endTime = System.nanoTime();  //获取结束时间
		System.out.println("反面例子程序运行时间：" + (endTime - startTime) + "ns"); //输出程序运行时间
		//正面例子
		long startTime1 = System.nanoTime();
		int[] arr1 = new int[]{1,2,3,4};
		//指定集合list 的容量大小
		List<Integer> list1 = new ArrayList<>(arr1.length);
		for (int i : arr1){
			list1.add(i);
		}

		long endTime1 = System.nanoTime();  //获取结束时间
		System.out.println("正面例子程序运行时间：" + (endTime1 - startTime1) + "ns"); //输出程序运行时间
	}

	//一般的字符串拼接在编译期Java 会对其进行优化，但是在循环中字符串的拼接Java 编译期无法执行优化，所以需要使用StringBuilder 进行替换。
	@Test
	public void testStringBuilder () {
		//反面例子
		long startTime = System.nanoTime();
		//在循环中拼接字符串反例
		String str = "";
		for (int i = 0; i < 10; i++){
			//在循环中字符串拼接Java 不会对其进行优化
			str += i;
		}
		long endTime = System.nanoTime();  //获取结束时间
		System.out.println("反面例子程序运行时间：" + (endTime - startTime) + "ns"); //输出程序运行时间
		//正面例子
		long startTime1 = System.nanoTime();
		//在循环中拼接字符串正例
		String str1 = "Love";
		String str2 = "Courage";
		String strConcat = str1 + str2;  //Java 编译器会对该普通模式的字符串拼接进行优化
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++){
			//在循环中，Java 编译器无法进行优化，所以要手动使用StringBuilder
			sb.append(i);
		}

		long endTime1 = System.nanoTime();  //获取结束时间
		System.out.println("正面例子程序运行时间：" + (endTime1 - startTime1) + "ns"); //输出程序运行时间
	}


	@Test
	public void testBigDecimal() {
		//反面例子
		long startTime = System.nanoTime();
		// BigDecimal 反例
		BigDecimal bigDecimal = new BigDecimal(0.111111D);
		System.out.println("反面例子bigDecimal:" + bigDecimal);
		long endTime = System.nanoTime();  //获取结束时间
		System.out.println("反面例子程序运行时间：" + (endTime - startTime) + "ns"); //输出程序运行时间
		//正面例子
		long startTime1 = System.nanoTime();
		// BigDecimal 正例
		BigDecimal bigDecimal1 = BigDecimal.valueOf(0.111111D);
		System.out.println("正面例子bigDecimal1:" + bigDecimal1);
		long endTime1 = System.nanoTime();  //获取结束时间
		System.out.println("正面例子程序运行时间：" + (endTime1 - startTime1) + "ns"); //输出程序运行时间
	}

	//返回空数组和空集正例,返回对象不为空,但长度为0
	public Result[] getResults() {
		return new Result[0];
	}

	public List<Result> getResultList() {
		return Collections.emptyList();
	}

	public Map<String, Result> getResultMap() {
		return Collections.emptyMap();
	}

	@Test
	public void testResult() {
		Result[] result = getResults();
		if (null == result){
			System.out.println("result is null.");
		}
		if (result.length == 0){
			System.out.println("result.length is 0.");
		}
		//List<Result> list = getResultList();
		List<Result> list = null;
		if (null == list){
			System.out.println("list is null.");
		}
//		if (0 == list.size()){
//			System.out.println("list.size is 0.");
//		}
//		if (list.isEmpty()){
//			System.out.println("list1 is null.");
//		}
		if (CollectionUtils.isEmpty(list)){
			System.out.println("list2 is null.");
		}
		//Map<String, Result> map = getResultMap();
		Map<String, Result> map = null;
		if (null == map){
			System.out.println("map is null.");
		}
//		if (0 == map.size()){
//			System.out.println("map.size is 0.");
//		}
//		if (map.isEmpty()){
//			System.out.println("map1 is null.");
//		}
		if (CollectionUtils.isEmpty(map)){
			System.out.println("map2 is null.");
		}
		String s = "";
		if (null == s){
			System.out.println("s is null.");
		}
		if (0 == s.length()){
			System.out.println("s.length is 0.");
		}
		System.out.println("测试结束");
	}

	@Test
	public void testEequals() {
		String fileName = null;
		//正例
		System.out.println("Charming".equals(fileName));
		System.out.println(Objects.equals("Charming",fileName));
		//反例,报空指针
		//System.out.println(fileName.equals("Charming"));

	}

	@Test
	public void testStringSplit() {
		String fileName = null;
		// String.split(String regex) 反例
		String[] split10 = "a.ab.abc".split(".");
		System.out.println(Arrays.toString(split10));   // 结果为[]

		String[] split11 = "a|ab|abc".split("|");
		System.out.println(Arrays.toString(split11));  // 结果为["a", "|", "a", "b", "|", "a", "b", "c"]

		String[] split12 = "a.ab.abc".split("a");
		System.out.println(Arrays.toString(split12));   // 结果为["", ".", "b.", "bc"]

		String[] split13 = "a|ab|abc".split("a");
		System.out.println(Arrays.toString(split13));  // 结果为["", "|", "b|", "bc"]

		// String.split(String regex) 正例
		// . 需要转译
		String[] split20 = "a.ab.abc".split("\\.");
		System.out.println(Arrays.toString(split20));  // 结果为["a", "ab", "abc"]

		// | 需要转译
		String[] split21 = "a|ab|abc".split("\\|");
		System.out.println(Arrays.toString(split21));  // 结果为["a", "ab", "abc"]

		String[] split22 = "a.ab.abc".split("\\a");
		System.out.println(Arrays.toString(split22));   // 结果为[a.ab.abc]

		String[] split23 = "a|ab|abc".split("\\a");
		System.out.println(Arrays.toString(split23));  // 结果为[a|ab|abc]

	}
	@Test
	public void testJsonToList() {
		//list<map> 转 json
		List list = new ArrayList();
		Map<String,String> map = new HashMap<>();
		map.put("name","szy");
		map.put("num","2");
		list.add(map);
		Map<String,String> map1 = new HashMap<>();
		map1.put("name","aaa");
		map1.put("num","123");
		list.add(map1);

		String inventoryResultStr = JSON.serialize(list).toString();
		System.out.println(inventoryResultStr);

		List inventoryResultList = (List) JSON.parse(inventoryResultStr);
		for(Integer i=0;i<inventoryResultList.size();i++){
			Map map2 = (Map) inventoryResultList.get(i);
			String name =map1.get("name").toString();
		}
	}

}
