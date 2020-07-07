package com.kz.practice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description Redis的工具类
 * @Author KatieZ
 * @Date Created in 18:23  18:23
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置过期时间
     * @param key key值
     * @param seconds 缓存失效时间 秒为单位
     * @return 是否设置成功
     */
    public boolean setExpire(String key,long seconds){
        try {
            return redisTemplate.expire(key,seconds, TimeUnit.SECONDS);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 返回过期时间
     * 返回结果以秒为单位
     * 返回值为 0 即永久有效
     * @param key
     * @return
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 清除对应缓存
     * @param key
     */
    public void del(String... key){
        if(null != key && key.length > 0){
            if(key.length == 1){
                redisTemplate.delete(key[0]);
            }
            redisTemplate.delete(CollectionUtils.arrayToList(key));
        }
    }

    // ======================================= String ========================================


    /**
     * 根据key值获取对应的value
     * @param key
     * @return
     */
    public Object get(String key){
        return key == null?null:redisTemplate.opsForValue().get(key);
    }

    /**
     * 插入数据
     * @param key
     * @param value
     */
    public boolean set(String key,Object value){
        try{
            redisTemplate.boundValueOps(key).set(value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 插入数据携带过期时间
     * @param key
     * @param value
     * @param seconds 过期时间 秒为单位
     */
    public boolean set(String key,Object value,long seconds){
        try{
            if(seconds > 0){
                redisTemplate.boundValueOps(key).set(value,seconds,TimeUnit.SECONDS);
            }else{
                set(key,value);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增数据
     * @param key
     * @param incrta 增加数（> 0）
     * @return
     */
    public long incr(String key,long incrta){
        if(incrta < 0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,incrta);
    }

    /**
     * 递减数据
     * @param key
     * @param decrta 减少数（>0）
     * @return
     */
    public long decr(String key,long decrta){
        if(decrta < 0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(key,-decrta);
    }

    // ========================================== Map ===================================

    /**
     * HashGet
     * @param key 键值不能为null
     * @param item 项不能为null
     * @return
     */
    public Object hget(String key,String item){
        return redisTemplate.opsForHash().get(key,item);
    }

    /**
     * 获取hash所有键值对信息
     * @param key
     * @return
     */
    public Map<Object,Object> hgetall(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 以item相同的顺序从缓存中返回对象list
     * 空将生成 {item,null} list
     * @param key
     * @param items
     * @return
     */
    public List<Object> hmget(String key, List<Object> items){
        return redisTemplate.opsForHash().multiGet(key,items);
    }

    /**
     * 向hash表中插入数据 不存在将创建
     * @param key
     * @param item
     * @param value
     * @return
     */
    public boolean hset(String key,String item,Object value){
        try{
            redisTemplate.opsForHash().put(key,item,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向hash表中插入数据 不存在将创建
     * @param key
     * @param item
     * @param value
     * @param time 如果原hash表有时间 将会替换原时间
     * @return
     */
    public boolean hset(String key,String item,Object value,long time){
        try{
            redisTemplate.opsForHash().put(key,item,value);
            if(time > 0){
                setExpire(key,time);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 插入多个键值
     * @param key
     * @param map
     * @return
     */
    public boolean hmset(String key,Map<String,Object> map){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 插入多个键值 并设置过期时间
     * @param key
     * @param map
     * @param time
     * @return
     */
    public boolean hmset(String key,Map<String,Object> map,long time){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            if(time > 0){
                setExpire(key,time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * @param key 不能为null
     * @param item 可以是多个 不能为null
     */
    public void hdel(String key,Object... item){
        redisTemplate.opsForHash().delete(key,item);
    }

    /**
     * 判断hash中是有该项的值
     * @param key
     * @param item
     * @return
     */
    public boolean hHasKey(String key,String item){
        return redisTemplate.opsForHash().hasKey(key,item);
    }

    /**
     * 递增 不存在会创建 并将新增值返回
     * @param key
     * @param item
     * @param by
     * @return
     */
    public double hincr(String key,String item,double by){
        return redisTemplate.opsForHash().increment(key,item,by);
    }

    /**
     * 递减
     * @param key
     * @param item
     * @param by
     * @return
     */
    public double hdecr(String key,String item,double by){
        return redisTemplate.opsForHash().increment(key,item,-by);
    }

    // =========================================== set ============================

    /**
     * 数据放入缓存集合
     * @param key
     * @param value
     * @return 成功个数
     */
    public long sadd(String key,Object... value){
        try {
            return redisTemplate.opsForSet().add(key,value);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 数据放入缓存集合
     * @param key
     * @param time 超时时间
     * @param value
     * @return
     */
    public long sadd(String key,long time,Object... value){
        try {
            Long count = redisTemplate.opsForSet().add(key,value);
            if(time > 0){
                setExpire(key,time);
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHaskey(String key,Object value){
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                setExpire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将值放入list 最右边缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                setExpire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移出并获取列表的第一个元素
     *
     * @param key
     * @return 删除的元素
     */
    public Object lLeftPop(String key) {
        try {
            return redisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
