package com.yc.dao.mybatis.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.log4j.Logger;

import com.yc.dao.redis.RedisPool;

import redis.clients.jedis.Jedis;

public class RedisCache implements Cache {
	
	/*
	 * 日志对象
	 */
	private static Logger logger=org.apache.log4j.Logger.getLogger(RedisCache.class) ; 
	
	private String id;
	
	private Jedis redisClient=createRedis();
	
	//用于同步的锁
	private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
	
	public RedisCache( String id){
		if( id==null ){
			throw new IllegalArgumentException("Cache instance requires an Id");
		}
		logger.debug("create an cache instance with id:"+ id);
		this.id=id;
	}
	
	public String getId() {
		return this.id;
	}
	

 //将缓存中的数据删除
	public void clear() {
		logger.debug("clear redis cache");
		this.redisClient.flushDB();    
	}

	

	 //通过key到缓存redis中取值
	public Object getObject(Object key) {
		//缓存穿透.
		byte[] values=this.redisClient.get(  SerializableUtil.serialize(key)       );
		//System.out.println(   values );
		if(  values==null  ){
			//this.putObject(  SerializableUtil.serialize(key)  , null);
			return null;
		}
		Object obj=SerializableUtil.unSerialize(  values);
		logger.debug("get data:"+key+" from cache,result is:"+ obj);
		return obj;
	}

	
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	public int getSize() {
		Long size=this.redisClient.dbSize();
		int s=Integer.valueOf(  size+"");
		return s;
	}

	public void putObject(Object key, Object value) {
		byte[] keybyte= SerializableUtil.serialize(key);
		byte[] valuebyte=SerializableUtil.serialize(value);
		this.redisClient.set(keybyte, valuebyte);
	}

	public Object removeObject(Object key) {
		byte[] keybyte= SerializableUtil.serialize(key);
		return this.redisClient.expire(keybyte, 0);
	}
	
	/**
	 * TODO:jedis从联接池中取
	 * @return
	 */
	protected static Jedis createRedis(){
		//TODO: 获取jedis实例  -> 这个地址要变
		//Jedis jedis = new Jedis("192.168.137.128");
		Jedis jedis=   RedisPool.getPool().getResource();
		return jedis;
	}

}
