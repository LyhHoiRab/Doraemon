package org.wah.doraemon.utils;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.security.exception.UtilsException;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.ShardedJedis;

import java.lang.reflect.Type;
import java.util.*;

public class RedisUtils{

    private static final String NULL_STRING = "nil";

    public RedisUtils(){

    }

    private static void checkKey(Object key){
        if(key == null){
            throw new UtilsException("缓存名称不能为空");
        }

        if(key instanceof String && StringUtils.isBlank((String) key)){
            throw new UtilsException("缓存名称不能为空");
        }

        if(key instanceof Collection && ((Collection) key).isEmpty()){
            throw new UtilsException("缓存名称集合不能为空");
        }
    }

    private static void checkValue(Object value){
        if(value == null){
            throw new UtilsException("缓存对象不能为空");
        }

        if(value instanceof String && StringUtils.isBlank((String) value)){
            throw new UtilsException("缓存对象不能为空");
        }

        if(value instanceof Collection && ((Collection) value).isEmpty()){
            throw new UtilsException("缓存对象不能为空");
        }
    }

    private static void checkField(Object field){
        if(field == null){
            throw new UtilsException("缓存集合元素名称不能为空");
        }

        if(field instanceof String && StringUtils.isBlank((String) field)){
            throw new UtilsException("缓存集合元素名称不能为空");
        }

        if(field instanceof Collection && ((Collection) field).isEmpty()){
            throw new UtilsException("缓存集合元素名称不能为空");
        }
    }

    private static void checkJedis(ShardedJedis jedis){
        if(jedis == null){
            throw new UtilsException("Redis连接不能为空");
        }
    }

    private static boolean checkJson(Object json){
        if(json instanceof String){
            return !StringUtils.isBlank(json.toString()) && !NULL_STRING.equalsIgnoreCase(json.toString());
        }

        if(json instanceof Collection){
            Collection collection = (Collection) json;

            return !collection.isEmpty();
        }

        return json != null;
    }

    private static void close(ShardedJedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }

    //----------------- 基本(Object)操作 -----------------//
    public static <T> T get(ShardedJedis jedis, String key, Class<T> clazz){
        try{
            checkJedis(jedis);
            checkKey(key);

            String json = jedis.get(key);
            return checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> T get(ShardedJedis jedis, String key, Type type){
        try{
            checkJedis(jedis);
            checkKey(key);

            String json = jedis.get(key);
            return (T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void save(ShardedJedis jedis, String key, Object value){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkValue(value);

            jedis.set(key, ObjectUtils.serialize(value));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void save(ShardedJedis jedis, String key, Object value, int expire){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkValue(value);

            jedis.set(key, ObjectUtils.serialize(value));
            if(expire > -1){
                jedis.expire(key, expire);
            }
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void save(ShardedJedis jedis, String key, Object value, Date expire){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkValue(value);

            jedis.set(key, ObjectUtils.serialize(value));
            if(expire != null){
                jedis.expireAt(key, expire.getTime());
            }
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void delete(ShardedJedis jedis, String key){
        try{
            checkJedis(jedis);
            checkKey(key);

            jedis.del(key);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static boolean exists(ShardedJedis jedis, String key){
        try{
            checkJedis(jedis);
            checkKey(key);

            return jedis.exists(key);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void expire(ShardedJedis jedis, String key, int expire){
        try{
            checkJedis(jedis);
            checkKey(key);

            if(expire > -1){
                jedis.expire(key, expire);
            }
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void expireAt(ShardedJedis jedis, String key, Date expire){
        try{
            checkJedis(jedis);
            checkKey(key);

            if(expire != null){
                jedis.expireAt(key, expire.getTime());
            }
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    //----------------- 哈希表(Map)操作 -----------------//
    public static <T> T hget(ShardedJedis jedis, String key, String field, Class<T> clazz){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkField(field);

            String json = jedis.hget(key, field);
            return checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> T hget(ShardedJedis jedis, String key, String field, Type type){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkField(field);

            String json = jedis.hget(key, field);
            return (T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> List<T> hmget(ShardedJedis jedis, String key, List<String> fields, Class<T> clazz){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkField(fields);

            List<String> jsons = jedis.hmget(key, fields.toArray(new String[fields.size()]));

            if(checkJson(jsons)){
                List<T> target = new ArrayList<T>();

                Iterator<String> iterators = jsons.iterator();
                while(iterators.hasNext()){
                    String json = iterators.next();
                    target.add(checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null);
                }

                return target;
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> List<T> hmget(ShardedJedis jedis, String key, List<String> fields, Type type){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkField(fields);

            List<String> jsons = jedis.hmget(key, fields.toArray(new String[fields.size()]));

            if(checkJson(jsons)){
                List<T> target = new ArrayList<T>();

                Iterator<String> iterators = jsons.iterator();
                while(iterators.hasNext()){
                    String json = iterators.next();
                    target.add((T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null));
                }

                return target;
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> Map<String, T> hgetAll(ShardedJedis jedis, String key, Class<T> clazz){
        try{
            checkJedis(jedis);
            checkKey(key);

            Map<String, String> jsons = jedis.hgetAll(key);

            if(checkJson(jsons)){
                Map<String, T> target = new HashMap<String, T>();

                Iterator<Map.Entry<String, String>> iterators = jsons.entrySet().iterator();
                while(iterators.hasNext()){
                    Map.Entry<String, String> iterator = iterators.next();
                    String json = iterator.getValue();
                    target.put(iterator.getKey(), checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null);
                }

                return target;
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> Map<String, T> hgetAll(ShardedJedis jedis, String key, Type type){
        try{
            checkJedis(jedis);
            checkKey(key);

            Map<String, String> jsons = jedis.hgetAll(key);

            if(checkJson(jsons)){
                Map<String, T> target = new HashMap<String, T>();

                Iterator<Map.Entry<String, String>> iterators = jsons.entrySet().iterator();
                while(iterators.hasNext()){
                    Map.Entry<String, String> iterator = iterators.next();
                    String json = iterator.getValue();
                    target.put(iterator.getKey(), (T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null));
                }

                return target;
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static Set<String> hkeys(ShardedJedis jedis, String key){
        try{
            checkJedis(jedis);
            checkKey(key);

            return jedis.hkeys(key);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> List<T> hvalues(ShardedJedis jedis, String key, Class<T> clazz){
        try{
            checkJedis(jedis);
            checkKey(key);

            List<String> jsons = jedis.hvals(key);

            if(checkJson(jsons)){
                List<T> target = new ArrayList<T>();

                Iterator<String> iterators = jsons.iterator();
                while(iterators.hasNext()){
                    String json = iterators.next();
                    target.add(checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null);
                }

                return target;
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> List<T> hvalues(ShardedJedis jedis, String key, Type type){
        try{
            checkJedis(jedis);
            checkKey(key);

            List<String> jsons = jedis.hvals(key);

            if(checkJson(jsons)){
                List<T> target = new ArrayList<T>();

                Iterator<String> iterators = jsons.iterator();
                while(iterators.hasNext()){
                    String json = iterators.next();
                    target.add((T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null));
                }

                return target;
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void hmset(ShardedJedis jedis, String key, Map<String, Object> values){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkValue(values);

            Map<String, String> jsons = new HashMap<String, String>();

            Iterator<Map.Entry<String, Object>> iterators = values.entrySet().iterator();
            while(iterators.hasNext()){
                Map.Entry<String, Object> entry = iterators.next();
                Object json = entry.getValue();
                jsons.put(entry.getKey(), checkJson(json) ? ObjectUtils.serialize(json) : null);
            }

            jedis.hmset(key, jsons);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void hset(ShardedJedis jedis, String key, String field, Object value){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkField(field);
            checkValue(value);

            jedis.hset(key, field, ObjectUtils.serialize(value));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void hsetnx(ShardedJedis jedis, String key, String field, Object value){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkField(field);
            checkValue(value);

            jedis.hsetnx(key, field, ObjectUtils.serialize(value));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void hdel(ShardedJedis jedis, String key, List<String> fields){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkField(fields);

            jedis.hdel(key, fields.toArray(new String[fields.size()]));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static boolean hexists(ShardedJedis jedis, String key, String field){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkField(field);

            return jedis.hexists(key, field);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static long hlen(ShardedJedis jedis, String key){
        try{
            checkJedis(jedis);
            checkKey(key);

            return jedis.hlen(key);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    //----------------- 队列(List)操作 -----------------//
    public static <T> T lindex(ShardedJedis jedis, String key, long index, Class<T> clazz){
        try{
            checkJedis(jedis);
            checkKey(key);

            String json = jedis.lindex(key, index);

            return checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> T lindex(ShardedJedis jedis, String key, long index, Type type){
        try{
            checkJedis(jedis);
            checkKey(key);

            String json = jedis.lindex(key, index);

            return (T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> List<T> lall(ShardedJedis jedis, String key, Class<T> clazz){
        try{
            checkJedis(jedis);
            checkKey(key);

            long length = jedis.llen(key);

            if(length > 0){
                List<String> jsons = jedis.lrange(key, 0, length);

                if(checkJson(jsons)){
                    List<T> target = new ArrayList<T>();

                    Iterator<String> iterators = jsons.iterator();
                    while(iterators.hasNext()){
                        String json = iterators.next();
                        target.add(checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null);
                    }

                    return target;
                }
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> List<T> lall(ShardedJedis jedis, String key, Type type){
        try{
            checkJedis(jedis);
            checkKey(key);

            long length = jedis.llen(key);

            if(length > 0){
                List<String> jsons = jedis.lrange(key, 0, length);

                if(checkJson(jsons)){
                    List<T> target = new ArrayList<T>();

                    Iterator<String> iterators = jsons.iterator();
                    while(iterators.hasNext()){
                        String json = iterators.next();
                        target.add((T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null));
                    }

                    return target;
                }
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> List<T> lrange(ShardedJedis jedis, String key, long start, long end, Class<T> clazz){
        try{
            checkJedis(jedis);
            checkKey(key);

            if(start > -1 && end > start){
                List<String> jsons = jedis.lrange(key, start, end);

                if(checkJson(jsons)){
                    List<T> target = new ArrayList<T>();

                    Iterator<String> iterators = jsons.iterator();
                    while(iterators.hasNext()){
                        String json = iterators.next();
                        target.add(checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null);
                    }

                    return target;
                }
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> List<T> lrange(ShardedJedis jedis, String key, long start, long end, Type type){
        try{
            checkJedis(jedis);
            checkKey(key);

            if(start > -1 && end > start){
                List<String> jsons = jedis.lrange(key, start, end);

                if(checkJson(jsons)){
                    List<T> target = new ArrayList<T>();

                    Iterator<String> iterators = jsons.iterator();
                    while(iterators.hasNext()){
                        String json = iterators.next();
                        target.add((T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null));
                    }

                    return target;
                }
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> T lpop(ShardedJedis jedis, String key, Class<T> clazz){
        try{
            checkJedis(jedis);
            checkKey(key);

            String json = jedis.lpop(key);

            return checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> T lpop(ShardedJedis jedis, String key, Type type){
        try{
            checkJedis(jedis);
            checkKey(key);

            String json = jedis.lpop(key);

            return (T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> T rpop(ShardedJedis jedis, String key, Class<T> clazz){
        try{
            checkJedis(jedis);
            checkKey(key);

            String json = jedis.rpop(key);

            return checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> T rpop(ShardedJedis jedis, String key, Type type){
        try{
            checkJedis(jedis);
            checkKey(key);

            String json = jedis.rpop(key);

            return (T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void lset(ShardedJedis jedis, String key, long index, Object value){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkValue(value);

            jedis.lset(key, index, ObjectUtils.serialize(value));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void linsert(ShardedJedis jedis, String key, BinaryClient.LIST_POSITION position, Object exist, Object insert){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkValue(exist);
            checkValue(insert);

            position = position == null ? BinaryClient.LIST_POSITION.BEFORE : position;
            jedis.linsert(key, position, ObjectUtils.serialize(exist), ObjectUtils.serialize(insert));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void lpush(ShardedJedis jedis, String key, Object value){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkValue(value);

            jedis.lpush(key, ObjectUtils.serialize(value));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void lpush(ShardedJedis jedis, String key, List<Object> values){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkValue(values);

            List<String> jsons = new ArrayList<String>();

            Iterator<Object> iterators = values.iterator();
            while(iterators.hasNext()){
                Object object = iterators.next();
                String json = object != null ? ObjectUtils.serialize(object) : null;
                jsons.add(json);
            }

            jedis.lpush(key, jsons.toArray(new String[jsons.size()]));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void rpush(ShardedJedis jedis, String key, Object value){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkValue(value);

            jedis.rpush(key, ObjectUtils.serialize(value));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void rpush(ShardedJedis jedis, String key, List<Object> values){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkValue(values);

            List<String> jsons = new ArrayList<String>();

            Iterator<Object> iterators = values.iterator();
            while(iterators.hasNext()){
                Object object = iterators.next();
                String json = object != null ? ObjectUtils.serialize(object) : null;
                jsons.add(json);
            }

            jedis.rpush(key, jsons.toArray(new String[jsons.size()]));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void lrem(ShardedJedis jedis, String key, long count, Object value){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkValue(value);

            jedis.lrem(key, count, ObjectUtils.serialize(value));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static long llen(ShardedJedis jedis, String key){
        try{
            checkJedis(jedis);
            checkKey(key);

            return jedis.llen(key);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    //----------------- 集合(Set)操作 -----------------//
    public static <T> Set<T> smembers(ShardedJedis jedis, String key, Class<T> clazz){
        try{
            checkJedis(jedis);
            checkKey(key);

            Set<String> jsons = jedis.smembers(key);

            if(checkJson(jsons)){
                Set<T> target = new HashSet<T>();

                Iterator<String> iterators = jsons.iterator();
                while(iterators.hasNext()){
                    String json = iterators.next();
                    target.add(checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null);
                }

                return target;
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> Set<T> smembers(ShardedJedis jedis, String key, Type type){
        try{
            checkJedis(jedis);
            checkKey(key);

            Set<String> jsons = jedis.smembers(key);

            if(checkJson(jsons)){
                Set<T> target = new HashSet<T>();

                Iterator<String> iterators = jsons.iterator();
                while(iterators.hasNext()){
                    String json = iterators.next();
                    target.add((T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null));
                }

                return target;
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> T srandmember(ShardedJedis jedis, String key, Class<T> clazz){
        try{
            checkJedis(jedis);
            checkKey(key);

            String json = jedis.srandmember(key);

            return checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> T srandmember(ShardedJedis jedis, String key, Type type){
        try{
            checkJedis(jedis);
            checkKey(key);

            String json = jedis.srandmember(key);

            return (T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> Set<T> srandmember(ShardedJedis jedis, String key, int count, Class<T> clazz){
        try{
            checkJedis(jedis);
            checkKey(key);

            List<String> jsons = jedis.srandmember(key, count);

            if(checkJson(jsons)){
                Set<T> target = new HashSet<T>();

                Iterator<String> iterators = jsons.iterator();
                while(iterators.hasNext()){
                    String json = iterators.next();

                    target.add(checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null);
                }

                return target;
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> Set<T> srandmember(ShardedJedis jedis, String key, int count, Type type){
        try{
            checkJedis(jedis);
            checkKey(key);

            List<String> jsons = jedis.srandmember(key, count);

            if(checkJson(jsons)){
                Set<T> target = new HashSet<T>();

                Iterator<String> iterators = jsons.iterator();
                while(iterators.hasNext()){
                    String json = iterators.next();

                    target.add((T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null));
                }

                return target;
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> T spop(ShardedJedis jedis, String key, Class<T> clazz){
        try{
            checkJedis(jedis);
            checkKey(key);

            String json = jedis.spop(key);

            return checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> T spop(ShardedJedis jedis, String key, Type type){
        try{
            checkJedis(jedis);
            checkKey(key);

            String json = jedis.spop(key);

            return (T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> Set<T> sunion(ShardedJedis jedis, String key1, String key2, Class<T> clazz){
        try{
            checkJedis(jedis);

            checkKey(key1);
            Set<String> set1 = jedis.smembers(key1);

            checkKey(key2);
            Set<String> set2 = jedis.smembers(key2);

            if(checkJson(set1) && checkJson(set2)){
                Set<String> jsons = Sets.union(set1, set2);

                if(checkJson(jsons)){
                    Set<T> target = new HashSet<T>();

                    Iterator<String> iterators = jsons.iterator();
                    while(iterators.hasNext()){
                        String json = iterators.next();
                        target.add(checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null);
                    }

                    return target;
                }
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> Set<T> sunion(ShardedJedis jedis, String key1, String key2, Type type){
        try{
            checkJedis(jedis);

            checkKey(key1);
            Set<String> set1 = jedis.smembers(key1);

            checkKey(key2);
            Set<String> set2 = jedis.smembers(key2);

            if(checkJson(set1) && checkJson(set2)){
                Set<String> jsons = Sets.union(set1, set2);

                if(checkJson(jsons)){
                    Set<T> target = new HashSet<T>();

                    Iterator<String> iterators = jsons.iterator();
                    while(iterators.hasNext()){
                        String json = iterators.next();
                        target.add((T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null));
                    }

                    return target;
                }
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> Set<T> sdiff(ShardedJedis jedis, String key1, String key2, Class<T> clazz){
        try{
            checkJedis(jedis);

            checkKey(key1);
            Set<String> set1 = jedis.smembers(key1);

            checkKey(key2);
            Set<String> set2 = jedis.smembers(key2);

            if(checkJson(set1) && checkJson(set2)){
                Set<String> jsons = Sets.difference(Sets.union(set1, set2), Sets.intersection(set1, set2));

                if(checkJson(jsons)){
                    Set<T> target = new HashSet<T>();

                    Iterator<String> iterators = jsons.iterator();
                    while(iterators.hasNext()){
                        String json = iterators.next();
                        target.add(checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null);
                    }

                    return target;
                }
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> Set<T> sdiff(ShardedJedis jedis, String key1, String key2, Type type){
        try{
            checkJedis(jedis);

            checkKey(key1);
            Set<String> set1 = jedis.smembers(key1);

            checkKey(key2);
            Set<String> set2 = jedis.smembers(key2);

            if(checkJson(set1) && checkJson(set2)){
                Set<String> jsons = Sets.difference(Sets.union(set1, set2), Sets.intersection(set1, set2));

                if(checkJson(jsons)){
                    Set<T> target = new HashSet<T>();

                    Iterator<String> iterators = jsons.iterator();
                    while(iterators.hasNext()){
                        String json = iterators.next();
                        target.add((T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null));
                    }

                    return target;
                }
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> Set<T> sinter(ShardedJedis jedis, String key1, String key2, Class<T> clazz){
        try{
            checkJedis(jedis);

            checkKey(key1);
            Set<String> set1 = jedis.smembers(key1);

            checkKey(key2);
            Set<String> set2 = jedis.smembers(key2);

            if(checkJson(set1) && checkJson(set2)){
                Set<String> jsons = Sets.intersection(Sets.union(set1, set2), Sets.intersection(set1, set2));

                if(checkJson(jsons)){
                    Set<T> target = new HashSet<T>();

                    Iterator<String> iterators = jsons.iterator();
                    while(iterators.hasNext()){
                        String json = iterators.next();
                        target.add(checkJson(json) ? ObjectUtils.deserialize(json, clazz) : null);
                    }

                    return target;
                }
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static <T> Set<T> sinter(ShardedJedis jedis, String key1, String key2, Type type){
        try{
            checkJedis(jedis);

            checkKey(key1);
            Set<String> set1 = jedis.smembers(key1);

            checkKey(key2);
            Set<String> set2 = jedis.smembers(key2);

            if(checkJson(set1) && checkJson(set2)){
                Set<String> jsons = Sets.intersection(Sets.union(set1, set2), Sets.intersection(set1, set2));

                if(checkJson(jsons)){
                    Set<T> target = new HashSet<T>();

                    Iterator<String> iterators = jsons.iterator();
                    while(iterators.hasNext()){
                        String json = iterators.next();
                        target.add((T) (checkJson(json) ? ObjectUtils.deserialize(json, type) : null));
                    }

                    return target;
                }
            }

            return null;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void sadd(ShardedJedis jedis, String key, Object value){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkValue(value);

            jedis.sadd(key, ObjectUtils.serialize(value));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void sadd(ShardedJedis jedis, String key, List<Object> value){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkValue(value);

            List<String> jsons = new ArrayList<String>();

            Iterator<Object> iterators = value.iterator();
            while(iterators.hasNext()){
                jsons.add(ObjectUtils.serialize(iterators.next()));
            }

            jedis.sadd(key, jsons.toArray(new String[jsons.size()]));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static boolean sismemeber(ShardedJedis jedis, String key, Object value){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkValue(value);

            return jedis.sismember(key, ObjectUtils.serialize(value));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static void srem(ShardedJedis jedis, String key, Object value){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkValue(value);

            jedis.srem(key, ObjectUtils.serialize(value));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static  void srem(ShardedJedis jedis, String key, List<Object> value){
        try{
            checkJedis(jedis);
            checkKey(key);
            checkValue(value);

            List<String> jsons = new ArrayList<String>();

            Iterator<Object> iterators = value.iterator();
            while(iterators.hasNext()){
                jsons.add(ObjectUtils.serialize(iterators.next()));
            }

            jedis.srem(key, jsons.toArray(new String[jsons.size()]));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    public static long scard(ShardedJedis jedis, String key){
        try{
            checkJedis(jedis);
            checkKey(key);

            return jedis.scard(key);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(jedis);
        }
    }

    //----------------- 有序集合(Sorted Set)操作 -----------------//
}
