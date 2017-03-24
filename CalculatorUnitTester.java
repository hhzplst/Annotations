import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)

@interface Initialize {}

@interface Before {}

@interface After {}

@interface Cleanup {}

@interface Test {}

public class CalculatorUnitTester {
  private Calculator c;
  private Double expected, result;

  @Initialize
  public void initialize() {
    c = new Calculator();
    System.out.println("Initializing...");
  }

  @Before
  public void setup() {
    expected = result = 0.0;
    System.out.println("Setting up the test...");
  }

  @After
  public void finish() {
    System.out.println("Finishing up the test...");
  }

  @Cleanup
  public void cleanup() {
    c = null;
    System.out.println("Cleaning up after all tests...");
  }

  @Test
  public void testAdd() {
    expected = 11.0;
    result = c.add(5.0, 6.0);
    if (ifPass())
      System.out.println("Test Add succeeded.");
    else
      System.out.println("Test Add failed."); 
  }

  @Test
  public void testSubtract() {
    expected = -1.0;
    result = c.subtract(5.0, 6.0);
    if (ifPass())
      System.out.println("Test Subtract succeeded.");
    else
      System.out.println("Test Subtract failed."); 
  }

  @Test
  public void testMultiply() {
    expected = 30.0;
    result = c.multiply(5.0, 6.0);
    if (ifPass())
      System.out.println("Test Multiply succeeded.");
    else
      System.out.println("Test Multiply failed."); 
  }

  @Test
  public void testDivide() {
    expected = 0.825;
    result = c.divide(5.0, 6.0);
    if (ifPass())
      System.out.println("Test Divide succeeded.");
    else
      System.out.println("Test Divide failed."); 
  }

  @Test
  public void testDivideException() {
    try {
      c.divide(5.0, 0.0);
      System.out.println("Test Divide Exception failed.");
    } catch(IllegalArgumentException e) {
      System.err.println(e);
      System.out.println("Test Divide Exception succeeded.");
    } catch(Exception e) {
      System.out.println("Test Divide Exception failed.");
    }
  }

  private boolean ifPass() {
    return Math.abs(expected - result) < 0.000000001;
  }
}