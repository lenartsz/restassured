import org.junit.jupiter.api.*;

import static org.junit.Assert.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//2. Check order of test Execution/TestMethodOrder
public class A01JunitStructureTests {
    // Annotations
    // 1. Three Testcase
    // 3. BeforeEach/BeforeAll/AfterEach/AfterAll
    // 4. Disabled
    // 5. Assertions with own error message
    // 6. mvn test / mvn surefire-report:report

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before All");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After All");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Test IS DONE");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Test IS Running");
    }

    @Order(1)
    //First Test case
    @Test
    public void structureFirst() {
        System.out.println("In test First execution");
        Assertions.assertTrue(false, "True is expected, but false arrived");
    }
    @Order(2)
    @Disabled
    @Test
    public void structureSecond() {
        System.out.println("In test Second execution");
    }

    @Test
    public void structureThird() {
        System.out.println("In test Third execution");
    }
}