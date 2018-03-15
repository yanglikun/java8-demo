import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;
import java.util.List;

class Test {

    public static void test(List<String> list) {

    }

    public static void main(String[] args) throws NoSuchMethodException {
        for (Method method : Test.class.getMethods()) {
            if (!method.getName().equals("test")) {
                continue;
            }
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            ParameterizedType genericParameterType = (ParameterizedType) genericParameterTypes[0];
            Type[] actualTypeArguments = genericParameterType.getActualTypeArguments();
            System.out.println(Arrays.toString(actualTypeArguments));
        }

    }

}
