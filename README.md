# Udemy Mockito Tutorial

* [Links](#links)
* [Step 01 : Set up an Eclipse Project with JUnit and Mockito frameworks. First Green Bar.](#step-01---set-up-an-eclipse-project-with-junit-and-mockito-frameworks-first-green-bar)
* [Step 02 : Example to start understanding why we need mocks.](#step-02---example-to-start-understanding-why-we-need-mocks)
* [Step 03 : What is a stub? Create an unit test using Stub? Disadvantages of Stubs.](#step-03---what-is-a-stub--create-an-unit-test-using-stub--disadvantages-of-stubs)
* [Step 04 : Your first Mockito code! Hurrah!!! Lets use Mockito to mock TodoService.](#step-04---your-first-mockito-code--hurrah----lets-use-mockito-to-mock-todoservice)
* [Step 05 : Stubbing variations with Mockito.](#step-05---stubbing-variations-with-mockito)
* [Some Theory : Mockito vs EasyMock](#some-theory---mockito-vs-easymock)
* [Step 06 : Behavior-Driven Development (BDD)](#step-06---behavior-driven-development--bdd-)
* [Step 07 : How to verify calls on a mock?](#step-07---how-to-verify-calls-on-a-mock-)
* [Step 08 : How to capture an argument which is passed to a mock?](#step-08---how-to-capture-an-argument-which-is-passed-to-a-mock-)
* [Step 09 : Hamcrest Matchers.](#step-09---hamcrest-matchers)
* [Step 10 : Mockito Annotations.](#step-10---mockito-annotations)
* [Step 11 : JUnit Rules.](#step-11---junit-rules)
* [Step 12 : Real world Example with Spring](#step-12---real-world-example-with-spring)
* [Step 13 : What is a spy? How to spy with Mockito?](#step-13---what-is-a-spy--how-to-spy-with-mockito-)
* [Step 14 : Some Theory : Why does Mockito not allow stubbing final and private methods?](#step-14---some-theory---why-does-mockito-not-allow-stubbing-final-and-private-methods-)
* [Step 15 : Using PowerMock and Mockito to mock a Static Method.](#step-15---using-powermock-and-mockito-to-mock-a-static-method)
* [Step 16 : Using PowerMock and Mockito to invoke a private Method.](#step-16---using-powermock-and-mockito-to-invoke-a-private-method)
* [Step 17 : Using PowerMock and Mockito to mock a constructor.](#step-17---using-powermock-and-mockito-to-mock-a-constructor)
* [Step 18 : Good Unit Tests.](#step-18---good-unit-tests)

## Links
* Udemy Course: https://www.udemy.com/mockito-tutorial-with-junit-examples
* Instructor repository: https://github.com/in28minutes/MockitoTutorialForBeginners/
* My repository: https://github.com/AntonioDiaz/mockito.tutorial
* Certificate of completion: https://www.udemy.com/certificate/UC-FXX6JW9W/

## Step 01 : Set up an Eclipse Project with JUnit and Mockito frameworks. First Green Bar.
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step01.md
* Add maven dependencies:
```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-all</artifactId>
    <version>1.10.19</version>
    <scope>test</scope>
</dependency>
```

## Step 02 : Example to start understanding why we need mocks.
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step02.md
* Create interface TodoService:
```java
public interface TodoService {
  List<String> retrieveTodos(String user);
}
```
* Create class TodoBusinessImpl that uses interface TodoService:
```java
public List<String> retrieveTodosRelatedToSpring(String user) {
     List<String> filteredTodos = new ArrayList<String>();
     List<String> allTodos = todoService.retrieveTodos(user);
     for (String todo : allTodos) {
        if (todo.contains("Spring")) {
           filteredTodos.add(todo);
        }
     }
     return filteredTodos;
  }
}
```

## Step 03 : What is a stub? Create an unit test using Stub? Disadvantages of Stubs.
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step03.md
* TodoServiceStub: stub, TodoService implementation because is needed to test TodoBusinessImpl.
```java
public class TodoServiceStub implements TodoService {
  public List<String> retrieveTodos(String user) {
     return Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
  }
}
```

* TodoBussinessImplStubTest: to Test TodoBussinessImpl with the stub.
```java
public class TodoBusinessImplStubTest  {
  @Test
  public void usingAStub() {
     TodoService todoService = new TodoServiceStub();
     TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
     List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");
     assertEquals(2, todos.size());
  }
}
```

## Step 04 : Your first Mockito code! Hurrah!!! Lets use Mockito to mock TodoService.
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step04.md
* TodoBusinessImplMockitoTest.java
```java
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class TodoBusinessImplMockitoTest {
  @Test
  public void usingMockito() {
     TodoService todoService = mock(TodoService.class);
     List<String> allTodos = Arrays.asList("Learn Spring MVC","Learn Spring",
"Learn to Dance");
     when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
     TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
     List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");
     assertEquals(2, todos.size());
  }
}
```

## Step 05 : Stubbing variations with Mockito.
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step05.md
* ListTest.java: mockito examples mocking List class → Multiple return values, Argument Matchers and throwing exceptions.
```java
import org.junit.Test;
import org.mockito.Mockito;
import java.util.List;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
public class ListTest {
  @Test
  public void letsMockListSize() {
     List list = mock(List.class);
     Mockito.when(list.size()).thenReturn(10);
     assertEquals(10, list.size());
  }
  @Test
  public void letsMockListSizeWithMultipleReturnValues() {
     List list = mock(List.class);
     Mockito.when(list.size()).thenReturn(10).thenReturn(20);
     assertEquals(10, list.size()); // First Call
     assertEquals(20, list.size()); // Second Call
  }
  @Test
  public void letsMockListGet() {
     List<String> list = mock(List.class);
     Mockito.when(list.get(0)).thenReturn("in28Minutes");
     assertEquals("in28Minutes", list.get(0));
     assertNull(list.get(1));
  }
  @Test
  public void letsMockListGetWithAny() {
     List<String> list = mock(List.class);
     Mockito.when(list.get(Mockito.anyInt())).thenReturn("in28Minutes");
     // If you are using argument matchers,
     //all arguments have to be provided by matchers.
     assertEquals("in28Minutes", list.get(0));
     assertEquals("in28Minutes", list.get(1));
  }
}
```
## Some Theory : Mockito vs EasyMock
* https://github.com/mockito/mockito/wiki/Mockito-vs-EasyMock

## Step 06 : Behavior-Driven Development (BDD)
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step06.md
* Introduction to BDD
* Given When Then
* BDD Mockito Syntax
```java
given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);
```
* Example: 
```java
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
@Test
public void usingMockito_UsingBDD() {
    TodoService todoService = mock(TodoService.class);
    TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
    List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
    given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);
    List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");
    assertThat(todos.size(), is(2));
}
```

## Step 07 : How to verify calls on a mock?
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step07.md
* Verify how many times a method is called.
* We will add deleteTodo method to the TodoService and deleteTodosNotRelatedToSpring to TodoBussinessImpl.
```java
public void deleteTodosNotRelatedToSpring(String user) {
  List<String> allTodos = todoService.retrieveTodos(user);
  for (String todo : allTodos) {
     if (!todo.contains("Spring")) {
        todoService.deleteTodo(todo);
     }
  }
}
```
* In TodoBusinessImplMockitoTest add method:
```java
import static org.mockito.Mockito.verify;
@Test
public void letsTestDeleteNow() {
    TodoService todoService = mock(TodoService.class);
    List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
    when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
    TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
    todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
    verify(todoService).deleteTodo("Learn to Dance");
    verify(todoService, Mockito.never()).deleteTodo("Learn Spring MVC");
    verify(todoService, Mockito.never()).deleteTodo("Learn Spring");
    verify(todoService, Mockito.times(1)).deleteTodo("Learn to Dance");
}
```

## Step 08 : How to capture an argument which is passed to a mock?
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step08.md
* How to capture an argument which is passed to a mock?
```java
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
@Test
public void captureArgument() {
  ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
  TodoService todoService = mock(TodoService.class);
  List<String> allTodos = Arrays.asList("Learn Spring MVC","Learn Spring", "Learn to Dance");
  Mockito.when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
  TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
  todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
  Mockito.verify(todoService).deleteTodo(argumentCaptor.capture());
  assertEquals("Learn to Dance", argumentCaptor.getValue());
}
```

## Step 09 : Hamcrest Matchers.
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step09.md
* Add maven dependence to pom.xml
```xml
<dependency>
  <groupId>org.hamcrest</groupId>
  <artifactId>hamcrest-library</artifactId>
  <version>1.3</version>
  <scope>test</scope>
</dependency>
```
* Create test method:
```java
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Every.everyItem;
public class HamcrestMatcherTest {
  @Test
  public void basicHamcrestMatchers() {
    List<Integer> scores = Arrays.asList(99, 100, 101, 105);
    assertThat(scores, hasSize(4));
    assertThat(scores, hasItems(100, 101));
    assertThat(scores, everyItem(greaterThan(90)));
    assertThat(scores, everyItem(lessThan(200)));
    // String
    assertThat("", isEmptyString());
    assertThat(null, isEmptyOrNullString());
    // Array
    Integer[] marks = { 1, 2, 3 };
    assertThat(marks, arrayWithSize(3));
    assertThat(marks, arrayContainingInAnyOrder(2, 3, 1));
  }
}
```
## Step 10 : Mockito Annotations.
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step10.md
* Annotations:
    * @Mock
    * @InjectMocks
    * @RunWith(MockitoJUnitRunner.class)
    * @Captor.
* TodoBusinessImplMockitoInjectMocksTest.java
```java
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectMocksTest  {
  @Mock
  TodoService todoService;
  @InjectMocks
  TodoBusinessImpl todoBusinessImpl;
  @Captor
  ArgumentCaptor<String> stringArgumentCaptor;
  @Test
  public void usingMockito() {
    List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
    when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
    List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");
    assertEquals(2, todos.size());
  }
  @Test
  public void letsTestDeleteNow() {
    List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
    when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
    todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
    verify(todoService).deleteTodo("Learn to Dance");
    verify(todoService, Mockito.never()).deleteTodo("Learn Spring MVC");
    verify(todoService, Mockito.never()).deleteTodo("Learn Spring");
    verify(todoService, Mockito.times(1)).deleteTodo("Learn to Dance");
  }
  @Test
  public void captureArgument() {
    List<String> allTodos = Arrays.asList("Learn Spring MVC",  "Learn Spring", "Learn to Dance");
    Mockito.when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
    todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
    Mockito.verify(todoService).deleteTodo(stringArgumentCaptor.capture());
    assertEquals("Learn to Dance", stringArgumentCaptor.getValue());
  }
}
```


## Step 11 : JUnit Rules.
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step11.md 
* Using MockitoJUnit.rule() instead of @RunWith (MockitoJUnitRunner.class).
* TodoBusinessImplMockitoRulesTest.java
```java
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
public class TodoBusinessImplMockitoRulesTest {
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock
  TodoService todoService;
  @InjectMocks
  TodoBusinessImpl todoBusinessImpl;

  @Test
  public void usingMockito() {
    List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
    when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
    List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");
    assertEquals(2, todos.size());
  }
}
```

## Step 12 : Real world Example with Spring
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step12.md
* Code here: https://github.com/in28minutes/MockitoTutorialForBeginners/raw/master/mockito-real-world-example-with-spring.zip
* ClientBOMockitoTest.java
```java
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.in28minutes.example.layering.business.api.client.ClientBO;
import com.in28minutes.example.layering.business.impl.client.ClientBOImpl;
import com.in28minutes.example.layering.data.api.client.ClientDO;
import com.in28minutes.example.layering.data.api.client.ProductDO;
import com.in28minutes.example.layering.model.api.client.Amount;
import com.in28minutes.example.layering.model.api.client.Client;
import com.in28minutes.example.layering.model.api.client.Currency;
import com.in28minutes.example.layering.model.api.client.Product;
import com.in28minutes.example.layering.model.api.client.ProductType;
import com.in28minutes.example.layering.model.impl.client.AmountImpl;
import com.in28minutes.example.layering.model.impl.client.ClientImpl;
import com.in28minutes.example.layering.model.impl.client.ProductImpl;
@RunWith(MockitoJUnitRunner.class)
public class ClientBOMockitoTest {
  @Mock
  private ProductDO productDO;
  @Mock
  private ClientDO clientDO;
  @InjectMocks
  private ClientBO clientBO = new ClientBOImpl();
  @Captor
  ArgumentCaptor<Client> clientArgumentCaptured;
  private static final int DUMMY_CLIENT_ID = 1;
  @Test
  public void testClientProductSum() {
    List<Product> products = Arrays.asList(createProductWithAmount("5.0"), createProductWithAmount("6.0"));
    when(productDO.getAllProducts(anyInt())).thenReturn(products);
    assertAmountEquals(
          new AmountImpl(new BigDecimal("11.0"), Currency.EURO), clientBO
                .getClientProductsSum(DUMMY_CLIENT_ID));
  }
  private void assertAmountEquals(Amount expectedAmount, Amount actualAmount) {
    assertEquals(expectedAmount.getCurrency(), actualAmount.getCurrency());
    assertEquals(expectedAmount.getValue(), actualAmount.getValue());
  }
  private Product createProductWithAmount(String amount) {
    return new ProductImpl(100, "Product 15", ProductType.BANK_GUARANTEE,
          new AmountImpl(new BigDecimal(amount), Currency.EURO));
  }
  @Test
  public void saveChangedProducts_ProductInScreenAndNotInDatabase_ProductIsInserted() {
    List<Product> screenProducts = Arrays.asList(createProduct());
    List<Product> emptyDatabaseProducts = new ArrayList<Product>();
    stub(productDO.getAllProducts(anyInt())).toReturn(emptyDatabaseProducts);
    clientBO.saveChangedProducts(1, screenProducts);
    verify(productDO).insertProduct(1, screenProducts.get(0));
  }
  private Product createProduct() {
    return new ProductImpl(100, "Product 15", ProductType.BANK_GUARANTEE,
          new AmountImpl(new BigDecimal("5.0"), Currency.EURO));
  }
  @Test
  public void saveChangedProducts_ProductInScreenAndDatabase_IsUpdated() {
    Product screenProduct = createProductWithAmount("5.0");
    List<Product> databaseProducts = Arrays.asList(createProductWithAmount("6.0"));
    List<Product> screenProducts = Arrays.asList(screenProduct);
    stub(productDO.getAllProducts(anyInt())).toReturn(databaseProducts);
    clientBO.saveChangedProducts(1, screenProducts);
    verify(productDO).updateProduct(1, screenProduct);
  }
  @Test
  public void saveChangedProducts_ProductInDatabaseButNotInScreen_Deleted() {
    Product productFromDatabase = createProduct();
    List<Product> databaseProducts = Arrays.asList(productFromDatabase);
    List<Product> emptyScreenProducts = new ArrayList<Product>();
    stub(productDO.getAllProducts(anyInt())).toReturn(databaseProducts);
    clientBO.saveChangedProducts(1, emptyScreenProducts);
    verify(productDO).deleteProduct(1, productFromDatabase);
  }
  @Test
  public void calculateAndSaveClientProductSum1() {
    ClientImpl client = createClientWithProducts(createProductWithAmount("6.0"), createProductWithAmount("6.0"));
    clientBO.calculateAndSaveClientProductSum(client);
    verify(clientDO).saveClient(clientArgumentCaptured.capture());
    assertEquals(new BigDecimal("12.0"), clientArgumentCaptured.getValue().getProductAmount());
  }
  private ClientImpl createClientWithProducts(Product... products) {
    ClientImpl client = new ClientImpl(0, null, null, null, Arrays.asList(products));
    return client;
  }
}
```
## Step 13 : What is a spy? How to spy with Mockito?
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step13.md
* SpyTest.java
```java
package com.adiaz.data.api;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class SpyTest {

  @Test
  public void creatingASpyOnArrayList() {
     List<String> listSpy = spy(ArrayList.class);
     listSpy.add("Ranga");
     listSpy.add("in28Minutes");
     verify(listSpy).add("Ranga");
     verify(listSpy).add("in28Minutes");
     assertEquals(2, listSpy.size());
     assertEquals("Ranga", listSpy.get(0));
  }

  @Test
  public void creatingASpyOnArrayList_overridingSpecificMethods() {
     List<String> listSpy = spy(ArrayList.class);
     listSpy.add("Ranga");
     listSpy.add("in28Minutes");
     stub(listSpy.size()).toReturn(-1);
     assertEquals(-1, listSpy.size());
     assertEquals("Ranga", listSpy.get(0));
  }
}
```
## Step 14 : Some Theory : Why does Mockito not allow stubbing final and private methods?
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step14.md

## Step 15 : Using PowerMock and Mockito to mock a Static Method.
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step15.md
* pom.xml: add PowerMock dependencies
```xml
<dependency>
  <groupId>org.powermock</groupId>
  <artifactId>powermock-api-mockito</artifactId>
  <version>1.6.4</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>org.powermock</groupId>
  <artifactId>powermock-module-junit4</artifactId>
  <version>1.6.4</version>
  <scope>test</scope>
</dependency>
```
* Create UtilityClass.java
```java
public class UtilityClass {
  static int staticMethod(long value) {
     throw new RuntimeException("I dont want to be executed. I will anyway be mocked out.");
  }
}
```
* Create SystemUnderTest.java
```java
interface Dependency {
  List<Integer> retrieveAllStats();
}

public class SystemUnderTest {
  private Dependency dependency;

  public int methodUsingAnArrayListConstructor() {
     ArrayList list = new ArrayList();
     return list.size();
  }

  public int methodCallingAStaticMethod() {
     //privateMethodUnderTest calls static method SomeClass.staticMethod
     List<Integer> stats = dependency.retrieveAllStats();
     long sum = 0;
     for (int stat : stats)
        sum += stat;
     return UtilityClass.staticMethod(sum);
  }

  private long privateMethodUnderTest() {
     List<Integer> stats = dependency.retrieveAllStats();
     long sum = 0;
     for (int stat : stats)
        sum += stat;
     return sum;
  }
}
```
* MockingStaticMethodTest.java
```java
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
…

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
public class MockingStaticMethodTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  Dependency dependency;

  @InjectMocks
  SystemUnderTest systemUnderTest;

  @Test
  public void testStats() {
     List<Integer> stats = Arrays.asList(1, 2, 3);
     when(dependency.retrieveAllStats()).thenReturn(stats);
     PowerMockito.mockStatic(UtilityClass.class);
     when(UtilityClass.staticMethod(6)).thenReturn(150);
     int result = systemUnderTest.methodCallingAStaticMethod();
     assertEquals(150, result);
     PowerMockito.verifyStatic();
     UtilityClass.staticMethod(6);
  }
}
```
## Step 16 : Using PowerMock and Mockito to invoke a private Method.
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step16.md
* Test private method:
```java
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.reflect.Whitebox;

public class InvokingPrivateMethodTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  Dependency dependency;

  @InjectMocks
  SystemUnderTest systemUnderTest;

  @Test
  public void testPrivateMethod() throws Exception {
     List<Integer> stats = Arrays.asList(1,2,3);
     when(dependency.retrieveAllStats()).thenReturn(stats);
     long result = Whitebox.invokeMethod(systemUnderTest, "privateMethodUnderTest");
     assertEquals(6, result);
  }
}
```
## Step 17 : Using PowerMock and Mockito to mock a constructor.
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step17.md
* PowerMockitoMockingConstructorTest.java
```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.stub;

@RunWith(PowerMockRunner.class)
/*To be able to mock the Constructor, we need to add in the Class that creates the new object*/
@PrepareForTest({ SystemUnderTest.class})
public class PowerMockitoMockingConstructorTest {

  private static final int SOME_DUMMY_SIZE = 100;

  @Mock
  ArrayList mockList;

  @InjectMocks
  SystemUnderTest systemUnderTest;

  @Test
  public void powerMockito_MockingAConstructor() throws Exception {
     //ArrayList<String> mockList = mock(ArrayList.class);
     stub(mockList.size()).toReturn(SOME_DUMMY_SIZE);
     PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);
     int size = systemUnderTest.methodUsingAnArrayListConstructor();
     assertEquals(SOME_DUMMY_SIZE, size);
  }
}
```

## Step 18 : Good Unit Tests.
* https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step18.md
* Unit Tests Are FIRST: Fast, Isolated, Repeatable, Self-Verifying, and Timely
* Test methods names: 
> saveChangedProducts_productInScreenAndNotInDataBas
* Test methods structure: Given - When - Then
```java
@Test
public void usingMockito_UsingBDD() {
  List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
  //given
  given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);
  //when
  List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");
  //then
  assertThat(todos.size(), is(2));
}
```
* Test should fail only when there are real logic failures.
