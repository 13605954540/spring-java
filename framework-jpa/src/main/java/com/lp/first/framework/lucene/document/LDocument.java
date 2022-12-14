package com.lp.first.framework.lucene.document;

import com.google.common.collect.Maps;
import com.lp.first.framework.util.MapUtil;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.util.BytesRef;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.*;

public class LDocument<T> implements Iterable<IndexableField> {

    private static final Logger log = LoggerFactory.getLogger(LDocument.class);

    private final List<IndexableField> fields = Lists.newArrayList();

    private static final String[] NO_STRINGS = new String[0];

    /**
     * 实体参数转换为document类型
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T>LDocument toDoc(T t) {
        Class cla = t.getClass();
        java.lang.reflect.Field[] fields = cla.getDeclaredFields();
        LDocument lDocument = new LDocument();
        try {
            for(int i = 0; i < fields.length; i++) {
                java.lang.reflect.Field field = fields[i];
                if(field.getName().equals("serialVersionUID")) {
                    break;
                }
                field.setAccessible(true);
                lDocument.put(field.getName(), field.get(t));
            }
            //判断是否有父类,有则递归调用此方法
            if(cla.getSuperclass() != null && !cla.getSuperclass().equals(Object.class)) {
                toDoc(cla.getSuperclass());
            }
        } catch (IllegalAccessException e) {
            log.error("参数转换错误,t = {}", t);
            e.printStackTrace();
        }
        return lDocument;
    }

    public static <T> T toObj(LDocument doc, Class cla) {
        Iterator<IndexableField> it = doc.iterator();
        Map<String, Object> map = Maps.newHashMap();
        while(it.hasNext()) {
            IndexableField field = it.next();
            Object value = null;
            if(isInClasses(field, TextField.class, StringField.class)) {
                value = field.stringValue();
            }
            if(isInClasses(field, IntPoint.class, LongPoint.class)) {
                value = field.numericValue();
            }
            if(value == null || value.getClass().equals(StoredField.class)) {
                continue;
            }
            if (isInClasses(field.getClass(), IntPoint.class, LongPoint.class)) {
                try {
                    java.lang.reflect.Field[] arrs = cla.getDeclaredFields();
                    boolean isHave = Boolean.FALSE;
                    for(int i = 0; i < arrs.length; i++) {
                        if(arrs[i].getName().equals(field.name())) {
                            isHave = true;
                            break;
                        }
                    }
                    if(isHave) {
                        java.lang.reflect.Field field1 = cla.getDeclaredField(field.name());
                        Class c = field1.getDeclaringClass();
                        if (c.equals(Date.class)) {
                            value = new Date(field.numericValue().longValue());
                        } else if (c.equals(Boolean.class)) {
                            long v = field.numericValue().longValue();
                            value = v == 1L ? Boolean.TRUE : (v == 0L ? Boolean.FALSE : null);
                        }
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
            map.put(field.name(), value);
        }

        T t = (T) MapUtil.mapToBean(map, cla);
        return t;
    }

    /**
     * 添加数据(根据val判断添加的field类型)
     *
     * @param key
     * @param val
     * @return
     */
    public LDocument put(String key, Object val) {
        if(val != null) {
            if (val instanceof Integer || val instanceof Short ||val instanceof Long) {
                this.add(new LongPoint(key, (Long) val));
            } else if (val instanceof String) {
                this.add(new TextField(key, (String) val, Field.Store.YES));
            } else if (val instanceof Date) {
                this.add(new LongPoint(key, ((Date) val).getTime()));
            } else if (val instanceof Double) {
                this.add(new DoublePoint(key, (Double) val));
            } else if (val instanceof BigInteger) {
                this.add(new BigIntegerPoint(key, (BigInteger) val));
            } else if (val instanceof Boolean) {
                this.add(new IntPoint(key, Boolean.TRUE.equals(val) ? 1 : 0));
                this.add(new StoredField(key, Boolean.TRUE.equals(val) ? 1 : 0));
            } else {
                this.add(new StringField(key, (String) val, Field.Store.YES));
            }
        }
        return this;
    }

    public Iterator<IndexableField> iterator() {
        return this.fields.iterator();
    }

    public final void removeField(String name) {
        Iterator it = this.fields.iterator();

        IndexableField field;
        do {
            if (!it.hasNext()) {
                return;
            }

            field = (IndexableField)it.next();
        } while(!field.name().equals(name));

        it.remove();
    }

    public final void removeFields(String name) {
        Iterator it = this.fields.iterator();

        while(it.hasNext()) {
            IndexableField field = (IndexableField)it.next();
            if (field.name().equals(name)) {
                it.remove();
            }
        }

    }

    public final BytesRef[] getBinaryValues(String name) {
        List<BytesRef> result = new ArrayList();
        Iterator var3 = this.fields.iterator();

        while(var3.hasNext()) {
            IndexableField field = (IndexableField)var3.next();
            if (field.name().equals(name)) {
                BytesRef bytes = field.binaryValue();
                if (bytes != null) {
                    result.add(bytes);
                }
            }
        }
        return (BytesRef[])result.toArray(new BytesRef[result.size()]);
    }

    public final BytesRef getBinaryValue(String name) {
        Iterator var2 = this.fields.iterator();

        while(var2.hasNext()) {
            IndexableField field = (IndexableField)var2.next();
            if (field.name().equals(name)) {
                BytesRef bytes = field.binaryValue();
                if (bytes != null) {
                    return bytes;
                }
            }
        }

        return null;
    }

    public final IndexableField getField(String name) {
        Iterator var2 = this.fields.iterator();

        IndexableField field;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            field = (IndexableField)var2.next();
        } while(!field.name().equals(name));

        return field;
    }

    public IndexableField[] getFields(String name) {
        List<IndexableField> result = new ArrayList();
        Iterator var3 = this.fields.iterator();

        while(var3.hasNext()) {
            IndexableField field = (IndexableField)var3.next();
            if (field.name().equals(name)) {
                result.add(field);
            }
        }

        return (IndexableField[])result.toArray(new IndexableField[result.size()]);
    }

    public final List<IndexableField> getFields() {
        return Collections.unmodifiableList(this.fields);
    }

    public final String[] getValues(String name) {
        List<String> result = new ArrayList();
        Iterator var3 = this.fields.iterator();

        while(var3.hasNext()) {
            IndexableField field = (IndexableField)var3.next();
            if (field.name().equals(name) && field.stringValue() != null) {
                result.add(field.stringValue());
            }
        }

        if (result.size() == 0) {
            return NO_STRINGS;
        } else {
            return (String[])result.toArray(new String[result.size()]);
        }
    }

    public final String get(String name) {
        Iterator var2 = this.fields.iterator();

        IndexableField field;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            field = (IndexableField)var2.next();
        } while(!field.name().equals(name) || field.stringValue() == null);

        return field.stringValue();
    }

    public final String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Document<");

        for(int i = 0; i < this.fields.size(); ++i) {
            IndexableField field = (IndexableField)this.fields.get(i);
            buffer.append(field.toString());
            if (i != this.fields.size() - 1) {
                buffer.append(" ");
            }
        }

        buffer.append(">");
        return buffer.toString();
    }

    public void clear() {
        this.fields.clear();
    }

    public final void add(IndexableField field) {
        this.fields.add(field);
    }

    private static boolean isInClasses(Object obj, Class...clas) {
        boolean isTrue = false;
        for(int i = 0; i < clas.length; i++) {
            if(obj.getClass().equals(clas[i])) {
                isTrue = true;
                break;
            }
        }
        return isTrue;
    }
}
