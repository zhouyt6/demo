package util;

import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.util.Collection;

/**
 * Description: Dto与Entity相互转换工具类
 * User: weishenpeng
 * Date: 2017/6/16
 * Time: 下午 04:22
 */
public class CopyUtil {
    /**
     * Copy properties of orig to dest Exception the Entity and Collection Type
     *
     * @param dest
     * @param orig
     * @return the dest bean
     */
    public static Object copyProperties(Object dest, Object orig) {
        if (dest == null || orig == null) {
            return dest;
        }

        PropertyDescriptor[] destDesc = PropertyUtils.getPropertyDescriptors(dest);
        try {
            for (int i = 0; i < destDesc.length; i++) {
                Class destType = destDesc[i].getPropertyType();
                if(containsProperty(orig, destDesc[i].getName())) {
                    Class origType = PropertyUtils.getPropertyType(orig, destDesc[i].getName());
                    if (destType != null && destType.equals(origType) && !destType.equals(Class.class)) {
                        if (!Collection.class.isAssignableFrom(origType)) {
                            Object value = PropertyUtils.getProperty(orig, destDesc[i].getName());
                            PropertyUtils.setProperty(dest, destDesc[i].getName(), value);
                        }
                    }
                }
            }

            return dest;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Copy properties of orig to dest Exception the Entity and Collection Type
     *
     * @param dest
     * @param orig
     * @param ignores
     * @return the dest bean
     */
    public static Object copyProperties(Object dest, Object orig, String[] ignores) {
        if (dest == null || orig == null) {
            return dest;
        }

        PropertyDescriptor[] destDesc = PropertyUtils.getPropertyDescriptors(dest);
        try {
            for (int i = 0; i < destDesc.length; i++) {
                if (contains(ignores, destDesc[i].getName())) {
                    continue;
                }

                Class destType = destDesc[i].getPropertyType();
                if(containsProperty(orig, destDesc[i].getName() + "")) {
                    Class origType = PropertyUtils.getPropertyType(orig, destDesc[i].getName());
                    if (destType != null && destType.equals(origType) && !destType.equals(Class.class)) {
                        if (!Collection.class.isAssignableFrom(origType)) {
                            Object value = PropertyUtils.getProperty(orig, destDesc[i].getName());
                            PropertyUtils.setProperty(dest, destDesc[i].getName(), value);
                        }
                    }
                }
            }

            return dest;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    static boolean contains(String[] ignores, String name) {
        boolean ignored = false;
        for (int j = 0; ignores != null && j < ignores.length; j++) {
            if (ignores[j].equals(name)) {
                ignored = true;
                break;
            }
        }

        return ignored;
    }

    /**
     * Copy properties of orig to dest Exception the Entity and Collection Type
     * 进行Byte和Integer类型转换
     *
     * @param dest
     * @param orig
     * @return the dest bean
     */
    public static Object copyPropertiesIgnoresBI(Object dest, Object orig) {
        if (dest == null || orig == null) {
            return dest;
        }

        PropertyDescriptor[] destDesc = PropertyUtils.getPropertyDescriptors(dest);
        try {
            for (int i = 0; i < destDesc.length; i++) {
                Class destType = destDesc[i].getPropertyType();
                if(containsProperty(orig, destDesc[i].getName())){
                    Class origType = PropertyUtils.getPropertyType(orig, destDesc[i].getName());
                    if (destType != null && !destType.equals(Class.class)) {
                        if(!Collection.class.isAssignableFrom(origType)){

                            if(destType.equals(origType)) {
                                Object value = PropertyUtils.getProperty(orig, destDesc[i].getName());
                                PropertyUtils.setProperty(dest, destDesc[i].getName(), value);
                            }else{
                                if(destType.equals(Byte.class) && origType.equals(Integer.class)){
                                    Object value = PropertyUtils.getProperty(orig, destDesc[i].getName());
                                    PropertyUtils.setProperty(dest, destDesc[i].getName(), value != null? Byte.parseByte(value + "") : null);
                                }else if(destType.equals(Integer.class) && origType.equals(Byte.class)){
                                    Object value = PropertyUtils.getProperty(orig, destDesc[i].getName());
                                    PropertyUtils.setProperty(dest, destDesc[i].getName(), value != null? Integer.parseInt(value + "") : null);
                                }
                            }
                        }
                    }
                }
            }

            return dest;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Object 是否包含property属性
     * @param object
     * @param property
     * @return
     */
    static boolean containsProperty(Object object, String property) {
        if (object == null || property == null || property == "") {
            return false;
        }
        try{
            PropertyDescriptor[] objDesc = PropertyUtils.getPropertyDescriptors(object);
            for (int i = 0; i < objDesc.length; i++) {
                if(property.equals(objDesc[i].getName())){
                    return true;
                }
            }
        }catch (Exception e){ }

        return false;
    }
}
