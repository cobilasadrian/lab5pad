import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by Adrian on 1/2/2016.
 */
public class TestRedis {

    public static void main(String[] args){
        Jedis jedis = new Jedis("localhost");
        System.out.println("Conectat cu succes la server...");

        System.out.println("\nStructura de date - String");
        System.out.println("Set hello:word: "+ jedis.set("hello", "word"));
        System.out.println("Get hello:word: "+ jedis.get("hello"));
        System.out.println("Del hello:word: "+ jedis.del("hello"));

        System.out.println("\nStructura de date - List");
        System.out.println("Rpush list-key:Redis: "+ jedis.rpush("list-key", "Redis"));
        System.out.println("Rpush list-key:Mongodb: "+ jedis.rpush("list-key", "Mongodb"));
        System.out.println("Rpush list-key:Mysql: "+ jedis.rpush("list-key", "Mysql"));
        System.out.println("Lrange 0 -1:");
        List<String> list = jedis.lrange("list-key", 0 ,-1);
        for(int i=0; i<list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("Lindex list-key 1: "+ jedis.lindex("list-key", 1));
        System.out.println("Lpop list-key: "+ jedis.lpop("list-key"));
        System.out.println("Lrange 0 -1:");
        list = jedis.lrange("list-key", 0 ,-1);
        for(int i=0; i<list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("\nStructura de date - Set");
        System.out.println("Sadd set-key:Redis: "+ jedis.sadd("set-key", "Redis"));
        System.out.println("Sadd set-key:Mongodb: "+ jedis.sadd("set-key", "Mongodb"));
        System.out.println("Sadd set-key:Mysql: "+ jedis.sadd("set-key", "Mysql"));
        System.out.println("Sadd set-key:Redis: "+ jedis.sadd("set-key", "Redis"));
        System.out.println("Smembers set-key:");
        Set<String> set = jedis.smembers("set-key");
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("Sismember set-key:Redis: "+ jedis.sismember("set-key", "Redis"));
        System.out.println("Srem set-key:Redis: "+ jedis.srem("set-key", "Redis"));
        System.out.println("Smembers set-key:");
        set = jedis.smembers("set-key");
        iterator = set.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("\nStructura de date - Hash");
        System.out.println("Hset user:1000 name John Smith: "+ jedis.hset("user:1000","name","John Smith"));
        System.out.println("Hset user:1000 email john.smith@example.com: "+ jedis.hset("user:1000","email","john.smith@example.com"));
        System.out.println("Hget user:1000 name: "+ jedis.hget("user:1000", "name"));
        System.out.println("Hget user:1000 email: "+ jedis.hget("user:1000","email"));
        Map mset = new HashMap();
        mset.put("name", "Vasile Ionescu");
        mset.put("email", "vasile@example.com");
        System.out.println("Hmset user:1001 name Vasile Ionescu email vasile@example.com: "+ jedis.hmset("user:1001",mset));
        System.out.println("Hmgeet user:1001 name email: "+ jedis.hmget("user:1001", "name", "email"));
        System.out.println("HgetAll user:1001: "+ jedis.hgetAll("user:1001"));
        System.out.println("Hkeys user:1001: "+ jedis.hkeys("user:1001"));
        System.out.println("Hdel user:1001 email: "+ jedis.hdel("user:1001","email"));
        System.out.println("Hkeys user:1001: "+ jedis.hkeys("user:1001"));


        System.out.println("\nStructura de date - Sorted Sets");
        mset = new HashMap();
        mset.put("ghanima",100.0);
        mset.put("paul",95.0);
        mset.put("chani",95.0);
        mset.put("jessica",75.0);
        mset.put("vladimir",1.0);
        System.out.println("Zadd friends:leto 100 ghanima 95 paul 95 chani 75 jessica 1 vladimir: "+ jedis.zadd("friends:leto",mset));
        System.out.println("Zcount friends:leto 90 100: "+ jedis.zcount("friends:leto",90.0,100.0));
        System.out.println("Zrank friends:leto chani: "+ jedis.zrank("friends:leto", "chani"));
        System.out.println("Zrevrank friends:leto chani: "+ jedis.zrevrank("friends:leto", "chani"));
        System.out.println("Zrange friends:leto 0 -1: " + jedis.zrange("friends:leto",0, -1));
        jedis.flushDB();
    }

}
