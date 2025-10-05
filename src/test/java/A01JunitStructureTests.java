import org.junit.jupiter.api.*;

import static org.junit.Assert.assertTrue;

//2. Check order of test Execution/TestMethodOrder
public class A01JunitStructureTests {
    // Annotations
    // 1. Three Testcase
    // 3. BeforeEach/BeforeAll/AfterEach/AfterAll
    // 4. Disabled
    // 5. Assertions with own error message
    // 6. mvn test / mvn surefire-report:report


    //First Test case
    @Test
    public void structureFirst() {
        System.out.println("In test First execution");
    }
}