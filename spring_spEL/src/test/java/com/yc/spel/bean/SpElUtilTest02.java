package com.yc.spel.bean;

import static org.junit.Assert.*;  

import java.lang.reflect.Method;
import java.util.ArrayList;  
import java.util.Arrays;  
import java.util.Collection;
import java.util.Date;  
import java.util.HashMap;  
import java.util.HashSet;
import java.util.List;  
import java.util.Map;  
import java.util.Properties;

import org.junit.After;  
import org.junit.Assert;
import org.junit.Before;  
import org.junit.Test;  
import org.springframework.context.ApplicationContext;  
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;  
import org.springframework.expression.ExpressionParser;  
import org.springframework.expression.common.TemplateParserContext;  
import org.springframework.expression.spel.standard.SpelExpressionParser;  
import org.springframework.expression.spel.support.StandardEvaluationContext;  

import com.yc.spel.factory.ApplicationContextFactory;
public class SpElUtilTest02 {  
	 // spring配置文件上下文  
    ApplicationContext context = null;  
    // spring el测试辅助类  
    SpElUtil spel = null;  
    // 表达式解析对象  
    ExpressionParser parser = null;  
    // 标准赋值上下文  
    StandardEvaluationContext secontext;  
    @Before  
    public void setUp() throws Exception {  
        context = ApplicationContextFactory.createInstance();  
        spel = context.getBean(SpElUtil.class);  
        secontext = new StandardEvaluationContext(spel);  
        parser = new SpelExpressionParser();  
    }  
    @After  
    public void tearDown() throws Exception {  
        context = null;  
        spel = null;  
        secontext = null;  
        parser = null;  
    }  
   
    /**
     * 1.逻辑表达式
     * 	逻辑运算符不支持 Java中的 && 和 || 
     */
    @Test  
    public void testLuoji(){
        String expression1 = "!true or !false";  
        //Expression exp = parser.parseExpression(expression1);
        boolean result1 = parser.parseExpression(expression1).getValue(boolean.class);  
        Assert.assertEquals(true, result1);  
        //正则表达式
        assertTrue(parser.parseExpression("'123' matches '\\d{3}'").getValue(Boolean.class));  
        // 运算操作  
        assertEquals(2, parser.parseExpression("1 + 1").getValue());  
        assertEquals("ABab",parser.parseExpression("'AB' + 'ab'").getValue(String.class));  
    }
    
    /**
     * 2.字符串拼接和截取
     * 字符串连接及截取表达式：使用“+”进行字符串连接，使用“'String'[0] [index]”来截取一个字符，目前只支持截取一个，
     * 如“'Hello ' + 'World!'”得到“Hello World!”；
     * 而“'Hello World!'[0]”将返回“H”。
     */
    @Test  
   	public void testPinJie() {  
    		//拼接
   	        Expression expression = parser.parseExpression("('Hello' + ' World').concat(#start)");  
   	        EvaluationContext context = new StandardEvaluationContext();  
   	        context.setVariable("start","");  
   	        Assert.assertEquals("Hello World", expression.getValue(context));  
   	        String str=(String) expression.getValue(context);
   	        System.out.println(str);
   	        //截取
   	        String  str1=parser.parseExpression("'Hello World'[1]").getValue(String.class);
   	        System.out.println(str1);
   	}  
    

   
    /** 
     *3.三元操作测试 
     *三目运算符 “表达式1?表达式2:表达式3”用于构造三目运算表达式，如“2>1?true:false”将返回true；
     */  
    @Test  
    public void testSpelTernaryOperatorExpression() throws Exception {
    	
        assertTrue(parser.parseExpression(" true ? true :false").getValue(Boolean.class));  
        assertEquals("is true",  
                parser.parseExpression(" 1 == 1 ? 'is true' :'is false'").getValue(String.class));  
    }  
    /** 
     * 4.Elvis 操作测试 
     *  Elvis运算符“表达式1?:表达式2”从Groovy语言引入用于简化三目运算符的，
         当表达式1为非null时则返回表达式1，当表达式1为null时则返回表达式2，简化了三目运算符方式“表达式1? 表达式1:表达式2”，
         如“null?:false”将返回false，而“true?:false”将返回true；
     */  
    @Test  
    public void testSpeleElvisOperatorExpression() throws Exception {  
        Expression ex = parser.parseExpression("name?:'name is null'");  
        
        spel.setName("anan");
        String str=ex.getValue(secontext, String.class);
        //String str=parser.parseExpression("anan").getValue(String.class);
        assertEquals("anan", ex.getValue(secontext, String.class));
        System.out.println(str);
        spel.setName("安安");  
        //spel.setName(""); 
        //spel.setName(null); 
        assertEquals("name is null", ex.getValue(secontext, String.class));  
    }  
    
    
    /**
     * 5.类类型表达式：使用“T(Type)”来表示java.lang.Class实例，“Type”必须是类全限定名，
     * “java.lang”包除外，即该包下的类可以不指定包名；使用类类型表达式还可以进行访问类静态方法及类静态字段。
     *  对于java.lang包里的可以直接使用“T(String)”访问；其他包必须是类全限定名；可以进行静态字段访问如“T(Integer).MAX_VALUE”；
     *  也可以进行静态方法访问如“T(Integer).parseInt('1')”。
           具体使用方法如下：
     */
    @Test  
    public void testClassTypeExpression() {  
        ExpressionParser parser = new SpelExpressionParser();  
        //java.lang包类访问  
        @SuppressWarnings("unchecked")
		Class<String> result1 = parser.parseExpression("T(String)").getValue(Class.class);  
        Assert.assertEquals(String.class, result1);  
        //其他包类访问  
        String expression2 = "T(com.yc.spel.bean.SpElUtilTest)";  
        Class<String> result2 = parser.parseExpression(expression2).getValue(Class.class);  
        Assert.assertEquals(SpElUtilTest.class, result2);  
        //类静态字段访问  
        int result3=parser.parseExpression("T(Integer).MAX_VALUE").getValue(int.class);  
        Assert.assertEquals(Integer.MAX_VALUE, result3);  
        //类静态方法调用  
        int result4 = parser.parseExpression("T(Integer).parseInt('1')").getValue(int.class);  
        Assert.assertEquals(1, result4);  
    }  
    
    
    /**
     * 6.类实例化：类实例化同样使用java关键字“new”，类名必须是全限定名，
     * 但java.lang包内的类型除外，如String、Integer。
     */
    @Test  
    public void testConstructorExpression() {  
        ExpressionParser parser = new SpelExpressionParser();  
        String result1 = parser.parseExpression("new String('haha')").getValue(String.class);  
        Assert.assertEquals("haha", result1);  
        Date result2 = parser.parseExpression("new java.util.Date()").getValue(Date.class);  
        Assert.assertNotNull(result2);  
    }  

    /**
     * java 中的instanceof 运算符是用来在运行时指出对象是否是特定类的一个实例。
     * instanceof通过返回一个布尔值来指出，这个对象是否是这个特定类或者是它的子类的一个实例。
     */
    
    /**
     * 7、变量定义及引用：变 量定义通过EvaluationContext接口的setVariable(variableName, value)方法定义；
     * 在表达式中使用“#variableName”引用；除了引用自定义变量，SpE还允许引用根对象及当前上下文对象，使用 “#root”引用根对象，使用“#this”引用当前上下文对象；
     *  使用“#variable”来引用在EvaluationContext定义的变量；除了可以引用自定义变量，还可以使用“#root”引用根对 象，
     *  “#this”引用当前上下文对象，此处“#this”即根对象。
     */
    @Test  
    public void testVariableExpression() {  
        ExpressionParser parser = new SpelExpressionParser();  
        EvaluationContext context = new StandardEvaluationContext();  
        //context.setVariable("variable", "haha");  
        context.setVariable("variable", "haha");  
        String result1 = parser.parseExpression("#variable").getValue(context, String.class);  
        Assert.assertEquals("haha", result1);  
       
        context = new StandardEvaluationContext("haha");  
        String result2 = parser.parseExpression("#root").getValue(context, String.class);  
        Assert.assertEquals("haha", result2);  
        String result3 = parser.parseExpression("#this").getValue(context, String.class);  
        Assert.assertEquals("haha", result3);  
    }  
    
    
    /**
     * 8.自定义函数：目前只支持类静态方法注册为自定义函数；SpEL使用StandardEvaluationContext的registerFunction方法进行注册自定义函数，
     * 其实完全可以使用setVariable代替，两者其实本质是一样的；
     * 此处可以看出“registerFunction”和“setVariable”都可以注册自定义函数，
     * 但是两个方法的含义不一样，推荐使用 “registerFunction”方法注册自定义函数。
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    @Test  
    public void testFunctionExpression() throws SecurityException, NoSuchMethodException {  
        ExpressionParser parser = new SpelExpressionParser();  
        StandardEvaluationContext context = new StandardEvaluationContext();  
        Method parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);  
        context.registerFunction("parseInt", parseInt);  
        context.setVariable("parseInt2", parseInt);  
        String expression1 = "#parseInt('3') == #parseInt2('3')";  
        boolean result1 = parser.parseExpression(expression1).getValue(context, boolean.class);  
        Assert.assertEquals(true, result1);         
    }  
    
    /**
     * 9.赋值表达式：SpEL即允许给自定义变量赋值，也允许给跟对象赋值，直接使用“#variableName=value”即可赋值：
     * 使用“#root='aaaaa'”给根对象赋值，使用“"#this='aaaa'”给当前上下文对象赋值，使用“#variable=#root”给自定义变量赋值
     */
    @Test  
    public void testAssignExpression() {  
        ExpressionParser parser = new SpelExpressionParser();  
        //1.给root对象赋值  
        EvaluationContext context = new StandardEvaluationContext("aaaa");  
        String result1 = parser.parseExpression("#root='aaaaa'").getValue(context, String.class);  
        Assert.assertEquals("aaaaa", result1);  
        String result2 = parser.parseExpression("#this='aaaa'").getValue(context, String.class);  
        Assert.assertEquals("aaaa", result2);  
       
        //2.给自定义变量赋值  
        context.setVariable("#variable", "variable");  
        String result3 = parser.parseExpression("#variable=#root").getValue(context, String.class);  
        Assert.assertEquals("aaaa", result3);  
    }  
    
    /**
     * 10.安全导航
     * SpEL还引入了 Groovy语言中的安全导航运算符“(对象|属性)?.属性”，用来避免当“?.”前边的表达式为null时抛出空指针异常，而是返回null；
     * 修改对 象属性值则可以通过赋值表达式或Expression接口的setValue方法修改。
     * @throws Exception
     */
    @Test  
    public void testSpelSafeNavOperatorExpression() throws Exception { 
    	ExpressionParser parser = new SpelExpressionParser();  
    	//1.访问root对象属性  ,对于当前上下文对象属性及方法访问，可以直接使用属性或方法名访问
    	Date date = new Date();  
    	StandardEvaluationContext context = new StandardEvaluationContext(date);  
    	int result1 = parser.parseExpression("Year").getValue(context, int.class);  
    	Assert.assertEquals(date.getYear(), result1);  
    	int result2 = parser.parseExpression("year").getValue(context, int.class);  
    	Assert.assertEquals(date.getYear(), result2); 
    	
    	//2.安全访问  ：  SpEL引入了Groovy的安全导航运算符，比如此处根对象为null，
    	//所以如果访问其属性时肯定抛出空指针异常，而采用“?.”安全访问导航运算符将 不抛空指针异常，而是简单的返回null。
    	context.setRootObject(null);  
    	Object result3 = parser.parseExpression("#root?.year").getValue(context, Object.class);  
    	Assert.assertEquals(null, result3);
    	
    	//3.给root对象属性赋值  : 给对象属性赋值可以采用赋值表达式或Expression接口的setValue方法赋值，而且也可以采用点缀方式赋值。
    	context.setRootObject(date);  
    	int result4 = parser.parseExpression("Year = 4").getValue(context, int.class);  
    	Assert.assertEquals(4, result4);  
    	parser.parseExpression("Year").setValue(context, 5);  
    	int result5 = parser.parseExpression("Year").getValue(context, int.class);  
    	Assert.assertEquals(5, result5);  
    }
    
    /**
     * 11.对象方法的调用
     * @throws Exception
     */
    @Test  
    public void testMethodInvoking() throws Exception { 
    	ExpressionParser parser = new SpelExpressionParser(); 
    	Date date = new Date();  
    	StandardEvaluationContext context = new StandardEvaluationContext(date);  
    	int result2 = parser.parseExpression("getYear()").getValue(context, int.class);  
    	Assert.assertEquals(date.getYear(), result2);  
    }
    
    /**
     * 12.Bean引用：SpEL支持使用“@”符号来引用Bean，在引用Bean时需要使用BeanResolver接口实现来查找Bean，
     * Spring提供BeanFactoryResolver实现；
     */
    @Test  
    public void testBeanExpression() {
    	// 在示例中我们首先初始化了一个IoC容器，ClassPathXmlApplicationContext 
    	//实现默认会把“System.getProperties()”注册为“systemProperties”Bean，
    	
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();  
        ctx.refresh();  
        ExpressionParser parser = new SpelExpressionParser();  
        StandardEvaluationContext context = new StandardEvaluationContext();  
        context.setBeanResolver(new BeanFactoryResolver(ctx));  
       //因此我们使用 “@systemProperties”来引用该Bean。
        Properties result1 = parser.parseExpression("@systemProperties").getValue(context, Properties.class);  
        Assert.assertEquals(System.getProperties(), result1);  
    }  
    
    /** 
     * 13.构造函数调用测试 
     *  
     * @throws Exception 
     */  
    @Test  
    public void testSpelConstructorsExpression() throws Exception {  
        SpelTestInnerClass spt = parser.parseExpression(  
                        "new com.yc.spel.bean.SpelTestInnerClass('constructTest',23)")  
                .getValue(SpelTestInnerClass.class);  
        assertEquals(23, spt.getAge());  
        assertEquals("constructTest", spt.getName());  
    }  
    
    /**
     * 14.集合，字典元素访问：SpEL目前支持所有集合类型和字典类型的元素访问，使用“集合[索引]”访问集合元素，使用“map[key]”访问字典元素；
     * @throws Exception
     */
    @Test  
    public void testExpression() throws Exception {
    	//SpEL内联List访问  
    	int result1 = parser.parseExpression("{1,2,3}[0]").getValue(int.class);  
    	//即list.get(0)  
    	Assert.assertEquals(1, result1);  
    	//SpEL目前支持所有集合类型的访问  
    	Collection<Integer> collection = new HashSet<Integer>();  
    	collection.add(1);  
    	collection.add(2);  
    	EvaluationContext context2 = new StandardEvaluationContext();  
    	//设置变量
    	context2.setVariable("collection", collection);  
    	int result2 = parser.parseExpression("#collection[1]").getValue(context2, int.class);  
    	//对于任何集合类型通过Iterator来定位元素  
    	Assert.assertEquals(2, result2);  
    	//SpEL对Map字典元素访问的支持  
    	Map<String, Integer> map = new HashMap<String, Integer>();  
    	map.put("a", 1);  
    	EvaluationContext context3 = new StandardEvaluationContext();  
    	context3.setVariable("map", map);  
    	int result3 = parser.parseExpression("#map['a']").getValue(context3, int.class);  
    	Assert.assertEquals(1, result3);  
    	
    	
    	//列表，字典，数组元素修改
    	
    }
    
    /**
     * 15.列表，字典，数组元素修改：可以使用赋值表达式或Expression接口的setValue方法修改；
     */
    @Test  
    public void testUpdateExpression(){
    	//1.修改数组元素值  
    	int[] array = new int[] {1, 2};  
    	EvaluationContext context1 = new StandardEvaluationContext();  
    	context1.setVariable("array", array);  
    	int result1 = parser.parseExpression("#array[1] = 3").getValue(context1, int.class);  
    	Assert.assertEquals(3, result1);  
    	//2.修改集合值  
    	Collection<Integer> collection = new ArrayList<Integer>();  
    	collection.add(1);  
    	collection.add(2);  
    	EvaluationContext context2 = new StandardEvaluationContext();  
    	context2.setVariable("collection", collection);  
    	int result2 = parser.parseExpression("#collection[1] = 3").getValue(context2, int.class);  
    	Assert.assertEquals(3, result2);  
    	parser.parseExpression("#collection[1]").setValue(context2, 4);  
    	result2 = parser.parseExpression("#collection[1]").getValue(context2, int.class);  
    	Assert.assertEquals(4, result2);  
    	//3.修改map元素值  
    	Map<String, Integer> map = new HashMap<String, Integer>();  
    	map.put("a", 1);  
    	EvaluationContext context3 = new StandardEvaluationContext();  
    	context3.setVariable("map", map);  
    	int result3 = parser.parseExpression("#map['a'] = 2").getValue(context3, int.class);  
    	Assert.assertEquals(2, result3);  
    	//对数组修改直接对“#array[index]”赋值即可修改元素值，同理适用于集合和字典类型。
    }
    /** 
     * 16.集合选择表达式测试 
     *  
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")  
    @Test  
    public void testSpelCollectionSelectExpression() throws Exception {
    	//1.首先准备测试数据  
    	Collection<Integer> collection = new ArrayList<Integer>();  
    	collection.add(4);  
    	collection.add(5);  
    	Map<String, Integer> map = new HashMap<String, Integer>();  
    	map.put("a", 1);   
    	map.put("b", 2);  
    	//2.集合或数组测试  
    	EvaluationContext context1 = new StandardEvaluationContext();  
    	context1.setVariable("collection", collection);  
    	Collection<Integer> result1 =  
    	parser.parseExpression("#collection.?[#this>4]").getValue(context1, Collection.class);  
    	Assert.assertEquals(1, result1.size());  
    	Assert.assertEquals(new Integer(5), result1.iterator().next());  
    	//3.字典测试  
    	EvaluationContext context2 = new StandardEvaluationContext();  
    	context2.setVariable("map", map);  
    	Map<String, Integer> result2 =  
    	parser.parseExpression("#map.?[#this.key != 'a']").getValue(context2, Map.class);  
    	Assert.assertEquals(1, result2.size());  
    	   
    	List<Integer> result3 =  
    	 parser.parseExpression("#map.?[key != 'a'].![value+1]").getValue(context2, List.class);  
    	Assert.assertEquals(new Integer(3), result3.iterator().next());  
    }
    
    /** 
     * 17.投影表达式测试 
     *  
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")  
    @Test  
    public void testSpelProjectionExpression() throws Exception {  
    	//1.首先准备测试数据  
    	Collection<Integer> collection = new ArrayList<Integer>();  
    	collection.add(4); 
    	collection.add(5);  
    	Map<String, Integer> map = new HashMap<String, Integer>();  
    	map.put("a", 1);  
    	map.put("b", 2);  
    	//2.测试集合或数组  
    	EvaluationContext context1 = new StandardEvaluationContext();  
    	context1.setVariable("collection", collection);  
    	Collection<Integer> result1 =  
    	parser.parseExpression("#collection.![#this+1]").getValue(context1, Collection.class);  
    	Assert.assertEquals(2, result1.size());  
    	Assert.assertEquals(new Integer(5), result1.iterator().next());//iterator:迭代器
    	/*for(int col:collection){
    		System.out.println(col+" sdas");
    	}*/
    	//3.测试字典  
    	EvaluationContext context2 = new StandardEvaluationContext();  
    	context2.setVariable("map", map); 
    	//用“value”来获取值，使用“key”来获取键。
    	List<Integer> result2 =  
    	parser.parseExpression("#map.![value+1]").getValue(context2, List.class);  
    	Assert.assertEquals(2, result2.size());  
    	Assert.assertEquals(new Integer(2), result2.get(1));
    }
    
    
    
    
    /** 
     * 18.内联list测试 
     *  
     * @throws Exception 
     */  
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    @Test  
    public void testSpELInnerListExpression() throws Exception {  
        // 构造list  
        List<String> nums = (List<String>) parser.parseExpression("{'a','b','c','d'}").getValue();  
        assertEquals(Arrays.asList("a", "b", "c", "d"), nums);  
        // 构造List<List<>>  
        List listOfLists = (List) parser.parseExpression("{{1,2},{3,4}}").getValue(secontext);  
        assertEquals(Arrays.asList(1, 2), listOfLists.get(0));  
    }  
}  
