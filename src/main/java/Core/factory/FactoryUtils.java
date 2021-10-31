package Core.Factory;

import java.lang.reflect.Field;

public class FactoryUtils {
    public static void set(Field f,Object instance,Object value){
        Class<?> type =f.getType();
        f.setAccessible(true);
        try {
            if(type.equals(int.class)){
                f.setInt(instance,Integer.parseInt((String)value));
            }else if(type.equals(double.class)){
                f.setDouble(instance,Double.parseDouble((String) value));
            }else if(type.equals(float.class)){
                f.setFloat(instance,Float.parseFloat((String) value));
            }else if(type.equals(long.class)){
                f.setLong(instance,Long.parseLong((String) value));
            }else if(type.equals(short.class)){
                f.setShort(instance,Short.parseShort((String) value));
            }else if(type.equals(byte.class)){
                f.setByte(instance,Byte.parseByte((String) value));
            }else if(type.equals(boolean.class)){
                f.setBoolean(instance,Boolean.parseBoolean((String) value));
            }else{
                f.set(instance,value);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
