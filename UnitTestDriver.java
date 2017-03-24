import java.lang.reflect.*;

public class UnitTestDriver {

  public static void main(String[] args) {
    CalculatorUnitTester cut = new CalculatorUnitTester();
    Class<?> type = cut.getClass();

    for (Method m : type.getDeclaredMethods()) {
      if (m.isAnnotationPresent(Initialize.class)) {
        try {
          m.invoke(cut);
        } catch(IllegalAccessException e) {
          System.err.println(e);
        } catch(InvocationTargetException o) {
          System.err.println(o);
        }  
      }
    }

    Method before = null;
    Method after = null;

    try {
      before = type.getMethod("setup"); 
      after = type.getMethod("finish");
    } catch (NoSuchMethodException e) {
      System.err.println(e);
    }

    for (Method m : type.getDeclaredMethods()) {
      if (m.isAnnotationPresent(Test.class)) {
        try {
          before.invoke(cut);
          m.invoke(cut);
          after.invoke(cut);
        } catch(IllegalAccessException e) {
          System.err.println(e);
        } catch(InvocationTargetException o) {
          System.err.println(o);
        }  
      }
    }

    for (Method m : type.getDeclaredMethods()) {
      if (m.isAnnotationPresent(Cleanup.class)) {
        try {
          m.invoke(cut);
        } catch(IllegalAccessException e) {
          System.err.println(e);
        } catch(InvocationTargetException o) {
          System.err.println(o);
        }  
      }
    }    
  }
}