import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//2. Check order of test Execution/TestMethodOrder @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class My_A00JunitStructureTests {
    // Annotations
    // 1. Three Testcase
    // 3. BeforeEach/BeforeAll/AfterEach/AfterAll
    // 4. Disabled
    // 5. Assertions with own error message
    // 6. mvn test / mvn surefire-report:report

    @BeforeAll
    public static void beforeAll() { System.out.println("Before All"); }

    @BeforeEach
    public void beforeEach() {System.out.println("Before Each"); }

    @AfterEach
    public void afterEach() {System.out.println("After Each"); }

    @AfterAll
    public static void afterAll() { System.out.println("After All"); }

    @Test
    @Order(1)
    public void structureFirst() {
        System.out.println("In test First execution");
        Assertions.assertTrue(true, "Own error message!!");

    }

    @Test
    @Order(2)
    @Disabled//Fix it later TASKID
    public void structureSecond() {
        System.out.println("In test Second execution");
    }

    @Test
    @Order(3)
    public void structureThird() {
        System.out.println("In test Third execution");
    }

    @Test
    @Order(4)
    public void structureFourth() {
        System.out.println("In test Fourth execution");
    }
}