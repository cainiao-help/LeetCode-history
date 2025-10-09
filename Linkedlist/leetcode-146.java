/*
146. LRU 缓存
请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
实现 LRUCache 类：
LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。

示例：
输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4
 
提示：
1 <= capacity <= 3000
0 <= key <= 10000
0 <= value <= 105
最多调用 2 * 105 次 get 和 put
*/

// 首先要考虑到若使用了get方法，则需要将key所对应的map放在最前面，这样下次插入新的数据若超过最大容量则只需要将最后的map去掉。，该方法使用了java内置函数
// LinkedHashMap
// LinkedHashMap维护了双向链表用来记录存入 Map 中的数据的顺序, 即存储到 Map 中的 key-value 是有序的


public class LRUCache {
    final int capactiy;
    Map<Integer,Integer> map = new LinkedHashMap<>();

    public LRUCache(int capacity) {
        this.capactiy = capacity;
    }

    public int get(int key) {
        Integer res = map.remove(key);   // 若调用了get则将取出的集合重新放置一次，这样确保若增加新元素超过最大容量不会被去掉
        if (res != null){
            map.put(key, res);
            return res;
        }
        return -1;
    }

    public void put(int key, int value) {
       if(map.remove(key) != null){
           map.put(key, value);
           return;
       }

       if (map.size() == capactiy){
           Integer old = map.keySet().iterator().next();   // map.keySet()拿到map集合中的所有key，iterator()得到一个迭代器对象，next把迭代器向前移动一次，并返回第一个键
           map.remove(old);
       }
       map.put(key, value);
    }
}

