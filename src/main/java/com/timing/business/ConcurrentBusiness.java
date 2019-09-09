package com.timing.business;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/7/26 1:11 PM
 **/
@Component
@Slf4j
public class ConcurrentBusiness {

    private LoadingCache<String, Object> loadingCache;

    public ConcurrentBusiness(){
        log.info("init ConcurrentBusiness");
        CacheLoader<String, Object> cacheLoader = new CacheLoader<String, Object>(){
            @Override
            public Object load(String k) {
                if (StringUtils.isEmpty(k)){
                    return null;
                }
                Object value = null;
                try {
                    value = get(k);
                }catch (Exception e){
                    if (!(e instanceof IllegalStateException)){
                        e.printStackTrace();
                    }
                }
                return value;
            }
        };
        //缓存数量为1000，为了展示缓存删除效果
        CacheBuilder<String, Object> cacheBuilder = CacheBuilder.newBuilder().removalListener(removalListener()).maximumSize(1000);
        //设置{expireAfterWrite}分钟没有更新将会移除数据
        loadingCache = cacheBuilder.build(cacheLoader);
    }


    public void put(String key, Object value ){
        loadingCache.put(key, value);
    }

    public Object get(String key){
        try {
            return loadingCache.get(key);
        }catch (Exception e){

        }
        return null;
    }

    public void remove(String key, Object value){
        loadingCache.invalidate(key);
    }

    private static RemovalListener removalListener(){
        return (RemovalListener<String, String>) removalNotification -> {
            //可以在监听器中获取key,value,和删除原因
            removalNotification.getValue();
            removalNotification.getCause();//EXPLICIT、REPLACED、COLLECTED、EXPIRED、SIZE
        };

    }

}
